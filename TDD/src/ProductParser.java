import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductParser {

    private static final String PRODUCTSTRING_COMPOSITION_REGEX = "([0-9]+) (.*?) at ([0-9]+\\.[0-9]{2})";
    public static final String IMPORTED_PRODUCT_LABEL = "imported ";

    public static List<String> splitProductsString(String products) {
        List<String> result = new ArrayList<>();

        Matcher matcher = Pattern
                .compile(PRODUCTSTRING_COMPOSITION_REGEX)
                .matcher(products);

        while(matcher.find())
            result.add(matcher.group());

        return result;
    }

    public static Product productFromString(String product) {
        Matcher matcher = Pattern
                .compile(PRODUCTSTRING_COMPOSITION_REGEX)
                .matcher(product);

        matcher.matches();

        int quantity        = Integer.valueOf(matcher.group(1));
        String name         = sanitizeProductName(matcher.group(2));
        BigDecimal price    = new BigDecimal(matcher.group(3));
        boolean isImported  = matcher.group(2).contains(IMPORTED_PRODUCT_LABEL);

        return new Product(quantity, name, price, isImported);
    }

    private static String sanitizeProductName(String name) {
        return name.replace(IMPORTED_PRODUCT_LABEL, "");
    }
}
