package main.java;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {

    protected static int XShift = 80;
    public static final Point DECK_POSITION = new Point(500, 20);
    public static final Point TABLEAU_POS = new Point(20, 150);
    public static int TABLEAU_OFFSET = 80;
    private static Deck deck;
    private static Waste waste;
    private static Restart restart;
    private static Tableau[] tableau;
    private static Foundation[] foundationPiles;

    public GamePanel() {
        super.setLayout(null);
        iniPiles();

        GameMoveListener l = new GameMoveListener();
        addMouseListener(l);
        addMouseMotionListener(l);
    }

    public void iniPiles() {
        deck = new Deck(DECK_POSITION.x, DECK_POSITION.y);
        add(deck);
        waste = new Waste(DECK_POSITION.x - XShift, DECK_POSITION.y);
        add(waste);
        foundationPiles = new Foundation[4];
        for(int i = 0; i < foundationPiles.length; ++i) {
            foundationPiles[i] = new Foundation(20 + XShift * i, 20, i + 1);
            add(foundationPiles[i]);
        }
        tableau = new  Tableau[7];
        for(int tableauI = 1; tableauI <= tableau.length; ++tableauI) {
            tableau[tableauI - 1] = new Tableau (TABLEAU_POS.x + TABLEAU_OFFSET * (tableauI - 1), TABLEAU_POS.y, tableauI + 1);
            add(tableau[tableauI - 1]);
        }
        restart = new Restart(DECK_POSITION.x + 100, DECK_POSITION.y);
        add(restart);
    }


    public static Foundation[] getFoundationPiles() {
        return foundationPiles;
    }

    public static Waste getWastePile() {
        return waste;
    }

    public static Deck getDeck() {
        return deck;
    }

    public static Restart getRestart() {
        return restart;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.decode("#004524"));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
