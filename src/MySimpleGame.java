import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QPEncoderStream;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MySimpleGame extends GamePanel {

    public static int speed = 1;

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    Image grassLand = Toolkit.getDefaultToolkit().getImage("./images/grass_template_2.JPG");
    Image cabin = Toolkit.getDefaultToolkit().getImage("./images/woodcutter_cabin.PNG");

    Sprite ranger1 = new Sprite(100, 100, (int)(screen.width*.5), (int)(screen.height*.5),"rg", Ranger.pose, 10, "PNG" );

    int cabinX = (int)(screen.width * .5);

    int cabinY = (int)(screen.height * .5);

    int cabinWidth = (int)(screen.width * .33);

    int cabinHeight = (int)(screen.height * .33);

    int cabinWidthOffset = cabinX+cabinWidth;

    int mx = 0;

    int my = 0;

    int nx = 0;

    int ny = 0;

    Line cabinDoor = new Line(cabinX + 300, cabinY + 240, cabinX + 340, cabinY + 255);

    Arrow arrow = new Arrow(-1000, 0, 0,"arrow.png");


    public void initialize() {
        //mySprite = ranger1;

    }

    @Override
    public void respond_To_User_Keyboard_Input() {
        if(Sprite.isAlive) {
            if(pressing[UP] || pressing[W]) ranger1.moveUp(speed);
            if(pressing[DN] || pressing[S]) ranger1.moveDn(speed);
            if(pressing[LT] || pressing[A]) ranger1.moveLt(speed);
            if(pressing[RT] || pressing[D]) ranger1.moveRt(speed);

            if(pressing[SHIFT]) speed = 2;
            else                speed = 1;
            if(pressing[F]) { ranger1.shoot(arrow);


            }

        }

        if(pressing[SPACE]) ranger1.isAlive = false;
        if(pressing[COMMA]) ranger1.revive();
    }

    public void paint(Graphics gfx) {
        gfx.setColor(new Color(100, 100, 100));

        gfx.fillRect(0, 0, screen.width, screen.height);

        gfx.drawImage(grassLand, 0, 0, screen.width, screen.height, null);

        gfx.drawImage(cabin, cabinX, cabinY, cabinWidth, cabinHeight, null);

        ranger1.draw(gfx);
        arrow.draw(gfx);

        gfx.setColor(Color.red);
        cabinDoor.draw(gfx);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent event) {
        mx = event.getX();
        my = event.getY();
    }


    @Override
    public void move_Computer_Controlled_Entities() {
        // enemies, fauna

        arrow.move();

    }

    @Override
    public void resolve_Collisions() {

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        mx = event.getX();
        my = event.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}