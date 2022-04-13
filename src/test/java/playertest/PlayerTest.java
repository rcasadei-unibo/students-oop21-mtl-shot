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
		System.out.println("tutto ok");
	}
	
    @Test 
    public void movementTest() {
        
    }
}
