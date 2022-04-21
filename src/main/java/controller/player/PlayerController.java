package controller.player;

import java.util.LinkedList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import model.map.MapModel;
import model.map.tile.Tile;
import model.map.tile.TileImpl;
import model.map.tile.TileType;
import util.Vector;
import view.player.PlayerView;

public class PlayerController {

	private final Player player;
	private final PlayerView playerView;
	private final MapModel mapModel;

	public PlayerController(final MapModel mapModel, final PlayerView playerView) {
		this.playerView = playerView;
		this.mapModel = mapModel;
		player = new PlayerBuilder()
				.hitbox(new Vector(32, 32))
				.position(mapModel.getPlayerSpawn())
				.health(new SimpleHealth())
				.lives(3)
				.build();
	}

	public void startGame() {
		final Timeline timer = new Timeline(
				new KeyFrame(Duration.seconds(0.01),
						new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent event) {
						final List<Vector> underPlayer = new LinkedList<>();
						for(double i = 0; i < player.getHitbox().getX(); i++) {
							underPlayer.add(new Vector(player.getPosition().getX()+i,
									player.getPosition().getY()+player.getHitbox().getY()+1));
						}
						for(final Vector position : underPlayer) {
							if(mapModel.getTile(position).get().getTileType() == TileType.GROUND) {
								player.setFall(false);
								player.setSpeed(player.getSpeed().getX(), 0);
								player.setPosition(player.getPosition().getX(),
										mapModel.getTile(position).get().getPosition().getY()-player.getHitbox().getY());						
							}
							player.moveEntity();
							playerView.updatePlayer(player.getPosition());
						}
						
					}
				}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}

	public Player getPlayer() {
		return this.player;
	}





}
