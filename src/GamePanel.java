import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Key;
import javax.naming.ldap.Control;
import javax.swing.*;

public abstract class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
    boolean mousePressed = false;

    boolean[] pressing = new boolean[1024];
    boolean[] typed = new boolean[1024];
    boolean[] released = new boolean[1024];

    //String[] pose = {"_up_", "_dn_", "_lt_", "_rt_"};

    //Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();


    // can't figure out how to make sprite bigger/smaller
    //Sprite ranger1 = new Sprite(100, 100, (int)(screen.width*.5), (int)(screen.height*.5), "rg", Ranger.pose, 10, "PNG" );

//    Animation rgRt = new Animation("rg_rt_", 10, "PNG");

    //Sprite mySprite = ranger1;

    public final static int UP = KeyEvent.VK_UP;
    public final static int DN = KeyEvent.VK_DOWN;
    public final static int LT = KeyEvent.VK_LEFT;
    public final static int RT = KeyEvent.VK_RIGHT;

    public final static int W = KeyEvent.VK_W;
    public final static int S = KeyEvent.VK_S;
    public final static int A = KeyEvent.VK_A;
    public final static int D = KeyEvent.VK_D;
    public final static int F = KeyEvent.VK_F;
    public final static int Q = KeyEvent.VK_Q;

    public final static int CTRL   = KeyEvent.VK_CONTROL;
    public final static int SHIFT  = KeyEvent.VK_SHIFT;
    public final static int COMMA  = KeyEvent.VK_COMMA;
    public final static int PERIOD = KeyEvent.VK_PERIOD;

    public final static int ENTER = KeyEvent.VK_ENTER;
    public final static int SPACE = KeyEvent.VK_SPACE;
    public final static int BACKSPACE = KeyEvent.VK_BACK_SPACE;
    public final static int ESC = KeyEvent.VK_ESCAPE;

    public final static int _1 = KeyEvent.VK_1;
    public final static int _2 = KeyEvent.VK_2;
    public final static int _3 = KeyEvent.VK_3;
    public final static int _4 = KeyEvent.VK_4;
    public final static int _5 = KeyEvent.VK_5;
    public final static int _6 = KeyEvent.VK_6;
    public final static int _7 = KeyEvent.VK_7;
    public final static int _8 = KeyEvent.VK_8;
    public final static int _9 = KeyEvent.VK_9;
    public final static int _0 = KeyEvent.VK_0;

    Thread thread1;

    public void init() {
        addKeyListener(this);

        addMouseListener(this);

        addMouseMotionListener(this);

        //setBackground(Color.WHITE);

        initialize();

//        setDoubleBuffered(true); // did not fix flickering sprite

        thread1 = new Thread(this);

        thread1.start();

//        setFocusable(true); // saw in sample code, need to look this up

        requestFocus();

    }

    public abstract void initialize();

    public void run() {

        while(true) {

            respond_To_User_Keyboard_Input();

            move_Computer_Controlled_Entities();

            resolve_Collisions();

            repaint();

            try {
                thread1.sleep(15);
            }
            catch (Exception e) {
                // empty catch
            }
        }
    }

    public abstract void respond_To_User_Keyboard_Input();

    public abstract void move_Computer_Controlled_Entities();

    public abstract void resolve_Collisions();

    public void mousePressed(MouseEvent event) {
//        int mx = event.getX();
//        int my = event.getY();
    }

    public void mouseReleased(MouseEvent event) {
        // FIX ME
    }

    public void mouseMoved(MouseEvent event) {
        // FIX ME
    }

    public void keyPressed(KeyEvent event) {
        pressing[event.getKeyCode()] = true;

    }

    public void keyReleased(KeyEvent event) {
        pressing[event.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent event) {
        // FIXME

    }
}
