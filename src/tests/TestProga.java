import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import main.java.Enum.Suit;
import main.java.Card;

import javax.swing.*;

import static org.assertj.core.api.BDDAssertions.then;

public class TestProga {

    @Test
    @DisplayName("TestCardFile")
    public void TestCardFile() {
        Card card = new Card(1, Suit.Diamonds);
        then(card.cardFile(Suit.Diamonds, 1)).isEqualTo("/01d.gif");
    }
}