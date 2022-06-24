package controller.enemy;

import model.character.Character;
import model.character.Enemy;
import model.character.Player;
import model.character.movableentity.MovableEntity;
import model.map.Level;

public class StillBot implements SimpleBot{

    private Enemy enemy;
    private Player player;
    private Level level;

    public StillBot(final Character enemy, Level level) {
        this.enemy = (Enemy) enemy;
        this.level = level;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setPlayer(final Player p) {
        this.player = p;
    }

    @Override
    public MovableEntity getEntity() {
        // TODO Auto-generated method stub
        return this.enemy;
    }

    @Override
    public void setEnemy(final Character character) {
        this.enemy = (Enemy) character;

    }

    @Override
    public void fire() {
        // TODO Auto-generated method stub
    }

    @Override
    public void controllerTick() {
        // TODO Auto-generated method stub
    }

}
