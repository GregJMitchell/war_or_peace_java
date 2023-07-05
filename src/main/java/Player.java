public class Player {
    String name;
    Deck deck;

    public Player(String newName, Deck newDeck) {
        name = newName;
        deck = newDeck;
    }

    public boolean hasLost() {
        return deck.cards.size() == 0;
    }
}
