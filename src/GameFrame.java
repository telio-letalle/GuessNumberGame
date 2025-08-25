import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Devine le nombre !");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        add(new GamePanel());

        setVisible(true);
    }
}
