import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameStart extends JFrame {
    static MySimpleGame gamePanel = new MySimpleGame();

    static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
        new GameStart();

        gamePanel.init();

    }

    public GameStart() {
        setTitle("Game");

        setSize((int)(screen.width),
                (int)(screen.height * .9));

        getContentPane().add(gamePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
}
