import java.awt.*;

public class Animation {

    Image[] image;

    int current = 0;

    final int STILL = 0;

    int delay = 10;

    public Animation(String name, int count, String fileType) {
        image = new Image[count];

        for(int i = 0; i < count; i++) {
            image[i] = Toolkit.getDefaultToolkit().getImage("./images/" + name + i + "." + fileType);
        }
    }

    public void draw(Graphics gfx) {
        gfx.drawImage(getCurrentImage(), 200, 200,null);
    }

    public Image getCurrentImage() {
        if(MySimpleGame.speed > 1) {
            if(delay == 0) {
                current++;
                if(current == image.length) {
                    current = 0;
                }
                delay = 6;
            }

            delay--;

            return image[current];
        }
        else {
            if(delay == 0) {
                current++;
                if(current == image.length) {
                    current = 0;
                }
                delay = 10;
            }

            delay--;

            return image[current];
        }
    }

    public Image deathAnimation() {
        if(delay == 0) {
            current++;

            if(current == image.length) {
                current = 9;
            }
            delay = 10;
        }
        delay--;

        return image[current];
    }

    public Image getStillImage() {
        return image[STILL];
    }
}
