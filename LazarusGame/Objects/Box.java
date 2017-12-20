package LazarusGame.Objects;

import LazarusGame.SoundPlayer;
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
    
    public void update(){
        isFalling = false;
        setY(boxY);
    }
    public void draw(ImageObserver obs, Graphics2D g2){
        if(visible){
            g2.drawImage(img,x,y,obs);
            if(isFalling){
                boxFalling();
            }
        }
    }


}
