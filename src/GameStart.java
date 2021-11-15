import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameStart extends JFrame {
    static MySimpleGame gamePanel = new MySimpleGame();

    static Dimension screen = new Dimension(1920,1080);

    public static void main(String[] args) {
        new GameStart();

        gamePanel.init();
    }

    public GameStart() {
        setTitle("Game");

        setSize(GameStart.screen.width, GameStart.screen.height);

        getContentPane().add(gamePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        setVisible(true);
    }
}
