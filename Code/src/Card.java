/**
 * Created by John on 8/26/2016.
 */
public abstract class Card {
    public String name;

    Card(String name) {
        this.name = name;
    }

    void print() {
        System.out.println(name);
    }

    abstract String printHardness();
}

class MCard extends Card {
    public String hardness;
    public String specificGravity;
    public String cleavage;
    public String crustalAbundance;
    public String economicValue;

    MCard(String name, String hardness, String specificGravity, String cleavage, String crustalAbundance, String economicValue) {

        super(name);
        this.hardness = hardness;
        this.specificGravity = specificGravity;
        this.cleavage = cleavage;
        this.crustalAbundance = crustalAbundance;
        this.economicValue = economicValue;

    }

    public String getHardness() {
        return hardness;
    }

    @Override
    String printHardness() {
        return hardness;
    }
}
class SCard extends Card {
    public String description;
    SCard(String name, String description) {
        super(name);
        this.description = description;
    }

    @Override
    String printHardness() {
        return "0";
    }
}




