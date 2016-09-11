/**
 * Created by John on 9/2/2016.
 */
import javax.swing.*;
import java.lang.*;
import java.util.*;

public class Game {

    public static void main(String[] args) {

        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Card> deck = new ArrayList<Card>();

        // Declare the plist file reader class
        XML_Reader xml_reader = new XML_Reader();

        xml_reader.Reader();
        // Read the plist file and write to a string arraylist and return
        ArrayList<String> cardData = xml_reader.Reader();

        // Testing returned arraylist
        while(deck.size() <= 60) {
            int n = 0;
            // temporarily holds card data
            ArrayList<String> tempCardData = new ArrayList<String>();
            // same as above but for super trump cards
            ArrayList<String> tempSCardData = new ArrayList<String>();
            for (String s :cardData) {
                if (s.equals("title")) {
                    tempCardData.add(cardData.get(n + 1));
                }
                if (s.equals("hardness")) {
                    tempCardData.add(cardData.get(n + 1));
                }
                if (s.equals("specific_gravity")) {
                    tempCardData.add(cardData.get(n + 1));
                }
                if (s.equals("cleavage")) {
                    tempCardData.add(cardData.get(n + 1));
                }
                if (s.equals("crustal_abundance")) {
                    tempCardData.add(cardData.get(n + 1));
                }
                if (s.equals("economic_value")) {
                    tempCardData.add(cardData.get(n + 1));
                }
                if (s.equals("The Miner")) {
                    tempSCardData.add(s);
                    tempSCardData.add(cardData.get(n + 2));
                }
                if (s.equals("The Petrologist")) {
                    tempSCardData.add(s);
                    tempSCardData.add(cardData.get(n + 2));
                }
                if (s.equals("The Gemmologist")) {
                    tempSCardData.add(s);
                    tempSCardData.add(cardData.get(n + 2));
                }
                if (s.equals("The Mineralogist")) {
                    tempSCardData.add(s);
                    tempSCardData.add(cardData.get(n + 2));
                }
                if (s.equals("The Geophysicist")) {
                    tempSCardData.add(s);
                    tempSCardData.add(cardData.get(n + 2));
                }
                if (s.equals("The Geologist")) {
                    tempSCardData.add(s);
                    tempSCardData.add(cardData.get(n + 2));
                }
                if (tempCardData.size() == 6) {
                    deck.add(new MCard(tempCardData.get(0), tempCardData.get(1), tempCardData.get(2), tempCardData.get(3), tempCardData.get(4), tempCardData.get(5)));
                    tempCardData.clear();
                }
                if (tempSCardData.size() == 2) {
                    deck.add(new SCard(tempSCardData.get(0), tempSCardData.get(1)));
                    tempSCardData.clear();
                }
                n++;
            }
        }
        // Removing a particular card that stored unusual values... Does not compromise the deck however
        deck.remove(deck.size() - 2);

        // Testing cards in deck
        for(Card c: deck) {
            System.out.println(c.name);
            System.out.println(c.getHardness());
            System.out.println(c.getSpecificGravity());
            System.out.println(c.getCleavage());
            System.out.println(c.getCrustalAbundance());
            System.out.println(c.getEconomicValue());
            System.out.println(c.getCategory());
            System.out.println();
        }

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
        // Testing player class
        for (Player s: players) {
            System.out.println("Testing Player " + s.playerNo);
        }

    }

}
