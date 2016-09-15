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

    public Integer getCrustalAbundance(String value) {
        Integer crustalAbundance = 0;
        if (value.equals("ultratrace")) {
            crustalAbundance = 0;
        } else if (value.equals("trace")) {
            crustalAbundance = 1;
        } else if (value.equals("low")) {
            crustalAbundance = 2;
        } else if (value.equals("moderate")) {
            crustalAbundance = 3;
        } else if (value.equals("high")) {
            crustalAbundance = 4;
        } else if (value.equals("very high")) {
            crustalAbundance = 5;
        }

        return crustalAbundance;
    }
}
