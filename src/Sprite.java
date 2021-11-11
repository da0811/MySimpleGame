import java.awt.*;

public class Sprite extends Rect {
    int x;
    int y;

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    int w = (int)(screen.width*0.05); // made dynamic
    int h = (int)(screen.height*0.08); // "

    Animation[] animation;

    final int UP    = 0;
    final int DOWN  = 1;
    final int LEFT  = 2;
    final int RIGHT = 3;
    final int IDLE_DOWN = 4;
    final int IDLE_LEFT = 5;
    final int IDLE_RIGHT = 6;
    final int DIE_FACING_LEFT = 7;
    final int DIE_FACING_RIGHT = 8;

    boolean isMoving  = false;

    static boolean isIdle = true;

    int motion = RIGHT;

    static boolean isAlive = true;

    int stamina = 100;

    Arrow[] arrows = new Arrow[20];

    public void revive() {
        isAlive = true;
        animation[DIE_FACING_LEFT].current = 0;
        animation[DIE_FACING_RIGHT].current = 0;
    }

    // FIX ME: This method is taking in width & height but at this time the values are not reflecting upon construction of sprite
    public Sprite(int x, int y, int w, int h, String name, String[] pose, int count, String fileType) {
        super(x, y, w, h, null);
        this.x = x;
        this.y = y;

//        this.w = w;
//        this.h = h;

        animation = new Animation[pose.length];


        for(int i = 0; i < pose.length; i++) {
            animation[i] = new Animation(name + "_" + pose[i] + "_", count, fileType);

        }
    }


    public void moveUp(int dy) {
        isMoving = true;

        motion = UP;

        py -= (dy+MySimpleGame.speed);
    }

    public void moveDn(int dy) {
        isMoving = true;

        motion = DOWN;

        py += (dy+MySimpleGame.speed);
    }

    public void moveLt(int dx) {
        isMoving = true;

        motion = LEFT;

        px -= (dx+MySimpleGame.speed);

    }

    public void moveRt(int dx) {
//        vx += dx;
        isMoving = true;

        motion = RIGHT;

        px += (dx+MySimpleGame.speed);
    }

    public void die() {
        isAlive = false;
        py = -100000;
    }

//    public void draw(Graphics gfx) {
//        if(isAlive) {
//            if(isMoving) {
//                gfx.drawImage(animation[motion].getCurrentImage(), (int)px, (int)py, w, h, null);
//            }
//            if(!isMoving && Sprite.isIdle == false){ if(Sprite.isIdle == false) {
//                    gfx.drawImage(animation[motion].getStillImage(), (int)px, (int)py, w, h, null); }if(!isMoving && Sprite.isIdle == true){
//                    motion = IDLE;
//                    gfx.drawImage(animation[IDLE].getCurrentImage(), (int)px, (int)py, w, h, null);
//                }
//            }
//            isMoving = false;
//        }
////        gfx.drawImage(animation[motion].getCurrentImage(), x, y, 100, 250, null);
//    }
    public void draw(Graphics gfx) {
        if(isAlive) {
            if(isMoving) {
                gfx.drawImage(animation[motion].getCurrentImage(), (int)px, (int)py, w, h, null);
            }
            else {
                if(motion == DOWN) {
                    gfx.drawImage(animation[IDLE_DOWN].getCurrentImage(), (int)px, (int)py, w, h, null);
                }
                else if(motion == LEFT) {
                    gfx.drawImage(animation[IDLE_LEFT].getCurrentImage(), (int)px, (int)py, w, h, null);
                }
               else if(motion == RIGHT) {
                    gfx.drawImage(animation[IDLE_RIGHT].getCurrentImage(), (int)px, (int)py, w, h, null);
                }
                else {
                    gfx.drawImage(animation[UP].getStillImage(), (int)px, (int)py, w, h, null);
                }

            }
            isMoving = false;
        }
        else {
            if(motion % 2 == 1) {
                gfx.drawImage(animation[DIE_FACING_RIGHT].deathAnimation(), (int)px, (int)py, w, h, null);
            }
            else {
                gfx.drawImage(animation[DIE_FACING_LEFT].deathAnimation(), (int)px, (int)py, w, h, null);
            }
        }
    //        gfx.drawImage(animation[motion].getCurrentImage(), x, y, 100, 250, null);
    }
    public void shoot(Arrow arrow){
        arrow.fire(px, py, 0, 30);
    }


}