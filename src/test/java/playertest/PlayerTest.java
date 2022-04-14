package playertest;

import org.junit.jupiter.api.Test;

import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import utilities.Direction;
import utilities.Vector;

public class PlayerTest {
	
	private final Player player;
	
	public PlayerTest() {
		player = new PlayerBuilder()
				.health(new SimpleHealth())
				.hitbox(new Vector(1, 2))
				.lives(3)
				.position(new Vector(0,0))
				.build();
	}
	
    @Test 
    public void movementTest() {
      //--------SIMPLE MOVEMENT-------------
        System.out.println("sx x4");
        System.out.println(player.getPosition() + " " + player.getSpeed());
        player.setLeft(true);
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        System.out.println(player.getPosition() + " " + player.getSpeed());
        
        System.out.println("rallenta");
        player.setLeft(false);
        while (player.getSpeed().getX() != 0) {
            player.moveEntity();
            System.out.println(player.getPosition() + " " + player.getSpeed());
        }
        
        player.setPosition(new Vector(0,0));
        System.out.println("dx x4");
        player.setRight(true);
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        System.out.println(player.getPosition() + " " + player.getSpeed());
        
        System.out.println("rallenta");
        player.setRight(false);
        while (player.getSpeed().getX() != 0) {
            player.moveEntity();
            System.out.println(player.getPosition() + " " + player.getSpeed());
        }
        
        player.setPosition(new Vector(0,0));
        System.out.println("jump");
        player.setJump(true);
        for (int i = 0; i < 60; i++) {
            System.out.println(player.getPosition() + " " + player.getSpeed());
            player.moveEntity();
        }
        player.setFall(false);
        player.setJump(false);
        player.setSpeed(new Vector(0,0));
        
        //---------COMBO MOVEMENT-------------
        player.setPosition(new Vector(0,0));
        System.out.println("dx sx x4");
        System.out.println(player.getPosition() + " " + player.getSpeed());
        player.setLeft(true);
        player.setRight(true);
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        System.out.println(player.getPosition() + " " + player.getSpeed());
        player.setLeft(false);
        player.setRight(false);
        
        player.setPosition(new Vector(0,0));
        System.out.println("jump dx");
        System.out.println(player.getPosition() + " " + player.getSpeed());
        player.setRight(true);
        player.setJump(true);
        for (int i = 0; i < 50; i++) {
            player.moveEntity();
            System.out.println(player.getPosition() + " " + player.getSpeed());
        }
        player.setRight(false);
        player.setJump(false);
        player.setFall(false);
        player.setSpeed(new Vector(0,0));
        
        player.setPosition(new Vector(0,0));
        
        //-----------AIM-----------
        player.getAim().setDirection(Direction.LEFT);
        System.out.println(player.getAim());
        player.getAim().setDirection(Direction.DOWN);
        System.out.println(player.getAim());
        player.getAim().returnToHorizontal();
        System.out.println(player.getAim());
        player.getAim().setDirection(Direction.UP);
        System.out.println(player.getAim());
        player.getAim().returnToHorizontal();
        System.out.println(player.getAim());
        player.getAim().setDirection(Direction.NEUTRAL);
        System.out.println(player.getAim());
        
        //-----------HEALTH-----------------
        System.out.println(player.getHealth());
        player.getHealth().hurt(10);
        System.out.println(player.getHealth());
        player.getHealth().heal(1);
        System.out.println(player.getHealth());
        player.getHealth().setMaxHealth(120);
        player.getHealth().heal(player.getHealth().getMaxHealth());
        System.out.println(player.getHealth());
        player.getHealth().heal(1000);
        System.out.println(player.getHealth());
        player.getHealth().hurt(1000);
        System.out.println(player.getHealth());
        System.out.println(player.getHealth().isDead());
        try {
            player.getHealth().heal(-1);
        } catch(final IllegalStateException e) {
            System.out.println("errore preso");
        }
        try {
            player.getHealth().hurt(-1);
        } catch(final IllegalStateException e) {
            System.out.println("errore preso");
        }
        
        //-----------RESPAWN--------------
        double i = 0;
        while (player.getLives() > 0) {
            player.respawn(new Vector(i, 0));
            i++;
            System.out.println(player.getPosition());
        }
        try {
            player.respawn(new Vector(5,5));
        } catch (final IllegalStateException e) {
            System.out.println("non respawnato");
        }
    }
}
