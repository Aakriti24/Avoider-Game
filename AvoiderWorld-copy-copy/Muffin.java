import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Muffin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Muffin extends PowerItems
{
     public Muffin( int tX, int tY, int eT) {
                   super(tX, tY, eT);
                  }
              protected double curveX(double f) {
                   return f;
                 }
              protected double curveY(double f) {
                   return f;
                  }
              protected void checkHitAvatar() {
                     Avatar a = (Avatar) getOneIntersectingObject(Avatar.class);
                     if( a != null ) {
                          a.stun();
                          getWorld().removeObject(this);
                        }
                }

}
