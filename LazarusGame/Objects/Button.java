/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazarusGame.Objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

/**
 *
 * @author Jack
 */
public class Button extends GameObj{
    public Button(Image img, int y, Random gen){
        super(img,y,gen);
    }
    public void draw(ImageObserver obs, Graphics2D g2){
        g2.drawImage(img, x, y, obs);
    }
}
