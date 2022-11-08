import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Powerups geben dem Smacker Temporere Buffs, Permanente lebeben oder änderen die Welt.
 */
public class Powerups extends Actor
{
    public int powertype;
    
    /**
     * Constructor sets Powertype at random an Replace picture.
     */
    public Powerups()
    {
        GreenfootImage image = new GreenfootImage("Powerup1.png");
        switch(Greenfoot.getRandomNumber(4))
        {
            case 0:
                powertype = 0;
                image = new GreenfootImage("Powerup1.png");
                break;
                
            case 1:
                powertype = 1;
                image = new GreenfootImage("Powerup2.png");
                break;
                
            case 2:
                powertype = 2;
                image = new GreenfootImage("Powerup3.png");
                break;
                
            case 3:
                powertype = 3;
                image = new GreenfootImage("Powerup4.png");
                break;
        }
        
        image.scale(image.getWidth()/30, image.getHeight()/30);
        setImage(image);
    }
    
    /**
     * Falls der Smacker das Powerup berührt wird dise function laufen gelassen.
     */
    public void Powerup()
    {
        GreenfootSound gfs = new GreenfootSound("video-game-powerup-38065.mp3");
        gfs.setVolume(40);
        gfs.play();
        switch(powertype)
        {
            case 0:
                KillallVillain();
                break;
                
            case 1:
                Runfast();
                break;
                
            case 2:
                Smackharder();
                break;
                
            case 3:
                heal();
                break;
        }
        ((Smacktown)getWorld()).currentPowers--;
        getWorld().removeObject(this);
    }
    
    /**
     * Funktion die alle Villain tötet.
     */
    public void KillallVillain()
    {
        if(!getWorld().getObjects(Villain.class).isEmpty())
        {
            ((Smacktown)getWorld()).villainssmacked += getWorld().getObjects(Villain.class).size();
            getWorld().removeObjects(getWorld().getObjects(Villain.class));
            ((Smacktown)getWorld()).currentVillain = 0;
            ((Smacktown)getWorld()).showsmacked();
        }
    }
    
    /**
     * Setzt die Zeit wie lange der Spieler Schneller rennen kann.
     */
    public void Runfast()
    {
        ((Smacktown)getWorld()).speedboost = ((Smacktown)getWorld()).timer - 10;
    }
    
    /**
     * Setzt die Zeit wie lange der Stick schnell schlagen kann.
     */
    public void Smackharder()
    {
        ((Smacktown)getWorld()).smackboost = ((Smacktown)getWorld()).timer - 10;
    }
    
    /**
     * Heal Smacker with negative dmg.
     */
    public void heal()
    {
        Smacker smacker = getWorld().getObjects(Smacker.class).get(0);
        
        if(smacker != null){
            smacker.loselife(-1);
        }
    }
}
