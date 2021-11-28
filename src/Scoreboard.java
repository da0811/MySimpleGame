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
            Font font = new Font("Old English Text MT", Font.BOLD, 42);
            gfx.setFont(font);
            gfx.setColor(Color.BLACK);
            gfx.drawString("Score: " + score, posX, posY);
            gfx.drawString("Arrows: " + Math.abs(MySimpleGame.nextArrow - 5), posX-300, posY);
        }
        if(Sprite.isPaused && !Sprite.isFinished) {
            Font font = new Font("Times New Roman", Font.BOLD, 42);
            gfx.setFont(font);
            gfx.setColor(Color.BLACK);
            gfx.drawString("Score: " + score, 960, 360);
        }
        if(Sprite.isFinished && !Sprite.isPaused) {
            Font font = new Font("Old English Text MT", Font.BOLD, 128);
            gfx.setFont(font);
            gfx.setColor(Color.BLACK);
            if(score == 0) {
                MySimpleGame.laughter.play(MySimpleGame.laughterStream);
                gfx.drawString("Better Luck Next Time", 320, 360);
            }
            else {
                if(score < 20) MySimpleGame.sparseClapping.play(MySimpleGame.sparseClappingStream);
                if(score >=20) MySimpleGame.applause.play(MySimpleGame.applauseStream);
                gfx.drawString("Score: " + score, 480, 360);
            }
        }
    }
}
