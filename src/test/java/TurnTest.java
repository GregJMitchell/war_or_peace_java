import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {
    private Card card1;
    private Card card2;
    private Card card3;

    private Card card4;
    private Card card5;
    private Card card6;

    private Player player1;
    private Deck deck1;

    private Player player2;
    private Deck deck2;

    @BeforeEach
    void setup() {
        card1 = new Card("Clubs", "Queen", 12);
        card2 = new Card("Clubs", "King", 13);
        card3 = new Card("Clubs", "Two", 2);
        Card[] cards = {card1, card2, card3};

        deck1 = new Deck(cards);

        card4 = new Card("Clubs", "Jack", 11);
        card5 = new Card("Clubs", "Ten", 10);
        card6 = new Card("Clubs", "Eight", 8);
        Card[] cards2 = {card4, card5, card6};

        deck2 = new Deck(cards2);

        player1 = new Player("Greg", deck1);
        player2 = new Player("Chris", deck2);
    }

    @Test
    void typeBasic() {
        Turn turn = new Turn(player1, player2);

        assertEquals(turn.type(), "basic");
    }

    @Test
    void typeWar() {
        Card[] cards = {card1, card4, card6};
        deck2 = new Deck(cards);
        player2 = new Player("Chris", deck2);

        Turn turn = new Turn(player1, player2);

        assertEquals(turn.type(), "war");
    }

    @Test
    void typeMutuallyAssuredDestruction() {
        Card[] cards = {card1, card4, card3};
        deck2 = new Deck(cards);
        player2 = new Player("Chris", deck2);

        Turn turn = new Turn(player1, player2);

        assertEquals(turn.type(), "mutually_assured_destruction");
    }
}