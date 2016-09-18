import java.util.regex.*;
import java.util.*;

/**
 * Created by John on 9/15/2016.
 */
// This class converts data from string to numerical formats such double or integer values from the DeckConstructor class
public class CardDataFetcher {
    public Double getHardnessValue(String value) {
        Matcher m = Pattern.compile("(?!=\\d\\.\\d\\.)([\\d.]+)").matcher(value);
        double hardness = 0;
        while(m.find()) {
            hardness = Double.parseDouble(m.group(1));
        }
        return hardness;
    }

    public Double getSpecificGravityValue(String value) {
        Matcher m = Pattern.compile("(?!=\\d\\.\\d\\.)([\\d.]+)").matcher(value);
        double specificGravity = 0;
        while(m.find()) {
            specificGravity = Double.parseDouble(m.group(1));
        }
        return specificGravity;
    }

    public Double getCrustalAbundance(String value) {
        Double crustalAbundance = 0.0;
        if (value.equals("ultratrace")) {
            crustalAbundance = 0.0;
        } else if (value.equals("trace")) {
            crustalAbundance = 1.0;
        } else if (value.equals("low")) {
            crustalAbundance = 2.0;
        } else if (value.equals("moderate")) {
            crustalAbundance = 3.0;
        } else if (value.equals("high")) {
            crustalAbundance = 4.0;
        } else if (value.equals("very high")) {
            crustalAbundance = 5.0;
        }

        return crustalAbundance;
    }

    public Double getEconomicValue(String value) {
        Double economicValue = 0.0;
        if (value.equals("trivial")) {
            economicValue = 0.0;
        } else if (value.equals("low")) {
            economicValue = 1.0;
        } else if (value.equals("moderate")) {
            economicValue = 2.0;
        } else if (value.equals("high")) {
            economicValue = 3.0;
        } else if (value.equals("very high")) {
            economicValue = 4.0;
        } else if (value.equals("I'm rich!")) {
            economicValue = 5.0;
        }

        return economicValue;
    }

    public boolean isTrumpCard(String cName) {
        ArrayList<String> tCardName = new ArrayList<String>(
                Arrays.asList("The Miner", "The Petrologist", "The Gemmologist", "The Mineralogist", "The Geophysicist", "The Geologist"));
        Boolean isTrumpCard = false;
        for (String s: tCardName) {
            if (cName.equals(s)) {
                isTrumpCard = true;
            }
        }
        return isTrumpCard;
    }
}
