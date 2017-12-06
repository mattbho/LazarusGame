package LazarusGame;
import LazarusGame.GameEvent;
import LazarusGame.Objects.Lazarus;
import java.awt.Image;
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
    Image Background,metal, wood,cardboard,stone;
    Image moveLeft,moveRight, jumpLeft,jumpRight, dead;
    
    Random gen = new Random(1234);
    public void init(){
        this.setFocusable(true);
        try{
            
            Background = ImageIO.read(new File("Resource/Background.bmp"));            
            cardboard = ImageIO.read(new File("Resource/CardBox.gif"));
            wood = ImageIO.read(new File("Resource/WoodBox.gif"));
            stone = ImageIO.read(new File("Resource/StoneBox.gif"));
            metal = ImageIO.read(new File("Resource/MetalBox.gif"));
               
        }catch(Exception e){}  
        Player=new Lazarus(320, 400, 32);
        gameEvents = new GameEvent();
        gameEvents.addObserver(Player);
        Controls key = new Controls(this.gameEvents);
        addKeyListener(key);
        
    }
    
    @Override
    public void run(){
        
    }
    public void paint(){
        
    }
    
}
