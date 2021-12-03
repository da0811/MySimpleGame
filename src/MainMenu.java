import java.awt.*;
import java.io.ByteArrayInputStream;

public class MainMenu {
    int posX;
    int posY;

    public MainMenu(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void draw(Graphics gfx) {
        if(Sprite.isPlaying == false) {
            Font titleFont = new Font("Old English Text MT", Font.BOLD, 82);
            gfx.setFont(titleFont);
            gfx.drawString("Archery", posX, posY);
            Font mainMenuFont = new Font("Old English", Font.BOLD, 42);
            gfx.setFont(mainMenuFont);
            FontMetrics fontMetrics = gfx.getFontMetrics(mainMenuFont);
            int lineHeight = fontMetrics.getHeight();
            gfx.setColor(Color.BLACK);
            gfx.drawString("Press ENTER to Play New Game", posX, posY+lineHeight);
            gfx.drawString("Press Q to Quit", posX, posY+lineHeight*2);
            Font authorsFont = new Font("Old English", Font.BOLD, 24);
            gfx.setFont(authorsFont);
            gfx.drawString("created by Nicholas Morales & David Abreu", posX*3, posY+lineHeight*10);
//            MySimpleGame.laughterStream = new ByteArrayInputStream(MySimpleGame.laughter.getSamples());
//            MySimpleGame.applauseStream = new ByteArrayInputStream(MySimpleGame.applause.getSamples());
//            MySimpleGame.sparseClappingStream = new ByteArrayInputStream(MySimpleGame.sparseClapping.getSamples());
        }
    }
}
