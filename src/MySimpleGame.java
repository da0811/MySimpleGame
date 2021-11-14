import java.awt.*;
import java.awt.event.MouseEvent;

public class MySimpleGame extends GamePanel {

    public static int speed = 1;

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();


    Image grassLand = Toolkit.getDefaultToolkit().getImage("./images/grass_template_2.JPG");
    Image cabin = Toolkit.getDefaultToolkit().getImage("./images/woodcutter_cabin.PNG");
    Image target = Toolkit.getDefaultToolkit().getImage("./images/target_side_view.png");


    Sprite ranger1 = new Sprite(100, 100, (int)(screen.width*.5), (int)(screen.height*.5),"rg", Ranger.pose, 10, "PNG" );

    int cabinX = (int)(screen.width / 2);
    int cabinY = (int)(screen.height / 2);
    int cabinWidth = (int)(screen.width / 3);
    int cabinHeight = (int)(screen.height / 3);
    int cabinWidthOffset = cabinX+cabinWidth;

    int mx = 0;
    int my = 0;

    int nx = 0;
    int ny = 0;

    //Line cabinDoor = new Line(cabinX + (cabinWidth/2) - (cabinWidth / 16),  cabinY + (cabinHeight/2) + (cabinHeight/8), cabinX + (cabinWidth/2) + (cabinWidth/80) , cabinY + (cabinHeight/2) + (cabinHeight/6));
    //Line topCabin = new Line(cabinX, cabinY, cabinX + cabinWidth, cabinY);
    //Line testLine = new Line(cabinX, cabinY, cabinX + cabinWidth/2, cabinY);
    //Line testLine2 = new Line(cabinX + cabinWidth/2, cabinY, cabinX + cabinWidth/2, cabinY + cabinHeight/2);

    //Arrow arrow1, arrow2;
    Arrow[] arrows = new Arrow[5];
    int nextArrow = 0;


    public void initialize() {
        //mySprite = ranger1;
        for(int i = 0; i < arrows.length; i++){
            arrows[i] = new Arrow(0, i * 10, 0);
            System.out.println("arrow" + i);
        }

    }
    public void paint(Graphics gfx) {
        gfx.setColor(new Color(100, 100, 100));

        gfx.fillRect(0, 0, screen.width, screen.height);

        gfx.drawImage(grassLand, 0, 0, screen.width, screen.height, null);

        gfx.drawImage(cabin, cabinX, cabinY, cabinWidth, cabinHeight, null);
        gfx.drawImage(target, screen.width - (screen.width / 5), (screen.height / 2) - (screen.height/3), target.getWidth(null)/2, target.getHeight(null)/2, null);

        //gfx.drawImage(target, screen.width - (screen.width/5), )

        ranger1.draw(gfx);



        gfx.setColor(Color.BLUE);
//        topCabin.draw(gfx);
//        gfx.setColor(Color.magenta);
//        testLine.draw(gfx);
//        testLine2.draw(gfx);

        gfx.setColor(Color.red);
        //cabinDoor.draw(gfx);
        for(int i = 0; i < arrows.length; i++) {
            arrows[i].draw(gfx);
        }
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
            if(pressing[F]){
                    //ranger1.shoot(arrows[nextArrow]);

                arrows[nextArrow].fire(ranger1.px, ranger1.py, 0, 10);
                System.out.println("Arrow " + nextArrow + " fired.");

                pressing[F] = false;
                nextArrow++;


                if(nextArrow == arrows.length) {
                    nextArrow = 0;
                }
            }
        }

        if(pressing[SPACE]) ranger1.isAlive = false;
        if(pressing[COMMA]) ranger1.revive();
    }

    @Override
    public void move_Computer_Controlled_Entities() {
        // enemies, fauna
        for (int i = 0; i < arrows.length; i++) {
            arrows[i].move();
        }
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
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent event) {
        mx = event.getX();
        my = event.getY();
    }


}