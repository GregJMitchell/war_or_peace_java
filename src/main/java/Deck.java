import java.util.ArrayList;
import java.util.Arrays;

public class Deck {
    Card[] cards;

    public Deck(Card[] newCards) {
        cards = newCards;
    }

    public int rankOfCardAt(int index) {
        return cards[index].rank;
    }

public Card[] highRankingCards() {
         ArrayList<Card> highCards = new ArrayList<Card>();
    for (int i = 0; i < cards.length; i++) {
        if (cards[i].rank >= 11){
            highCards.add((cards[i]));
        }
    }

    Card[] highCardsArr = new Card[highCards.size()];
    return highCards.toArray(highCardsArr);
}

    public float percentHighRanking() {
        return highRankingCards().length / cards.length;
    }

    public void removeCard() {
        cards = Arrays.copyOf(cards, cards.length - 1);
    }

    public void addCard(Card card) {
        Card[] newCards = Arrays.copyOf(cards, cards.length + 1);
        newCards[newCards.length - 1] = card;
        cards = newCards;
    }
}