package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import controller.weapon.Cooldown;
import view.sounds.SoundManager;
import view.sounds.SoundManager.Sounds;

/**
 * Handles sounds putting a cooldown between two sounds of the same type.
 *
 */
public class SoundsController {
    private Map<Sounds, Cooldown> timers;
    private SoundManager soundManager;
    private Random rnd;
    private final int hurtAndDieCooldownTicks = 50;

    /**
     * Instantiates SoundsController.
     */
    public SoundsController() {
        this.timers = new HashMap<>();
        this.soundManager = new SoundManager();
        this.rnd = new Random();
    }

    /**
     * This function is called by Controller every game tick.
     */
    public void controllerTick() {
        this.timers.forEach((c, sc) -> {
            sc.tick();
        });
        this.timers.entrySet().removeIf(e -> e.getValue().isCooldownOver());
    }

    /**
     * Chooses a random sound in sounds List, and plays it.
     * 
     * @param sounds - List of sounds from which choose
     * @param force  - Forced play when true
     * @return true if the sound is actually played
     */
    private boolean playRandomSound(final List<Sounds> sounds, final boolean force) {
        List<Sounds> tmp = new ArrayList<>(sounds);
        tmp.removeIf(s -> !this.timers.containsKey(s));

        if (tmp.isEmpty()) {
            int n = this.rnd.nextInt(sounds.size());
            if (!force) {
                this.timers.put(sounds.get(n), new Cooldown(hurtAndDieCooldownTicks));
            }
            this.soundManager.playSound(sounds.get(n));
            return true;
        }
        return false;
    }

    /**
     * Plays a sound only if the cooldown corresponding to its soundType is over.
     * 
     * @param soundType - the type of the sound to be played.
     * @return true if the sound has been played, otherwise false.
     */
    public boolean playSound(final Sounds soundType) {
        if (soundType.equals(Sounds.RIFLE_FIRING)) {
            this.forcePlaySound(soundType);
        } else if (soundType.equals(Sounds.RELOAD)) {
            this.forcePlaySound(soundType);
        } else if (soundType.equals(Sounds.HURT_1) || soundType.equals(Sounds.HURT_2) || soundType.equals(Sounds.HURT_3)
                || soundType.equals(Sounds.HURT_4)) {
            if (this.playRandomSound(List.of(Sounds.HURT_1, Sounds.HURT_2, Sounds.HURT_3, Sounds.HURT_4), false)) {
                return true;
            }
        } else if (soundType.equals(Sounds.JUMP_1) || soundType.equals(Sounds.JUMP_2) || soundType.equals(Sounds.JUMP_3)
                || soundType.equals(Sounds.JUMP_4)) {
            if (this.playRandomSound(List.of(Sounds.JUMP_1, Sounds.JUMP_2, Sounds.JUMP_3, Sounds.JUMP_4), false)) {
                return true;
            }
        } else if (soundType.equals(Sounds.DIE_1) || soundType.equals(Sounds.DIE_2) || soundType.equals(Sounds.DIE_3)
                || soundType.equals(Sounds.DIE_4)) {
            if (this.playRandomSound(List.of(Sounds.DIE_1, Sounds.DIE_2, Sounds.DIE_3, Sounds.DIE_4), true)) {
                return true;
            }
        } else if (!this.timers.containsKey(soundType)) {
            this.timers.put(soundType, new Cooldown(10));
            this.soundManager.playSound(soundType);
            return true;
        }
        return false;
    }

    /**
     * Plays a sound without starting a cooldown timer and without checking for
     * previously started cooldowns, so it is played in any case.
     * 
     * @param soundType - Sound to be played.
     */
    public void forcePlaySound(final Sounds soundType) {
        this.soundManager.playSound(soundType);
    }

    /**
     * Stops a given sound.
     * 
     * @param soundType - Sound to be stopped.
     */
    public void stopSound(final Sounds soundType) {
        this.soundManager.stopSound(soundType);
    }
}
