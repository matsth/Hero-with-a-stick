import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Das ist der Endscreen mit den Passenden Bildern und Statistiken.
 */
public class Endscreen extends World
{

    /**
     * Constructor for objects of class Endscreen.
     * Setzt das Entsprechende Bild falls man gewonnen hat und zeigt weitere Daten an.
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
        
        addObject(new Message("Zeit überlebt: " + survived), getWidth()/4 - getWidth()/10, getHeight()/2);
        addObject(new Message("Villains besigt: " + villainssmacked), getWidth()/2, getHeight()/2);
        addObject(new Message("Leben übrig: " + lifes), getWidth() - getWidth()/4 + getWidth()/10, getHeight()/2);
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
