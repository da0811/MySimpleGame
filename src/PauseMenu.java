import java.awt.*;

public class PauseMenu {
    int posX;
    int posY;

    public PauseMenu(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void draw(Graphics gfx) {
        if(Sprite.isPaused == true) {
            Font font = new Font("Times New Roman", Font.BOLD, 42);
            gfx.setFont(font);
            FontMetrics fontMetrics = gfx.getFontMetrics(font);
            int lineHeight = fontMetrics.getHeight();
            gfx.setColor(Color.BLACK);
            gfx.drawString("Press ENTER to Resume", posX, posY+lineHeight);
            gfx.drawString("Press BACKSPACE to Go Back to Main Menu", posX, posY+lineHeight*2);
            gfx.drawString("Press Q to Quit", posX, posY+lineHeight*3);
        }
    }
}
