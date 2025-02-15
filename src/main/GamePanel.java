package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entite.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    final int originalTtileSize = 16;
    final int scale = 3;

    public int tileSize = originalTtileSize * scale;

    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int fps = 60;

    TileManager tileManager = new TileManager(this);
    Thread thread;
    Controleur controleur = new Controleur();
    Player joueur = new Player(this, controleur);

    int x = 100;
    int y = 100;
    int speed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(controleur);
        this.setFocusable(true);

        startGameThread(); 
         
    }

    public void startGameThread(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (thread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
                joueur.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d =  (Graphics2D)g;
        tileManager.draw(g2d);
        joueur.draw(g2d);
        g2d.dispose();
    }
}