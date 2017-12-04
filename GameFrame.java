
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
    Image Background,metal, wood,cardboard,stone;
    Image[] moveLeft,moveRight, jumpLeft,jumpRight, box;
    
    Random gen = new Random(1234);
    public void init(){
        this.setFocusable(true);
        try{
            Background = ImageIO.read(new File("Resource/Background.bmp"));            
            cardboard = ImageIO.read(new File("Resource/CardBox.gif"));
            wood = ImageIO.read(new File("Resource/WoodBox.gif"));
            stone = ImageIO.read(new File("Resource/StoneBoc.gif"));
            metal = ImageIO.read(new File("Resource/MetalBox.gif"));
        }catch(Exception e){}  
        
    }
    @Override
    public void run() {
        
    }
    
}
