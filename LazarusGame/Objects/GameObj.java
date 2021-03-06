package LazarusGame.Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public abstract class GameObj implements Observer {

    protected BufferedImage img;
    protected BufferedImage[] imgArray;
    Rectangle box;
    protected int x, y, speed, frame = 0;
    protected boolean visible = true;
    protected char collisionType = '~';
    public GameObj(BufferedImage img, int x, int y){
        this.img = img;
        this.x = x;
        this.y = y;
        box = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
    }
    public GameObj(BufferedImage img){
        this.img = img;
        box = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
    }

    public GameObj(BufferedImage[] imgArray, int x, int y){
        this.imgArray = imgArray;
        this.x = x;
        this.y = y;
        box = new Rectangle(x,y,this.imgArray[0].getWidth(), this.imgArray[0].getHeight());
    }

    public void update(Observable o, Object arg){

    }
    public void draw(ImageObserver obs, Graphics2D g2){
        if(visible){
            g2.drawImage(img,x,y,obs);
        }
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
        box.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
        box.y = y;
    }
   
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public boolean isVisible(){
        return visible;
    }
    public void setVisible(boolean vision){
        this.visible = vision;
    }

    public Rectangle getBox() {
        return box;
    }
}
