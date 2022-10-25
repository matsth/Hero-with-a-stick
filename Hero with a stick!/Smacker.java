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
    public int lifes;
    
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
        smack();
    }
    
    /**
     * 
     */
    public void movement()
    {
        int X = getX();
        int Y = getY();
        if(Greenfoot.isKeyDown("d"))
        {
            X += movementspeed;
        }
        if(Greenfoot.isKeyDown("a"))
        {
            X -= movementspeed;
        }
        if(Greenfoot.isKeyDown("s"))
        {
            Y += movementspeed;
        }
        if(Greenfoot.isKeyDown("w"))
        {
            Y -= movementspeed;
        }
        setLocation(X, Y);
    }
    
    /**
     * 
     */
    public void smack()
    {
        int X = getX();
        int Y = getY();
        if(Greenfoot.isKeyDown("space"))
        {
            if(Greenfoot.isKeyDown("d"))
            {
                X += movementspeed;
            }
            if(Greenfoot.isKeyDown("a"))
            {
                X -= movementspeed;
            }
            if(Greenfoot.isKeyDown("s"))
            {
                Y += movementspeed;
            }
            if(Greenfoot.isKeyDown("w"))
            {
                Y -= movementspeed;
            }
            getWorld().addObject(new Stick(), getX(), getY());
        }
    }
}
