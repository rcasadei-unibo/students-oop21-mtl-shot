package view.character;

/**
 * 
 * Extends the CharacterView into a EnemyView, assigning correspective sprites.
 */
public class EnemyView extends CharacterView {

    /**
     * The constructor of the EnemyView.
     */
    public EnemyView() {
        super(CharacterSprites.PLAYERIDLERIFLE, CharacterSprites.PLAYERIDLEUPRIFLE, CharacterSprites.PLAYERRUNRIFLE,
                CharacterSprites.PLAYERRUNUPRIFLE, CharacterSprites.PLAYERCROUCHIDLERIFLE,
                CharacterSprites.PLAYERCROUCHRUNRIFLE, CharacterSprites.PLAYERRUNDOWNRIFLE);
    }

}
