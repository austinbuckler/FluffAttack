import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * Created by Austin on 2014-07-03.
 */
public enum SoundEffects {

    JUMP("sound_jump"),
    BOSS_DEATH("sound_boss_death"),
    HIT("sound_hit"),
    LOSE("sound_lose"),
    WIN("sound_win"),
    SHOOT("sound_shoot");

    private Clip clip;

    private SoundEffects(String fileName) {
        try {
            URL url = this.getClass().getClassLoader().getResource("assets/" + fileName + ".wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }

    public static void init() {
        values(); // preload all sounds.
    }
}
