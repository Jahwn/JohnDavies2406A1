/**
 * Created by John on 8/26/2016.
 */
public abstract class Card {
    public String name;

    Card(String name) {
        this.name = name;
    }

    abstract String getHardness();
    abstract String getSpecificGravity();
    abstract String getCleavage();
    abstract String getCrustalAbundance();
    abstract String getEconomicValue();
    abstract String getCategory();

    public String getName() {
        return "Card name: " + name;
    }
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
        return "Hardness: " + hardness;
    }
    String getSpecificGravity() {
        return "Specific gravity: " + specificGravity;
    }
    String getCleavage() {
        return "Cleavage: " + cleavage;
    }
    String getCrustalAbundance() {
        return "Crustal abundance: " + crustalAbundance;
    }
    String getEconomicValue() {
        return "Economic value: " +economicValue;
    }
    String getCategory() {
        return "Category: N/A";
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
        return "Hardness: N/A";
    }
    String getSpecificGravity() {
        return "Specific gravity: N/A";
    }
    String getCleavage() {
        return "Cleavage: N/A";
    }
    String getCrustalAbundance() {
        return "Crustal abundance: N/A";
    }
    String getEconomicValue() {
        return "Economic value: N/A";
    }
    String getCategory() {
        return "Category: " + category;
    }
}




