/**
 * Created by John on 14/09/2016.
 */

import java.util.*;

public class DeckConstructor {
    public ArrayList ConstructDeck(ArrayList<Card> deck) {
        // Declare the plist file reader class
        XML_Reader xml_reader = new XML_Reader();

        xml_reader.Reader();
        // Read the plist file and write to a string arraylist and return
        ArrayList<String> cardData = xml_reader.Reader();

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

        // Shuffle the deck
        Collections.shuffle(deck);

        return deck;
    }
}
