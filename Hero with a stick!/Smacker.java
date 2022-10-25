import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Smacker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Smacker extends Actor
{
    public int movementspeed;
    
    public Smacker()
    {
        GreenfootImage image = new GreenfootImage("Smacker.png");
        image.scale(image.getWidth()/10, image.getHeight()/10);
        setImage(image);
        
        movementspeed = 4;
    }
    /**
     * Act - do whatever the Smacker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        movement();
    }
    
    /**
     * 
     */
    public void movement()
    {
        if(Greenfoot.isKeyDown("d"))
        {
            setLocation(getX()+movementspeed, getY());
        }
        if(Greenfoot.isKeyDown("a"))
        {
            setLocation(getX()-movementspeed, getY());
        }
        if(Greenfoot.isKeyDown("s"))
        {
            setLocation(getX(), getY()+movementspeed);
        }
        if(Greenfoot.isKeyDown("w"))
        {
            setLocation(getX(), getY()-movementspeed);
        }
    }
}
