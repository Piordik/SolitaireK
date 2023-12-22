package main.java;

import java.awt.*;

public class Restart extends Pile{
    public Restart(int x, int y) {
        super(x, y);
        super.setSize(30, 30);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Card.resBut(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
