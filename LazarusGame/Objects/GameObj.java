package LazarusGame.Objects;


import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public abstract class GameObj implements Observer {
    protected Image img;
    protected Image[] imgArray;
    Rectangle box;
    protected int x, y, width, height, speed;
    //private boolean collide = false;

    public GameObj(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.speed = speed;

    }
    public GameObj(Image[] img, int x, int y){
        this.imgArray = img;
        this.x = x;
        this.y = y;
        this.width = img[0].getWidth(null);
        this.height = img[0].getHeight(null);
    }
    public void update(Observable o, Object arg){

    }
    public void draw(ImageObserver obs, Graphics2D g2){

    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;

    }
    public int getHeight(){
        return height;
    }
    public int getSpeed(){
        return speed;
    }
    public Image getImg(){
        return img;
    }
}
