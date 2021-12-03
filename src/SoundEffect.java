import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    private Clip clip; // clips can be loaded before playback allowing for playback from any position in clip

    public SoundEffect(String filename) {
        try {
            AudioInputStream sfxStream = AudioSystem.getAudioInputStream(new File(filename));
            clip = AudioSystem.getClip(); // returns clip object
            DataLine.Info info =
                    new DataLine.Info(Clip.class, clip.getFormat()); // specify what kind of line we want to create
            clip = (Clip)AudioSystem.getLine(info); // create the line
            clip.open(sfxStream); // load samples from stream
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
        clip.stop();
        clip.setFramePosition(0);
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
