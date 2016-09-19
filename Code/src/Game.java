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
        CardDataFetcher cardData = new CardDataFetcher();

        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Card> deck = new ArrayList<Card>();

        // Table will contain cards currently in play
        Card table = null;

        deck = deckConstructor.ConstructDeck(deck);

        // Will contain current category
        String category = "No category";
        // Will contain value of card presently on the table (value in current category)
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
            while(s.pCards.size() <= 7) {
                s.pCards.add(deck.get(0));
                deck.remove(0);
            }
        }

        Boolean running = true;

        // This is where players take their turns
        while (running) {
            for (Player p : players) {
                System.out.println("Player " + p.playerNo + "'s turn");
                Card playerChoice = p.playerTurn();
                boolean cardChosen = false;

                // If the player has chosen to pass
                if (playerChoice == null) {
                    p.pCards.add(deck.get(0));
                    System.out.println(deck.get(0).getName() + " added to your hand Player " + p.playerNo);
                    deck.remove(0);
                } else {
                    while (!cardChosen) {
                        playerChoice = checkPlayerChoice(playerChoice, p);
                        System.out.println(playerChoice.getName() + "'s data:");
                        System.out.println("0 Hardness: " + playerChoice.getHardness() +
                                "\n1 Specific Gravity: " + playerChoice.getSpecificGravity() +
                                "\n2 Cleavage: " + playerChoice.getCleavage() +
                                "\n3 Crustal Abundance: " + playerChoice.getCrustalAbundance() +
                                "\n4 Economic Value: " + playerChoice.getEconomicValue() +
                                "\n4 Category (if trump card): " + playerChoice.getCategory());
                        Boolean isTrumpCard = cardData.isTrumpCard(playerChoice.name);
                        // If a trump card is detected, the category is automatically changed
                        // Unless that card is The Geologist
                        // If so, select a category of your choice
                        if (isTrumpCard) {
                            if (playerChoice.name.equals("The Geologist")) {
                                System.out.println("Select a category: " +
                                        "\n0 Hardness" +
                                        "\n1 Specific Gravity" +
                                        "\n2 Cleavage" +
                                        "\n3 Crustal Abundance" +
                                        "\n4 Economic Value");
                                int input = reader.nextInt();
                                if (input == 0) {
                                    category = "Hardness";
                                } else if (input == 1) {
                                    category = "Specific Gravity";
                                } else if (input == 2) {
                                    category = "Cleavage";
                                } else if (input == 3) {
                                    category = "Crustal Abundance";
                                } else if (input == 4) {
                                    category = "Economic Value";
                                }
                                categoryValue = 0.0;
                                System.out.println("Category value has been reset");
                            } else {
                                System.out.println(playerChoice.getName() + "'s description: " +
                                        "\n" + playerChoice.getCategory());
                                category = playerChoice.getCategory();
                                categoryValue = 0.0;
                                System.out.println("Category value has been reset");
                            }
                            System.out.println("----------------------------------------" +
                                    "\nNew category is " + category +
                                    "\n----------------------------------------");
                            cardChosen = true;
                        } else {
                            // If a category already exists then players don't choose a category
                            if (category.equals("No category")) {
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
                                    categoryValue = playerChoice.getCleavage();
                                } else if (input == 3) {
                                    category = "Crustal Abundance";
                                    categoryValue = playerChoice.getCrustalAbundance();
                                } else if (input == 4) {
                                    category = "Economic Value";
                                    categoryValue = playerChoice.getEconomicValue();
                                }
                                // Put the card on the table
                                table = playerChoice;
                                System.out.println("Category: " + category + " | value: " + categoryValue);
                                System.out.println();
                                cardChosen = true;
                            } else {
                                Double playerCategoryValue = 0.0;

                                if (category.equals("Hardness")) {
                                    playerCategoryValue = playerChoice.getHardness();
                                } else if (category.equals("Specific Gravity")) {
                                    playerCategoryValue = playerChoice.getSpecificGravity();
                                } else if (category.equals("Cleavage")) {
                                    playerCategoryValue = playerChoice.getCleavage();
                                } else if (category.equals("Crustal Abundance")) {
                                    playerCategoryValue = playerChoice.getCrustalAbundance();
                                } else if (category.equals("Economic Value")) {
                                    playerCategoryValue = playerChoice.getEconomicValue();
                                }

                                if (categoryValue > playerCategoryValue) {
                                    System.out.println("Your cards value in " + category + " is lower than " + table.name +
                                    "\nPlease select another card");
                                } else {
                                    System.out.println("Your card beat " + table.name + "!");
                                    table = playerChoice;
                                    categoryValue = playerCategoryValue;
                                    System.out.println("New value to beat for " + category + " is: " + categoryValue);
                                    System.out.println();
                                    cardChosen = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static Card checkPlayerChoice(Card playerChoice, Player p) {
        Scanner reader = new Scanner(System.in);
        boolean choiceValid = false;
        // This while loop enables players to go back on their choice of cards
        // If they don't like their card's stats, they can go back and choose again
        while (!choiceValid) {
            System.out.println(playerChoice.getName() + "'s data:");
            System.out.println("0 Hardness: " + playerChoice.getHardness() +
                    "\n1 Specific Gravity: " + playerChoice.getSpecificGravity() +
                    "\n2 Cleavage: " + playerChoice.getCleavage() +
                    "\n3 Crustal Abundance: " + playerChoice.getCrustalAbundance() +
                    "\n4 Economic Value: " + playerChoice.getEconomicValue() +
                    "\n5 Category (if trump card): " + playerChoice.getCategory());
            System.out.print("Do you want to proceed with this card? Press 'y', or 'n' to proceed: ");
            String choice = reader.next();
            if (choice.equals("y")) {
                choiceValid = true;
            } else if (choice.equals("n")) {
                System.out.println("Select another card or pass.");
                p.pCards.add(playerChoice);
                playerChoice = p.playerTurn();
            } else {
                System.out.println("Please enter proper input");
            }
        }
        return playerChoice;
    }
}
