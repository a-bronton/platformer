package Tile;

import main.GamePanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TileHandler {

    GamePanel gPanel;

    public TileHandler(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public void loadMap(String filePath) {
        File f = new File("res/maps/" + filePath);

        try {
            BufferedReader in = new BufferedReader(new FileReader(f));

            String line;
            int j = 0;
            while ((line = in.readLine()) != null) {
                String[] types = line.split(" ");
                for (int i = 0; i < types.length; i++) {
                    int type = Integer.parseInt(types[i]);

                    switch (type) {
                        case 1:
                            // TODO: BRICK
                            gPanel.tiles.add(new Tile("Brick", gPanel.TILE_SIZE * i, j, "block1.png", gPanel));
                            break;
                    }
                }
                j += gPanel.TILE_SIZE;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
