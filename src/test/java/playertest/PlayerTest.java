package playertest;

import org.junit.jupiter.api.Test;

import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
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
        final Player player = new PlayerBuilder()
                .health(new SimpleHealth())
                .hitbox(new Vector(1, 2))
                .lives(3)
                .position(new Vector(0,0))
                .build();
        
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
    }
}
