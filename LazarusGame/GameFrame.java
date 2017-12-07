package LazarusGame;

import LazarusGame.Objects.Lazarus;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JApplet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jack
 */
public class GameFrame extends JApplet implements Runnable{
    GameEvent gameEvents;
    Lazarus Player;
    Image Background,metal, wood,cardboard,stone, button;
    Image moveLeft,moveRight, jumpLeft,jumpRight, dead;
    Graphics2D g2;
    private Thread thread;
    BufferedImage bimg;
    Random gen = new Random(1234);
    public void init(){
        setBackground(Color.BLACK);
        this.setFocusable(true);
        try{
            
            Background = ImageIO.read(new File("LazarusGame/Resources/Background.bmp"));            
            cardboard = ImageIO.read(new File("LazarusGame/Resources/CardBox.gif"));
            wood = ImageIO.read(new File("LazarusGame/Resources/WoodBox.gif"));
            stone = ImageIO.read(new File("LazarusGame/Resources/StoneBox.gif"));
            metal = ImageIO.read(new File("LazarusGame/Resources/MetalBox.gif"));
            button = ImageIO.read(new File("LazarusGame/Resources/Button.gif"));
               
        }catch(Exception e){}  
        //Player=new Lazarus(320, 400, 32);
        gameEvents = new GameEvent();
        //gameEvents.addObserver(Player);
        Controls key = new Controls(this.gameEvents);
        addKeyListener(key);
        
    }
    
    public void BackgroundImage(){
        g2.drawImage(Background,0,0,this);
    }
    public void drawDemo(){
        
        BackgroundImage();
    }
    
    public void paint(Graphics g){
        if (bimg == null || bimg.getWidth() != LazarusMain.getX() || bimg.getHeight() != LazarusMain.getY()) {
            bimg = (BufferedImage) createImage(LazarusMain.getX(), LazarusMain.getY());
        }
        g2= bimg.createGraphics();
        drawDemo();
        g.drawImage(bimg,0,0,this);
    }
    
    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();

    }

    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();  
          try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
            
        }
    }    
}
