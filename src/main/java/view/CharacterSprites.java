package view;

import util.view.Animation;
import util.Pair;

/**
 * The player's animation when standing still and holding a Rifle.
 */
public final class CharacterSprites {

    /**
     * The player's animation when standing still and holding a Rifle.
     */
    public static final Animation PLAYERIDLERIFLE = new Animation("PlayerIdleRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when standing still, looking up and holding a Rifle.
     */
    public static final Animation PLAYERIDLEUPRIFLE = new Animation("PlayerIdleUpRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running and holding a Rifle.
     */
    public static final Animation PLAYERRUNRIFLE = new Animation("PlayerRunRifle.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when running, looking up and holding a Rifle.
     */
    public static final Animation PLAYERRUNUPRIFLE = new Animation("PlayerAimUpRunRifle.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when standing still, crouching and holding a Rifle.
     */
    public static final Animation PLAYERCROUCHIDLERIFLE = new Animation("PlayerCrouchIdleRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running, crouching and holding a Rifle.
     */
    public static final Animation PLAYERCROUCHRUNRIFLE = new Animation("PlayerCrouchRunRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running, looking down and holding a Rifle.
     */
    public static final Animation PLAYERRUNDOWNRIFLE = new Animation("PlayerRunDownRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);

    /**
     * The player's animations when standing still and holding a Shotgun.
     */
    public static final Animation PLAYERIDLESHOTGUN = new Animation("PlayerIdleShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when standing still, looking up and holding a Shotgun.
     */
    public static final Animation PLAYERIDLEUPSHOTGUN = new Animation("PlayerIdleUpShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running and holding a Shotgun.
     */
    public static final Animation PLAYERRUNSHOTGUN = new Animation("PlayerRunShotgun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when running, looking up and holding a Shotgun.
     */
    public static final Animation PLAYERRUNUPSHOTGUN = new Animation("PlayerAimUpRunShotgun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when standing still, crouching and holding a Shotgun.
     */
    public static final Animation PLAYERCROUCHIDLESHOTGUN = new Animation("PlayerCrouchIdleShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running, crouching and holding a Shotgun.
     */
    public static final Animation PLAYERCROUCHRUNSHOTGUN = new Animation("PlayerCrouchRunShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running, looking down and holding a Shotgun.
     */
    public static final Animation PLAYERRUNDOWNSHOTGUN = new Animation("PlayerRunDownShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when standing still and holding a Sniper.
     */
    public static final Animation PLAYERIDLESNIPER = new Animation("PlayerIdleSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when standing still, looking up and holding a Sniper.
     */
    public static final Animation PLAYERIDLEUPSNIPER = new Animation("PlayerIdleUpSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running and holding a Sniper.
     */
    public static final Animation PLAYERRUNSNIPER = new Animation("PlayerRunSniper.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when running, looking up and holding a Sniper.
     */
    public static final Animation PLAYERRUNUPSNIPER = new Animation("PlayerAimUpRunSniper.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when standing still, crouching and holding a Sniper.
     */
    public static final Animation PLAYERCROUCHIDLESNIPER = new Animation("PlayerCrouchIdleSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running, crouching and holding a Sniper.
     */
    public static final Animation PLAYERCROUCHRUNSNIPER = new Animation("PlayerCrouchRunSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);

    /**
     * The enemy's animation when standing still.
     */
    public static final Animation ENEMYIDLE = new Animation("PlayerIdleRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The enemy's animation when standing still and looking up.
     */
    public static final Animation ENEMYIDLEUP = new Animation("PlayerIdleUpRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The enemy's animation when running.
     */
    public static final Animation ENEMYRUN = new Animation("PlayerRunRifle.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The enemy's animation when running and looking up.
     */
    public static final Animation ENEMYRUNUP = new Animation("PlayerAimUpRunRifle.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The enemy's animation when standing still and crouching.
     */
    public static final Animation ENEMYCROUCHIDLE = new Animation("PlayerCrouchIdleRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The enemy's animation when running and crouching.
     */
    public static final Animation ENEMYCROUCHRUN = new Animation("PlayerCrouchRunRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The enemy's animation when running and looking down.
     */
    public static final Animation ENEMYRUNDOWN = new Animation("PlayerRunDownRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
}
