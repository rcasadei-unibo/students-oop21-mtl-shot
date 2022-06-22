package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import view.sounds.SoundManager;
import view.sounds.SoundManager.Sounds;

/**
 * It handles sounds putting a cllodown
 * between two sounds of the same type.
 *
 */
public class SoundsController {
	private Map<Sounds, Cooldown> timers;
	private SoundManager soundManager;
	private Random rnd;
	private final int hurtAndDieCooldownTicks = 50;

	public SoundsController() {
		this.timers = new HashMap<>();
		this.soundManager = new SoundManager();
		this.rnd = new Random();
	}
	
	/**
	 * This function is called by Controller every game tick.
	 */
	public void controllerTick() {
		this.timers.forEach((c, sc) -> { sc.tick(); });
		this.timers.entrySet().removeIf(e -> e.getValue().isCooldownOver());
	}
	
	private boolean playRandomSound(final Sounds sound1, final Sounds sound2, final Sounds sound3, final Sounds sound4, final boolean force) {
		if (!this.timers.containsKey(sound1) &&
				!this.timers.containsKey(sound2) &&
				!this.timers.containsKey(sound3) &&
				!this.timers.containsKey(sound4)) {
			int n = this.rnd.nextInt(4);
			switch (n) {
			case 0:
				if (!force) {
					this.timers.put(sound1, new Cooldown(hurtAndDieCooldownTicks));
				}
				this.soundManager.playSound(sound1);
				break;
			case 1:
				if (!force) {
					this.timers.put(sound2, new Cooldown(hurtAndDieCooldownTicks));
				}
				this.soundManager.playSound(sound2);
				break;
			case 2:
				if (!force) {
					this.timers.put(sound3, new Cooldown(hurtAndDieCooldownTicks));
				}
				this.soundManager.playSound(sound3);
				break;
			case 3:
				if (!force) {
					this.timers.put(sound4, new Cooldown(hurtAndDieCooldownTicks));
				}
				this.soundManager.playSound(sound4);
				break;
			default:
				break;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Plays a sound only if the cooldown corresponding to
	 * its soundType is over.
	 * @param soundType: the type of the sound to be played.
	 * @return true if the sound has been played, otherwise false.
	 */
	public boolean playSound(final Sounds soundType) {
		if (soundType.equals(Sounds.RIFLE_FIRING)) {
			this.forcePlaySound(soundType);
		} else if (soundType.equals(Sounds.RELOAD)) {
			this.forcePlaySound(soundType);
		} else if (soundType.equals(Sounds.HURT_1) ||
				soundType.equals(Sounds.HURT_2) ||
				soundType.equals(Sounds.HURT_3) ||
				soundType.equals(Sounds.HURT_4)) {
			if (this.playRandomSound(Sounds.HURT_1, Sounds.HURT_2, Sounds.HURT_3, Sounds.HURT_4, false)) {
				return true;
			}
		} else if (soundType.equals(Sounds.JUMP_1) ||
				soundType.equals(Sounds.JUMP_2) ||
				soundType.equals(Sounds.JUMP_3) ||
				soundType.equals(Sounds.JUMP_4)) {
			if (this.playRandomSound(Sounds.JUMP_1, Sounds.JUMP_2, Sounds.JUMP_3, Sounds.JUMP_4, false)) {
				return true;
			}
		} else if (soundType.equals(Sounds.DIE_1) ||
				soundType.equals(Sounds.DIE_2) ||
				soundType.equals(Sounds.DIE_3) ||
				soundType.equals(Sounds.DIE_4)) {
			if (this.playRandomSound(Sounds.DIE_1, Sounds.DIE_2, Sounds.DIE_3, Sounds.DIE_4, true)) {
				return true;
			}
		} else if (!this.timers.containsKey(soundType)) {
			/* Se un suono di tipo soundType non è stato fatto ancora partire, lo fa partire TODO: translate comment */
			this.timers.put(soundType, new Cooldown(10));
			this.soundManager.playSound(soundType);
			return true;
		}
		return false;
	}
	
	public void forcePlaySound(final Sounds soundType) {
		this.soundManager.playSound(soundType);
	}
	
	public void stopSound(final Sounds soundType) {
		this.soundManager.stopSound(soundType);
	}
}
