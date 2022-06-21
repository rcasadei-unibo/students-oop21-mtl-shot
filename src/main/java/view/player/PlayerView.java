package view.player;

import view.CharacterSprites;
import view.CharacterView;

/**
 * 
 *
 */
public class PlayerView extends CharacterView { 
     public PlayerView() {
    	 super(CharacterSprites.playerIdle, CharacterSprites.playerIdleUp, CharacterSprites.playerRun, CharacterSprites.playerRunUp, CharacterSprites.playerCrouchIdle, CharacterSprites.playerCrouchRun);
     } 
}
