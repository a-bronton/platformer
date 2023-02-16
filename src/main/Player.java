package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import Tile.*;

public class Player {

    // TODO: FIELDS
    public int x, y, width, height;
    public int yVel = 0;

    // TODO: IMAGES
    BufferedImage image;
    BufferedImage left, right;

    GamePanel gp;

    public Player(GamePanel gp) {
        this.gp = gp;
        width = gp.TILE_SIZE * 2;
        height = gp.TILE_SIZE * 2;
        x = (gp.SCREEN_WIDTH / 2) - (gp.TILE_SIZE / 2);
        y = gp.groundY - height;

        try {
            right = ImageIO.read(getClass().getResourceAsStream("/player_right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player_left.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        image = right;
    }

    public void draw(Graphics2D g2) {
        if (y + height > gp.groundY & yVel > -1) {
            yVel = 0;
        } else {
            yVel += gp.GRAVITY_ACC;
        }

        y += yVel;
        //System.out.println(yVel);

        g2.drawImage(image, x, y, width, height, null);

        if (gp.keyH.spacePressed && y + height >= gp.groundY) {
            yVel = -20;
            gp.keyH.spacePressed = false;
        }

        if (gp.keyH.rightPressed) {
            for (Tile t : gp.tiles) {
                t.x -= 5;
            }
            image = right;
        }
        if (gp.keyH.leftPressed) {
            for (Tile t : gp.tiles) {
                t.x += 5;
            }
            image = left;
        }

        for (Tile t : gp.tiles) {
            if (x >= t.x - gp.TILE_SIZE - 10 && x <= t.x + gp.TILE_SIZE) {
                if (y <= t.y - 1) {
                    yVel = 0;
                }
            }
        }
    }
}
