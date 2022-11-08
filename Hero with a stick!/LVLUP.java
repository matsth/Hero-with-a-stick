import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Text + Sound for lvlup.
 */
public class LVLUP extends Actor
{
    public LVLUP(String text)
    {
        GreenfootImage image = null;
        image = new GreenfootImage(text, 60, Color.WHITE, null, null);
        setImage(image);
        
        Greenfoot.playSound("army-rank-up-2-84543.mp3");
    }
}
