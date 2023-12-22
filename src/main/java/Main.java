package main.java;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {

    static protected GamePanel game;
    public static final int PANEL_WIDTH = 640, PANEL_HEIGHT = 500;

    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = new GamePanel();
        game.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        add(game);
        pack();
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}