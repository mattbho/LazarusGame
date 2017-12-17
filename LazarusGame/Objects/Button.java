/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazarusGame.Objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author Jack
 */
public class Button extends GameObj{
    public Button(BufferedImage img, int x, int y){
        super(img,x,y);
    }
    @Override
    public void update(Observable o, Object arg){

    }
}
