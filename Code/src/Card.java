/**
 * Created by John on 8/26/2016.
 */
public abstract class Card {
    public String name;

    Card(String name) {
        this.name = name;
    }

    abstract Double getHardness();
    abstract Double getSpecificGravity();
    abstract String getCleavage();
    abstract Integer getCrustalAbundance();
    abstract String getEconomicValue();
    abstract String getCategory();

    public String getName() {
        return "Card name: " + name;
    }
}

class MCard extends Card {
    public Double hardness;
    public Double specificGravity;
    public String cleavage;
    public Integer crustalAbundance;
    public String economicValue;

    MCard(String name, Double hardness, Double specificGravity, String cleavage, Integer crustalAbundance, String economicValue) {

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
    Double getHardness() {
        return hardness;
    }
    Double getSpecificGravity() {
        return specificGravity;
    }
    String getCleavage() {
        return "Cleavage: " + cleavage;
    }
    Integer getCrustalAbundance() {
        return crustalAbundance;
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
    Double getHardness() {
        return null;
    }
    Double getSpecificGravity() {
        return null;
    }
    String getCleavage() {
        return "Cleavage: N/A";
    }
    Integer getCrustalAbundance() {
        return null;
    }
    String getEconomicValue() {
        return "Economic value: N/A";
    }
    String getCategory() {
        return "Category: " + category;
    }
}




