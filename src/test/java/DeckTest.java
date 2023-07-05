import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Card card1;
    private Card card2;
    private Card card3;

    private final ArrayList<Card> cards = new ArrayList<>();
    @BeforeEach
    void setup() {
        card1 = new Card("Clubs", "Queen", 12);
        card2 = new Card("Clubs", "King", 13);
        card3 = new Card("Clubs", "Two", 2);
    }
    @Test
    void rankOfCardAt() {
        cards.add(card1);
        cards.add(card2);

        Deck deck = new Deck(cards);

        assertEquals(13, deck.rankOfCardAt(1));
        assertEquals(12, deck.rankOfCardAt(0));
    }

    @Test
    void highRankingCards() {
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Deck deck = new Deck(cards);

        Card[] actual = deck.highRankingCards();
        Card[] expected = {card1, card2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void percentHighRanking() {
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Deck deck = new Deck(cards);

        float expected = 2.0f / 3.0f;

        assertEquals(expected, deck.percentHighRanking());
    }

    @Test
    void removeCard() {
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Deck deck = new Deck(cards);

        Card[] expected = {card2, card3};

        deck.removeCard();

        assertArrayEquals(expected, deck.cards.toArray());
    }

    @Test
    void addCard() {
        cards.add(card1);
        cards.add(card2);

        Deck deck = new Deck(cards);

        Card[] expected = {card1, card2, card3};

        deck.addCard(card3);

        assertArrayEquals(expected, deck.cards.toArray());
    }
}