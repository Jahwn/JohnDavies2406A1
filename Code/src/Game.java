/**
 * Created by John on 9/2/2016.
 */
import javax.swing.*;
import java.lang.*;
import java.util.*;

public class Game {

    public static ArrayList<Player> players = new ArrayList<Player>();
    public static ArrayList<Card> deck = new ArrayList<Card>();

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        DeckConstructor deckConstructor = new DeckConstructor();
        CardDataFetcher cardData = new CardDataFetcher();

        // Table will contain cards currently in play
        Card table = new MCard("[No card, please disregard]", 0.0, 0.0, 0.0, 0.0, 0.0);;

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
            while(s.pCards.size() <= 1) {
                s.pCards.add(deck.get(0));
                deck.remove(0);
            }
        }

        Boolean running = true;

        int round = 1;

        ArrayList<Integer> winners = new ArrayList<Integer>();

        // This is where players take their turns
        while (running) {
            for (Player p : players) {
                boolean turnValid = false;
                if (p.pCards.size() == 0) {
                    p.playerTurn = false;
                    System.out.println("Player " + p.playerNo + " has no cards remaining");
                    turnValid = true;
                }
                int playersPassed = playersPassed();
                if (p.playerTurn) {
                    if (playersPassed == (playerCount - 1) && p.isPlaying) {
                        category = "No category";
                        System.out.println("----------------------------------------" +
                                "\nPlayer " + p.playerNo + " won round " + round +
                                "\n----------------------------------------");
                        round++;
                    }
                }
                if (category.equals("No category") && p.isPlaying) {
                    System.out.println("----------------------------------------" +
                            "\nRound " + round +
                            "\n----------------------------------------");
                    bringBackPlayers();
                }
                if (!p.playerTurn) {
                    System.out.println("Player " + p.playerNo + " is out of the round");
                    turnValid = true;
                } else {
                    if (p.isPlaying) {
                        System.out.println("Player " + p.playerNo + "'s turn");
                    } else {
                        turnValid = true;
                    }
                }

                while (!turnValid) {
                    boolean tableIsEmpty = false;
                    if (category.equals("No category")) {
                        tableIsEmpty = true;
                    }
                    Card playerChoice = checkPlayerChoice(p);
                    while (tableIsEmpty) {
                        if (playerChoice == null) {
                            System.out.println("Please draw a card");
                            playerChoice = checkPlayerChoice(p);
                        } else {
                            tableIsEmpty = false;
                        }
                    }
                    // If the player has chosen to pass
                    if (playerChoice == null) {
                        if (!p.pCards.isEmpty()) {
                            p.pCards.add(deck.get(0));
                            System.out.println(deck.get(0).getName() + " added to your hand Player " + p.playerNo);
                            deck.remove(0);
                            p.playerTurn = false;
                            turnValid = true;
                        }
                    } else {
                        Boolean isTrumpCard = cardData.isTrumpCard(playerChoice.name);
                        // If a trump card is detected, the category is automatically changed
                        // Unless that card is The Geologist
                        // If so, select a category of your choice
                        if (isTrumpCard) {
                            if (playerChoice.name.equals("The Geologist")) {
                                System.out.println("Select a category: " +
                                        "\n0 Hardness" +
                                        "\n1 Specific gravity" +
                                        "\n2 Cleavage" +
                                        "\n3 Crustal abundance" +
                                        "\n4 Economic value");
                                System.out.print("Enter the category by inputting the number next to the category name: ");
                                int input = reader.nextInt();
                                if (input == 0) {
                                    category = "Hardness";
                                } else if (input == 1) {
                                    category = "Specific gravity";
                                } else if (input == 2) {
                                    category = "Cleavage";
                                } else if (input == 3) {
                                    category = "Crustal abundance";
                                } else if (input == 4) {
                                    category = "Economic value";
                                }
                                categoryValue = 0.0;
                                bringBackPlayers();
                                System.out.println("Category value has been reset" +
                                        "\nAll players who have passed are back in");
                            } else {
                                System.out.println(playerChoice.getName() + "'s description: " +
                                        "\n" + playerChoice.getCategory());
                                category = playerChoice.getCategory();
                                categoryValue = 0.0;
                                bringBackPlayers();
                                System.out.println("Category value has been reset" +
                                        "\nAll players who have passed are back in");
                            }
                            // When category changes, put the card from the table back to the deck
                            deck.add(table);
                            table = new MCard("[No card, please disregard]", 0.0, 0.0, 0.0, 0.0, 0.0);
                            System.out.println("----------------------------------------" +
                                    "\nNew category is " + category +
                                    "\n----------------------------------------");
                            turnValid = true;
                        } else {
                            // If a category already exists then players don't choose a category
                            if (category.equals("No category")) {
                                boolean inputValid = false;
                                while (!inputValid) {
                                    System.out.print("Enter the category by inputting the number next to the category name: ");
                                    int input = reader.nextInt();
                                    if (input == 0) {
                                        category = "Hardness";
                                        categoryValue = playerChoice.getHardness();
                                        inputValid = true;
                                    } else if (input == 1) {
                                        category = "Specific gravity";
                                        categoryValue = playerChoice.getSpecificGravity();
                                        inputValid = true;
                                    } else if (input == 2) {
                                        category = "Cleavage";
                                        categoryValue = playerChoice.getCleavage();
                                        inputValid = true;
                                    } else if (input == 3) {
                                        category = "Crustal abundance";
                                        categoryValue = playerChoice.getCrustalAbundance();
                                        inputValid = true;
                                    } else if (input == 4) {
                                        category = "Economic value";
                                        categoryValue = playerChoice.getEconomicValue();
                                        inputValid = true;
                                    } else {
                                        System.out.println("***Error: Please enter proper input***");
                                    }
                                }
                                // Put the card on the table
                                table = playerChoice;
                                System.out.println("----------------------------------------" +
                                        "\nCategory: " + category + " | value to beat: " + categoryValue +
                                        "\n----------------------------------------");
                                turnValid = true;
                            } else {
                                Double playerCategoryValue = 0.0;

                                if (category.equals("Hardness")) {
                                    playerCategoryValue = playerChoice.getHardness();
                                } else if (category.equals("Specific gravity")) {
                                    playerCategoryValue = playerChoice.getSpecificGravity();
                                } else if (category.equals("Cleavage")) {
                                    playerCategoryValue = playerChoice.getCleavage();
                                } else if (category.equals("Crustal abundance")) {
                                    playerCategoryValue = playerChoice.getCrustalAbundance();
                                } else if (category.equals("Economic value")) {
                                    playerCategoryValue = playerChoice.getEconomicValue();
                                }

                                if (categoryValue > playerCategoryValue) {
                                    System.out.println("----------------------------------------" +
                                            "\nYour card's value in " + category + " is lower than " + table.name +
                                            "\nPlease select another card" +
                                            "\n----------------------------------------");
                                    p.pCards.add(playerChoice);
                                } else {
                                    System.out.println("----------------------------------------" +
                                            "\nYour card beat " + table.name + "!");
                                    table = playerChoice;
                                    categoryValue = playerCategoryValue;
                                    System.out.println("New value to beat for " + category + " is: " + categoryValue +
                                            "\n----------------------------------------");
                                    turnValid = true;
                                }
                            }
                        }
                    }
                }
                if (p.pCards.isEmpty()) {
                    p.isPlaying = false;
                    System.out.println("Player " + p.playerNo + " has no cards remaining");
                    if (winners.isEmpty()) {
                        winners.add(p.playerNo);
                        playerCount--;
                    } else {
                        if (!winners.contains(p.playerNo)) {
                            winners.add(p.playerNo);
                            playerCount--;
                        }
                    }
                }
                if (playerCount == 1) {
                    running = false;
                }
            }
        }
        System.out.println("GAME OVER!! Winners: " +
                "\n------------------****------------------");
        for (int n: winners) {
            System.out.println("Player " + n);
        }
        System.out.println("------------------****------------------");
    }
    public static Card checkPlayerChoice(Player p) {
        Scanner reader = new Scanner(System.in);
        Card playerChoice = p.playerTurn();
        boolean choiceValid = false;
            // This while loop enables players to go back on their choice of cards
            // If they don't like their card's stats, they can go back and choose again
        while (!choiceValid) {
            if (playerChoice != null) {
                System.out.println(playerChoice.getName() + "'s data:");
                System.out.println("0 Hardness: " + playerChoice.getHardness() +
                        "\n1 Specific gravity: " + playerChoice.getSpecificGravity() +
                        "\n2 Cleavage: " + playerChoice.getCleavage() +
                        "\n3 Crustal abundance: " + playerChoice.getCrustalAbundance() +
                        "\n4 Economic value: " + playerChoice.getEconomicValue() +
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
                    System.out.println("***Error: Please enter proper input***");
                }
            } else {
                return playerChoice;
            }
        }
        return playerChoice;
    }
    public static int playersPassed() {
        int playersPassed = 0;
        for (Player p: players) {
            if (!p.playerTurn && p.isPlaying) {
                playersPassed++;
            }
        }
        return playersPassed;
    }
    public static void bringBackPlayers() {
        for (Player p: players) {
            p.playerTurn = true;
        }
    }
}
