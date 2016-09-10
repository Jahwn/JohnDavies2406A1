import java.util.ArrayList;

/**
 * Created by John on 2/09/2016.
 */
public class Test {

    public static void main(String[] args) {

        //********************Card Class Testing*******************
        ArrayList<Card> cardArray = new ArrayList<Card>();

        cardArray.add(new MCard("PHIL", 1, 1.2, "hello", "hello 1", "hello 2"));

        for(Card p: cardArray) {
            //System.out.println(p.name);
        }

        //******************Player Class Testing**********************
        Player player = new Player(1, new ArrayList<Card>());

        player.pCards.add(new MCard("John", 1, 1.2, "hello", "hello 1", "hello 2"));
        player.pCards.add(new MCard("Bill", 1, 1.2, "hello", "hello 1", "hello 2"));
        player.pCards.add(new MCard("Lucy", 1, 1.2, "hello", "hello 1", "hello 2"));
        player.pCards.add(new MCard("Asshole", 3, 1.2, "hello", "hello 1", "hello 2"));
        for(Card c: player.pCards) {
            System.out.println(c.name);
        }
        player.print();

    }
}
/*
public int id_no;
    public String name;
    public int hardness;
    public double specific_gravity;
    public String cleavage;
    public String crustal_abundance;
    public String economic_value;
 */
