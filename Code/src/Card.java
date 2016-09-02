/**
 * Created by John on 8/26/2016.
 */
public class Card {
    public int idNo;
    public String name;
    public int hardness;
    public double specificGravity;
    public String cleavage;
    public String crustalAbundance;
    public String economicValue;

    Card(int id_no, String name, int hardness, double specificGravity, String cleavage, String crustalAbundance, String economicValue) {

        this.idNo = id_no;
        this.name = name;
        this.hardness = hardness;
        this.specificGravity = specificGravity;
        this.cleavage = cleavage;
        this.crustalAbundance = crustalAbundance;
        this.economicValue = economicValue;

    }

    void print() {
        System.out.println(name);
    }
}

