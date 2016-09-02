/**
 * Created by John on 8/26/2016.
 */
public class Card {
    public int id_no;
    public String name;
    public int hardness;
    public double specific_gravity;
    public String cleavage;
    public String crustal_abundance;
    public String economic_value;

    Card(int id_no, String name, int hardness, double specific_gravity, String cleavage, String crustal_abundance, String economic_value) {

        this.id_no = id_no;
        this.name = name;
        this.hardness = hardness;
        this.specific_gravity = specific_gravity;
        this.cleavage = cleavage;
        this.crustal_abundance = crustal_abundance;
        this.economic_value = economic_value;

    }

    void print() {
        System.out.println(name);
    }
}

