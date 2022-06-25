package view.character;

/**
 * Defines the graphic properties of the enemy.
 *
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
