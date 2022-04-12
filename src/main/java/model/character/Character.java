package model.character;

import model.character.entity.Entity;
import model.character.tools.aim.Aim;
import model.character.tools.health.Health;
import utilities.hitbox.Hitbox;

public interface Character extends Entity {

    Aim getAim();
    //Weapon getWeapon();
    Health getHealth();
    Hitbox getHitbox();
    //void changeWeapon(Weapon weapon);
}
