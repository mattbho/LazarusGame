/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazarusGame;


import LazarusGame.Objects.GameObj;



public class Collision {
    
    public static boolean collision(GameObj obj1, GameObj obj2){
        if(obj1.getBox().intersects(obj2.getBox())){
            return true;
        }else
            return false;

    }
    
    public void LazarusvWallCollision(){
        if ( !GameFrame.getPlayer().isSquished( ) ) {
            for ( int i = 0; i < GameFrame.getWallArray().size( ); i++ ) {
                if (GameFrame.getWallArray().get( i ).isVisible( ) ) {
                    if (collision( GameFrame.getPlayer(), GameFrame.getWallArray().get( i )) ) {
                        GameFrame.getPlayer().collisionAction( );
                    }
                }
            }
        }
    }
    public void LazarusvBoxCollision(){
        if ( !GameFrame.getPlayer().isSquished( ) ) {
            for ( int i = 0; i < GameFrame.getBoxArray().size( ); i++ ) {
                if (GameFrame.getBoxArray().get( i ).isVisible( ) ) {
                    if (collision( GameFrame.getPlayer(), GameFrame.getBoxArray().get( i )) ) {
                        if(GameFrame.getBoxArray().get(i).getFalling()){
                            GameFrame.getPlayer().setSquished(true);
                            SoundPlayer.player("LazarusGame/Resources/Squished.wav", false);
                        }
                        GameFrame.getPlayer().collisionAction( );
                    }
                }
            }
        }
    }
    public void LazarusvButtonCollision(){
        if(!GameFrame.getPlayer().isSquished()){
            if(collision(GameFrame.getPlayer(),GameFrame.getButton())){
                GameFrame frame = new GameFrame();
                SoundPlayer.player("LazarusGame/Resources/Button.wav", false);
                frame.levelUp();
            }
        }
    }
    public void BoxvWallCollision(){
        for ( int i = 0; i < GameFrame.getBoxArray().size( ); i++ ) {
            for ( int j = 0; j < GameFrame.getWallArray().size( ); j++ ) {
                if (GameFrame.getBoxArray().get( i ).isVisible( ) ) {
                    if ( GameFrame.getBoxArray().get( i ).getFalling( ) ) {
                        if ( collision( GameFrame.getBoxArray().get( i ), GameFrame.getWallArray().get( j )) ) {
                            SoundPlayer.player("LazarusGame/Resources/Wall.wav", false);
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
                            if ( collision( GameFrame.getBoxArray().get( i ), GameFrame.getBoxArray().get( j )) ) {
                                SoundPlayer.player("LazarusGame/Resources/Wall.wav", false);
                                GameFrame.getBoxArray().get( i ).update();
                            }
                        }else if ( collision( GameFrame.getBoxArray().get( i ), GameFrame.getBoxArray().get( j ))) {
                            SoundPlayer.player("LazarusGame/Resources/Crush.wav", false);
                            GameFrame.getBoxArray().get( j ).setVisible( false );                        
                        }
                    }
                }
            }
        }
    }

    
    
}
