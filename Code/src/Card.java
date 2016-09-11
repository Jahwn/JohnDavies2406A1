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

    abstract String getHardness();
    abstract String getSpecificGravity();
    abstract String getCleavage();
    abstract String getCrustalAbundance();
    abstract String getEconomicValue();
    abstract String getCategory();
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

    //public String getHardness() {
       //return hardness;
    //}

    @Override
    String getHardness() {
        return hardness;
    }
    String getSpecificGravity() {
        return specificGravity;
    }
    String getCleavage() {
        return cleavage;
    }
    String getCrustalAbundance() {
        return crustalAbundance;
    }
    String getEconomicValue() {
        return economicValue;
    }
    String getCategory() {
        return "N/A";
    }
}
class SCard extends Card {
    public String category;
    SCard(String name, String category) {
        super(name);
        this.category = category;
    }

    @Override
    String getHardness() {
        return "N/A";
    }
    String getSpecificGravity() {
        return "N/A";
    }
    String getCleavage() {
        return "N/A";
    }
    String getCrustalAbundance() {
        return "N/A";
    }
    String getEconomicValue() {
        return "N/A";
    }
    String getCategory() {
        return category;
    }
}




