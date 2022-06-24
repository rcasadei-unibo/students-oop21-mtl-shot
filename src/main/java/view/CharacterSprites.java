package view;

import util.view.Animation;
import util.Pair;

public final class CharacterSprites {

	public static final Animation playerIdleRifle = new Animation("PlayerIdleRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerIdleUpRifle = new Animation("PlayerIdleUpRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerRunRifle = new Animation("PlayerRunRifle.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation playerRunUpRifle = new Animation("PlayerAimUpRunRifle.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation playerCrouchIdleRifle = new Animation("PlayerCrouchIdleRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerCrouchRunRifle = new Animation("PlayerCrouchIdleRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	/*
	public static final Animation playerIdleShotgun = new Animation("PlayerIdleShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerIdleUpShotgun = new Animation("PlayerIdleUpShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerRunShotgun = new Animation("PlayerRunShotgun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation playerRunUpShotgun = new Animation("PlayerAimUpRunShotgun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation playerCrouchIdleShotgun = new Animation("PlayerCrouchIdleShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerCrouchRunShotgun = new Animation("PlayerCrouchIdleShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	
	public static final Animation playerIdleSniper = new Animation("PlayerIdleSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerIdleUpSniper = new Animation("PlayerIdleUpSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerRunSniper = new Animation("PlayerRunSniper.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation playerRunUpSniper = new Animation("PlayerAimUpRunSniper.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation playerCrouchIdleSniper = new Animation("PlayerCrouchIdleSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation playerCrouchRunSniper = new Animation("PlayerCrouchIdleSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	*/
	public static final Animation enemyIdle = new Animation("PlayerIdle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation enemyIdleUp = new Animation("PlayerIdleUp.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation enemyRun = new Animation("PlayerRun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation enemyRunUp = new Animation("PlayerAimUpRun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
	public static final Animation enemyCrouchIdle = new Animation("PlayerCrouchIdle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
	public static final Animation enemyCrouchRun = new Animation("PlayerCrouchIdle.png", new Pair<Integer, Integer>(128, 128), 3, 64);

}
