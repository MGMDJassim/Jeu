package tile;

import main.GamePanel;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[30];
        getTileImage();
    }

    public void getTileImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/ressource/tiles/002.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/ressource/tiles/032.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/ressource/tiles/029.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){
            g2d.drawImage(tiles[0].image, col * gamePanel.tileSize, row * gamePanel.tileSize, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x += gamePanel.tileSize;
            
            if(col == gamePanel.maxScreenCol){
                col = 0;
                x=0;
                row++;
                y += gamePanel.tileSize;
            }

        }

    }
}
