package main.java;

import java.awt.*;

public class Tableau extends Pile {

    public Tableau(int x, int y, int initSize) {
        super(x, y);
        super.setSize(72, 450);
        super.setOpaque(false);
        for (int i = 0; i < initSize; ++i) {
            push(GamePanel.getDeck().pop());
        }

        if (initSize > 0) {
            topCard().showFace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.drawLine(0, 0, this.getWidth(), 0);
        g2d.drawLine(0, 0, 0, 96);
        g2d.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, 96);

        g2d.setPaint(new GradientPaint(36, 0, new Color(255, 255, 255, 160), 36, 60, new Color(0, 0, 0, 0)));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        int cardY = 0;
        if (!this.isEmpty()) {
            for (Card c : this.cards) {
                if (c.isFaceUp()) {
                    g.drawImage(c.getCardImage(), 0, cardY, 72, 96, this);
                    cardY += 20;
                } else {
                    g.drawImage(Card.cardBack(), 0, cardY, 72, 96, this);
                    cardY += 20;
                }

            }
        }
    }

    public void moveFromWaste(Waste source, Card card) {
        if (this.accepts(card)) {
            this.push(source.pop());
        }
        source = null;
    }

    public boolean accepts(Card card) {
        if (!this.isEmpty()) {
            return this.topCard().getValue() == card.getValue() + 1
                    && !this.topCard().getColour().equals(card.getColour());
        }
        return card.getValue() == 13;
    }

    public Card getClickedCard(int y) {
        int index = y / 20;
        if (index < this.cards.toArray().length) {
            Card retur = (Card) cards.toArray()[index];
            if (retur.isFaceUp()) {
                return retur;
            }
        }
        return (Card) cards.toArray()[cards.toArray().length - 1];
    }

    public boolean moveTo(Foundation dest, Card card) {
        if (dest.accepts(card)) {
            dest.push(this.pop());
            if (!this.isEmpty()) {
                this.topCard().showFace();
            }
            return true;
        }
        return false;
    }

    public void moveTo(Tableau dest, Card card) {
        if (dest.accepts(card)) {
            dest.push(this.pop());
        }
        if (!this.isEmpty()) {
            this.topCard().showFace();
        }
    }
}