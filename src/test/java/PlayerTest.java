import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Deck deck;
    @BeforeEach
    void setup() {
        Card card1 = new Card("Clubs", "Queen", 12);
        Card card2 = new Card("Clubs", "King", 13);

        Card[] cards = {card1, card2};

        deck = new Deck(cards);
    }
    @Test
    void initializesValues() {
        Player player = new Player("Greg", deck);

        assertEquals("Greg", player.name);
        assertEquals(deck, player.deck);
    }

    @Test
    void hasLost(){
        Card[] emptyCards = new Card[0];
        Deck emptyDeck = new Deck(emptyCards);
        Player player = new Player("Greg", deck);
        Player lostPlayer = new Player("Greg", emptyDeck);

        assertFalse(player.hasLost());
        assertTrue(lostPlayer.hasLost());
    }
}