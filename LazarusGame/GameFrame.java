package LazarusGame;

import LazarusGame.Objects.Box;
import LazarusGame.Objects.Lazarus;
import LazarusGame.Objects.Wall;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JApplet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *electron 
 * dipole
 * 
 * @author jack
 */
public class GameFrame extends JApplet implements Runnable{
    GameEvent gameEvents;
    private static Lazarus Player;
    private BufferedImage afraidStrip, jumpLeftStrip, jumpRightStrip, moveleftStrip, moveRightStrip, squishedStrip,stand;
    private BufferedImage cardboard, stone, wood, metal, wall, button, Background;
    private BufferedImage[] afraid, jumpLeft, jumpRight, moveLeft, moveRight, squished;
    Graphics2D g2;
    private ArrayList<Wall> walls = new ArrayList();
    private Thread thread;
    BufferedImage bimg;
    private FileReader lvl1;
    private static ArrayList<Box> boxes = new ArrayList();
    Random gen = new Random(4);
    private int[] keys = {KeyEvent.VK_SPACE, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
    
    @Override
    public void init(){
        setBackground(Color.BLACK);
        this.setFocusable(true);

        try{
            loadImageStrips();
            stand = ImageIO.read(new File("LazarusGame/Resources/Lazarus_stand.png"));
            afraid = stripSplitter(afraidStrip,40,10);
            jumpLeft = stripSplitter(jumpLeftStrip,80,7);
            jumpRight = stripSplitter(jumpRightStrip,80,7);
            moveLeft = stripSplitter(moveleftStrip,80,7);
            moveRight = stripSplitter(moveRightStrip,80,7);
            squished = stripSplitter(squishedStrip,40,11);
            cardboard = ImageIO.read(new File("LazarusGame/Resources/CardBox.gif"));
            stone = ImageIO.read(new File("LazarusGame/Resources/StoneBox.gif"));
            wood = ImageIO.read(new File("LazarusGame/Resources/WoodBox.gif"));
            metal = ImageIO.read(new File("LazarusGame/Resources/MetalBox.gif"));
            wall = ImageIO.read(new File("LazarusGame/Resources/Wall.gif"));
            button = ImageIO.read(new File("LazarusGame/Resources/Button.gif"));
            Background = ImageIO.read(new File("LazarusGame/Resources/Background.bmp"));
            lvl1=new FileReader("LazarusGame/Resources/level1.txt");


        }catch(Exception e){}  
        
        Player = new Lazarus(stand, moveLeft,moveRight,jumpLeft,jumpRight,squished,keys,320,360);

        gameEvents = new GameEvent();
        gameEvents.addObserver(Player);
        Controls key = new Controls(this.gameEvents);
        addKeyListener(key);
        
    }
    public void loadImageStrips(){
        try{

            afraidStrip = ImageIO.read(new File("LazarusGame/Resources/Lazarus_afraid_strip10.png"));
            jumpLeftStrip = ImageIO.read(new File("LazarusGame/Resources/Lazarus_jump_left_strip7.png"));
            jumpRightStrip = ImageIO.read(new File("LazarusGame/Resources/Lazarus_jump_right_strip7.png"));
            moveleftStrip = ImageIO.read(new File("LazarusGame/Resources/Lazarus_left_strip7.png"));
            moveRightStrip = ImageIO.read(new File("LazarusGame/Resources/Lazarus_right_strip7.png"));
            squishedStrip = ImageIO.read(new File("LazarusGame/Resources/Lazarus_squished_strip11.png"));


        }catch(Exception e){System.out.println(e.toString());}
    }

    public BufferedImage[] stripSplitter(BufferedImage img, int imgWidth, int frameNumber){
        int width = img.getWidth()/frameNumber;
        int height = img.getHeight();
        BufferedImage[] splitSprites = new BufferedImage[img.getTileWidth()/imgWidth];
        int imgDivider = 0;
        for(int i = 0; i < frameNumber; i++){
            splitSprites[i] = img.getSubimage(imgDivider,0,width,height);
            imgDivider += imgWidth;
        }
        return splitSprites;
    }

    public void setBackGround(FileReader lvl){
        BufferedReader line = new BufferedReader(lvl);
        String number;
        
        int position=0;
        try{
            while((number = line.readLine()) != null){
                for(int i= 0; i<number.length(); i++){
                    if(number.charAt(i)=='1')
                        this.walls.add(new Wall(wall, (position % 16) * 40, (position/ 16) * 40));
                    if(number.charAt(i)=='2')
                        this.walls.add(new Wall(button, (position % 16) * 40, (position/ 16) * 40));
                    position++;
                }
            }
        }catch(Exception e){}
    }
    
    public void BackgroundImage(){  
        g2.drawImage(Background,0,0,this);
    }
    
    public void drawDemo(){
        setBackGround(lvl1);
        BackgroundImage();
        if (!walls.isEmpty()) {
            for (int i = 0; i <= walls.size() - 1; i++)
		(walls.get(i)).draw(this, g2);
        }
        Player.draw(this, g2);
    }
    
    public static Lazarus getPlayer(){
        return Player;
    }
    
    public void boxDrop(){
        int x = gen.nextInt(4);
        switch(x){
            case 1:
                boxes.add(new Box(cardboard, Player.getX()-Player.getX()%40, 0, 5, x, true));
                break;
            case 2:
                boxes.add(new Box(wood, Player.getX()-Player.getX()%40, 0, 5, x, true));
                break;
            case 3:
                boxes.add(new Box(stone, Player.getX()-Player.getX()%40, 0, 5, x, true));
                break;
            case 4:
                boxes.add(new Box(metal, Player.getX()-Player.getX()%40, 0, 5, x, true));
                break;
            default:
                break;
        }
    }
    public void randomDrop(){
        if (!Player.isSquished()) {
            if (!boxes.get(boxes.size() - 2).getFalling()) {
                boxes.get(boxes.size() - 1).setBoxFalling(true);
                boxDrop();
            }
        }
    }
    public static ArrayList<Box> getBoxArray(){
        return boxes; 
    }
    
    @Override
    public void paint(Graphics g){
        if (bimg == null || bimg.getWidth() != LazarusMain.getX() || bimg.getHeight() != LazarusMain.getY()) {
            bimg = (BufferedImage) createImage(LazarusMain.getX(), LazarusMain.getY());
        }
        g2= bimg.createGraphics();
        drawDemo();
        g.drawImage(bimg,0,0,this);
    }
    
    @Override
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
