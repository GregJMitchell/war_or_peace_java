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
         ArrayList<Card> highCards = new ArrayList<>();
    for (Card card : cards) {
        if (card.rank >= 11) {
            highCards.add(card);
        }
    }

    Card[] highCardsArr = new Card[highCards.size()];
    return highCards.toArray(highCardsArr);
}

    public float percentHighRanking() {
        return (float)highRankingCards().length / cards.length;
    }

    public Card removeCard() {
        Card removedCard = cards[cards.length - 1];
        cards = Arrays.copyOf(cards, cards.length - 1);
        return removedCard;
    }

    public void addCard(Card card) {
        Card[] newCards = Arrays.copyOf(cards, cards.length + 1);
        newCards[newCards.length - 1] = card;
        cards = newCards;
    }
}
