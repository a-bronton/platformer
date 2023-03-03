package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import Tile.*;

public class Player {

    // TODO: FIELDS
    public int x, y, width, height;
    public int feetY = 0;
    public int yVel = 0;
    public int jumpHeight = 20;

    public Rectangle solidArea;

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
        feetY = y + height;

        try {
            right = ImageIO.read(getClass().getResourceAsStream("/player_right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player_left.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        image = right;

        solidArea = new Rectangle(x + 10, y, width - 20, height);
    }

    public void draw(Graphics2D g2) {
        // TODO: STAY ON GROUND
        if (feetY >= gp.groundY & yVel > -1) {
            yVel = 0;
            y = gp.groundY - height;
        } else {
            yVel += gp.GRAVITY_ACC;
        }

        // TODO: COLLIDE WITH BLOCKS
        for (Tile t : gp.tiles) {
            if (solidArea.x + solidArea.width >= t.x && solidArea.x <= t.x + t.width) {
                if (feetY >= t.y && yVel > 0) {
                    yVel = 0;
                    y = t.y - height;
                }

                if (y > t.y + t.height && yVel < 0) {
                    yVel = 0;
                    y = t.y + height + t.height;
                }
            }
        }

        // TODO: JUMPING
        if (gp.keyH.spacePressed && yVel == 0) {
            yVel = -jumpHeight;
            gp.keyH.spacePressed = false;
        }

        // TODO: MOVE RIGHT
        if (gp.keyH.rightPressed) {
            for (Tile t : gp.tiles) {
                t.x -= 5;
            }
            image = right;
        }

        // TODO: MOVE LEFT
        if (gp.keyH.leftPressed) {
            for (Tile t : gp.tiles) {
                t.x += 5;
            }
            image = left;
        }

        solidArea.x = x + 10;
        solidArea.y = y + yVel;

        // TODO: MOVE Y ACCORDING TO VELOCITY
        y += yVel;
        feetY = y + height;
        // TODO: DRAW CHARACTER
        g2.drawImage(image, x, y, width, height, null);

        // TODO: DRAW HITBOX
        g2.setColor(new Color(255, 0, 0, 100));
        g2.fillRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
    }

    // Returns direction of collision
    public String checkCollision(Tile t) {
        if (solidArea.intersects(t.solidArea)) {
            if (feetY < t.y + t.height) {
                if (solidArea.x + width > t.x && solidArea.x < t.width) {
                    return "TOP";
                }
            }
        }

        return null;
    }
}
