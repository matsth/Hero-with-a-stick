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
     * Act - do whatever the Villain wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
       public Villain()
    {
        GreenfootImage image = new GreenfootImage("Villain1.png");
        image.scale(image.getWidth()/10, image.getHeight()/10);
        setImage(image);
        Movementspeed = 5;
        
    }
    public void VillainMove()
    {
        int X = 0;
        int Y = 0;
        int diff = 0;
        Smacker smacker = getWorld().getObjects(Smacker.class).get(0);
        if(smacker.getX() > this.getX())
        {
            diff = smacker.getX() - this.getX();
            if(diff < 0)
            {
                diff = diff * (-1);
            }
            if (diff >= 20) 
            {
                X+=Movementspeed;
            }
            
        }
        else if(smacker.getX() < this.getX())
        {
            diff = smacker.getX() - this.getX();
            if(diff < 0)
            {
                diff = diff * (-1);
            }
            if (diff >= 20) 
            {
                X-=Movementspeed;
            }
        
        }
        if(smacker.getY() > this.getY())
        {
            diff = smacker.getY() - this.getY();
            if(diff < 0)
            {
                diff = diff * (-1);
            }
            if (diff >= 20) 
            {
                Y+=Movementspeed;
            }
            
        }
        else if(smacker.getY() < this.getY())
        {
            diff = smacker.getY() - this.getY();
            if(diff < 0)
            {
                diff = diff * (-1);
            }
            if (diff >= 20) 
            {
                Y-=Movementspeed;
            }
        
        }
        
        this.setLocation(this.getX() + X, this.getY() + Y);
        
    } 
    public void Collision()
    {
        Smacker smacker = getWorld().getObjects(Smacker.class).get(0);
        if(smacker.getX() == this.getX() && smacker.getY() == this.getY() )
        {
             getWorld().removeObject(this);
        }
    }

    public void act()
    {
        VillainMove();
        Collision();
    }
    
}



