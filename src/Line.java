import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Line extends Line2D {
    double Ax;
    double Ay;

    double Bx;
    double By;

    double Ux;
    double Uy;

    double Nx;
    double Ny;
    String name;
    Color color;

    public Line(double x0, double y0, double x1, double y1, String name, Color color)
    {
        Ax = x0;
        Ay = y0;

        Bx = x1;
        By = y1;


        double vx = Ax - Bx;
        double vy = Ay - By;

        double mag_v = Math.sqrt(vx*vx + vy*vy);

        Ux = vx / mag_v;
        Uy = vy / mag_v;

        Nx =  Uy;
        Ny = -Ux;

        this.name = name;
        this.color = color;
    }

    public double distanceTo(double Px, double Py)
    {
        return (Px - Bx) * Uy - (Py - By) * Ux;
    }

    public void draw(Graphics pen) {
        pen.setColor(color);
        pen.drawLine((int)Ax, (int)Ay, (int)Bx, (int)By);
    }

    @Override
    public double getX1() {
        return Ax;
    }

    @Override
    public double getY1() {
        return Ay;
    }

    @Override
    public Point2D getP1() {
        return null;
    }

    @Override
    public double getX2() {
        return Bx;
    }

    @Override
    public double getY2() {
        return By;
    }

    @Override
    public Point2D getP2() {
        return null;
    }

    @Override
    public void setLine(double x1, double y1, double x2, double y2) {

    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }
}
