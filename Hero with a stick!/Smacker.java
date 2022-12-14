import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Smacker ist der Character der der Spieler kontrolliert.
 */
public class Smacker extends Actor
{
    public int movementspeed;
    public int lifes;
    public int dmg;
    public int attackspeed;
    
    public boolean hasStick;
    public boolean[] lastMove = new boolean[4];
    
    /**
     * Standart Konstruktor.
     * Das Bild wird gesesetz und die anderen Variablen.
     */
    public Smacker()
    {
        GreenfootImage image = new GreenfootImage("Smacker.png");
        image.scale(image.getWidth()/20, image.getHeight()/20);
        setImage(image);
        
        dmg = 1;
        attackspeed = 5;
        lifes = 6;
        movementspeed = 4;
        hasStick = false;
        lastMove[0] = true;
    }
    /**
     * Act - do whatever the Smacker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        movement();
        touchpowerup();
        smack();
    }
    
    /**
     * Zuerst wird getestet ob ein bewegungstaste gedrückt wird.
     * Falls nicht wird nicht bewegt.
     * 
     * Danach wird jede Taste durchgetestet und der Array lastMove entsprechen gesetzt.
     * 
     * lastMove[0]: Nach Rechts bewegen.
     * lastMove[1]: Nach Links bewegen.
     * lastMove[2]: Nach Unten bewegen.
     * lastMove[3]: Nach Oben bewegen.
     * Diese Variablen sind wichtig falls mit space angegriffen wird.
     * 
     * Falls ein Stick aktiv ist wird dieser auch bewegt.
     */
    public void movement()
    {
        int X = 0;
        int Y = 0;

        if(Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("w"))
        {
            //Rechts bewegen
             if(Greenfoot.isKeyDown("d"))
            {
                X += movementspeed;
                lastMove[0] = true;
            } else 
            {
                lastMove[0] = false;
            }
            //Links bewegen
            if(Greenfoot.isKeyDown("a"))
            {
                X -= movementspeed;
                lastMove[1] = true;
            } else 
            {
                lastMove[1] = false;
            }
            //Unten bewegen
            if(Greenfoot.isKeyDown("s"))
            {
                Y += movementspeed;
                lastMove[2] = true;
            } else
            {
                lastMove[2] = false;
            }
            //Oben bewegen
            if(Greenfoot.isKeyDown("w"))
            {
                Y -= movementspeed;
                lastMove[3] = true;
            } else 
            {
                lastMove[3] = false;
            }
            //Fals ein Stick aktiv ist diesen Bewegen
            if(hasStick)
            {
                Stick stick = getWorld().getObjects(Stick.class).get(0);
                stick.movewithSmacker(X, Y);
            }
            this.setLocation(this.getX() + X, this.getY() + Y);
            
        }
    }
    
    /**
     * Testet ob ein Powerup berührt wird und falls ja wird es aktiviert.
     */
    public void touchpowerup()
    {
        Powerups powerups = (Powerups)getOneIntersectingObject(Powerups.class);
        
        if(powerups != null){
            powerups.Powerup();
        }
    }
    
    /**
     * Falls man keinen Stick hat und space drückt erzeugt man einen
     * Stick um Gegner zu smacken.
     * 
     * Arrowkey:
     * Je nach Arrowkey wird ein anderen schlagort ausgewählt.
     * 
     * Space:
     * Danach wird getestet ob es einen aktiven lastMove[] gibt.
     * Falls gleichzeitig nach Links und Rechts bewegt wird.
     * Wird nach Rechts geschlagen.
     * 
     * Falls gleichzeitig nach Unten und Oben bewegt wird nach Oben geschlagen.
     * 
     * Danach werden die Entsprechende Rotation und Position für die
     * 8 unterschidlichen Movements erzeugt.
     * 
     * Danach wird der Stick erzeugt und die hasStick variable true gesetzt.
     */
    public void smack()
    {
        int X = 0;
        int Y = 0;
        int rotation = 0;
        if(!hasStick)
        {
            //Mit Pfeiltasten schlagen.
            if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left"))
            {
                    if(Greenfoot.isKeyDown("right"))
                    {
                        if(Greenfoot.isKeyDown("down"))
                        {
                            //Rechts und unten
                            X += this.getImage().getWidth();
                            rotation = 0;
                        } else if(Greenfoot.isKeyDown("up"))
                        {
                            //Rechts und oben
                            Y -= this.getImage().getHeight();
                            rotation = 270;
                        } else
                        {
                            //Rechts
                            X += this.getImage().getWidth();
                            Y -= this.getImage().getHeight()/2;
                            rotation = 315;
                        }
                    } else if(Greenfoot.isKeyDown("left")) 
                    {
                        if(Greenfoot.isKeyDown("down"))
                        {
                            //Links und unten
                            Y += this.getImage().getHeight();
                            rotation = 90;
                        } else if (Greenfoot.isKeyDown("up"))
                        {
                            //Links und oben
                            X -= this.getImage().getWidth();
                            rotation = 180;
                        } else 
                        {
                            //Links
                            X -= this.getImage().getWidth();
                            Y += this.getImage().getHeight()/2;
                            rotation = 135;
                        }
                    } else 
                    {
                        if(Greenfoot.isKeyDown("down"))
                        {
                            //Unten
                            X += this.getImage().getWidth()/2;
                            Y += this.getImage().getHeight();
                            rotation = 45;
                        } else if (Greenfoot.isKeyDown("up"))
                        {
                            //Oben
                            X -= this.getImage().getWidth()/2;
                            Y -= this.getImage().getHeight();
                            rotation = 225;
                        } 
                    }
                    hasStick = true;
                    getWorld().addObject(new Stick(rotation, getImage().getWidth(), dmg, attackspeed), getX()+X, getY()+Y);
                    
            //Mit Space schlagen
            } else if(Greenfoot.isKeyDown("space"))
            {
                if(lastMove[0] || lastMove[1] || lastMove[2] || lastMove[3])
                {              
                    if(lastMove[0])
                    {
                        if(lastMove[2])
                        {
                            //Rechts und unten
                            X += this.getImage().getWidth();
                            rotation = 0;
                        } else if(lastMove[3])
                        {
                            //Rechts und oben
                            Y -= this.getImage().getHeight();
                            rotation = 270;
                        } else
                        {
                            //Rechts
                            X += this.getImage().getWidth();
                            Y -= this.getImage().getHeight()/2;
                            rotation = 315;
                        }
                    } else if(lastMove[1]) 
                    {
                        if(lastMove[2])
                        {
                            //Links und unten
                            Y += this.getImage().getHeight();
                            rotation = 90;
                        } else if (lastMove[3])
                        {
                            //Links und oben
                            X -= this.getImage().getWidth();
                            rotation = 180;
                        } else 
                        {
                            //Links
                            X -= this.getImage().getWidth();
                            Y += this.getImage().getHeight()/2;
                            rotation = 135;
                        }
                    } else 
                    {
                        if(lastMove[2])
                        {
                            //Unten
                            X += this.getImage().getWidth()/2;
                            Y += this.getImage().getHeight();
                            rotation = 45;
                        } else if (lastMove[3])
                        {
                            //Oben
                            X -= this.getImage().getWidth()/2;
                            Y -= this.getImage().getHeight();
                            rotation = 225;
                        } 
                    }
                    hasStick = true;
                    getWorld().addObject(new Stick(rotation, getImage().getWidth(), dmg, attackspeed), getX()+X, getY()+Y);
                }
            }
        }
    }
    
    /**
     * Mit dieser Methode kann das Leben des Smacker reduziert werden und
     * mit der showlife Methode auf dem Screen angepasst werden.
     */
    public void loselife(int dmg)
    {
        lifes -= dmg;
        Smacktown smacktown = (Smacktown)getWorld();
        smacktown.showlife();
    }
}
