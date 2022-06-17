package controller;

import java.util.HashMap;
import java.util.Map;

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

	public SoundsController() {
		this.timers = new HashMap<>();
		this.soundManager = new SoundManager();
	}
	
	/**
	 * This function is called by Controller every game tick.
	 */
	public void controllerTick() {
		this.timers.forEach((c, sc) -> { sc.tick(); });
		this.timers.entrySet().removeIf(e -> e.getValue().isCooldownOver());
	}
	
	/* Example sound playing: this.soundsController.tryToPlaySound(Sounds.BULLET_FIRING) */
	
	/**
	 * Plays a sound only if the cooldown corresponding to
	 * its soundType is over.
	 * @param soundType: the type of the sound to be played.
	 * @return true if the sound has been played, otherwise false.
	 */
	public boolean tryToPlaySound(final Sounds soundType) {
		if (!this.timers.containsKey(soundType)) {
			/* Se un suono di tipo soundType non è stato fatto ancora partire, lo fa partire TODO: translate comment */
			this.timers.put(soundType, new Cooldown(100));	/* Temporal gap between two sounds of the same type (TODO: too high, fix) */
			this.soundManager.playSound(soundType);
			return true;
		}
		return false;
	}
}
