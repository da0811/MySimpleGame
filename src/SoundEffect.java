import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    private Clip clip;

    public SoundEffect(String filename) {
        try {
            AudioInputStream sfxStream = AudioSystem.getAudioInputStream(new File(filename));
            clip = AudioSystem.getClip();
            DataLine.Info info = new DataLine.Info(Clip.class, clip.getFormat());
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(sfxStream);
//            clip.open(sfx);
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }
}
