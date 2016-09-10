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

    abstract int printHardness();
}

class MCard extends Card {
    public int hardness;
    public double specificGravity;
    public String cleavage;
    public String crustalAbundance;
    public String economicValue;

    MCard(String name, int hardness, double specificGravity, String cleavage, String crustalAbundance, String economicValue) {

        super(name);
        this.hardness = hardness;
        this.specificGravity = specificGravity;
        this.cleavage = cleavage;
        this.crustalAbundance = crustalAbundance;
        this.economicValue = economicValue;

    }

    public int getHardness() {
        return hardness;
    }

    @Override
    int printHardness() {
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
    int printHardness() {
        return 0;
    }
}




