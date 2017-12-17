/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazarusGame.Objects;

import LazarusGame.GameEvent;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Observable;
import javax.imageio.ImageIO;

/**
 *
 * @author jack
 */
public class Lazarus extends GameObj{
    Image moveLeft,moveRight, jumpLeft,jumpRight, dead, stand;
    Boolean left=false, right=false;
    int lives;
    int delay =1;
    int count;
    private Boolean finished=false, falling = false, jump = false;
    private Boolean squished=false, collisionLeft=false, collisionRight=false;
    private int lx,ly;


    public Boolean isSquished(){
        return squished;
    }
    
    public Lazarus(BufferedImage img, int x, int y){
        super(img, x,y);
        this.lives = 5;
        count = 0;
        
    }
    
    public int getX(){
        return x;
    }
    
    public void collisionAction(){
        if(!squished){
            if(falling){
                falling=false;
                jump = false;
            }else if(!jump){
                if(left == true){
                    box.x = x-40;
                    box.y = y-40;
                    delay =0;
                }else if(right == true){
                    box.x = x+40;
                    box.y = y-40;
                    delay=0;
                }
                LazarusJump();
            }else if(jump){
                jump = false;
                x=lx;
                y=ly;
                delay = 0;
            }
        }else if(squished)
            dead();
    }
    
    public void dead(){
        squished = true;
    }
    public void LazarusJump(){
        if(left == true)
            collisionLeft=true;
        if(right ==true)            
            collisionRight=true;
    }
    public void resetCollision(){
        collisionLeft = false;
        collisionRight = false;
    }
    
    public void updateMove(){
        if(!falling)
        if(left==true && collisionLeft == false){
            x -= 20;
        }else if(right == true && collisionRight == false){
            x += 20;
        }else if(left == true && collisionLeft == true){
            x -= 20;
            y -= 20;
        }else if(right == true && collisionRight == true){
            x += 20;
            y -= 20;
        } 
    }

    public void update(Observable o, Object arg) {
        GameEvent ge = (GameEvent) arg;
        if (ge.getType() == 1) {
            KeyEvent e = (KeyEvent) ge.getEvent();
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (e.getID() == KeyEvent.KEY_RELEASED)
                    left = false;
                else if (e.getID() == KeyEvent.KEY_PRESSED)
                    left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (e.getID() == KeyEvent.KEY_RELEASED)
                    right = false;
                else if (e.getID() == KeyEvent.KEY_PRESSED)
                    right = true;
            }            
        } else if (ge.getType() == 2) {
            String msg = (String) ge.getEvent();
            if (msg.equals("Squished")) {
                System.out.println("Squished");
            }
        }
    }
    
    public void draw(ImageObserver obs, Graphics2D g2){
        if(left==true && collisionLeft == false){
            if(delay < 7){
                g2.drawImage(moveLeft, x*delay*40, y, obs);
                if(((count++)%5) == 0){
                    delay++;
                }
            } else{
                finished = true;
            }
        }else if(right == true && collisionRight == false){
            if(delay < 7){
                g2.drawImage(moveRight, x, y, obs);
                if(((count++)%5) == 0){
                    delay++;
                }
            } else{
                finished = true;
            }              
        }else if(left == true && collisionLeft == true){
            if(delay < 7){
                g2.drawImage(jumpLeft, x, y, obs);
                if(((count++)%5) == 0){
                    delay++;                    
                }
            } else{
                resetCollision();
                finished = true;
            }              
        }else if(right == true && collisionRight == true){
            if(delay < 7){
                g2.drawImage(jumpRight, x, y, obs);
                if(((count++)%5) == 0){
                    delay++;
                }
            } else{
                resetCollision();
                finished = true;
            }             
        }else if(squished == true){
            if(delay < 11){
                g2.drawImage(dead, x, y, obs);
                if(((count++)%5) == 0){
                    delay++;
                }
            } else{
                resetCollision();
                finished = true;
                count = 0;
            }
        }else{
            finished = false;
            g2.drawImage(stand, x, y, obs);
            System.out.println(y);
        }
    }
}
