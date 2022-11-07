import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LVLUP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LVLUP extends Actor
{
        public LVLUP(String text)
    {
        GreenfootImage image = null;
        image = new GreenfootImage(text, 100, Color.WHITE, null, null);
        setImage(image);
    }
}
