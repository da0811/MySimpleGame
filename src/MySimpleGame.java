import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MySimpleGame extends GamePanel {

    public static int speed = 1;
    public static int score = 0;
    public static int collisionsResolved = 0;

    Image mainMenuBackground = Toolkit.getDefaultToolkit().getImage("./images/menu_bg.png");
    Image grassLand = Toolkit.getDefaultToolkit().getImage("./images/grass_template_2.JPG");
    Image pauseBackground = Toolkit.getDefaultToolkit().getImage("./images/pause_bg.png");
    Image finalScoreBackground = Toolkit.getDefaultToolkit().getImage("./images/game_over_bg.png");
    Image cabin = Toolkit.getDefaultToolkit().getImage("./images/woodcutter_cabin.PNG");
    BufferedImage pond;
    BufferedImage target;
    BufferedImage crowd;

    Rect ground;

    Scoreboard scoreboard;
    PauseMenu pauseMenu;
    MainMenu mainMenu;
    GameOver gameOver;

    Sprite ranger1 = new Sprite(100, 100, 100, 100,"rg", Ranger.pose, 10, "PNG" );
    PowerMeter powerMeter;

    int cabinX = 1024 - 150;
    int cabinY = 576 - 100;
    int cabinWidth = 900;
    int cabinHeight = 506;

    int targetX = 1639;
    int targetY = 192;
    int targetWidth;
    int targetHeight;

    int pondWidth;
    int pondHeight;

    int crowdWidth;
    int crowdHeight;

    int mx = 0;
    int my = 0;

    int nx = 0;
    int ny = 0;

    //Line cabinDoor = new Line(cabinX + (cabinWidth/2) - (cabinWidth / 16),  cabinY + (cabinHeight/2) + (cabinHeight/8), cabinX + (cabinWidth/2) + (cabinWidth/80) , cabinY + (cabinHeight/2) + (cabinHeight/6));
    //Line topCabin = new Line(cabinX, cabinY, cabinX + cabinWidth, cabinY);
    //Line testLine = new Line(cabinX, cabinY, cabinX + cabinWidth/2, cabinY);
    //Line testLine2 = new Line(cabinX + cabinWidth/2, cabinY, cabinX + cabinWidth/2, cabinY + cabinHeight/2);

    Arrow[] arrows = new Arrow[5];
    int nextArrow = 0;

    Line axle;
    Rect[] targets = new Rect[9];


    int tick = 0;
    boolean checkNextArrow = false;

    public void initialize() {
        //mySprite = ranger1;
        for(int i = 0; i < arrows.length; i++){
            arrows[i] = new Arrow(-1000, 400 + (i * 10), 0);
            //System.out.println("arrow" + i);
        }
        try {
            target = ImageIO.read(new File("./images/target_side_view.png"));
            pond = ImageIO.read(new File("./images/pond.png"));
            crowd = ImageIO.read(new File("./images/crowd_people.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        targetWidth = target.getWidth(null)/2;
        targetHeight = target.getHeight(null)/2;

        pondWidth = pond.getWidth(null)/2;
        pondHeight = pond.getWidth(null)/3;

        crowdWidth = crowd.getWidth(null);
        crowdHeight = crowd.getHeight(null);

        axle = new Line(1784, 205, 1749, 342, "Center", Color.RED);

        targets[0] = new Rect(1772, 203, 150, 24, "Upper White", Color.WHITE); //Done
        targets[1] = new Rect(1772, 227, 150, 9, "Upper Black", Color.BLACK); //Done
        targets[2] = new Rect(1772, 236, 150, 11, "Upper Blue", Color.CYAN); //Done
        targets[3] = new Rect(1769, 247, 150, 14, "Upper Red", Color.red); //Done
        targets[4] = new Rect(1768, 261, 150, 22, "Yellow", Color.yellow); //Done
        targets[5] = new Rect(1765, 283, 150, 12, "Lower Red", Color.red); //Done
        targets[6] = new Rect(1763, 295, 150, 12, "Lower Blue", Color.CYAN); //Done
        targets[7] = new Rect(1761, 307, 150, 11, "Lower Black", Color.BLACK); //Done
        targets[8] = new Rect(1760, 318, 150, 24, "Lower White", Color.WHITE);

        ground = new Rect(0, targetY + targetHeight - 10, GameStart.screen.width, 100, Color.WHITE);
        powerMeter = new PowerMeter();

        scoreboard = new Scoreboard(880, 100, score);
        pauseMenu = new PauseMenu(640, 400);
        mainMenu = new MainMenu(640, 400);
        gameOver = new GameOver(320, 400);

    }



    public void paint(Graphics gfx) {
        if(Sprite.isPlaying) {
            gfx.setColor(new Color(100, 100, 100));
            gfx.fillRect(0, 0, 1920, 1052);
            gfx.setColor(Color.red);
            gfx.drawImage(grassLand, 0, 0, 1920, 1052, null);
            gfx.drawImage(cabin, cabinX, cabinY, cabinWidth, cabinHeight, null);
            gfx.drawImage(target, targetX, targetY, targetWidth, targetHeight, null);
            gfx.drawImage(pond, 200, 600, pondWidth, pondHeight, null);
            gfx.drawImage(crowd, 1000, -100, crowdWidth, crowdHeight, null);
            ranger1.draw(gfx);
            scoreboard.draw(gfx);
//            finalScore.draw(gfx);
        }
        if(Sprite.isPaused) {
            gfx.drawImage(pauseBackground, 0, 0, 1920, 1080, null);
            pauseMenu.draw(gfx);
            scoreboard.draw(gfx);
        }

        if(!Sprite.isPlaying) {
            gfx.drawImage(mainMenuBackground, 0, 0, 1920, 1080, null);
            mainMenu.draw(gfx);
        }

        if(nextArrow == arrows.length) {
            if(arrows[arrows.length-1].velocityX == 0 && arrows[arrows.length-1].velocityY == 0) {
                gfx.drawImage(finalScoreBackground, 0,0,1920, 1080, null);
                gameOver.draw(gfx);
                Sprite.isFinished = true;
                scoreboard.draw(gfx);
            }
        }


        //axle.draw(gfx);
        //arrows[0].draw(gfx);
        tick++;
        if(Sprite.isPlaying && !Sprite.isPaused && !Sprite.isFinished) {
            if(tick >= 60) { //creates a delay of 1 second to allow time for both loops to finish execution.
                tick = 60;
//            for (int i = 0; i < targets.length; i++) {
//                targets[i].draw(gfx);
//            }

                for (int i = 0; i < arrows.length; i++) {
                    arrows[i].draw(gfx);
                }
                //ground.draw(gfx);
                powerMeter.draw(gfx);
            }
        }
    }

    @Override
    public void respond_To_User_Keyboard_Input() {
        if(!Sprite.isPlaying) {
            if(pressing[ENTER]) ranger1.isPlaying = true;
            if(pressing[Q]){} // FIXME: close gamed
        }
        if(Sprite.isAlive && !Sprite.isPaused) {
            if(pressing[UP] || pressing[W]) ranger1.moveUp(speed);
            if(pressing[DN] || pressing[S]) ranger1.moveDn(speed);
            if(pressing[LT] || pressing[A]) ranger1.moveLt(speed);
            if(pressing[RT] || pressing[D]) ranger1.moveRt(speed);
            if(pressing[SHIFT]) speed = 2;
            else                speed = 1;
            if(pressing[F]){

                checkNextArrow = true;
                ranger1.drawBowRight();
//                if(nextArrow == arrows.length){
//                    nextArrow = 0;
//                }
                arrows[nextArrow].fire(ranger1.px, ranger1.py, 0, (PowerMeter.speed / 2));
                pressing[F] = false;
                nextArrow++;

            }
        }
        if(ranger1.isPaused) {
            if(pressing[ENTER])     ranger1.isPaused = false;
            if(pressing[BACKSPACE]) {
                ranger1.isPlaying = false;
                Scoreboard.score = 0;
                nextArrow = 0;
                for (int i = 0; i < arrows.length; i++) {
                    arrows[i] = new Arrow(-1000, 400 + (i * 10), 0);
                }

            }
//            if(pressing[Q])         ranger1.isPlaying = false;
        }
        if(ranger1.isFinished) {
            if (pressing[BACKSPACE]) {
                ranger1.isFinished = false;
                ranger1.isPlaying = false;
                Scoreboard.score = 0;
                nextArrow = 0;
                for (int i = 0; i < arrows.length; i++) {
                    arrows[i] = new Arrow(-1000, 400 + (i * 10), 0);
                }
            }
        }

        if(pressing[ESC])   {
            pressing[ESC] = false;
            ranger1.isPaused = !ranger1.isPaused;
        }
        if(pressing[SPACE]) ranger1.isAlive = false;
        if(pressing[COMMA]) ranger1.revive();
        if(mousePressed) {
            //System.out.println("x coordinate: " + mx + ", y coordinate: " + my);
            mousePressed = false;
        }

    }

    @Override
    public void move_Computer_Controlled_Entities() {
        // enemies, fauna
        for (int i = 0; i < arrows.length; i++) {
            arrows[i].move();
        }
        powerMeter.moveSpeed();
    }

    @Override
    public void resolve_Collisions() {
        if(checkNextArrow) {
            for (int i = targets.length - 1; i >= 0; i--) {
                if (nextArrow == 0) {
                    if(!arrows[nextArrow].isOverLapping) {
                        if (targets[i].isOverlapping(arrows[nextArrow].tip)) {
                            arrows[nextArrow].isOverLapping = true;
                            arrows[nextArrow].stop();
                            Sprite.handleScore(targets[i].name);
                        }
                        if(ground.isOverlapping(arrows[nextArrow].tip)) {
                            arrows[nextArrow].isOverLapping = true;
                            arrows[nextArrow].stop();
                        }
                    }
                } else {
                    if(!arrows[nextArrow - 1].isOverLapping){
                        if (targets[i].isOverlapping(arrows[nextArrow - 1].tip)) {
                            arrows[nextArrow - 1].isOverLapping = true;
                            arrows[nextArrow - 1].stop();
                            Sprite.handleScore(targets[i].name);
                        }
                        if(ground.isOverlapping(arrows[nextArrow - 1].tip)) {
                            arrows[nextArrow - 1].isOverLapping = true;
                            arrows[nextArrow - 1].stop();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        mx = event.getX();
        my = event.getY();
        mousePressed = true;
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