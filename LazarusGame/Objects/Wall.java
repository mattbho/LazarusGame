/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazarusGame.Objects;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;

/**
 *
 * @author jack
 */
public class Wall extends GameObj{
    private int width, height;
    public Wall(BufferedImage img, int x, int y){
        super(img,x,y);
        this.width = img.getWidth();
        this.height = img.getHeight();
    }

    
    
    @Override
    public void draw(ImageObserver obs, Graphics2D g){
        g.drawImage(img, x, y, obs);
    }
    @Override
    public void update(Observable o, Object arg){

        }
}
