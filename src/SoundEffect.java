import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    private Clip clip; // clips can be loaded before playback allowing for playback from any position in clip

    public SoundEffect(String filename) {
        try {
            AudioInputStream sfxStream = AudioSystem.getAudioInputStream(new File(filename));
            clip = AudioSystem.getClip(); // AudioSystem allows us to load sound using getClip
            DataLine.Info info = new DataLine.Info(Clip.class, clip.getFormat());
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(sfxStream);
            if(filename.contains("Forest_Ambience")) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void playOnce() {
        clip.start();
    }

    public void reset() {
        clip.setFramePosition(0);
    }
}
