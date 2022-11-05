import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Startscreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Startscreen extends World
{

    /**
     * Constructor for objects of class Startscreen.
     * 
     */
    public Startscreen()
    {    
        super(800, 500, 1);
        
        GreenfootImage image = new GreenfootImage("Start.png");
        image.scale(image.getWidth()/3, image.getHeight()/3);
        this.setBackground(image);
    }
    
    /**
     * Warte bis Space gedrückt wird und starte dann das Spiel.
     */
    public void act()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            Smacktown smacktown = new Smacktown();
            Greenfoot.setWorld(smacktown);
        }
    }
}
