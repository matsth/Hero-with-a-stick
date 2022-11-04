import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Smacktown ist die Spiel Welt in der die Villains gespawnd werden
 * und der Timer gezählt wird.
 */
public class Smacktown extends World
{
    public int maxVillain = 7;
    public int currentVillain = 0;
    
    public int villainssmacked = 0;
    
    public int starttime = 300;
    public int timer = starttime;
    private int ticker = 0;
    
    public int maxPowers = 1;
    public int currentPowers = 0;

    public int Powerincreasoffset = 90;
    public int powerincreas = timer - Powerincreasoffset;
    
    public int spawntimerpowerup = timer;
    public int spawntimerpowerupoffset = 15;
    
    public int speedboost = timer;
    public int smackboost = timer;
    

    
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
        spawner();
        powerup();
    }
    /**
     * Erzeugt gegner und Powerups und erhöht deren capazität.
     */
    public void spawner()
    {
        powerupupper();
        powerupspawner();
    }
    
    /**
     * Erzeugt alle spawntimerpowerupoffset Sekunden ein Powerup falls es weniger als die maximal Powerups hat.
     */
    public void powerupspawner()
    {
        if(spawntimerpowerup >= timer && maxPowers > currentPowers)
        {
            addObject(new Powerups(), Greenfoot.getRandomNumber(this.getWidth() - 60) + 30, Greenfoot.getRandomNumber(this.getHeight() - 60) + 30);
            
            spawntimerpowerup -= spawntimerpowerupoffset;
            currentPowers++;
        }
    }
    
    /**
     * Erhöht die Maximal anzahl an Powerups die erzeugt werden können.
     * Erhöht die geschwindigkeit in der die Powerups erzeugt werden.
     */
    public void powerupupper()
    {
        if(powerincreas >= timer)
        {
            powerincreas -= Powerincreasoffset;
            maxPowers++;
            
            if (spawntimerpowerupoffset >= 3)
            {
                spawntimerpowerupoffset -= 2;
                spawntimerpowerup += 2;
            }
        }
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
                gameend(true);
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
            gameend(false);
        }
    }
    
    /**
     * Sorgt dafür das Powerups die über eine Zeit laufen solange aktiv sind.
     */
    public void powerup()
    {
        if(speedboost < timer)
        {
            speedboster();
        }
        if (smackboost < timer)
        {
            smackboster();
        }
    }
    
    /**
     * Lässt die movement function von Smacker nochmals laufen.
     */
    public void speedboster()
    {
        Smacker smacker = getObjects(Smacker.class).get(0);
        
        smacker.movement();
    }
    
    /**
     * Lässt die rotate function von Stick nochmals laufen.
     */
    public void smackboster()
    {
        if(!getObjects(Stick.class).isEmpty())
        {
            Stick stick = getObjects(Stick.class).get(0);
        
            stick.rotate();
        }
    }
    
    /**
     * Zum Abschlussscreen wechseln.
     */
    public void gameend(boolean won)
    {
        Smacker smacker = this.getObjects(Smacker.class).get(0);
        
        Endscreen endscreen = new Endscreen(starttime - timer, won, smacker.lifes, villainssmacked);
        Greenfoot.setWorld(endscreen);
    }
}
