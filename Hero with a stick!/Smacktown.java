import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Smacktown here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Smacktown extends World
{
    public int maxVillain;
    public int currentVillain = 0;
    /**
     * Constructor for objects of class Smacktown.
     * 
     */
    public Smacktown()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        prepare();
        setPaintOrder(Smacker.class, Stick.class, Powerups.class, Villain.class);
    }
    
    /**
     * 
     */
    public void prepare()
    {
        addObject(new Smacker(), 400, 250);
    }
}
