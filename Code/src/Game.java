/**
 * Created by John on 9/2/2016.
 */
import javax.swing.*;
import java.lang.*;
import java.util.*;

public class Game {

    public static void main(String[] args) {

        DeckConstructor deckConstructor = new DeckConstructor();

        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Card> deck = new ArrayList<Card>();

        // Table will contain cards currently in play
        ArrayList<Card> table = new ArrayList<Card>();

        deck = deckConstructor.ConstructDeck(deck);

        // Will contain current category
        String category;

        JOptionPane.showMessageDialog(null, "Welcome to the Mineral Super Trumps Game!");

        // Display a list of possible player counts users can choose from
        Object[] possibleValues = { 3, 4, 5 };
        Object userInput = JOptionPane.showInputDialog(null,
                "Choose how many players", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        int playerCount = (Integer) userInput;

        // Declare players. Their id numbers are automatically assigned
        for (int n = 1; n <= playerCount; n++) {
            players.add(new Player(n, new ArrayList<Card>()));
        }
        // Assigning cards to players
        for (Player s: players) {
            System.out.println("Testing Player " + s.playerNo);
            while(s.pCards.size() <= 7) {
                s.pCards.add(deck.get(0));
                deck.remove(0);
            }
        }

        for (Player p: players) {
            System.out.println("Player " + p.playerNo + "'s turn");
            table.add(p.playerTurn());
        }

        for (Card c: table) {
            System.out.println(c.getName());
        }

        System.out.println(deck.size());
    }

}
