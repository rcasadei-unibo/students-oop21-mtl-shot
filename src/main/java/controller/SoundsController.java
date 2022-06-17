package controller;

import java.util.HashMap;
import java.util.Map;

import view.sounds.SoundManager;
import view.sounds.SoundManager.Sounds;

public class SoundsController {
	private Map<Sounds, Cooldown> timers;
	private SoundManager soundManager;

	public SoundsController() {
		this.timers = new HashMap<>();
		this.soundManager = new SoundManager();
	}

	public void controllerTick() {
		this.timers.forEach((c, sc) -> { sc.tick(); });
		this.timers.entrySet().removeIf(e -> e.getValue().isCooldownOver());
	}
	
	/* Example sound playing: this.soundsController.tryToPlaySound(Sounds.BULLET_FIRING) */
	
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
