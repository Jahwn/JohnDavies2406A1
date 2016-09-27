import java.util.*;

/**
 * Created by John on 9/2/2016.
 */
public class Player {
    public int playerNo;
    public ArrayList<Card> pCards;
    boolean playerPassed = false;
    boolean isPlaying = true;

    Player(int playerNo, ArrayList<Card> pCards) {
        this.playerNo = playerNo;
        this.pCards = pCards;
    }

    public Card getPlayerCard(int index) {
        return pCards.get(index);
    }

    Scanner reader = new Scanner(System.in);

    public Card playerTurn() {
        if (pCards.size() != 0) {
            Card cardChoice = null;
            int n = 0;
            System.out.println("Your card(s):");

            boolean containsTheGeophysicist = false;
            boolean containsMagnetite = false;
            for (Card c: pCards) {
                System.out.println(n + " " + c.getName());
                n++;
                if (c.getName().equals("The Geophysicist")) {
                    containsTheGeophysicist = true;
                }
                if (c.getName().equals("Magnetite")) {
                    containsMagnetite = true;
                }
            }
            if (containsMagnetite && containsTheGeophysicist) {
                System.out.println("***Your deck contains Magnetite and The Geophysicist***" +
                "\nWould you like to draw both of these cards? Press 'y' or 'n'");
                boolean inputValid = false;
                while (!inputValid) {
                    String input = reader.next();
                    if (input.equals("y")) {
                        System.out.println("SOME MAGICAL SHOULD HAPPEN HERE");
                        inputValid = true;
                    } else if (input.equals("n")) {
                        System.out.println("Continuing game as normal...");
                    }
                }
            }
            System.out.print("Enter 'p' to pass or choose card by typing the number next to their name: ");
            String input = reader.next();
            boolean inputValid = false;
            while (!inputValid) {
                try {
                    if (input.equals("p")) {
                        cardChoice = null;
                        playerPassed = true;
                        inputValid = true;
                    } else {
                        cardChoice = pCards.get(Integer.parseInt(input));
                        pCards.remove(Integer.parseInt(input));
                        inputValid = true;
                    }
                } catch (Exception e) {
                    System.out.println("***Error: Please enter proper input***");
                    input = reader.next();
                }
            }
            return cardChoice;
        } else {
            return null;
        }
    }
}
