package view.character;

/**
 * 
 * Extends the CharacterView into a PlayerView, assigning correspective sprites.
 */
public class PlayerView extends CharacterView {

    /**
     * The constructor of the PlayerView.
     */
    public PlayerView() {
        super(CharacterSprites.PLAYERIDLERIFLE, CharacterSprites.PLAYERIDLEUPRIFLE, CharacterSprites.PLAYERRUNRIFLE,
                CharacterSprites.PLAYERRUNUPRIFLE, CharacterSprites.PLAYERCROUCHIDLERIFLE,
                CharacterSprites.PLAYERCROUCHRUNRIFLE, CharacterSprites.PLAYERRUNDOWNRIFLE);
    }
}
