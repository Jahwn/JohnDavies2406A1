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
    abstract Double getCleavage();
    abstract Double getCrustalAbundance();
    abstract Double getEconomicValue();
    abstract String getCategory();

    public String getName() {
        return "Card name: " + name;
    }
}

class MCard extends Card {
    public Double hardness;
    public Double specificGravity;
    public Double cleavage;
    public Double crustalAbundance;
    public Double economicValue;

    MCard(String name, Double hardness, Double specificGravity, Double cleavage, Double crustalAbundance, Double economicValue) {

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
    Double getCleavage() {
        return cleavage;
    }
    Double getCrustalAbundance() {
        return crustalAbundance;
    }
    Double getEconomicValue() {
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
    Double getHardness() {
        return null;
    }
    Double getSpecificGravity() {
        return null;
    }
    Double getCleavage() {
        return null;
    }
    Double getCrustalAbundance() {
        return null;
    }
    Double getEconomicValue() {
        return null;
    }
    String getCategory() {
        return category;
    }
}




