import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Eye here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Eye extends Actor
{
    public Eye()
    {
        drawEye(2,2);
    }

    /**
     * Act - do whatever the Eye wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        lookAtEnemies();//Add your action code here.
    }
    
    public void drawEye(int dx, int dy)
    {
        GreenfootImage img = new GreenfootImage(10,10);
        img.setColor(Color.WHITE);
        img.fillOval(0,0,10,10);
        img.setColor(Color.BLACK);
        img.fillOval(dx,dy,6,6);
        setImage(img);
    }
    
    public void lookAtEnemies() {
        List<Enemy> eList = getObjectsInRange(120, Enemy.class);
        if( !eList.isEmpty() ) {
            Enemy e = eList.get(0);
            if( e.getX() < getX() ) {
                if( e.getY() < getY() )
                    drawEye(1,1);
                else
                    drawEye(1,3);
            }
            else
            {
                if(e.getY() < getY() )
                    drawEye(3,1);
                else
                    drawEye(3,3);
            }
        }
    }

    

    

}
