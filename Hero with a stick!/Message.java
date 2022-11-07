import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Message here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Message extends Actor
{
        public Message(String text)
    {
        setMessage(text);
    }
    
    public void setMessage(String text)
    {
        GreenfootImage image = null;
        if (text != null && !"".equals(text)) image = new GreenfootImage(text, 30, Color.BLACK, null, null);
        setImage(image);
    }
}
