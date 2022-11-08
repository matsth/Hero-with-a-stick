import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Startscreen mit einleitung wie man spielt.
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
        
        //Background Music
        GreenfootSound backgroundMusic = new GreenfootSound("Here it Comes - TrackTribe.mp3");
        backgroundMusic.playLoop();
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
