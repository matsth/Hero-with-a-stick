import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Smacker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Smacker extends Actor
{
    public int movementspeed;
    public int lifes;
    public boolean hasStick;
    public boolean[] lastMove = new boolean[4];
    
    public Smacker()
    {
        GreenfootImage image = new GreenfootImage("Smacker.png");
        image.scale(image.getWidth()/10, image.getHeight()/10);
        setImage(image);
        
        lifes = 3;
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
        smack();
    }
    
    /**
     * 
     */
    public void movement()
    {
        int X = 0;
        int Y = 0;
        boolean active = false;
        if(Greenfoot.isKeyDown("d"))
        {
            active = true;
        } else if(Greenfoot.isKeyDown("a"))
        {
            active = true;
        } else if(Greenfoot.isKeyDown("s"))
        {
            active = true;
        } else if(Greenfoot.isKeyDown("w"))
        {
            active = true;
        }
        if(active)
        {
             if(Greenfoot.isKeyDown("d"))
            {
                X += movementspeed;
                lastMove[0] = true;
            } else 
            {
                lastMove[0] = false;
            }
            if(Greenfoot.isKeyDown("a"))
            {
                X -= movementspeed;
                lastMove[1] = true;
            } else 
            {
                lastMove[1] = false;
            }
            if(Greenfoot.isKeyDown("s"))
            {
                Y += movementspeed;
                lastMove[2] = true;
            } else
            {
                lastMove[2] = false;
            }
            if(Greenfoot.isKeyDown("w"))
            {
                Y -= movementspeed;
                lastMove[3] = true;
            } else 
            {
                lastMove[3] = false;
            }
            if(hasStick)
            {
                Stick stick = getWorld().getObjects(Stick.class).get(0);
                stick.movewithSmacker(X, Y);
            }
            this.setLocation(this.getX() + X, this.getY() + Y);
            
        }
    }
    
    /**
     * 
     */
    public void smack()
    {
        int X = 0;
        int Y = 0;
        int rotation = 0;
        if(Greenfoot.isKeyDown("space") && !hasStick)
        {
            if(lastMove[0] || lastMove[1] || lastMove[2] || lastMove[3])
            {
                if(lastMove[0] && lastMove[1])
                {
                    lastMove[1] = false;
                }
                if(lastMove[2] && lastMove[3])
                {
                    lastMove[2] = false;
                    lastMove[3] = false;
                }
                
                
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
                getWorld().addObject(new Stick(rotation, getImage().getWidth()), getX()+X, getY()+Y);
            }
        }
    }
}
