package Generator.DBMapper;

import java.text.Normalizer;

public class Standardizer {
    public static String NameStandardize(String sqlName){
        String convertedString =
         Normalizer
                        .normalize(sqlName, Normalizer.Form.NFD)
                        .replaceAll("\\s+", "");
        return convertedString;
    }
}
