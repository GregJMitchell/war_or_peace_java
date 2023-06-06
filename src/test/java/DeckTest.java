import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Card card1;
    private Card card2;
    private Card card3;
    @BeforeEach
    void setup() {
        card1 = new Card("Clubs", "Queen", 12);
        card2 = new Card("Clubs", "King", 13);
        card3 = new Card("Clubs", "Two", 2);
    }
    @Test
    void rankOfCardAt() {
        Card[] cards = {card1, card2};

        Deck deck = new Deck(cards);

        assertEquals(13, deck.rankOfCardAt(1));
        assertEquals(12, deck.rankOfCardAt(0));
    }

    @Test
    void highRankingCards() {
        Card[] cards = {card1, card2, card3};

        Deck deck = new Deck(cards);

        Card[] actual = deck.highRankingCards();
        Card[] expected = {card1, card2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void percentHighRanking() {
        Card[] cards = {card1, card2, card3};

        Deck deck = new Deck(cards);

        float expected = 2.0f / 3.0f;

        assertEquals(expected, deck.percentHighRanking());
    }

    @Test
    void removeCard() {
        Card[] cards = {card1, card2, card3};

        Deck deck = new Deck(cards);

        Card[] expected = {card1, card2};

        deck.removeCard();

        assertArrayEquals(expected, deck.cards);
    }

    @Test
    void addCard() {
        Card[] cards = {card1, card2};

        Deck deck = new Deck(cards);

        Card[] expected = {card1, card2, card3};

        deck.addCard(card3);

        assertArrayEquals(expected, deck.cards);
    }
}