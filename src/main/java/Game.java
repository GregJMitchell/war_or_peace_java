import java.util.*;

public class Game {
    private ArrayList<Card> mainDeck = new ArrayList<>() {};

    private Deck deck1;
    private Deck deck2;

    private Player player1;
    private Player player2;
    private int turnCount = 0;

    public Game() {};

    public void start() {
        startScreen();

        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();
        System.out.println(input);
        if(input.toLowerCase(Locale.ROOT).equals("go")) {
            createDecks();
            runTurns();
        } else {
            System.out.println("Invalid input");
            start();
        }
    }

    private void createDecks() {
        String[] suits = {"diamond", "club", "heart", "spade"};

        for(String suit: suits) {
            for (int x = 2; x <= 14; x++) {
                if(x < 11) {
                    mainDeck.add(new Card(suit, Integer.toString(x), x));
                } else if (x == 11) {
                    mainDeck.add(new Card(suit, "Jack", x));
                } else if (x == 12) {
                    mainDeck.add(new Card(suit, "Queen", x));
                } else if (x == 13) {
                    mainDeck.add(new Card(suit, "King", x));
                } else {
                    mainDeck.add(new Card(suit, "Ace", x));
                }
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(mainDeck);
    }

    private void setStartingDecks() {
        Card[] deck1Cards = new Card[26];
        Card[] deck2Cards = new Card[26];

        for (int x = 0; x <= 52; x++) {
            if(x <= 25) {
                deck1Cards[x] = mainDeck.get(x);
            } else {
                deck2Cards[x - 26] = mainDeck.get(x);
            }
        }
        deck1 = new Deck(deck1Cards);
        deck2 = new Deck(deck2Cards);
    }

    private void setStartingPlayers() {
        player1 = new Player("Megan", deck1);
        player2 = new Player("Aurora", deck2);
    }

    private void startScreen() {
        System.out.println("Welcome to War! (or Peace) This game will be played with 52 cards.");
        System.out.println("The players today are Megan and Aurora.");
        System.out.println("Type 'GO' to start the game!");
        System.out.println("------------------------------------------------------------------");
    }

    private void turnMessages(Turn turn) {
        Player winner = turn.winner();
        turnCount += 1;
        turn.pileCards();
        turn.awardSpoils(winner);

        if (Objects.equals(turn.type(), "basic") || Objects.equals(turn.type(), " war")) {
            String message = "Turn " + turnCount + ": " + winner.name + " won " + turn.spoilsOfWar.size() + " cards";
            System.out.println(message);
        } else {
            String message = "Turn " + turnCount + ": *mutually assured destruction* " + turn.spoilsOfWar.size() + " cards removed from play";
            System.out.println(message);
        }
    }

    private Player determineGameWinner() {
        if (player1.hasLost()) {
            return player2;
        } else {
            return player1;
        }
    }

    private void runTurns() {
        while (!player1.hasLost() && !player2.hasLost()) {
            if(player1.hasLost() || player2.hasLost()) {
                break;
            }

            if (turnCount <= 999_999) {
                Turn turn = new Turn(player1, player2);

                turnMessages(turn);
            } else {
                System.out.println("---- DRAW ----");
            }
        }

        Player winner = determineGameWinner();
        String message = "*~*~*~* " + winner.name + " has won the game! *~*~*~*";
        System.out.println(message);
    }
}