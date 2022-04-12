package model.character.player;

import model.character.Character;
import utilities.Vector;

public interface Player extends Character {

    int getNumOfLives();
    //void interact(Item item);
    void respawn(Vector position);
}
