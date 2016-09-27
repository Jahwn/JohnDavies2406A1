/**
 * Created by John on 9/2/2016.
 */
/*
 * ****************************
 * INTERNET CONNECTION REQUIRED
 * ****************************
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

        // Table will contain the card currently in play
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
            while(s.pCards.size() < 1) {
                s.pCards.add(deck.get(0));
                deck.remove(0);
            }
        }

        Boolean running = true;

        int round = 1;

        ArrayList<Integer> winners = new ArrayList<Integer>();

        String noCategory = "No category";

        boolean roundStarted = false;

        // This is where players take their turns
        while (running) {
            for (Player p : players) {
                boolean turnValid = false;
                // If the player has already emptied their deck and won, disregard their turn by making the program think they've passed and have had their turn
                if (!p.isPlaying) {
                    p.playerPassed = true;
                    turnValid = true;
                }

                // Check the number of players who have passed
                int playersPassed = playersPassed();

                // If player hasn't passed...
                if (!p.playerPassed) {
                    // If all but 1 player has passed, reset the category and announce the winner of the round
                    if (playersPassed == playerCount - 1) {
                        category = noCategory;
                        System.out.println("----------------------------------------" +
                                "\nPlayer " + p.playerNo + " won round " + round +
                                "\n----------------------------------------");
                        // Move to the next round
                        round++;
                        roundStarted = false;
                    }
                }

                // If this is a new round, and the player is still in the game, announce the new round
                if (!roundStarted && p.isPlaying) {
                    System.out.println("----------------------------------------" +
                            "\nRound " + round +
                            "\n----------------------------------------");
                    // Because this is a new round, all players who have passed are now back in
                    bringBackPlayers();
                }

                // If this player has passed, skip this turn
                if (p.playerPassed) {
                    System.out.println("Player " + p.playerNo + " is out of the round");
                    turnValid = true;
                } else {
                    // Because the program thinks winning players have passed, a check to see if they're still playing is needed
                    if (p.isPlaying) {
                        System.out.println("Player " + p.playerNo + "'s turn");
                    }
                }

                while (!turnValid) {
                    boolean tableIsEmpty = false;
                    if (category.equals(noCategory)) {
                        tableIsEmpty = true;
                    }

                    // Bring up interface for card selection
                    Card playerChoice = checkPlayerChoice(p);

                    /*while (tableIsEmpty) {
                        if (playerChoice == null) {
                            System.out.println("Please draw a card");
                            playerChoice = checkPlayerChoice(p);
                            p.playerPassed = false;
                        } else {
                            tableIsEmpty = false;
                        }
                    }*/

                    // If the player has chosen to pass
                    if (playerChoice == null) {
                        if (p.isPlaying) {
                            p.pCards.add(deck.get(0));
                            System.out.println(deck.get(0).getName() + " added to your hand Player " + p.playerNo);
                            deck.remove(0);
                            p.playerPassed = true;
                            turnValid = true;
                            roundStarted = true;
                        }
                    } else {
                        roundStarted = true;
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
                            // If a category doesn't already exist, then the player must choose one
                            if (category.equals(noCategory)) {
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
                // If the player emptied their deck after a drawing a card, put them down as a winner
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
                // If there is only 1 player left, end the game and announce the winners
                if (playerCount == 1) {
                    running = false;
                    break;
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
            if (p.playerPassed && p.isPlaying) {
                playersPassed++;
            }
        }
        return playersPassed;
    }
    public static void bringBackPlayers() {
        for (Player p: players) {
            p.playerPassed = false;
        }
    }
}
