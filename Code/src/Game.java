/**
 * Created by John on 9/2/2016.
 */
import javax.swing.*;
import java.lang.*;
import java.util.*;

public class Game {

    public static void main(String[] args) {

        ArrayList<Player> players = new ArrayList<Player>();

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
