import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {
    private Card card1;
    private Card card2;
    private Card card3;

    private Card card4;
    private Card card5;
    private Card card6;

    private Player player1;

    private Player player2;
    private Deck deck2;

    @BeforeEach
    void setup() {
        card1 = new Card("Clubs", "Queen", 12);
        card2 = new Card("Clubs", "King", 13);
        card3 = new Card("Clubs", "Two", 2);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Deck deck1 = new Deck(cards);

        card4 = new Card("Clubs", "Jack", 11);
        card5 = new Card("Clubs", "Ten", 10);
        card6 = new Card("Clubs", "Eight", 8);
        ArrayList<Card> cards2 = new ArrayList<>();
        cards2.add(card4);
        cards2.add(card5);
        cards2.add(card6);

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
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card4);
        cards.add(card6);
        deck2 = new Deck(cards);
        player2 = new Player("Chris", deck2);

        Turn turn = new Turn(player1, player2);

        assertEquals(turn.type(), "war");
    }

    @Test
    void typeMutuallyAssuredDestruction() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card4);
        cards.add(card3);
        deck2 = new Deck(cards);
        player2 = new Player("Chris", deck2);

        Turn turn = new Turn(player1, player2);

        assertEquals(turn.type(), "mutually_assured_destruction");
    }

    @Test
    void pileCardsBasic() {
        Turn turn = new Turn(player1, player2);

        Card[] expectedPlayer1 = {card2, card3};
        Card[] expectedPlayer2 = {card5, card6};
        Card[] expectedSpoils = {card1, card4};

        turn.pileCards();

        assertArrayEquals(expectedPlayer1, player1.deck.cards.toArray());
        assertArrayEquals(expectedPlayer2, player2.deck.cards.toArray());
        assertArrayEquals(expectedSpoils, turn.spoilsOfWar.toArray());
    }

    @Test
    void pileCardsDefault() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card4);
        cards.add(card6);
        deck2 = new Deck(cards);
        player2 = new Player("Chris", deck2);

        Turn turn = new Turn(player1, player2);

        Card[] expectedPlayer1 = {};
        Card[] expectedPlayer2 = {};
        Card[] expectedSpoils = {card1, card1, card2, card4, card3, card6};

        turn.pileCards();

        assertArrayEquals(expectedPlayer1, player1.deck.cards.toArray());
        assertArrayEquals(expectedPlayer2, player2.deck.cards.toArray());
        assertArrayEquals(expectedSpoils, turn.spoilsOfWar.toArray());
    }

    @Test
    void winnerBasic() {
        Turn turn = new Turn(player1, player2);

        assertEquals(turn.winner(), player1);
    }

    @Test
    void winnerWar() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card4);
        cards.add(card6);
        deck2 = new Deck(cards);
        player2 = new Player("Chris", deck2);

        Turn turn = new Turn(player1, player2);

        assertEquals(turn.winner(), player2);
    }

    @Test
    void winnerMutuallyAssuredDestruction() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card4);
        cards.add(card3);
        deck2 = new Deck(cards);
        player2 = new Player("Chris", deck2);

        Turn turn = new Turn(player1, player2);

        assertNull(turn.winner());
    }

    @Test
    void awardSpoilsMutuallyAssuredDestruction() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card4);
        cards.add(card3);
        deck2 = new Deck(cards);
        player2 = new Player("Chris", deck2);

        Turn turn = new Turn(player1, player2);

        turn.awardSpoils(null);

        assertArrayEquals(new Card[0], turn.spoilsOfWar.toArray());
    }

    @Test
    void awardSpoilsWar() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card4);
        cards.add(card6);
        deck2 = new Deck(cards);
        player2 = new Player("Chris", deck2);

        Turn turn = new Turn(player1, player2);
        turn.pileCards();

        turn.awardSpoils(player2);

        Card[] expectedSpoils = {card1, card1, card2, card4, card3, card6};

        assertArrayEquals(expectedSpoils, player2.deck.cards.toArray());
    }

    @Test
    void awardSpoilsBasic() {
        Turn turn = new Turn(player1, player2);
        turn.pileCards();

        turn.awardSpoils(player1);

//        Card[] expectedSpoils = {card1, card2, card3, card6};
        Card[] expectedSpoils = {card2, card3, card1, card4};

        assertArrayEquals(expectedSpoils, player1.deck.cards.toArray());
    }
}