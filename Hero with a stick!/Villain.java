import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Die Bösewichte von Hero with a stick.
 * Hier werden die Leben abgezogen vom Smacker.
 */
public class Villain extends Actor
{
    public int Movementspeed;
    public int HP;
    public int dealdmg;
    public int movemod = 1;
    public int off;
    
    /**
     * Kosntruktor für Villains. Erzeugt spezielle Villains.
     */
    public Villain(int Type)
    {
        GreenfootImage image = new GreenfootImage("Villain1.png");
        switch(Type)
        {
            //Schnellerer Villain mit weniger leben
            case 2:
                image = new GreenfootImage("Villain2.png");
                image.scale(image.getWidth()/30, image.getHeight()/30);
                setImage(image);
                Movementspeed = 5;
                HP = 1;
                dealdmg = 1;
                off = 5;
                break;
            
            // Langsamer Villain aber dafür hat er mehr leben.
            case 1:
                image = new GreenfootImage("Villain3.png");
                image.scale(image.getWidth()/10, image.getHeight()/10);
                setImage(image);
                Movementspeed = 1;
                HP = 10;
                dealdmg = 2;
                off = 20;
                break;
                
            // Normaler Villain.
            default:
                image = new GreenfootImage("Villain1.png");
                image.scale(image.getWidth()/20, image.getHeight()/20);
                setImage(image);
                Movementspeed = 2;
                HP = 2;
                dealdmg = 1;
                off = 15;
                break;
        }
    }
    /**
     * act Method für die Bewegung und Testen ob der Smacker berührt wird.
     */
        public void act()
    {
        VillainMove(movemod);
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
    public void VillainMove(int movdir)
    {
        int X = 0;
        int Y = 0;
        
        int offset = off;
        
        if(! getWorld().getObjects(Smacker.class).isEmpty())
        {
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
            
            this.setLocation(this.getX() + X * movdir, this.getY() + Y * movdir);
        }
        
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
        Smacker smacker = (Smacker)getOneIntersectingObject(Smacker.class);
        
        if(smacker != null){
            smacker.loselife(dealdmg);
            ((Smacktown)getWorld()).currentVillain --;
            GreenfootSound gfs = new GreenfootSound("Stones and Water On Cement.mp3");
            gfs.setVolume(50);
            gfs.play();
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Falls der Villain überlebt wird er zurückgeschlagen ansonsten gelöscht.
     */
    public void takedmg(int dmg)
    {
        HP -= dmg;
        Greenfoot.playSound("Wooden Bat Hits Baseball Run.mp3");
        
        if(HP <= 0)
        {
            ((Smacktown)getWorld()).villainssmacked ++;
            ((Smacktown)getWorld()).currentVillain --;
            ((Smacktown)getWorld()).showsmacked();
            getWorld().removeObject(this);
        } else
        {
            VillainMove(movemod*-1*15);
        }
    }
}



