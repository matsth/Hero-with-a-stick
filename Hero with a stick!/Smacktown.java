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
    
    public int maxPowers;
    public int currentPowers = 0;
    
    public int timer = 300;
    private int ticker = 0;
    
    /**
     * Constructor for objects of class Smacktown.
     * 
     */
    public Smacktown()
    {    
        // Create a new world with 800x500 cells with a cell size of 1x1 pixels.
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
        showTime();
        showlife();
    }
    
    public void act()
    {
        timer();
    }
    
    public void timer()
    {
        ticker++;
        
        if(ticker >= 60)
        {
            timer--;
            ticker = 0;
            
            showTime();
            
            if(timer <= 0)
            {
                this.stopped();
            }
        }
    }
    
    private void showTime(){
        showText("Time: " + timer, 690, 25);
    }
    
    public void showlife()
    {
        Smacker smacker = this.getObjects(Smacker.class).get(0);
        showText("Lifes: " + smacker.lifes, 75, 25);
    }
}
