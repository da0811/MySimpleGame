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
        if(!Sprite.isPaused && !Sprite.isFinished) {
            Font font = new Font("Times New Roman", Font.BOLD, 42);
            gfx.setFont(font);
            gfx.setColor(Color.BLACK);
            gfx.drawString("Score: " + score, posX, posY);
        }
        if(Sprite.isPaused && !Sprite.isFinished) {
            Font font = new Font("Times New Roman", Font.BOLD, 42);
            gfx.setFont(font);
            gfx.setColor(Color.BLACK);
            gfx.drawString("Score: " + score, 960, 360);
        }
        if(Sprite.isFinished && !Sprite.isPaused) {
            Font font = new Font("Times New Roman", Font.BOLD, 82);
            gfx.setFont(font);
            gfx.setColor(Color.BLACK);
            if(score == 0) {
                gfx.drawString("Better Luck Next Time", 480, 360);
            }
            else {
                gfx.drawString("Score: " + score, 960, 360);
            }
        }
    }
}
