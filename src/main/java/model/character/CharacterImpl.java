package model.character;

import model.character.entity.EntityImpl;
import model.character.tools.aim.Aim;
import model.character.tools.health.Health;
import utilities.hitbox.Hitbox;
import utilities.movement.Movement;

public class CharacterImpl extends EntityImpl implements Character {

    private final Health health;
    private final Aim aim;
    //private Weapon weapon;
    
    public CharacterImpl(final Movement movement, final Health health, final Aim aim, final Hitbox hitbox/* , final Weapon weapon*/) {
        super(movement, hitbox);
        this.health = health;
        this.aim = aim;
        //this.weapon = weapon;
    }

    @Override
    public Aim getAim() {
        return this.aim;
    }

    @Override
    public Health getHealth() {
        return this.health;
    }

    /*@Override
    public Weapon getWeapon() {
    	return this.weapon;
     */
    
    /*@Override
    public void changeWeapon(final Weapon weapon) {
        this.weapon = weapon;
    }*/

}
