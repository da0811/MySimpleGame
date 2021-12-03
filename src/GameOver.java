import java.awt.*;

public class GameOver {
    int posX;
    int posY;

    public GameOver(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void draw(Graphics gfx) {
        if(Sprite.isFinished) {
            Font font = new Font("Times New Roman", Font.BOLD, 82);
            gfx.setFont(font);
            FontMetrics fontMetrics = gfx.getFontMetrics(font);
            int lineHeight = fontMetrics.getHeight();
            gfx.setColor(Color.BLACK);
            gfx.drawString("Press ENTER to Play Again", posX, posY+lineHeight);
            gfx.drawString("Press BACKSPACE to go to Main Menu", posX, posY+lineHeight*2);
            gfx.drawString("Press Q to Quit", posX, posY+lineHeight*3);
        }
    }
}
