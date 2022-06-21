package view;

import util.view.Animation;
import util.Pair;

public final class CharacterSprites {

/* 	 public static final Image playerCrouch = new Image(ClassLoader.getSystemResourceAsStream("croulpleier.png"));
	 public static final Image playerUp = new Image(ClassLoader.getSystemResourceAsStream("pleierUP.png"));
	 public static final Image playerDown = new Image(ClassLoader.getSystemResourceAsStream("pleierDOWN.png"));
	 public static final Image playerRight = new Image(ClassLoader.getSystemResourceAsStream("pleierLEFT.png"));
	 public static final Image playerLeft = new Image(ClassLoader.getSystemResourceAsStream("pleierRIGHT.png"));
	 
	 public static final Image enemyCrouch = new Image(ClassLoader.getSystemResourceAsStream("croulpleier.png"));
	 public static final Image enemyUp = new Image(ClassLoader.getSystemResourceAsStream("pleierUP.png"));
	 public static final Image enemyDown = new Image(ClassLoader.getSystemResourceAsStream("pleierDOWN.png"));
	 public static final Image enemyRight = new Image(ClassLoader.getSystemResourceAsStream("pleierRIGHT.png"));
	 public static final Image enemyLeft = new Image(ClassLoader.getSystemResourceAsStream("pleierLEFT.png"));
*/
	public static final Animation playerIdle = new Animation("PlayerIdle.png", new Pair<Integer, Integer>(128, 128), 3, 0);
	public static final Animation playerIdleUp = new Animation("PlayerIdleUp.png", new Pair<Integer, Integer>(128, 128), 3, 0);
	public static final Animation playerRun = new Animation("PlayerRun.png", new Pair<Integer, Integer>(128, 128), 7, 0);
	public static final Animation playerRunUp = new Animation("PlayerAimUpRun.png", new Pair<Integer, Integer>(128, 128), 7, 0);
	public static final Animation playerCrouchIdle = new Animation("PlayerCrouchIdle.png", new Pair<Integer, Integer>(128, 128), 3, 0);
	public static final Animation playerCrouchRun = new Animation("PlayerCrouchIdle.png", new Pair<Integer, Integer>(128, 128), 3, 0);
	
	public static final Animation enemyIdle = new Animation("PlayerIdle.png", new Pair<Integer, Integer>(128, 128), 3, 0);
	public static final Animation enemyIdleUp = new Animation("PlayerIdleUp.png", new Pair<Integer, Integer>(128, 128), 3, 0);
	public static final Animation enemyRun = new Animation("PlayerRun.png", new Pair<Integer, Integer>(128, 128), 7, 0);
	public static final Animation enemyRunUp = new Animation("PlayerAimUpRun.png", new Pair<Integer, Integer>(128, 128), 7, 0);
	public static final Animation enemyCrouchIdle = new Animation("PlayerCrouchIdle.png", new Pair<Integer, Integer>(128, 128), 3, 0);
	public static final Animation enemyCrouchRun = new Animation("PlayerCrouchIdle.png", new Pair<Integer, Integer>(128, 128), 3, 0);
	
	
	
}
