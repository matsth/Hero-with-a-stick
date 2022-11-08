import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Smacktown ist die Spiel Welt in der die Villains gespawnd werden
 * und der Timer gezählt wird. Die Villains und powerups schneller spawnen.
 */
public class Smacktown extends World
{
    public int starttime = 100;
    
    private int maxVillain = 10;
    public int currentVillain = 0;
    
    private int villainlastspawn = starttime;
    private int villainspawnoffset = 4;
    private int villainspawnrepeater = 3;
    
    private int villainpowerupoffset = 10;
    private int villainpowerup = starttime - villainpowerupoffset;
    
    private int allvillains = 3;
    private int villainnewvillainoffset = starttime/allvillains;
    private int villainnewvillain = starttime - villainnewvillainoffset;
    private int villainsunlocked = 1;
    
    public int villainssmacked = 0;
    private int needvillainforlvlup = 5;
    private double lvlupoffset = 1.5;
    
    public int timer = starttime;
    private int ticker = 0;
    
    private int maxPowers = 3;
    public int currentPowers = 0;

    private int Powerincreasoffset = 20;
    private int powerincreas = starttime - Powerincreasoffset;
    
    private int spawntimerpowerup = starttime;
    private int spawntimerpowerupoffset = 8;
    
    public int speedboost = starttime;
    public int smackboost = starttime;
    

    
    /**
     * Constructor for objects of class Smacktown.
     * Das Bild wird neu gesetzt damit die grösse stimmt.
     */
    public Smacktown()
    {    
        
        /*
        super(1200, 800, 1);
        
        GreenfootImage image = new GreenfootImage("Background.png");
        image.scale(image.getWidth()/2, image.getHeight()/2);
        */
       
        super(800, 533, 1);
        
        GreenfootImage image = new GreenfootImage("Background.png");
        image.scale(image.getWidth()/3, image.getHeight()/3);
        
        
        this.setBackground(image);
        
        prepare();
        setPaintOrder(Message.class, LVLUP.class, Smacker.class, Stick.class, Powerups.class, Villain.class);
    }
    
    /**
     * Hier ist das Setup für die Welt.
     * Dass heisst es wird der Smacker der Timer und die Leben hinzugefügt.
     */
    public void prepare()
    {
        addObject(new Smacker(), getWidth()/2, getHeight()/2);
        //Add Timer
        addObject(new Message("Time: " + timer), getWidth() - getWidth()/10, getHeight()/20);
        
        //Add Lifes
        Smacker smacker = this.getObjects(Smacker.class).get(0);
        addObject(new Message("Lifes: " + smacker.lifes), getWidth()/10, getHeight()/20);
        
        //Add villains
        addObject(new Message("Villains smacked: " + villainssmacked + " need " + (needvillainforlvlup - villainssmacked) + " more for lvl up"), getWidth()/2, getHeight()/20);
        
    }
    /**
     * Standard Act Methode.
     */
    public void act()
    {
        timer();
        powerup();
        lvlup();
    }
    /**
     * Falls der Smacker genügend Villains besigt hat krigt er ein lvlup
     */
    public void lvlup()
    {
        if(villainssmacked >= needvillainforlvlup)
        {
            boolean choselvlup = false;
            addObject(new LVLUP("Drücke 1 für speedup."), getWidth()/2, getHeight()/2 - getHeight()/10);
            addObject(new LVLUP("Drücke 2 für attack speedup."), getWidth()/2, getHeight()/2);
            addObject(new LVLUP("Drücke 3 für dmgup."), getWidth()/2, getHeight()/2 + getHeight()/10);
            Smacker smacker = this.getObjects(Smacker.class).get(0);
            
            this.repaint();
            
            do{
                //Speedup
                if(Greenfoot.isKeyDown("1"))
                {
                    smacker.movementspeed ++;
                    choselvlup = true;
                //Attackspeedup
                } else if(Greenfoot.isKeyDown("2"))
                {
                    smacker.attackspeed ++;
                    choselvlup = true;
                //dmgup
                } else if (Greenfoot.isKeyDown("3"))
                {
                    smacker.dmg ++;
                    choselvlup = true;
                }
            } while (!choselvlup);
            
            this.removeObjects(this.getObjects(LVLUP.class));
            
            needvillainforlvlup += needvillainforlvlup * lvlupoffset;
            showsmacked();
        }
    }
    /**
     * Erzeugt gegner und Powerups und erhöht deren capazität.
     */
    public void spawner()
    {
        powerupupper();
        powerupspawner();
        villainspawner();
        villainpowerup();
    }
    /**
     * Erzeugt villains
     */
    public void villainspawner()
    {
        if(villainlastspawn >= timer && maxVillain > currentVillain)
        {
            for(int i = 0; i < villainspawnrepeater; i++)
            {
                if(maxVillain > currentVillain)
                {
                    int x = 0;
                    int y = 0;
                    
                    switch(Greenfoot.getRandomNumber(4))
                    {
                        case 0:
                            y = Greenfoot.getRandomNumber(getHeight());
                            break;
                        case 1:
                            x = getWidth();
                            y = Greenfoot.getRandomNumber(getHeight());
                            break;
                        case 2:
                            x = Greenfoot.getRandomNumber(getWidth());
                            break;
                        case 3:
                            x = Greenfoot.getRandomNumber(getWidth());
                            y = getHeight();
                            break;
                    }
                    addObject(new Villain(Greenfoot.getRandomNumber(villainsunlocked)), x, y);
                    currentVillain++; 
                } 
            }
            
            villainlastspawn -= villainspawnoffset;
        }
    }
    /**
     * Nach einer Zeit wird schneller villains gespawnd.
     */
    public void villainpowerup()
    {
        if(villainpowerup >= timer)
        {
            if(villainspawnoffset % 2 == 0)
            {
                villainspawnoffset--;
                if(villainspawnoffset <= 0)
                {
                    villainspawnoffset = 1;
                    villainspawnrepeater ++;
                }
            } else 
            {
                villainspawnrepeater ++;
            }
            maxVillain += 3;
            
            villainpowerupoffset ++;
            villainpowerup -= villainpowerupoffset;
        }
        if(villainnewvillain >= timer)
        {
            if(villainsunlocked < allvillains)
            {
                villainsunlocked++;
                villainnewvillain -= villainnewvillainoffset;
            } else {
                villainnewvillain = 0;
            }
        }
    }
    
    /**
     * Erzeugt alle spawntimerpowerupoffset Sekunden ein Powerup falls es weniger als die maximal Powerups hat.
     */
    public void powerupspawner()
    {
        if(spawntimerpowerup >= timer && maxPowers > currentPowers)
        {
            addObject(new Powerups(), Greenfoot.getRandomNumber(this.getWidth() - 100) + 50, Greenfoot.getRandomNumber(this.getHeight() - 100) + 50);
            
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
            spawner();
            
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
        Message message = this.getObjects(Message.class).get(0);
        message.setMessage("Time: " + timer);
    }
    
    /**
     * Zeige die Leben an.
     * Falls die Leben kleiner gleich 0 sind wird das Spiel angehalten.
     */
    public void showlife()
    {
        Smacker smacker = this.getObjects(Smacker.class).get(0);
        Message message = this.getObjects(Message.class).get(1);
        
        message.setMessage("Lifes: " + smacker.lifes);
        
        if(smacker.lifes <= 0) 
        {
            gameend(false);
        }
    }
    
    /**
     * Zeigt an wie viele Villains gesmacked wurden.
     */
    public void showsmacked()
    {
        Message message = this.getObjects(Message.class).get(2);
        
        message.setMessage("Villains smacked: " + villainssmacked + " need " + (needvillainforlvlup - villainssmacked) + " more for lvl up");
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
