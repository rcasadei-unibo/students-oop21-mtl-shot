package view.sounds;

import javafx.scene.media.AudioClip;

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
	
	/**
	 * Plays a sound.
	 * @param sound to be played.
	 */
	public void playSound(final Sounds sound) {
//		sound.getAudioClip().play();
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
		RIFLE_FIRING(new AudioClip(ClassLoader.getSystemResource("sounds/rifle.mp3").toExternalForm())),
		
		METAL_SHOT_HAHA(new AudioClip(ClassLoader.getSystemResource("sounds/metalshotHaha.mp3").toExternalForm())),
		
		MAIN_THEME(new AudioClip(ClassLoader.getSystemResource("sounds/mainTheme.mp3").toExternalForm())),
		
		RELOAD(new AudioClip(ClassLoader.getSystemResource("sounds/reload.mp3").toExternalForm())),
		
		HURT_1(new AudioClip(ClassLoader.getSystemResource("sounds/hurt1.mp3").toExternalForm())),
		HURT_2(new AudioClip(ClassLoader.getSystemResource("sounds/hurt2.mp3").toExternalForm())),
		HURT_3(new AudioClip(ClassLoader.getSystemResource("sounds/hurt3.mp3").toExternalForm())),
		HURT_4(new AudioClip(ClassLoader.getSystemResource("sounds/hurt4.mp3").toExternalForm())),
		
		JUMP_1(new AudioClip(ClassLoader.getSystemResource("sounds/jump1.mp3").toExternalForm())),
		JUMP_2(new AudioClip(ClassLoader.getSystemResource("sounds/jump2.mp3").toExternalForm())),
		JUMP_3(new AudioClip(ClassLoader.getSystemResource("sounds/jump3.mp3").toExternalForm())),
		JUMP_4(new AudioClip(ClassLoader.getSystemResource("sounds/jump4.mp3").toExternalForm())),
		
		DIE_1(new AudioClip(ClassLoader.getSystemResource("sounds/die1.mp3").toExternalForm())),
		DIE_2(new AudioClip(ClassLoader.getSystemResource("sounds/die2.mp3").toExternalForm())),
		DIE_3(new AudioClip(ClassLoader.getSystemResource("sounds/die3.mp3").toExternalForm())),
		DIE_4(new AudioClip(ClassLoader.getSystemResource("sounds/die4.mp3").toExternalForm()));
		
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
