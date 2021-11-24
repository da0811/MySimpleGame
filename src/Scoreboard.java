import java.awt.*;

public class Scoreboard {
    int posX;
    int posY;
    static int score = 0;

    public Scoreboard(int posX, int posY, int score) {
        this.posX = posX;
        this.posY = posY;
        this.score = score;
    }

    public void draw(Graphics gfx) {
        if(Sprite.isPaused == false) {
            Font font = new Font("Times New Roman", Font.BOLD, 42);
            gfx.setFont(font);
            gfx.setColor(Color.BLACK);
            gfx.drawString("Score: " + score, posX, posY);
        }
        if(Sprite.isPaused == true) {
            Font font = new Font("Times New Roman", Font.BOLD, 42);
            gfx.setFont(font);
            gfx.setColor(Color.BLACK);
            gfx.drawString("Score: " + score, 960, 360);
        }
    }
}
