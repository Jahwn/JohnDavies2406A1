import java.util.ArrayList;

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

    void print() {
        for (Card a: pCards) {
            System.out.println(a.name);
        }
    }
}
