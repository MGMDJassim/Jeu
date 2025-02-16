package Entite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.Controleur;
import main.GamePanel;

public class Player extends Entity {
    GamePanel  gamePanel;
    Controleur controleur;

    public Player(GamePanel gamePanel, Controleur controleur) {
        this.gamePanel = gamePanel;
        this.controleur = controleur;

        setDefaultValues();
        playerImage();
    }
    
    public void setDefaultValues() {
        x = 200;
        y = 200;
        speed = 4;
        direction = "down";
    }

    public void playerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/ressource/joueur/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/ressource/joueur/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/ressource/joueur/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/ressource/joueur/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/ressource/joueur/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/ressource/joueur/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/ressource/joueur/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/ressource/joueur/boy_right_2.png"));
        }
        catch(Exception e){
            e.printStackTrace();
       }
    }  

    public void update() {
        if (controleur.up) {
            direction = "up";
            y -= speed;
        }
        if (controleur.down) {
            direction = "down";
            y += speed;
        }
        if (controleur.left) {
            direction = "left";
            x -= speed;
        }
        if (controleur.right) {
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g) {
        // g.setColor(Color.WHITE);
        // g.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage img = null;

        switch(direction) {
            case "up":
                img = up1;
                break;
            case "down":
                img = down1;
                break;
            case "left":
                img = left1;
                break;
            case "right":
                img = right1;
                break;
        }

        g.drawImage(img, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
