package view.character;

import util.view.Animation;
import util.Pair;

/**
 * The player's animation when standing still and holding a Rifle.
 */
public final class CharacterSprites {
    /**
     * The player's animation when standing still and holding a Rifle.
     */
    public static final Animation PLAYERIDLERIFLE = new Animation("player/idle/rifle/PlayerIdleRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when standing still, looking up and holding a Rifle.
     */
    public static final Animation PLAYERIDLEUPRIFLE = new Animation("player/idle/rifle/PlayerIdleUpRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running and holding a Rifle.
     */
    public static final Animation PLAYERRUNRIFLE = new Animation("player/run/rifle/PlayerRunRifle.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when running, looking up and holding a Rifle.
     */
    public static final Animation PLAYERRUNUPRIFLE = new Animation("player/run/rifle/PlayerAimUpRunRifle.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when standing still, crouching and holding a Rifle.
     */
    public static final Animation PLAYERCROUCHIDLERIFLE = new Animation("player/idle/rifle/PlayerCrouchIdleRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running, crouching and holding a Rifle.
     */
    public static final Animation PLAYERCROUCHRUNRIFLE = new Animation("player/run/rifle/PlayerCrouchRunRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running, looking down and holding a Rifle.
     */
    public static final Animation PLAYERRUNDOWNRIFLE = new Animation("player/run/rifle/PlayerRunDownRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);

    /**
     * The player's animations when standing still and holding a Shotgun.
     */
    public static final Animation PLAYERIDLESHOTGUN = new Animation("player/idle/shotgun/PlayerIdleShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when standing still, looking up and holding a Shotgun.
     */
    public static final Animation PLAYERIDLEUPSHOTGUN = new Animation("player/idle/shotgun/PlayerIdleUpShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running and holding a Shotgun.
     */
    public static final Animation PLAYERRUNSHOTGUN = new Animation("player/run/shotgun/PlayerRunShotgun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when running, looking up and holding a Shotgun.
     */
    public static final Animation PLAYERRUNUPSHOTGUN = new Animation("player/run/shotgun/PlayerAimUpRunShotgun.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when standing still, crouching and holding a Shotgun.
     */
    public static final Animation PLAYERCROUCHIDLESHOTGUN = new Animation("player/idle/shotgun/PlayerCrouchIdleShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running, crouching and holding a Shotgun.
     */
    public static final Animation PLAYERCROUCHRUNSHOTGUN = new Animation("player/run/shotgun/PlayerCrouchRunShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running, looking down and holding a Shotgun.
     */
    public static final Animation PLAYERRUNDOWNSHOTGUN = new Animation("player/run/shotgun/PlayerRunDownShotgun.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when standing still and holding a Sniper.
     */
    public static final Animation PLAYERIDLESNIPER = new Animation("player/idle/sniper/PlayerIdleSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when standing still, looking up and holding a Sniper.
     */
    public static final Animation PLAYERIDLEUPSNIPER = new Animation("player/idle/sniper/PlayerIdleUpSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running and holding a Sniper.
     */
    public static final Animation PLAYERRUNSNIPER = new Animation("player/run/sniper/PlayerRunSniper.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when running, looking up and holding a Sniper.
     */
    public static final Animation PLAYERRUNUPSNIPER = new Animation("player/run/sniper/PlayerAimUpRunSniper.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The player's animation when standing still, crouching and holding a Sniper.
     */
    public static final Animation PLAYERCROUCHIDLESNIPER = new Animation("player/idle/sniper/PlayerCrouchIdleSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The player's animation when running, crouching and holding a Sniper.
     */
    public static final Animation PLAYERCROUCHRUNSNIPER = new Animation("player/run/sniper/PlayerCrouchRunSniper.png", new Pair<Integer, Integer>(128, 128), 3, 64);

    /**
     * The enemy's animation when standing still.
     */
    public static final Animation ENEMYIDLE = new Animation("player/idle/rifle/PlayerIdleRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The enemy's animation when standing still and looking up.
     */
    public static final Animation ENEMYIDLEUP = new Animation("player/idle/rifle/PlayerIdleUpRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The enemy's animation when running.
     */
    public static final Animation ENEMYRUN = new Animation("player/run/rifle/PlayerRunRifle.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The enemy's animation when running and looking up.
     */
    public static final Animation ENEMYRUNUP = new Animation("player/run/rifle/PlayerAimUpRunRifle.png", new Pair<Integer, Integer>(128, 128), 7, 32);
    /**
     * The enemy's animation when standing still and crouching.
     */
    public static final Animation ENEMYCROUCHIDLE = new Animation("player/idle/rifle/PlayerCrouchIdleRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The enemy's animation when running and crouching.
     */
    public static final Animation ENEMYCROUCHRUN = new Animation("player/run/rifle/PlayerCrouchRunRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);
    /**
     * The enemy's animation when running and looking down.
     */
    public static final Animation ENEMYRUNDOWN = new Animation("player/run/rifle/PlayerRunDownRifle.png", new Pair<Integer, Integer>(128, 128), 3, 64);

    private CharacterSprites() { }

}
