package model.character;

import model.character.tools.aim.Aim;
import model.character.tools.health.Health;

public interface Character {

    Aim getAim();
    //Weapon getWeapon();
    Health getHealth();
    //void changeWeapon(Weapon weapon);
}
