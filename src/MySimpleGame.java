import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MySimpleGame extends GamePanel {

    public static int speed = 1;
    public static int score = 0;

//    static MusicPlayer bgm = new MusicPlayer("./sounds/Forest_Ambience.wav");
//    static InputStream bgmStream = new LoopingByteInputStream((bgm.getSamples()));
//
//    static MusicPlayer sparseClapping = new MusicPlayer("./sounds/sparse_clapping.wav");
//    static InputStream sparseClappingStream = new ByteArrayInputStream(sparseClapping.getSamples());
//
//    static MusicPlayer applause = new MusicPlayer("./sounds/applause.wav");
//    static InputStream applauseStream = new ByteArrayInputStream(applause.getSamples());
//
//    static MusicPlayer laughter = new MusicPlayer("./sounds/laughter.wav");
//    static InputStream laughterStream = new ByteArrayInputStream(laughter.getSamples());

    static SoundEffect bgm = new SoundEffect("./sounds/Forest_Ambience.wav");
    static SoundEffect sparseClapping = new SoundEffect("./sounds/sparse_clapping.wav");
    static SoundEffect applause = new SoundEffect("./sounds/applause.wav");
    static SoundEffect laughter = new SoundEffect("./sounds/laughter.wav");
    static SoundEffect shoot = new SoundEffect("./sounds/shoot_arrow.wav");
    static SoundEffect arrowImpact = new SoundEffect("./sounds/arrow_impact.wav");

    Image mainMenuBackground = Toolkit.getDefaultToolkit().getImage("./images/menu_bg.png");
    Image grassLand = Toolkit.getDefaultToolkit().getImage("./images/grass_template_1.JPG");
    Image pauseBackground = Toolkit.getDefaultToolkit().getImage("./images/pause_bg.png");
    Image finalScoreBackground = Toolkit.getDefaultToolkit().getImage("./images/game_over_bg.png");
    Image cabin = Toolkit.getDefaultToolkit().getImage("./images/woodcutter_cabin.PNG");
    Image archery = Toolkit.getDefaultToolkit().getImage("./images/archery.png");
    BufferedImage pond;
    BufferedImage target;
    BufferedImage crowd;

    Rect ground;

    Scoreboard scoreboardText;
    PauseMenu pauseMenuText;
    MainMenu mainMenuText;
    GameOver gameOverText;

    Sprite Sprite = new Sprite(100, 100, 100, 100,"rg", Ranger.pose, 10, "PNG" );
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
    static int nextArrow = 0;

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

        scoreboardText = new Scoreboard(880, 100, score);
        pauseMenuText = new PauseMenu(640, 400);
        mainMenuText = new MainMenu(320, 400);
        gameOverText = new GameOver(320, 400);


    }



    public void paint(Graphics gfx) {
        tick++;
        if(tick >= 60) { //creates a delay of 1 second to allow everything to load before it might be drawn.
            if(Sprite.isPlaying) {
                gfx.setColor(new Color(100, 100, 100));
                gfx.fillRect(0, 0, 1920, 1052);
                gfx.setColor(Color.red);
                gfx.drawImage(grassLand, 0, 0, 1920, 1052, null);
                gfx.drawImage(cabin, cabinX, cabinY, cabinWidth, cabinHeight, null);
                gfx.drawImage(target, targetX, targetY, targetWidth, targetHeight, null);
                gfx.drawImage(pond, 200, 600, pondWidth, pondHeight, null);
                gfx.drawImage(crowd, 1000, -100, crowdWidth, crowdHeight, null);
                Sprite.draw(gfx);
                scoreboardText.draw(gfx);
//            finalScore.draw(gfx);
            }
            if(Sprite.isPaused) {
                gfx.drawImage(pauseBackground, 0, 0, 1920, 1080, null);
                pauseMenuText.draw(gfx);
                scoreboardText.draw(gfx);
            }

            if(!Sprite.isPlaying) {
                gfx.drawImage(mainMenuBackground, 0, 0, 1920, 1080, null);
                gfx.drawImage(archery, 1100, 350,  300, 300, null);
                mainMenuText.draw(gfx);
            }

            if(nextArrow == arrows.length) {
                if(arrows[arrows.length-1].velocityX == 0 && arrows[arrows.length-1].velocityY == 0) {
                    Sprite.isFinished = true; // come back to this later
                    gfx.drawImage(finalScoreBackground, 0,0,1920, 1080, null);
                    gameOverText.draw(gfx);
                    scoreboardText.draw(gfx);
                }
            }


            //axle.draw(gfx);
            //arrows[0].draw(gfx);
            if(!Sprite.isPaused && Sprite.isPlaying &&!Sprite.isFinished) {
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
            if(pressing[ENTER]) {
                pressing[ENTER] = false;
                MySimpleGame.laughter.reset();
                applause.reset();
                sparseClapping.reset();
                Sprite.isPlaying = true;
                for (int i = 0; i < arrows.length; i++) {
                    arrows[i] = new Arrow(-1000, 400 + (i * 10), 0);
                }
            }
            if(pressing[Q]){
                System.exit(0);
            }
        }
        // !Sprite.isPaused && Sprite.isPlaying &&!Sprite.isFinished
        if(Sprite.isAlive && Sprite.isPlaying && !Sprite.isPaused && !Sprite.isFinished) {
            if(pressing[UP] || pressing[W]) Sprite.moveUp(speed);
            if(pressing[DN] || pressing[S]) Sprite.moveDn(speed);
            if(pressing[LT] || pressing[A]) Sprite.moveLt(speed);
            if(pressing[RT] || pressing[D]) Sprite.moveRt(speed);
            if(pressing[SHIFT]) speed = 2;
            else                speed = 1;
            if(pressing[F]){
                shoot.play();
//                Sprite.motion = 3;
                if(nextArrow < arrows.length) {
                    checkNextArrow = true;
                    Sprite.drawBowRight();
//                if(nextArrow == arrows.length){
//                    nextArrow = 0;
//                }
                    arrows[nextArrow].fire(Sprite.px, Sprite.py, 0, (PowerMeter.speed / 2));
                    pressing[F] = false;
                    if(nextArrow <= arrows.length) {
                        nextArrow++;
                    }
                }

            }
        }
        if(Sprite.isPaused) {
            if(pressing[ENTER])     Sprite.isPaused = false;
            if(pressing[BACKSPACE]) {
                Sprite.isPlaying = false;
                Scoreboard.score = 0;
                nextArrow = 0;
            }
            if(pressing[Q]) System.exit(0);
        }
        if(Sprite.isFinished) {
            if (pressing[BACKSPACE]) {
                Sprite.isFinished = false;
                Sprite.isPlaying = false;
                Scoreboard.score = 0;
                nextArrow = 0;
                for (int i = 0; i < arrows.length; i++) {
                    arrows[i] = new Arrow(-1000, 400 + (i * 10), 0);
                }
            }
            if(pressing[Q]) System.exit(0);
        }

        if(pressing[ESC] && !Sprite.isFinished)   {
            pressing[ESC] = false;
            Sprite.isPaused = !Sprite.isPaused;
        }
        if(pressing[SPACE]) Sprite.isAlive = false;
        if(pressing[COMMA]) Sprite.revive();
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
                            arrowImpact.play();
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
                            arrowImpact.play();
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