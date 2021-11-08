import java.awt.*;

public class Circle {
    double x;
    double y;

    double radius;

    int angle;

    double Ux;
    double Uy;

    double Nx;
    double Ny;


    Color color;

    public Circle(double x, double y, double radius, int angle, Color color) {
        this.x = x;
        this.y = y;

        this.radius = radius;

        this.angle = angle;

        Ux = Lookup.cos[angle];
        Uy = Lookup.sin[angle];

        Nx = -Uy;
        Ny =  Ux;

        this.color = color;
    }
    public Circle(double x, double y, double radius, int angle) {
        this.x = x;
        this.y = y;

        this.radius = radius;

        this.angle = angle;

        Ux = Lookup.cos[angle];
        Uy = Lookup.sin[angle];

        Nx = -Uy;
        Ny =  Ux;


    }


    public void setColor(Color color) {
        this.color = color;
    }


    public void moveForward(int distance) {
        x += distance * Ux;   	  // p += d *  <Ux, Uy>  = <d*Ux, d*Uy>
        y += distance * Uy;
    }

    public void moveBackward(int distance) {
        x -= distance * Ux;
        y -= distance * Uy;
    }


    public void turnLeft(int deltaAngle) {
        angle -= deltaAngle;

        if(angle < 0)  angle += 360;

        Ux = Lookup.cos[angle];
        Uy = Lookup.sin[angle];

        Nx = -Uy;
        Ny =  Ux;
    }

    public void turnRight(int deltaAngle) {
        angle += deltaAngle;

        if(angle > 359)  angle -= 360;

        Ux = Lookup.cos[angle];
        Uy = Lookup.sin[angle];

        Nx = -Uy;
        Ny =  Ux;
    }


    public void moveUp(int deltaY) {
        y -= deltaY;
    }

    public void moveDown(int deltaY) {
        y += deltaY;
    }

    public void moveLeft(int deltaX) {
        x -= deltaX;
    }

    public void moveRight(int deltaX) {
        x += deltaX;
    }

    public void shoot(Arrow arrow) {

        arrow.fire(x, y, angle, 10);

    }


    public void draw(Graphics pen) {
        pen.setColor(color);

        pen.drawOval((int)(x-radius), (int)(y-radius), (int)(2*radius), (int)(2*radius));

        pen.drawLine((int)x, (int)y, (int)(x + radius * Ux), (int)(y + radius * Uy));
    }
}
