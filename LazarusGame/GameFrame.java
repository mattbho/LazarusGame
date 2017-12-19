package LazarusGame;

import LazarusGame.Objects.Box;
import LazarusGame.Objects.Button;
import LazarusGame.Objects.Lazarus;
import LazarusGame.Objects.Wall;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    GameEvent gameEvents = new GameEvent();
    private static Lazarus Player;
    private static BufferedImage afraidStrip, jumpLeftStrip, jumpRightStrip, moveleftStrip, moveRightStrip, squishedStrip,stand;
    private static BufferedImage cardboard, stone, wood, metal, wall, button, Background;
    private BufferedImage[] afraid, jumpLeft, jumpRight, moveLeft, moveRight, squished;
    Graphics2D g2;
    private static ArrayList<Wall> walls = new ArrayList();
    private Thread thread;
    BufferedImage bimg;
    private static Button buttons;
    private FileReader lvl;
    private static ArrayList<Box> boxes = new ArrayList();
    Random gen = new Random();
    private int[] keys = {KeyEvent.VK_SPACE, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
    Collision collision = new Collision();
    private static int levels=1;
    Controls key;
    private int x,y;
    public static enum gameState{
        menu,game
    }
    private static gameState state = gameState.menu;
    StartMenu menu = new StartMenu();
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
            lvl=new FileReader("LazarusGame/Resources/level"+levels+".txt");

        }catch(Exception e){}  
        menu.Menu();
        addMouseListener(new Mouse());
        Player = new Lazarus(stand, moveLeft,moveRight,jumpLeft,jumpRight,squished,keys);
        gameEvents = new GameEvent();
        gameEvents.addObserver(Player);
        Controls key = new Controls(this.gameEvents);
        addKeyListener(key);
        
        
    }
    public static void loadImageStrips(){
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
        //this.setFocusable(true);
        int position=0;
        try{
            while((number = line.readLine()) != null){
                for(int i= 0; i<number.length(); i++){
                    if(number.charAt(i)=='1')
                        this.walls.add(new Wall(wall, (position % 16) * 40, (position/ 16) * 40));
                    if(number.charAt(i)=='2')
                        buttons =new Button(button, (position % 16) * 40, (position/ 16) * 40);
                    if(number.charAt(i) == 'L'){
                        x = (position % 16) * 40;
                        y = (position/ 16) * 40;
                        Player.setXY(x, y);
                        boxDrop();
                    }
                    position++;
                }
            }
        }catch(Exception e){}
    }
    public void resetLevel(){
        boxes.clear();
        Player.setSquished(false);
        Player.setVisible(true);
        Player.resetPosition();
        boxDrop();        
    }
    public void BackgroundImage(){  
        g2.drawImage(Background,0,0,this);
    }
    
    public void drawDemo(){
        BackgroundImage();
        if(state == gameState.game){
            setBackGround(lvl);
            if (!walls.isEmpty()) {
                for (int i = 0; i <walls.size(); i++)
                	(walls.get(i)).draw(this, g2);
            }
            if(!boxes.isEmpty()){
                for(int i = 0; i <boxes.size();i++){
                    boxes.get(i).draw(this, g2);
                }
            }
            buttons.draw(this, g2);
        }

    }
    
    public static Lazarus getPlayer(){
        return Player;
    }
    
    
    public void boxDrop(){
        int x = gen.nextInt(4)+1;
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
            if(!boxes.isEmpty())
            if (!boxes.get(boxes.size() - 1).getFalling()) {
                boxDrop();
            }
        }
    }
    
    
    public void levelUp(){        
        levels++;      
        if(levels > 3){
            state = gameState.menu;
        }else{            
            boxes.clear();
            walls.clear();
        try{
            lvl=new FileReader("LazarusGame/Resources/level"+levels+".txt");
        }catch(Exception e){}
        setBackGround(lvl);
        resetLevel();
        }
        
    }
    public static ArrayList<Box> getBoxArray(){
        return boxes; 
    }
    public static ArrayList<Wall> getWallArray(){
        return walls;
    }
    public static Button getButton(){
        return buttons;
    }
    public static gameState getState(){
        return state;
    }
    public static void setState(gameState temp){
        state = temp;
    }
    @Override
    public void paint(Graphics g){
        if (bimg == null || bimg.getWidth() != LazarusMain.getX() || bimg.getHeight() != LazarusMain.getY()) {
            bimg = (BufferedImage) createImage(LazarusMain.getX(), LazarusMain.getY());
        }
        
        g2= bimg.createGraphics();
        drawDemo();
        
        if(state == gameState.menu){     
            menu.draw(this, g2);
        }else if(state == gameState.game){
            collision.BoxvBoxCollision();
            collision.LazarusvWallCollision();
            collision.LazarusvBoxCollision();
            collision.BoxvWallCollision();
            collision.LazarusvButtonCollision();
            randomDrop();
            Player.draw(this, g2);
        }
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
        while (thread == me){
            repaint();
            try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
            
        }
    }    
}
