import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Smacktown ist die Spiel Welt in der die Villains gespawnd werden
 * und der Timer gezählt wird.
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
     * Das Bild wird neu gesetzt damit die grösse stimmt.
     */
    public Smacktown()
    {    
        // Create a new world with 800x500 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        
        GreenfootImage image = new GreenfootImage("Background.png");
        image.scale(image.getWidth()/3, image.getHeight()/3);
        this.setBackground(image);
        
        prepare();
        setPaintOrder(Smacker.class, Stick.class, Powerups.class, Villain.class);
    }
    
    /**
     * Hier ist das Setup für die Welt.
     * Dass heisst es wird der Smacker der Timer und die Leben hinzugefügt.
     */
    public void prepare()
    {
        addObject(new Smacker(), 400, 250);
        showTime();
        showlife();
    }
    /**
     * Standard Act Methode.
     */
    public void act()
    {
        timer();
    }
    /**
     * Der timer wird in dieser Methode heruntergezählt.
     * 60 ticks von greenfoot entsprechen ca 1 sekunde.
     * Falls der Timer auf 0 ist wird das Spiel gestopped.
     */
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
                Greenfoot.stop();
            }
        }
    }
    
    /**
     * Zeige die aktuelle Zeit an.
     */
    private void showTime(){
        showText("Time: " + timer, 690, 25);
    }
    
    /**
     * Zeige die Leben an.
     * Falls die Leben kleiner gleich 0 sind wird das Spiel angehalten.
     */
    public void showlife()
    {
        Smacker smacker = this.getObjects(Smacker.class).get(0);
        showText("Lifes: " + smacker.lifes, 75, 25);
        
        if(smacker.lifes <= 0) 
        {
            Greenfoot.stop();
        }
    }
}
