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
public class Lazarus extends GameObj {
    /*
    Image moveLeft, moveRight, jumpLeft, jumpRight, dead, stand;
    Boolean left = false, right = false;
    int lives;
    int delay = 1;
    int count;
    private Boolean finished = false, falling = false, jump = false;
    private Boolean squished = false, collisionLeft = false, collisionRight = false;
    private int lx, ly;
*/
    private BufferedImage[] moveLeft, moveRight, jumpLeft, jumpRight, squished;
    private BufferedImage stand;
    private int[] keys;
    private int lX, lY;
    private boolean falling = false;
    private boolean jump = false;
    private boolean isSquished = false;
    private boolean gameReset = false;
    private char direction = '~', faceDirection = '3';

    public Lazarus(
            BufferedImage stand,
            BufferedImage[] moveLeft,
            BufferedImage[] moveRight,
            BufferedImage[] jumpLeft,
            BufferedImage[] jumpRight,
            BufferedImage[] squished,
            int[] keys,
            int x,
            int y)
    {
        super(stand, x, y);
        speed = 5;
        this.moveLeft = moveLeft;
        this.moveRight = moveRight;
        this.jumpLeft = jumpLeft;
        this.jumpRight = jumpRight;
        this.squished = squished;
        this.stand = stand;
        this.keys = keys;
    }

    public boolean isSquished() {
        return isSquished;
    }

    public void setSquished(boolean squished){
        isSquished = squished;
    }

    public boolean isGameReset() {
        return gameReset;
    }
    public void setGameReset(boolean reset){
        this.gameReset = reset;
    }
    public void jumpLeft(){
        direction = '0';
        faceDirection = '0';

        setX(getX() - speed);

        if(frame > 7){
            frame = 0;
        }
    }
    public void jumpRight(){
        direction = '1';
        faceDirection = '1';

        setX(getX() + speed);

        if(frame > 7){
            frame = 0;
        }
    }
    public void moveLeft(){
        direction = '2';
        faceDirection = '2';

        setX(getX() - speed);

        if(frame > 7){
            frame = 0;
        }
    }
    public void moveRight(){
        direction = '3';
        faceDirection = '3';

        setX(getX() + speed);

        if(frame > 7){
            frame = 0;
        }
    }
    public void fall(){
        lY = y;
        setY(y + 40);
    }
    public void jump(){
        jump = true;
        if(faceDirection == '2'){
            jumpLeft();
        }
        if(faceDirection == '3'){
            jumpRight();
        }
    }
    @Override
    public void collisionAction(){
      switch(collisionType){
          case '0':
              if(falling){
                  falling = false;
                  jump = false;
                  setY(lY);
              } else if (!jump) {
                  if (faceDirection == '2') {
                      box.x -= 40;
                      box.y -= 40;
                  } else if (faceDirection == '3') {
                      box.x += 40;
                      box.y -= 40;
                      jump();
                  }
              }else if(jump){
                      jump = false;
                      wallCollision();
                  }
                    break;
          case '1':
              setSquished(true);
          default:
              break;
              }
              collisionType = '~';
      }

    public void wallCollision(){
        frame = 0;
        setX(lX);
        setY(lY+40);
    }
    @Override
   public void update(Observable o, Object arg){
    GameEvent ge = (GameEvent) arg;
    //if state = state.GAME
        if(ge.getType() == 1){
            KeyEvent e = (KeyEvent) ge.getEvent();
            int keyPressed = e.getKeyCode();
            if(keyPressed == this.keys[0]){
                setGameReset(true);
            } else if( keyPressed == this.keys[1]){
                if(frame == 0 && !falling){
                    if(visible){
                        lX = this.x;
                        lY = this.y;
                        moveLeft();
                    }
                }
            } else if(keyPressed == this.keys[2]){
                if(frame == 0 && !falling){
                    if(visible){
                        lX = this.x;
                        lY = this.y;
                        moveRight();
                    }
                }
            }

        }
   }
   @Override
    public void draw(ImageObserver obs, Graphics2D g2){
        if(visible){
            if(isSquished){
                if(frame != 10){
                    g2.drawImage(squished[frame],x,y,obs);
                    frame++;
                } else{
                    frame = 0;
                    setVisible(false);
                }
            } else {
                switch(direction){
                    case '0':
                        if((this.x % 40) != 0){
                            g2.drawImage(jumpLeft[frame], x-20, y-40, obs);
                            jumpLeft();
                            frame++;
                        } else{
                            frame = 0;
                            direction = '~';
                            setY(y-40);
                            g2.drawImage(stand,x,y,obs);
                            falling = true;
                        }
                        break;
                    case '1':
                        if((this.x % 40) != 0){
                            g2.drawImage(jumpRight[frame], x-20, y-40, obs);
                            jumpRight();
                            frame++;
                        } else{
                            frame = 0;
                            direction = '~';
                            setY(y-40);
                            g2.drawImage(stand,x,y,obs);
                            falling = true;
                        }
                        break;
                    case '2':
                        if((this.x % 40) != 0){
                            g2.drawImage(moveLeft[frame], x-20, y-40, obs);
                            moveLeft();
                            frame++;
                        } else{
                            frame = 0;
                            direction = '~';
                            g2.drawImage(stand,x,y,obs);
                            falling = true;
                        }
                        break;
                    case '3':
                        if((this.x%40)!= 0){
                            g2.drawImage(moveRight[frame],x-20,y-40,obs);
                            moveRight();
                            frame++;
                        } else{
                            frame = 0;
                            direction = '~';
                            g2.drawImage(stand,x,y,obs);
                            falling = true;
                        }
                        break;
                    default:
                        g2.drawImage(stand,x,y,obs);
                        break;
                }
                if(falling){
                    fall();
                }
            }
        }
   }


}
