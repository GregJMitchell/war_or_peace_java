import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards;

    public Deck(ArrayList<Card> newCards) {
        cards = newCards;
    }

    public int rankOfCardAt(int index) {
        if(cards.size() <= index) {
            return 0;
        }
        return cards.get(index).rank;
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
        return (float)highRankingCards().length / cards.size();
    }

    public Card removeCard() {
        if(cards.size() == 0) {
            return null;
        }
        Card removedCard = cards.get(0);
        cards.remove(0);
        return removedCard;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
