import java.awt.*;

public class StaminaBar extends Rectangle {
    int x;
    int y;

    Sprite mySprite;
    int currStamina;

    public StaminaBar(int x, int y, Sprite mySprite){
        //super();
        //this.currStamina = stamina;
        this.x = x;
        this.y = y;
        this.mySprite = mySprite;
        this.currStamina = mySprite.stamina;
    }

    public void draw(Graphics pen){
        pen.drawRect( (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 400, 20, 150, 15);
        pen.fillRect((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 400, 20, 150 - (100 - currStamina), 15);
    }
}
