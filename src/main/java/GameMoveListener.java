package main.java;

import main.java.database.ScorePersistence;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import java.awt.Component;

public class GameMoveListener extends MouseInputAdapter {

    private Deck deck = GamePanel.getDeck();
    private Waste waste = null;
    private Tableau selTaubleau = null;
    private Foundation selFoundation = null;
    private Card selectedCard = null;
    private Restart restart = null;
    private static int valera = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        Component pressedComponent = e.getComponent().getComponentAt(e.getPoint());
        if (pressedComponent instanceof Foundation) {
            selFoundation = (Foundation) pressedComponent;
            selTaubleau = null;
            waste = null;
            selectedCard = selFoundation.topCard();
        } else if (pressedComponent instanceof Tableau) {
            selTaubleau = (Tableau) pressedComponent;
            waste = null;
            selectedCard = selTaubleau.getClickedCard(e.getY() - 150);
            for (Foundation foundation : GamePanel.getFoundationPiles()) {
                if (selTaubleau.moveTo(foundation, selectedCard)) {
                    selTaubleau = null;
                    break;
                }
            }
            try {
                //Хз
            } catch (ArrayIndexOutOfBoundsException e1) {
                System.out.println("Не тыкай");
            }

        } else if (pressedComponent instanceof Deck) {
            selTaubleau = null;
            if(!deck.isEmpty()) {
                Waste waste = GamePanel.getWastePile();
                waste.push(deck.pop());
                waste.topCard().showFace();
            } else if (deck.isEmpty()) {
                Waste waste = GamePanel.getWastePile();
                if (!waste.isEmpty()) {
                    while (!waste.isEmpty()) {
                        deck.push(waste.pop());
                        deck.topCard().hideFace();
                    }
                }
            }

        } else if (pressedComponent instanceof Waste) {
            selTaubleau = null;
            waste = GamePanel.getWastePile();
            selectedCard = waste.topCard();
            if (selectedCard != null) {
                for (Foundation foundation : GamePanel.getFoundationPiles()) {
                    foundation.moveFromWaste(waste, selectedCard);
                }
            }
        } else if (pressedComponent instanceof Restart) {
            Main.game.removeAll();
            Main.game.iniPiles();
            System.out.println("Restart");
            ScorePersistence score = new ScorePersistence();
            System.out.println(valera);
            score.updateScoreById(valera, 1);
            valera = 0;
        }
        e.getComponent().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selectedCard != null) {
            Component releasedComp = e.getComponent().getComponentAt(e.getPoint());
            valera += 1;
            if (releasedComp instanceof Tableau) {
                if (waste != null) {
                    Tableau dest = (Tableau) releasedComp;
                    if (!waste.isEmpty()) {
                        dest.moveFromWaste(waste, selectedCard);
                    }
                    waste.repaint();
                } else if (selTaubleau != null) { //Перемещение по таблеау)
                    Tableau source = selTaubleau;
                    Tableau dest = (Tableau) releasedComp;
                    source.moveTo(dest, selectedCard);
                    source.repaint();
                } else if (selFoundation != null) { //Возврат из фондации
                    Foundation source = selFoundation;
                    Tableau dest = (Tableau) releasedComp;
                    source.moveTo(dest, selectedCard);
                    source.repaint();
                    dest.repaint();
                }
            }
            e.getComponent().repaint();
            selectedCard = null;
            selFoundation = null;
            selTaubleau = null;
        }
    }

}