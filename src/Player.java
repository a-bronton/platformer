import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    // TODO: FIELDS
    public int x, y, width, height;
    public int yVel = 0;

    // TODO: IMAGES
    BufferedImage image1;

    GamePanel gp;

    public Player(GamePanel gp) {
        this.gp = gp;
        x = 100;
        y = 0;
        width = 50;
        height = 50;

        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("player.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        if (y < gp.GROUND_Y) {
            yVel += gp.GRAVITY_VEL;
        } else {
            yVel = 0;
        }

        y += yVel;

        g2.drawImage(image1, x, y, width, height, null);

        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
