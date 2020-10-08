import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Avatar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Avatar extends Actor
{  
    private Eye leftEye, rightEye;
    private int health = 3;
    private int hitDelay = 0;
    private int nextImage = 1;

    private boolean shrunk= false;
    private int scoreWhenShrunk=0;
    GreenfootImage ordinaryAvatar = new GreenfootImage("skull1.png");
    GreenfootImage invincibleAvatar = new GreenfootImage("spiderman.gif");
    private boolean invincible = false;
    private SimpleTimer timer = new SimpleTimer();

    /**
     * Act - do whatever the Avatar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        followMouse();
        checkForCollisions();

        if(shrunk)
            getImage().scale(28,39);
        else
            getImage().scale(56,78);
        if(shrunk && AvoiderWorld.score.getValue() > scoreWhenShrunk + 10)
            shrunk = false;
        if(invincible)
            setImage(invincibleAvatar);
        else
            setImage("skull" + nextImage + ".png");
        if(Greenfoot.getRandomNumber(1000) < 1 && !invincible)
        {
            invincible = true;
            timer.mark();
        }
        if(invincible && timer.millisElapsed() > 10000)
        {
            invincible = false;
        }

    } 

    public void followMouse()
    {
        MouseInfo mi = Greenfoot.getMouseInfo();
        if(stunDelay < 0)
        {
            if(mi != null)
            {
                getWorld().setPaintOrder(Eye.class, Avatar.class);
                if(shrunk || invincible)
                {
                    leftEye.setLocation(-100, -100);
                    rightEye.setLocation(-100, -100);
                }
                else
                {
                    leftEye.setLocation(mi.getX() - 10, mi.getY() - 8);
                    rightEye.setLocation(mi.getX() + 10, mi.getY() - 8);
                }
                setLocation(mi.getX(), mi.getY());
            }
        }
        else
        {
            stunDelay--;
        }
    }

    public void checkForCollisions()
    { 
        Actor bear= getOneIntersectingObject(Bear.class);
        if(!shrunk&&bear!=null)
        {
            shrunk = true;
            scoreWhenShrunk = AvoiderWorld.score.getValue();
        }

        Actor enemy = getOneIntersectingObject(Enemy.class);
        if (hitDelay == 0 && enemy!=null && !invincible)
        { if(health == 0)
            {
                getWorld().removeObject(this);
                AvoiderWorld.bkgMusic.stop();
                Greenfoot.setWorld(new AvoiderGameOverWorld());
            } 
            else
            {
                health--;
                nextImage++;
                setImage("skull" + nextImage + ".png");
                hitDelay = 100;
            }

        }
        if(hitDelay > 0) hitDelay--;
    }

    public void addedToWorld(World w)
    {
        leftEye = new Eye();
        rightEye = new Eye();
        w.addObject(leftEye, getX() - 10, getY() - 8);
        w.addObject(rightEye, getX() + 10, getY() - 8);
    }

    public void stun()
    {
        stunDelay = 50;
    }
    private int stunDelay = -1;

}
