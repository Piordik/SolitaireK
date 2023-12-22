package main.java;

import java.awt.*;

public class Waste extends Pile {

    public Waste(int x, int y) {
        super(x, y);
        super.setSize(72, 96);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.isEmpty()) {
            g.drawImage(Card.cardBot(), 0, 0, 72, this.getHeight(), this);
        } else {
            g.drawImage(this.topCard().getCardImage(), 0, 0, 72, this.getHeight(), this);
        }

    }
}
