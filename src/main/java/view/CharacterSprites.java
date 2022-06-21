package view;

import util.view.Animation;
import util.Pair;

public final class CharacterSprites {

	public static final Animation playerIdle = new Animation("PlayerIdle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerIdleUp = new Animation("PlayerIdleUp.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerRun = new Animation("PlayerRun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation playerRunUp = new Animation("PlayerAimUpRun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation playerCrouchIdle = new Animation("PlayerCrouchIdle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerCrouchRun = new Animation("PlayerCrouchIdle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	
	public static final Animation enemyIdle = new Animation("PlayerIdle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation enemyIdleUp = new Animation("PlayerIdleUp.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation enemyRun = new Animation("PlayerRun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation enemyRunUp = new Animation("PlayerAimUpRun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation enemyCrouchIdle = new Animation("PlayerCrouchIdle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation enemyCrouchRun = new Animation("PlayerCrouchIdle.png", new Pair<Integer, Integer>(128, 128), 3, 64);

}
