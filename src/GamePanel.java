import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    // TODO: SETUP VARIABLES
    public final int TILE_SIZE = 16;
    public final int SCREEN_WIDTH = 64 * TILE_SIZE;
    public final int SCREEN_HEIGHT = 36 * TILE_SIZE;

    public final int GROUND_Y = SCREEN_HEIGHT - 200;
    public final int GRAVITY_VEL = 2;

    // TODO: Utilities
    public KeyHandler keyH = new KeyHandler(this);

    // TODO: PLAYER
    Player player = new Player(this);

    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        addKeyListener(keyH);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

        repaint();
    }
}
