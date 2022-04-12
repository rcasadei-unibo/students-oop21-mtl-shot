package model.character.entity;

import utilities.movement.Movement;

/**An entity is simply a thing that has a position and a speed (enemies, palyer but also items...) */
public interface Entity {

    Movement getMovement();
}
