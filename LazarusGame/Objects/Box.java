package LazarusGame.Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;

public class Box extends GameObj {
    protected int boxX, boxY;
    int type;
    boolean isFalling;

    public Box(BufferedImage img, int x, int y, int speed,int type, boolean isFalling) {
        super(img, x, y);
        this.speed = speed;
        this.type = type;
        boxX = x;
        boxY = y;

        this.isFalling = isFalling;
    }

    public void boxFalling() {
        boxY = y;
        setY(boxY + speed);
    }

    public boolean setBoxFalling(boolean falling) {
        return this.isFalling = falling;
    }

    public boolean getFalling() {
        return isFalling;
    }

    public int getType() {
        return type;
    }
    //Public void collision(){}
    //call collision in boxUpdate
    @Override
    public void update(Observable o, Object arg){
        
    }
    public void draw(Graphics g, ImageObserver obs){
        if(visible){
            g.drawImage(img,x,y,obs);
            if(isFalling){
                boxFalling();
            }
        }
    }


}
