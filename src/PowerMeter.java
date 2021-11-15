import java.awt.*;

public class PowerMeter extends Rectangle {
    int x;
    int y;
    static int speed = 0;

    public PowerMeter(){
        //super();
        //this.currStamina = stamina;
        this.x = 200;
        this.y = 20;
        //this.currStamina = mySprite.stamina;
    }

    public void draw(Graphics pen){
        pen.setColor(Color.BLACK);
        pen.drawRect(x, y, 200, 15);
        pen.setColor(Color.WHITE);
        pen.fillRect(x + 1, y + 1, (200 - ((100 - speed) *2)), 14);
    }

    public void moveSpeed(){

        if(speed < 100) {
            speed += 3;
        }
        if(speed >= 100) {
            speed = 0;
        }
    }
    public int stopSpeed(){
        speed = speed;
        return speed;
    }
}
