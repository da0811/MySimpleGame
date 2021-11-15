import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Arrow extends Circle {
    double velocityX = 0;
    double velocityY = 0;

    double ax = 0;
    double ay = 0.2;

    BufferedImage image;

    Rect tip; //used to detect collisions between the arrow and target.

    boolean isOverLapping = false;


    public Arrow(double x, double y, int angle)
    {
        super(x, y, 2, angle);
        try {
            this.image = ImageIO.read(new File("./images/arrow.png"));
        } catch (IOException e) {
        }
        //this.image = Toolkit.getDefaultToolkit().getImage("./images/" + fileName);
        tip = new Rect((int)(x + (image.getWidth(null) / 4)), (int)(y + ((image.getHeight(null) / 2) - 4)), 1, 1, Color.RED);

    }

    public void fire(double x, double y, int angle, int speed)
    {
        this.x = x;
        this.y = y;

        tip.px = x + (image.getWidth(null) / 4) - 1;
        tip.py = y + (image.getHeight(null) / 2) - 1;

        this.angle = angle;

        Ux = Lookup.cos[angle];
        Uy = Lookup.sin[angle];

        this.velocityX = speed * Ux;
        this.velocityY = speed * Uy;

        //this.ay = speed * ay;
    }

    public void setVelocity(double vx, double vy) {
        this.velocityX = vx;
        this.velocityY = vy;
    }

    public void setAcceleration(double ax, double ay) {
        this.ax = ax;
        this.ay = ay;
    }

    public void move()
    {
        x += velocityX;
        y += velocityY;

        velocityX += ax;
        velocityY += ay;

        tip.px += velocityX;
        tip.py += velocityY;
    }

    public void stop(){ //used to stop the arrow and tip once a collision is detected

        velocityX = 0;
        velocityY = 0;
        ay = 0;



    }

    public void draw(Graphics pen){

        //pen.drawImage(image, (int)x, (int)y, null);
        pen.drawImage(image, (int) x, (int) y + 20, (image.getWidth(null) / 4), (image.getHeight(null) / 4), null);
        pen.setColor(color);
        //tip.draw(pen);
        //pen.drawRect((image.getWidth(null) / 4), (image.getHeight(null) / 2) - tip.h, tip.w, tip.h);
    }

}
