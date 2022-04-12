package model.character;

import model.character.tools.aim.Aim;
import model.character.tools.health.Health;
import utilities.hitbox.Hitbox;
import utilities.movement.Movement;

public class CharacterImpl implements Character {

    private final Movement movement;
    private final Health health;
    private final Aim aim;
    private final Hitbox hitbox;
    //private Weapon weapon;
    
    public CharacterImpl(final Movement movement, final Health health, final Aim aim, final Hitbox hitbox/* , final Weapon weapon*/) {
        this.movement = movement;
        this.health = health;
        this.aim = aim;
        this.hitbox = hitbox;
        //this.weapon = weapon;
    }
    
    @Override
    public Movement getMovement() {
        return this.movement;
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

    /*@Override
    public void changeWeapon(final Weapon weapon) {
        this.weapon = weapon;
    }*/

}
