import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Endscreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Endscreen extends World
{

    /**
     * Constructor for objects of class Endscreen.
     * 
     */
    public Endscreen(int survived, boolean won, int lifes, int villainssmacked)
    {    
        super(800, 500, 1);
        
        GreenfootImage image;
        
        if(won)
        {
            image = new GreenfootImage("Winscreen.png");
        } else 
        {
            image = new GreenfootImage("Losescreen.png");
        }
        
        image.scale(image.getWidth()/3, image.getHeight()/3);
        this.setBackground(image);
        
        showText("Zeit überlebt: " + survived, 200, 250);
        showText("Villains besigt: " + villainssmacked, 400, 250);
        showText("Leben übrig: " + lifes, 600, 250);
    }
    
    
    /**
     * Warte bis Space gedrückt wird und starte dann das Spiel neu.
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
