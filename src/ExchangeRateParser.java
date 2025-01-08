import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ExchangeRateParser {
    public static double getRate(String json, String currency) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.getAsJsonObject("conversion_rates").get(currency).getAsDouble();
    }
}
