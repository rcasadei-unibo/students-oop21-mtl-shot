package view;

/**
 * Defines the graphic properties of the enemy.
 *
 */
public class EnemyView extends CharacterView {

    /**
     * The constructor of the EnemyView.
     */
    public EnemyView() {
        super(CharacterSprites.playerIdleRifle, CharacterSprites.playerIdleUpRifle, CharacterSprites.playerRunRifle,
                CharacterSprites.playerRunUpRifle, CharacterSprites.playerCrouchIdleRifle,
                CharacterSprites.playerCrouchRunRifle);
    }

}
