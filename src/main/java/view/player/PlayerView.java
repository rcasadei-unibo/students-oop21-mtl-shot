package view.player;

import view.CharacterSprites;
import view.CharacterView;

/**
 * 
 *
 */
public class PlayerView extends CharacterView {

    /**
     * The constructor of the PlayerView.
     */
    public PlayerView() {
        super(CharacterSprites.playerIdleRifle, CharacterSprites.playerIdleUpRifle, CharacterSprites.playerRunRifle,
                CharacterSprites.playerRunUpRifle, CharacterSprites.playerCrouchIdleRifle,
                CharacterSprites.playerCrouchRunRifle);
    }
}
