import java.awt.*;

public class Sprite extends Rect {
    int x;
    int y;

    int w = 75; // make dynamic
    int h = 75; // "

    Animation[] animation;

    final int UP    = 0;
    final int DOWN  = 1;
    final int LEFT  = 2;
    final int RIGHT = 3;

    boolean isMoving  = false;

    int motion = RIGHT;

    boolean isAlive = true;

    // FIX ME: This method is taking in width & height but at this time the values are not reflecting upon construction of sprite
    public Sprite(int x, int y, int w, int h, String name, String[] pose, int count, String fileType) {
        super(x, y, w, h, null);
        this.x = x;
        this.y = y;

//        this.w = w;
//        this.h = h;

        animation = new Animation[pose.length];

        // FIX ME: when walking, ranger sprite seems to only animate number 2 of 10 frames when walking
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

    public void draw(Graphics gfx) {
        if(isAlive) {
            if(isMoving) {
                gfx.drawImage(animation[motion].getCurrentImage(), (int)px, (int)py, w, h, null);
            }
            else {
                gfx.drawImage(animation[motion].getStillImage(), (int)px, (int)py, w, h, null);
            }
            isMoving = false;
        }
//        gfx.drawImage(animation[motion].getCurrentImage(), x, y, 100, 250, null);
    }
}
