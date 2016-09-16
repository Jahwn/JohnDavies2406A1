import java.util.*;

/**
 * Created by John on 9/2/2016.
 */
public class Player {
    public int playerNo;
    public ArrayList<Card> pCards;

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
            Card cardChoice;
            int n = 0;
            System.out.println("Your card(s):");
            for (Card c: pCards) {
                System.out.println(n + " " + c.getName());
                n++;
            }
            System.out.print("Enter 'p' to pass or choose card by typing the number next to their name: ");
            String input = reader.next();
            if (input.equals("p")) {
                cardChoice = null;
            } else {
                cardChoice = pCards.get(Integer.parseInt(input));
            }
            return cardChoice;
        } else {
            return null;
        }
    }
}
