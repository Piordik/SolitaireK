package main.java;

import main.java.Enum.Colour;
import main.java.Enum.Suit;

import javax.swing.*;
import java.awt.*;

public class Card {
    public static String cardBack = "back",
            cardBot = "bot",
            cardBase = "B";
    public static String dir = "images", extension = ".gif";
    public Image im;
    private final int value;
    private String suit;
    private boolean faceUp;
    private Colour colour;

    public Card(int value, Suit suit) {
        this.value = value;
        switch (suit) {
            case Clubs:
                this.suit = "c";
                colour = Colour.Black;
                break;
            case Diamonds:
                this.suit = "d";
                colour = Colour.Red;
                break;
            case Spades:
                this.suit = "s";
                colour = Colour.Black;
                break;
            case Hearts:
                this.suit = "h";
                colour = Colour.Red;
                break;
        }
        faceUp = false;

        try {
            ImageIcon ii = new ImageIcon(getClass().getResource(dir + cardFile(suit, value)));
            im = ii.getImage();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public String cardFile(Suit s, int val) {
        char ch;

        if (val < 1 || val > 13)
            throw new IllegalArgumentException("Bad main.java.Card Number");

        if(s == Suit.Clubs) {
            ch = 'c';
        }else if(s == Suit.Hearts) {
            ch = 'h';
        }else if(s == Suit.Spades) {
            ch = 's';
        }else if(s == Suit.Diamonds) {
            ch = 'd';
        }
        else throw new IllegalArgumentException("Bad main.java.Card Enum.main.java.Enum.Suit");

        if(val < 10)
            return "/0" + val + ch + extension;
        else
            return "/" + val + ch + extension;
    }

    public Image getCardImage() {
        return im;
    }


    public boolean isFaceUp() {
        return faceUp;
    }

    public Colour getColour() {
        return colour;
    }

    public String toStr() {
        return value + " of " + suit;
    }

    public static Image cardBase(int suit) {
        ImageIcon ii = new ImageIcon(Card.class.getResource(dir + "//" + cardBase + suit + extension));
        Image image = ii.getImage();
        return image;
    }

    public static Image cardBot() {
        ImageIcon ii = new ImageIcon(Card.class.getResource(dir + "//" + cardBot + extension));
        Image image = ii.getImage();
        return image;
    }

    public static Image cardBack() {
        ImageIcon ii = new ImageIcon(Card.class.getResource(dir + "//" + cardBack + extension));
        Image image = ii.getImage();
        return image;
    }

    public static Image resBut() {
        ImageIcon ii = new ImageIcon(Card.class.getResource(dir + "//restart2.png"));
        Image image = ii.getImage();
        return image;
    }

    public void showFace() {
        faceUp = true;
    }

    public void hideFace() {
        faceUp = false;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

}
