/**
 * Created by John on 9/2/2016.
 */
import javax.swing.*;
import java.lang.*;
import java.util.*;

public class Game {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        DeckConstructor deckConstructor = new DeckConstructor();

        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Card> deck = new ArrayList<Card>();

        // Table will contain cards currently in play
        Card table;

        deck = deckConstructor.ConstructDeck(deck);

        // Will contain current category
        String category = "";
        Double categoryValue = 0.0;

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

        // This is where players take their turns
        for (Player p: players) {
            System.out.println("Player " + p.playerNo + "'s turn");
            Card playerChoice = p.playerTurn();
            if (playerChoice == null) {
                p.pCards.add(deck.get(0));
                System.out.println(deck.get(0).getName() + " added to your hand Player " + p.playerNo);
                deck.remove(0);
            } else {
                table = playerChoice;
                System.out.println(playerChoice.getName() + "'s data:");
                System.out.println("0 Hardness: " + playerChoice.getHardness() +
                "\n1 Specific Gravity: " + playerChoice.getSpecificGravity() +
                "\n2 " + playerChoice.getCleavage() +
                "\n3 Crustal Abundance: " + playerChoice.getCrustalAbundance() +
                "\n4 Economic Value: " + playerChoice.getEconomicValue());
                System.out.print("Enter the category by inputting the number next to the category name: ");
                int input = reader.nextInt();
                if (input == 0) {
                    category = "Hardness";
                    categoryValue = playerChoice.getHardness();
                } else if (input == 1) {
                    category = "Specific Gravity";
                    categoryValue = playerChoice.getSpecificGravity();
                } else if (input == 2) {
                    category = "Cleavage";
                    categoryValue = null;
                } else if (input == 3) {
                    category = "Crustal Abundance";
                    categoryValue = playerChoice.getCrustalAbundance();
                } else if (input == 4) {
                    category = "Economic Value";
                    categoryValue = playerChoice.getEconomicValue();
                }
                System.out.println("Category: " + category + " | value: " + categoryValue);
            }
        }
    }
}
