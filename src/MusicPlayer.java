import sun.nio.ch.ThreadPool;

import javax.sound.sampled.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/*
Developing Games in Java textbook; Listing 4.1 SimpleSoundPlayer.java
 */

/**
 The AudioPlayer encapsulates a sound that can be opened
 from the file system and later played.
 */

/**
 Opens a sound from a file.
 */
public class MusicPlayer {

    public MusicPlayer(String filename) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
            format = stream.getFormat();
            samples = getSamples(stream);
        }
        catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    private AudioFormat format;
    private byte[] samples;

    /**
     Gets the samples from an AudioInputStream as an array
     of bytes.
     */
    private byte[] getSamples(AudioInputStream audioStream) {
        int length = (int)(audioStream.getFrameLength() * format.getFrameSize());

        byte[] samples = new byte[length];
        DataInputStream inputStream = new DataInputStream(audioStream);

        try {
            inputStream.readFully(samples);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return samples;
    }

    /**
     Gets the samples of this sound as a byte array.
     */
    public byte[] getSamples() {
        return samples;
    }

    /**
     Plays a stream. This method blocks (doesn't return) until
     the sound is finished playing.
     */
    public void play(InputStream source) {
        int bufferSize = format.getFrameSize() * Math.round(format.getSampleRate() / 10);
        byte[] buffer = new byte[bufferSize];

        SourceDataLine line;
        try {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine)AudioSystem.getLine(info);
            line.open(format, bufferSize);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }

        line.start();

        try {
            int numBytesRead = 0;
            while(numBytesRead != -1) {
                numBytesRead = source.read(buffer, 0, buffer.length);
                if(numBytesRead != -1) {
                    line.write(buffer, 0, numBytesRead);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        line.drain();

        line.close();
    }

}
