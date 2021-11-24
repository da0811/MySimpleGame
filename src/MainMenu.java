import java.awt.*;

public class MainMenu {
    int posX;
    int posY;

    public MainMenu(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void draw(Graphics gfx) {
        if(Sprite.isPlaying == false) {
            Font font = new Font("Times New Roman", Font.BOLD, 42);
            gfx.setFont(font);
            FontMetrics fontMetrics = gfx.getFontMetrics(font);
            int lineHeight = fontMetrics.getHeight();
            gfx.setColor(Color.BLACK);
            gfx.drawString("Press ENTER to Play New Game", posX, posY+lineHeight);
            gfx.drawString("Press Q to Quit", posX, posY+lineHeight*2);
        }
    }
}
