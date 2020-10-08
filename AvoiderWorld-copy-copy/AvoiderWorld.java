import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AvoiderWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AvoiderWorld extends World
{
    public static Counter level;
    private SimpleTimer timer = new SimpleTimer();
    public static GreenfootSound bkgMusic;
    public static Counter score;
    private int enemySpawnRate =20;
    private int enemySpeed = 1;
    private int nextLevel = 10;

    /**
     * Constructor for objects of class AvoiderWorld.
     * 
     */
    public AvoiderWorld()
    {  

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1,false); 
        score = new Counter("Score: ");
        addObject(score, 70, 20);

        bkgMusic = new GreenfootSound("ufo.mp3");

        prepare();
        setPaintOrder(Avatar.class, Enemy.class, Counter.class);
        generateInitialStarField();
        level = new Counter("Level: ");
        level.setValue(1);
        addObject(level, 540, 20);

    }

    public void increaseLevel(){
        if(score.getValue() > nextLevel) {
            muffinFrequency += 3;
            enemySpawnRate += 2;
            enemySpeed++;
            nextLevel += 10;
            level.setValue(nextLevel / 10);
        }

    }

    public void act()
    {  
        generateStars(-1);
        if (Greenfoot.getRandomNumber(1000)<enemySpawnRate)
        {
            Enemy e = new Enemy(Greenfoot.getRandomNumber(10)+1);
            e.setSpeed(enemySpeed);
            addObject(e, Greenfoot.getRandomNumber(getWidth()-20)+10,-30);
            score.setValue(score.getValue()+1);

        }
        increaseLevel();
        if (timer.millisElapsed()>20000)

        {
            Bear b = new Bear();
            addObject(b, Greenfoot.getRandomNumber(getWidth()-20)+10,-30);

            timer.mark();
        }
        bkgMusic.playLoop();

    }

    public void generateStars(int y)
    {
        if(Greenfoot.getRandomNumber(1000) < 350)
        {
            Star s = new Star();
            GreenfootImage image = s.getImage();
            if(Greenfoot.getRandomNumber(1000) < 300)
            {  // this is a bright, close star
                s.setSpeed(3);  
                image.setTransparency(Greenfoot.getRandomNumber(25)+225);
                image.scale(4,4);
            }
            else // this is a dim, far star
            {
                s.setSpeed(2);
                image.setTransparency(Greenfoot.getRandomNumber(50)+100);
                image.scale(2,2);
            }
            s.setImage(image);
            addObject(s, Greenfoot.getRandomNumber(getWidth()-20)+10, y);
        }
    }

    public void generatePowerItems()
    {
        generatePowerItem(0, muffinFrequency);
    }


    private void generateInitialStarField()
    {
        for(int i = 0; i < getHeight(); i++)
            generateStars(i);
    }

    

    public void generatePowerItem(int type, int freq)
    {
        if( Greenfoot.getRandomNumber(1000) < freq )
        {
            int targetX = Greenfoot.getRandomNumber(getWidth() -80) + 40;
            int targetY = Greenfoot.getRandomNumber(getHeight()/2) + 20;
            Actor a = createPowerItem(type, targetX, targetY, 100);
            if( Greenfoot.getRandomNumber(100) < 50)
            {
                addObject(a, getWidth() + 20,
                    Greenfoot.getRandomNumber(getHeight()/2) + 30);
            }
            else
            {
                addObject(a, -20,
                    Greenfoot.getRandomNumber(getHeight()/2) + 30);
            } } }

    public Actor createPowerItem(int type, int targetX, int targetY, int expireTime) {
        switch(type) {
            case 0: return new Muffin(targetX, targetY, expireTime);
            //case 1: return new Clover(targetX, targetY, expireTime);
            //case 2: return new Rock(targetX, targetY, expireTime);
        }
        return null;
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Avatar avatar = new Avatar();
        addObject(avatar,268,354);
    }

    private int muffinFrequency = 10;

}
