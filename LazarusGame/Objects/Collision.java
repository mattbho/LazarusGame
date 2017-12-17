/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazarusGame.Objects;

import LazarusGame.GameFrame;
import LazarusGame.SoundPlayer;
import java.awt.Rectangle;

/**
 *
 * @author Jack
 */
/*
public class Collision {
    Boolean collide;
    public boolean collision(GameObj obj1, GameObj obj2){
        if(obj1.box.intersects(obj2.box)){
            collide = true;
        }else
            collide = false;
        return collide;
    }
    
    public void LvBCollision(){
        if ( !GameFrame.getPlayer().isSquished( ) ) {
            for ( int i = 0; i < GameFrame.getBoxArray().size( ); i++ ) {
                if (GameFrame.getBoxArray().get( i ).isVisible( ) ) {
                    if (collision( GameFrame.getPlayer(), GameFrame.getBoxArray().get( i )) ) {
                        GameFrame.getPlayer().collisionAction( );
                    }
                }
            }
        }
    }
    
    public void BvBCollision(){
        for ( int i = 0; i < GameFrame.getBoxArray().size( ); i++ ) {
            for ( int j = 0; j < GameFrame.getBoxArray().size( ); j++ ) {
                if ( j != i && GameFrame.getBoxArray().get( j ).isVisible( ) ) {
                    if ( GameFrame.getBoxArray().get( i ).getFalling( ) ) {
                        if ( GameFrame.getBoxArray().get( j ).getType( ) > GameFrame.getBoxArray().get( i ).getType( )) {
                        if ( collision( GameFrame.getBoxArray().get( i ), GameFrame.getBoxArray().get( j )) ) {
                                        //SoundPlayer.AUDIO_PLAYER.play( StaticInfo.WALL_SOUND );
                            //GameFrame.getBoxArray().get( i ).update();
                        }
                        }else if ( collision( GameFrame.getBoxArray().get( i ), GameFrame.getBoxArray().get( j )) ) {
                            //SoundPlayer.AUDIO_PLAYER.play( StaticInfo.BOX_CRUSH_SOUND );
                            GameFrame.getBoxArray().get( j ).setVisible( false );                        
                        }
                    }
                }
            }
        }
    }
    
    
}
*/