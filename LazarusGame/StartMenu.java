/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazarusGame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;

/**
 *
 * @author Jack
 */
public class StartMenu{
    Image Title;
    public void Menu(){
        try{
            Title = ImageIO.read(new File("LazarusGame/Resources/Title.gif"));
        }catch(Exception e){}        
    }
    
    public void draw(ImageObserver obs, Graphics2D g2){
        g2.drawImage(Title, LazarusMain.getX()/2-Title.getWidth(null)/2, 0, obs);
        g2.drawRect(LazarusMain.getX()/2-100, 300, 200, 50);
        g2.setFont(new Font("arial", Font.BOLD, 30));
        g2.drawString("START", LazarusMain.getX()/2-50, 335);
        
        g2.drawString("\u2190", LazarusMain.getX()/2-45, 400);
        g2.drawRect(LazarusMain.getX()/2-55, 365, 50, 50);
        g2.drawRect(LazarusMain.getX()/2+5, 365, 50, 50);
        g2.drawString("\u2192", LazarusMain.getX()/2+15, 400);
    }

}
