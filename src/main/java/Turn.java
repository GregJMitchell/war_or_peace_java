import java.sql.Array;
import java.util.ArrayList;

public class Turn {
    Player player1;
    Player player2;
    ArrayList<Card> spoilsOfWar;

    public Turn(Player newPlayer1, Player newPlayer2) {
        player1 = newPlayer1;
        player2 = newPlayer2;
    }

    public String type() {
        if(mutuallyAssuredDestruction()) {
            return "mutually_assured_destruction";
        } else if (war()) {
            return "war";
        } else {
            return "basic";
        }
    }

    public Player winner() {
        Player winningPlayer = null;

        switch (type()) {
            case "basic":
                winningPlayer = findWinnerBasic();
            case "war":
                winningPlayer = findWinnerWar();
            case "mutually_assured_destruction":
                break;
        }

        return winningPlayer;
    }

    public void pileCards() {
        switch (type()) {
            case "basic":
                spoilsOfWar.add(player1.deck.removeCard());
                spoilsOfWar.add(player2.deck.removeCard());
            default:
                int index = 1;

                while (index <= 3) {
                    spoilsOfWar.add(player1.deck.removeCard());
                    spoilsOfWar.add(player2.deck.removeCard());

                    index += 1;
                }
        }
    }

    public void awardSpoils() {
        if(winner() == null){
            spoilsOfWar = new ArrayList();

            return;
        }

        for (Card card: spoilsOfWar) {
            winner().deck.addCard(card);
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

    private boolean mutuallyAssuredDestruction() {
        boolean firstCardEqual = player1.deck.rankOfCardAt(0) == player2.deck.rankOfCardAt(0);
        boolean thirdCardEqual = player1.deck.rankOfCardAt(2) == player2.deck.rankOfCardAt(2);
        return firstCardEqual && thirdCardEqual;
    }

    private boolean war() {
        boolean firstCardEqual = player1.deck.rankOfCardAt(0) == player2.deck.rankOfCardAt(0);
        return firstCardEqual;
    }

}
