import java.util.ArrayList;

public class Turn {
    Player player1;
    Player player2;
    ArrayList<Card> spoilsOfWar = new ArrayList<>();

    public Turn(Player newPlayer1, Player newPlayer2) {
        player1 = newPlayer1;
        player2 = newPlayer2;
    }

    public String type() {
        if(deckIsLessThan3() && (player1.deck.rankOfCardAt(0) == player2.deck.rankOfCardAt(0))) {
            return "mutually_assured_destruction";
        }

        if (player1.deck.rankOfCardAt(0) != player2.deck.rankOfCardAt(0)){
            return "basic";
        } else if (player1.deck.rankOfCardAt(0) == player2.deck.rankOfCardAt(0) && player1.deck.rankOfCardAt(2) == player2.deck.rankOfCardAt(2)) {
            return "mutually_assured_destruction";
        } else {
            return "war";
        }
    }

    private Boolean deckIsLessThan3() {
        return player1.deck.cards.size() < 3 || player2.deck.cards.size() < 3;
    }

    public Player winner() {
        Player winningPlayer = null;

        switch (type()) {
            case "basic":
                winningPlayer = findWinnerBasic();
                break;
            case "war":
                winningPlayer = findWinnerWar();
                break;
            case "mutually_assured_destruction":
                break;
        }

        return winningPlayer;
    }

    public void pileCards() {
        if (type().equals("basic")) {
            spoilsOfWar.add(player1.deck.removeCard());
            spoilsOfWar.add(player2.deck.removeCard());
        } else {
            int index = 1;
            while (index <= 3) {
                spoilsOfWar.add(player1.deck.removeCard());
                spoilsOfWar.add(player2.deck.removeCard());

                index += 1;
            }
        }
    }

    public void awardSpoils(Player winner) {
        if(winner == null){
            spoilsOfWar = new ArrayList<>();

            return;
        }

        for (Card card: spoilsOfWar) {
            winner.deck.addCard(card);
        }
    }

    private Player findWinnerWar() {
        if(player1.deck.rankOfCardAt(2) > player2.deck.rankOfCardAt(2)) {
            return player1;
        } else {
            return player2;
        }
    }

    private Player findWinnerBasic() {
        if(player1.deck.rankOfCardAt(0) > player2.deck.rankOfCardAt(0)) {
            return player1;
        } else {
            return player2;
        }
    }
}
