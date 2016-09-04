/**
 * Created by John on 9/2/2016.
 */
import javax.swing.*;
import java.lang.*;
import java.util.*;

public class Game {

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();

        Object[] possibleValues = { 3, 4, 5 };
        Object userInput = JOptionPane.showInputDialog(null,
                "Choose how many players", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        int playerCount = (Integer) userInput;

        for (int n = 1; n <= playerCount; n++) {
            players.add(new Player(n, new ArrayList<Card>()));
        }
        // Testing player class
        for (Player s: players) {
            System.out.print(s.playerNo);
        }
    }

}
