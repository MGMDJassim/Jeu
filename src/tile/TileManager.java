package tile;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.InputStream;
import java.util.ArrayList;
import java.awt.Rectangle;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    BufferedImage backgroundImage;
    int[][] mapData;
    public ArrayList<Rectangle> borders = new ArrayList<>();

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[30];
        loadBackgroundImage();
        loadMapData();
    }

    public void loadBackgroundImage() {
        try {
            InputStream is = getClass().getResourceAsStream("/ressource/tiles/map.png");
            if (is == null) {
                System.out.println("Image resource not found!");
            } else {
                backgroundImage = ImageIO.read(is);
                System.out.println("Image loaded successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMapData() {
        try {
            InputStream is = getClass().getResourceAsStream("/ressource/tiles/map.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();

            // Charger les données de la carte (déjà existant)
            NodeList layerList = doc.getElementsByTagName("layer");
            Element layerElement = (Element) layerList.item(0);
            String data = layerElement.getElementsByTagName("data").item(0).getTextContent().trim();
            String[] tileIds = data.split(",");

            int width = Integer.parseInt(layerElement.getAttribute("width"));
            int height = Integer.parseInt(layerElement.getAttribute("height"));

            mapData = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    mapData[i][j] = Integer.parseInt(tileIds[i * width + j].trim());
                }
            }

            // Charger les frontières (bordures)
            NodeList objectList = doc.getElementsByTagName("object");
            for (int i = 0; i < objectList.getLength(); i++) {
                Element objectElement = (Element) objectList.item(i);
                String xStr = objectElement.getAttribute("x");
                String yStr = objectElement.getAttribute("y");
                String widthStr = objectElement.getAttribute("width");
                String heightStr = objectElement.getAttribute("height");

                if (!xStr.isEmpty() && !yStr.isEmpty() && !widthStr.isEmpty() && !heightStr.isEmpty()) {
                    int x = (int) Float.parseFloat(xStr);
                    int y = (int) Float.parseFloat(yStr);
                    int widthB = (int) Float.parseFloat(widthStr);
                    int heightB = (int) Float.parseFloat(heightStr);

                    borders.add(new Rectangle(x, y, widthB, heightB));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
        // Effacez le fond avant de dessiner l'image
        g2d.clearRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());

        // Dessinez l'image de fond
        if (backgroundImage != null) {
            int width = gamePanel.getWidth();
            int height = gamePanel.getHeight();
            g2d.drawImage(backgroundImage, 0, 0, width, height, null);
        }

        // Dessinez les tuiles
        if (mapData != null) {
            int tileWidth = 16; // Assuming tile width is 16 pixels
            int tileHeight = 16; // Assuming tile height is 16 pixels
            for (int y = 0; y < mapData.length; y++) {
                for (int x = 0; x < mapData[y].length; x++) {
                    int tileId = mapData[y][x];
                    // Dessinez la tuile en fonction de l'ID de la tuile
                    // Vous devez implémenter la méthode drawTile pour dessiner la tuile en fonction de l'ID
                    drawTile(g2d, tileId, x * tileWidth, y * tileHeight);
                }
            }
        }
    }

    private void drawTile(Graphics2D g2d, int tileId, int x, int y) {
        // Implémentez cette méthode pour dessiner la tuile en fonction de l'ID de la tuile
        // Par exemple, vous pouvez utiliser un tableau de BufferedImage pour stocker les images des tuiles
        // et dessiner l'image correspondante en fonction de l'ID de la tuile
    }
}