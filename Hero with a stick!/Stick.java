import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Der Stick ist die Waffe des Smackers mit dem er die Villains besigt.
 */
public class Stick extends Actor
{
    public int rot;
    public int threshhold;
    
    public int rotationspeed;
    
    public int moveset;
    public int movedist;
    
    public int dmg;
    
    /**
     * Das Bild des Sticks wird auf eine Passende Grösse geändert.
     * 
     * Die Länge des Smackers wird durchgegeben und die Rotations des Sticks.
     * Die Movedist ist die Länge des Smackers*2 durch die anzahl an Ticks für
     * die Rotation.
     * 
     * Die Bewegung des Sticks wird durch die anfangs rotation bestimmt. (moveset)
     * 
     * Die Parameter rotation ist die anfangs Rotation und length die länge des Smackers.
     * 
     * threshhold ist die hälfte der Rotation. Das ist wichtig fals sich der 
     * Stick um die Ecke des Smackers bewegt.
     */
    public Stick(int rotation, int length, int Damage, int attackspeed)
    {
        GreenfootImage image = new GreenfootImage("Stick.png");
        image.scale(image.getWidth()/8, image.getHeight()/10);
        setImage(image);
        
        dmg = Damage;
        
        this.setRotation(rotation);
        rot = 90;
        threshhold = rot/2;
        rotationspeed = attackspeed;
        
        moveset = rotation;
        movedist = length*2/(rot/rotationspeed);
    }
    /**
     * Act - do whatever the Stick wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        rotate();
    }
    /**
     * Der Stick dreht sich falls er sich noch nicht 90 Grad im Uhrzeiger sin gedreht hat.
     * Falls sich der Stick dreht bewegter sich auch.
     * 
     * Falls sich der Stick nicht bewegt löscht er sich selber und
     * sagt am Smacker das er keinen Stick mehr hat.(hasStick)
     */
    public void rotate()
    {
        if(rot > 0)
        {
            this.setRotation(this.getRotation()+rotationspeed);
            rot -= rotationspeed;
            smackvillain();
            move();
            smackvillain();
        } else {
            Smacker smacker = getWorld().getObjects(Smacker.class).get(0);
            smacker.hasStick = false;
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Für jede Rotation des Sticks gibt es einen Einzigartigen Movement set.
     * 
     * Falls sich der Stick um eine Ecke bewegen muss ändert sich die Bewegungsrichtung
     * nach dem er sich über die Ecke bewegt hat.
     * 
     * Weitere Komentare beschreiben die genaue bewegungsrichtung.
     */
    public void move()
    {
        switch(moveset)
        {
            //Obenrechts nach untenrechts.
            case 315:
                this.setLocation(this.getX(), this.getY() +  movedist/2);
                break;
            //Untenlinks nach obenlinks.
            case 135:
                this.setLocation(this.getX(), this.getY() - movedist/2);
                break;
            //Untenrechts nach untenlinks.
            case 45:
                this.setLocation(this.getX() -  movedist/2, this.getY());
                break;
            //Obenlinks nach obenrechts.
            case 225:
                this.setLocation(this.getX() +  movedist/2, this.getY());
                break;
            //Mitterechts nach untenrechts.
            //Untenrechts nach mitteunten.
            case 0:
                if(rot > threshhold)
                {
                    this.setLocation(this.getX(), this.getY() +  movedist);
                } else {
                    this.setLocation(this.getX() -  movedist, this.getY());
                }
                break;
            //Obenmitte nach Obenrechts.
            //Obenrechts nach mitterechts.
            case 270:
                if(rot > threshhold)
                {
                    this.setLocation(this.getX() + movedist, this.getY());
                } else {
                    this.setLocation(this.getX(), this.getY() + movedist);
                }
                break;
            //Untenmitte nach Untenlinks.
            //Untenlinks nach mittelinks.
            case 90:
                if(rot > threshhold)
                {
                    this.setLocation(this.getX() - movedist, this.getY());
                } else {
                    this.setLocation(this.getX(), this.getY() - movedist);
                }
                break;
            //Mittelinks nach Obenlinks.
            //Obenlinks nach mitteoben.
            case 180:
                if(rot > threshhold)
                {
                    this.setLocation(this.getX(), this.getY() - movedist);
                } else {
                    this.setLocation(this.getX() + movedist, this.getY());
                }
                break;
        }
    }
    /**
     * Diese Funktion wird im Smacker aufgerufen damit sich der Stick mit dem
     * Smacker mit bewegt.
     */
    public void movewithSmacker(int x, int y)
    {
        this.setLocation(getX() + x, getY() + y);
    }
    
    /**
     * Falls ein gegner getroffen wird füge ihm schaden zu.
     */
    public void smackvillain()
    {
        Villain villain = (Villain)getOneIntersectingObject(Villain.class);
        
        if(villain != null){
            villain.takedmg(dmg);
        }
    }
}
