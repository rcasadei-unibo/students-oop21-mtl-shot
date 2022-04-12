package model.character.player;

import model.character.tools.aim.Aim;
import model.character.tools.health.Health;
import utilities.Vector;
import utilities.hitbox.Hitbox;
import utilities.hitbox.HitboxImpl;
import utilities.movement.Movement;

//FARE UN BUILDER
public class PlayerImpl implements Player {

    private int lives;
    private final Aim aim;
    private final Health health;
    private Hitbox hitbox;
    //private Weapon weapon;
    //private final List<Item> items;
    private final Movement movement;
    
    public PlayerImpl(final Movement movement, final Health health, final Aim aim, /*final Weapon weapon,*/ final Hitbox hitbox, final int lives) {
        this.lives = lives;
        this.aim = aim;
        this.health = health;
        this.hitbox = hitbox;
        this.movement = movement;
        //this.weapon = weapon;
        //this.items = new LinkedList<>();
    }
    
    @Override
    public Aim getAim() {
        return this.aim;
    }

    @Override
    public Health getHealth() {
        return this.health;
    }

    @Override
    public Hitbox getHitbox() {
        return this.hitbox;
    }

    @Override
    public Movement getMovement() {
        return this.movement;
    }

    @Override
    public int getNumOfLives() {
        return this.lives;
    }

    @Override
    public void respawn(final Vector position) {
        this.lives--;
        if (this.lives >= 0) {
            this.movement.setPosition(position);
            this.hitbox = new HitboxImpl(hitbox.getWidth(), hitbox.getHeight(), position);
        }
    }

}
