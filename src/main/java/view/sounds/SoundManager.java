package view.sounds;

import javafx.scene.media.AudioClip;

/**
 * It contains every sound. Each one is loaded the first time it is played It
 * also Contains public enum Sounds, used to identify and play a specific sound.
 * The only callable method is playSound(Sounds).
 *
 */
public class SoundManager {

    /**
     * Plays a sound.
     * 
     * @param sound to be played.
     */
    public void playSound(final Sounds sound) {
        sound.getAudioClip().play();
    }

    /**
     * Immediately stops a sound.
     * 
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
        /**
         * Rifle firing sound.
         */
        RIFLE_FIRING(new AudioClip(ClassLoader.getSystemResource("sounds/rifle.mp3").toExternalForm())),

        /**
         * Main menu sound.
         */
        METAL_SHOT_HAHA(new AudioClip(ClassLoader.getSystemResource("sounds/metalshotHaha.mp3").toExternalForm())),

        /**
         * Main theme.
         */
        MAIN_THEME(new AudioClip(ClassLoader.getSystemResource("sounds/mainTheme.mp3").toExternalForm())),

        /**
         * Reload sound.
         */
        RELOAD(new AudioClip(ClassLoader.getSystemResource("sounds/reload.mp3").toExternalForm())),

        /**
         * Hurt(1) sound.
         */
        HURT_1(new AudioClip(ClassLoader.getSystemResource("sounds/hurt1.mp3").toExternalForm())),

        /**
         * Hurt(2) sound.
         */
        HURT_2(new AudioClip(ClassLoader.getSystemResource("sounds/hurt2.mp3").toExternalForm())),

        /**
         * Hurt(3) sound.
         */
        HURT_3(new AudioClip(ClassLoader.getSystemResource("sounds/hurt3.mp3").toExternalForm())),

        /**
         * Hurt(4) sound.
         */
        HURT_4(new AudioClip(ClassLoader.getSystemResource("sounds/hurt4.mp3").toExternalForm())),

        /**
         * Jump(1) sound.
         */
        JUMP_1(new AudioClip(ClassLoader.getSystemResource("sounds/jump1.mp3").toExternalForm())),

        /**
         * Jump(2) sound.
         */
        JUMP_2(new AudioClip(ClassLoader.getSystemResource("sounds/jump2.mp3").toExternalForm())),

        /**
         * Jump(3) sound.
         */
        JUMP_3(new AudioClip(ClassLoader.getSystemResource("sounds/jump3.mp3").toExternalForm())),

        /**
         * Jump(4) sound.
         */
        JUMP_4(new AudioClip(ClassLoader.getSystemResource("sounds/jump4.mp3").toExternalForm())),

        /**
         * Death(1) sound.
         */
        DIE_1(new AudioClip(ClassLoader.getSystemResource("sounds/die1.mp3").toExternalForm())),

        /**
         * Death(2) sound.
         */
        DIE_2(new AudioClip(ClassLoader.getSystemResource("sounds/die2.mp3").toExternalForm())),

        /**
         * Death(3) sound.
         */
        DIE_3(new AudioClip(ClassLoader.getSystemResource("sounds/die3.mp3").toExternalForm())),

        /**
         * Death(4) sound.
         */
        DIE_4(new AudioClip(ClassLoader.getSystemResource("sounds/die4.mp3").toExternalForm()));

        private final AudioClip audioClip;

        /**
         * Creates and loads sounds.
         * 
         * @param audioClip - Clip to load
         */
        Sounds(final AudioClip audioClip) {
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
