package view.sounds;

import java.io.File;
import javafx.scene.media.AudioClip;
import java.nio.file.Path;

/**
 * It contains every sound. Each one
 * is loaded the first time it is played
 * It also Contains public enum Sounds,
 * used to identify and play a specific
 * sound. The only callable method is
 * playSound(Sounds).
 *
 */
public class SoundManager {
	public SoundManager() {
		
	}
	
	/**
	 * Plays a sound.
	 * @param sound to be played.
	 */
	public void playSound(final Sounds sound) {
		sound.getAudioClip().play();
	}
	
	/**
	 * Immediately stops a sound.
	 * @param sound to be stopped.
	 */
	public void stopSound(final Sounds sound) {
		sound.getAudioClip().stop();
	}
	
	/**
	 * It contains, identifies and loads every sound.
	 *
	 */
	public enum Sounds {
		RIFLE_FIRING(new AudioClip(Path.of("src" + File.separator + "main" + File.separator + "resources" + File.separator + "sounds" + File.separator + "rifle.mp3")
				.toFile()
				.toURI()
				.toString())),
		
		METAL_SHOT_HAHA(new AudioClip(Path.of("src" + File.separator + "main" + File.separator + "resources" + File.separator + "sounds" + File.separator + "metalshotHaha.mp3")
				.toFile()
				.toURI()
				.toString())),
		
		MAIN_THEME(new AudioClip(Path.of("src" + File.separator + "main" + File.separator + "resources" + File.separator + "sounds" + File.separator + "mainTheme.mp3")
				.toFile()
				.toURI()
				.toString())),
		
		RELOAD(new AudioClip(Path.of("src" + File.separator + "main" + File.separator + "resources" + File.separator + "sounds" + File.separator + "reload.mp3")
				.toFile()
				.toURI()
				.toString()));
		
		private final AudioClip audioClip;
		
		private Sounds(final AudioClip audioClip) {
			this.audioClip = audioClip;
		}
		
		/**
		 * @return the audioClip object associated with "this" sound.
		 */
		public AudioClip getAudioClip() {
			return this.audioClip;
		}
	}
}
