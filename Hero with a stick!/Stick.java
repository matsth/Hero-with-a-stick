import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stick here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stick extends Actor
{
    public int rot;
    public int rotationspeed;
    public int moveset;
    public int length;
    public Stick(int rotation, int length)
    {
        GreenfootImage image = new GreenfootImage("Stick.png");
        image.scale(image.getWidth()/10, image.getHeight()/10);
        setImage(image);
        
        moveset = rotation;
        
        this.setRotation(rotation);
        rot = 90;
        rotationspeed = 5;
    }
    /**
     * Act - do whatever the Stick wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        rotate();
    }
    
    public void rotate()
    {
        if(rot > 0)
        {
            this.setRotation(this.getRotation()+rotationspeed);
            rot -= rotationspeed;
            move();
        } else {
            Smacker smacker = getWorld().getObjects(Smacker.class).get(0);
            smacker.hasStick = false;
            getWorld().removeObject(this);
        }
    }
    
    public void move()
    {
        switch(moveset)
        {
            case 315:
                
                break;
            case 135:
                
                break;
            case 45:
                
                break;
            case 225:
                
                break;
            case 0:
                
                break;
            case 270:
                
                break;
            case 90:
                
                break;
            case 180:
                
                break;
        }
    }
    
    public void movewithSmacker(int x, int y)
    {
        this.setLocation(getX() + x, getY() + y);
    }
}
