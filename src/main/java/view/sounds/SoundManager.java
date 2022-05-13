package view.sounds;

import java.io.File;
import javafx.scene.media.AudioClip;
import java.nio.file.Path;

/**
 * TODO: write javadoc
 *
 */
public class SoundManager {
	public SoundManager() {
		
	}
	
	public void playSound(final Sounds sound) {
		sound.getAudioClip().play();
	}
	
	/**
	 * TODO: write javadoc
	 *
	 */
	public enum Sounds {
		BULLET_FIRING(new AudioClip(Path.of("src" + File.separator + "main" + File.separator + "resources" + File.separator + "sounds" + File.separator + "bullet.wav")
				.toFile()
				.toURI()
				.toString())),
		
		MAIN_THEME(new AudioClip(Path.of("src" + File.separator + "main" + File.separator + "resources" + File.separator + "sounds" + File.separator + "Domenico Bini - Il Vulcano.mp3")
				.toFile()
				.toURI()
				.toString()));
		
		private final AudioClip audioClip;
		
		private Sounds(final AudioClip audioClip) {
			this.audioClip = audioClip;
		}
		
		public AudioClip getAudioClip() {
			return this.audioClip;
		}
	}
}
