/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazarusGame;


import LazarusGame.Objects.GameObj;

/**
 *
 * @author Jack
 */

public class Collision {
    // stop = ~
    // run into = 0
    // squish = 1;

    public static boolean collision(GameObj obj1, GameObj obj2, char type){
        if(obj1.getBox().intersects(obj2.getBox())){
            obj1.setCollisionType(type);
            return true;
        }else
            return false;

    }
    
    public static void LazarusvWallCollision(){
        if ( !GameFrame.getPlayer().isSquished( ) ) {
            for ( int i = 0; i < GameFrame.getWallArray().size( ); i++ ) {
                if (GameFrame.getWallArray().get( i ).isVisible( ) ) {
                    if (collision( GameFrame.getPlayer(), GameFrame.getWallArray().get( i ), '0') ) {
                        GameFrame.getPlayer().collisionAction( );
                    }
                }
            }
        }
    }
    
    public void BoxvWallCollision(){
        for ( int i = 0; i < GameFrame.getBoxArray().size( ); i++ ) {
            for ( int j = 0; j < GameFrame.getWallArray().size( ); j++ ) {
                if (GameFrame.getBoxArray().get( i ).isVisible( ) ) {
                    if ( GameFrame.getBoxArray().get( i ).getFalling( ) ) {
                        if ( collision( GameFrame.getBoxArray().get( i ), GameFrame.getWallArray().get( j ), '0') ) {
                                        //SoundPlayer.AUDIO_PLAYER.play( StaticInfo.WALL_SOUND );
                            GameFrame.getBoxArray().get( i ).update();                       
                        }
                    }
                }
            }
        }
    }
    
    public void BoxvBoxCollision(){
        for ( int i = 0; i < GameFrame.getBoxArray().size( ); i++ ) {
            for ( int j = 0; j < GameFrame.getBoxArray().size( ); j++ ) {
                if ( j != i && GameFrame.getBoxArray().get( j ).isVisible( ) ) {
                    if ( GameFrame.getBoxArray().get( i ).getFalling( ) ) {
                        if ( GameFrame.getBoxArray().get( j ).getType( ) >= GameFrame.getBoxArray().get( i ).getType( )) {
                            if ( collision( GameFrame.getBoxArray().get( i ), GameFrame.getBoxArray().get( j ), '0') ) {
                                        //SoundPlayer.AUDIO_PLAYER.play( StaticInfo.WALL_SOUND );
                                GameFrame.getBoxArray().get( i ).update();
                            }
                        }else if ( collision( GameFrame.getBoxArray().get( i ), GameFrame.getBoxArray().get( j ), '~') ) {
                            //SoundPlayer.AUDIO_PLAYER.play( StaticInfo.BOX_CRUSH_SOUND );
                            GameFrame.getBoxArray().get( j ).setVisible( false );                        
                        }
                    }
                }
            }
        }
    }

    
    
}
