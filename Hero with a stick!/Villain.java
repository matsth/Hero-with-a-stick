import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Villain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Villain extends Actor
{
    public int Movementspeed;
    
    /**
     * Standard Constructor set Image and movement Speed.
     */
       public Villain()
    {
        GreenfootImage image = new GreenfootImage("Villain1.png");
        image.scale(image.getWidth()/10, image.getHeight()/10);
        setImage(image);
        Movementspeed = 5;
        
    }
    
    /**
     * act Method für die Bewegung und Testen ob der Smacker berührt wird.
     */
        public void act()
    {
        VillainMove();
        Collision();
    }
    
    /**
     * Das Movement des Villain.
     * 
     * Der Villain testet zuerst ob er weiter oder weniger weit wer bei der 
     * X und Y Achse ist.
     * 
     * 
     * Falls die Differenz grösser als offset ist.
     * 
     * Danach bewegt er sich dementsprechen -Movementspeed oder +Movementspeed auf
     * den Achsen.
     */
    public void VillainMove()
    {
        int X = 0;
        int Y = 0;
        
        int offset = 20;
        
        Smacker smacker = getWorld().getObjects(Smacker.class).get(0);
        
        if(smacker.getX() > this.getX())
        {
            if (caldiff(smacker.getX(), this.getX()) >= offset) 
            {
                X+=Movementspeed;
            }
        }
        else if(smacker.getX() < this.getX())
        {
            if (caldiff(smacker.getX(), this.getX()) >= offset) 
            {
                X-=Movementspeed;
            }
        }
        if(smacker.getY() > this.getY())
        {
            if (caldiff(smacker.getY(), this.getY()) >= offset) 
            {
                Y+=Movementspeed;
            }
        }
        else if(smacker.getY() < this.getY())
        {
            if (caldiff(smacker.getY(), this.getY()) >= offset) 
            {
                Y-=Movementspeed;
            }
        }
        
        this.setLocation(this.getX() + X, this.getY() + Y);
        
    } 
    /**
     * Mathematische funktion um die Differenz von Villain zum Smacker zu berechnen.
     */
    public int caldiff(int varSmack, int varVill)
    {
        int diff;
        
        diff = varSmack - varVill;
        if(diff < 0)
        {
            diff = diff * (-1);
        }
        
        return diff;
    }
    
    /**
     * Teste ob der Smacker berührt wird und falls ja ziehe die Leben ab.
     */
    public void Collision()
    {
        Smacker smacker = getWorld().getObjects(Smacker.class).get(0);
        
        if(this.isTouching(Smacker.class)){
            smacker.loselife(1);
            getWorld().removeObject(this);
        }
    }
    
}



