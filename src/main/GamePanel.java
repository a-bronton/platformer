package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Tile.*;

public class GamePanel extends JPanel {

    // TODO: SETUP VARIABLES
    public final int TILE_SIZE = 32;
    public final int SCREEN_WIDTH = 32 * TILE_SIZE;
    public final int SCREEN_HEIGHT = 18 * TILE_SIZE;

    public int groundY = SCREEN_HEIGHT - 200;
    public final int GRAVITY_ACC = 1; // Acceleration of gravity

    // TODO: Utilities
    public KeyHandler keyH = new KeyHandler(this);

    // TODO: PLAYER
    Player player = new Player(this);

    // TODO: TILES
    public ArrayList<Tile> tiles = new ArrayList<Tile>();

    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        tiles.add(new Tile("Block", 250, 250, "block.png", this));
        tiles.add(new Tile("Block", 250 + TILE_SIZE, 250, "block.png", this));

        addKeyListener(keyH);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        groundY = getParent().getHeight() - 200;

        g2.setColor(Color.GREEN);
        g2.fillRect(0, groundY, getParent().getWidth(), getParent().getHeight());

        player.draw(g2);

        for (Tile t : tiles) {
            t.draw(g2);
        }

        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        repaint();
    }
}
