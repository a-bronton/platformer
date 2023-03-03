package Tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public int width, height;
    public String name;
    public int x, y;
    public Rectangle solidArea;

    public BufferedImage image;

    GamePanel gp;
    public Tile(String name, int x, int y, String imagePath, GamePanel gp) {
        this.x = x;
        this.y = y;
        this.name = name;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.gp = gp;
        width = gp.TILE_SIZE;
        height = gp.TILE_SIZE;

        solidArea = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics2D g2) {
        solidArea.x = x;
        solidArea.y = y;
        g2.drawImage(image, x, y, width, height, null);

        g2.setColor(new Color(255, 0, 0, 100));
        g2.fillRect(solidArea.x, solidArea.y, width, height);
    }
}
