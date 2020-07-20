import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt {

    public String produce(List<Product> products) {
        String productsDescriptions = products.stream()
                .map(Product::toString)
                .collect(Collectors.joining(" "));

        BigDecimal salesTax = products.stream()
                .map(Product::getTaxAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal total = products.stream()
                .map(Product::getTaxedPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return String.format(
                "%s Sales Taxes: %s Total: %s",
                productsDescriptions,
                salesTax,
                total
        );
    }
}
