package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controleur implements KeyListener {
    public boolean up, down, left, right;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) {
            up = true;
        }
        if (code == KeyEvent.VK_S) {
            down = true;
        }
        if (code == KeyEvent.VK_Q) {
            left = true;
        }
        if (code == KeyEvent.VK_D) {
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) {
            up = false;
        }
        if (code == KeyEvent.VK_S) {
            down = false;
        }
        if (code == KeyEvent.VK_Q) {
            left = false;
        }
        if (code == KeyEvent.VK_D) {
            right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Implémentation vide pour éviter l'exception
    }
}