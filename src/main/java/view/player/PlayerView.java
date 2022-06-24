package view.player;

import view.CharacterSprites;
import view.CharacterView;

/**
 * 
 *
 */
public class PlayerView extends CharacterView { 
     public PlayerView() {
    	 super(CharacterSprites.PLAYERIDLERIFLE, CharacterSprites.PLAYERIDLEUPRIFLE, CharacterSprites.PLAYERRUNRIFLE, CharacterSprites.PLAYERRUNUPRIFLE, CharacterSprites.PLAYERCROUCHIDLERIFLE, CharacterSprites.PLAYERCROUCHRUNRIFLE, CharacterSprites.PLAYERRUNDOWNRIFLE);
     } 
}
