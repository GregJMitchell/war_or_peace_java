import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Test
    void rankOfCardAt() {
        Card card1 = new Card("Clubs", "Queen", 12);
        Card card2 = new Card("Clubs", "King", 13);

        Card[] cards = {card1, card2};

        Deck deck = new Deck(cards);

        assertEquals(13, deck.rankOfCardAt(1));
        assertEquals(12, deck.rankOfCardAt(0));
    }

    @Test
    void highRankingCards() {
        Card card1 = new Card("Clubs", "Queen", 12);
        Card card2 = new Card("Clubs", "King", 13);
        Card card3 = new Card("Clubs", "Two", 2);

        Card[] cards = {card1, card2, card3};

        Deck deck = new Deck(cards);

        Card[] actual = deck.highRankingCards();
        Card[] expected = {card1, card2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void percentHighRanking() {
        Card card1 = new Card("Clubs", "Queen", 12);
        Card card2 = new Card("Clubs", "King", 13);
        Card card3 = new Card("Clubs", "Two", 2);
        Card[] cards = {card1, card2, card3};

        Deck deck = new Deck(cards);

        float expected = 2 / 3;

        assertEquals(expected, deck.percentHighRanking());
    }

    @Test
    void removeCard() {
        Card card1 = new Card("Clubs", "Queen", 12);
        Card card2 = new Card("Clubs", "King", 13);
        Card card3 = new Card("Clubs", "Two", 2);
        Card[] cards = {card1, card2, card3};

        Deck deck = new Deck(cards);

        Card[] expected = {card1, card2};

        deck.removeCard();

        assertArrayEquals(expected, deck.cards);
    }

    @Test
    void addCard() {
        Card card1 = new Card("Clubs", "Queen", 12);
        Card card2 = new Card("Clubs", "King", 13);
        Card card3 = new Card("Clubs", "Two", 2);
        Card[] cards = {card1, card2};

        Deck deck = new Deck(cards);

        Card[] expected = {card1, card2, card3};

        deck.addCard(card3);

        assertArrayEquals(expected, deck.cards);
    }
}