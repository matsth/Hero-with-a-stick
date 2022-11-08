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
        image = new GreenfootImage(text, 20, Color.BLACK, null, null);
        setImage(image);
    }
}
