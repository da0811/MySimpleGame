import java.awt.*;
import java.awt.image.BufferedImage;

public class Arrow extends Circle {
    double velocityX = 0;
    double velocityY = 0;

    Image image;


    public Arrow(double x, double y, int angle, String fileName)
    {
        super(x, y, 2, 0);
        this.image = Toolkit.getDefaultToolkit().getImage("./images/" + fileName);

    }

    public void fire(double x, double y, int angle, int speed)
    {
        this.x = x;
        this.y = y;

        this.angle = angle;

        Ux = Lookup.cos[angle];
        Uy = Lookup.sin[angle];

        this.velocityX = speed * Ux;
        this.velocityY = speed * Uy;
    }


    public void move()
    {
        x += velocityX;
        y += velocityY;
    }

    public void draw(Graphics pen){

        //pen.drawImage(image, (int)x, (int)y, null);
        pen.drawImage(image, (int) x, (int) y + 20, (image.getWidth(null) / 4), (image.getHeight(null) / 4), null);
    }

}
