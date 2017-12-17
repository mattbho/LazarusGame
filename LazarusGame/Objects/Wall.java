/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazarusGame.Objects;


import LazarusGame.GameFrame;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

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
    
    public void update(){
            /*if ((GameFrame.getPlayer()).collision(this.x, this.y, this.width, this.height)){
                if(GameFrame.getP1().x>this.x){ 
                    GameFrame.getP1().x+=3;
                }else if(GameFrame.getP1().x<this.x){
                    GameFrame.getP1().x-=3;
                }
                if(GameFrame.getP1().y>this.y){
                    GameFrame.getP1().y+=3;
                }else if(GameFrame.getP1().y<this.y){
                    GameFrame.getP1().y-=3;
                }
            }*/
        }
}
