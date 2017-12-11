/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazarusGame.Objects;

import LazarusGame.GameEvent;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import javax.imageio.ImageIO;

/**
 *
 * @author jack
 */
public class Lazarus extends GameObj{
    Image moveLeft,moveRight, jumpLeft,jumpRight, dead;
    Boolean left, right;
    int lives;
    public void init(){
        try{
            moveLeft =ImageIO.read(new File("Resource/Lazarus_left_strip7.png"));
            moveRight = ImageIO.read(new File("Resource/Lazarus_right_strip7.png"));
            jumpLeft = ImageIO.read(new File("Resource/Lazarus_jump_left_strip7.png"));
            jumpRight = ImageIO.read(new File("Resource/Lazarus_jump_right_strip7.png"));
            dead = ImageIO.read(new File("Resources/Lazarus_squished_strip11"));
        }catch(Exception e){}

    }
    public Lazarus(BufferedImage[] img, int x, int y, int speed){
        super(img,x,y);
        this.lives = 5;
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
            if (msg.equals("Explosion")) {
                System.out.println("Explosion! Reduce Health");
            }
        }
    }
}
