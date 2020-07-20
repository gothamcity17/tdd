import Category.Category;
import Tax.TaxRate;

import javax.xml.catalog.Catalog;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private int quantity;
    private String name;
    private BigDecimal netPrice;
    private boolean isImported;

    private Category category;
    private BigDecimal inflation;

    public Product(int quantity, String name, BigDecimal netPrice, boolean isImported) {
        this.quantity = quantity;
        this.name = name;
        this.netPrice = netPrice;
        this.isImported = isImported;

        this.inflation = BigDecimal.ZERO;
        this.category = Category.Goods;
    }

    @Override
    public String toString() {
        return
                quantity + " " +
                        getImportedLabel() +
                        name + ": "+
                        getTaxedPrice();
    }

    public Product applyCategory(Catalog catalog) {
        category = catalog.fromProductName(name);
        return this;
    }

    public Product applyTaxRule(TaxRate taxRate) {
        inflation = taxRate.calculateInflation(category, isImported);
        return this;
    }

    public BigDecimal getTaxedPrice() {
        return getUnitTaxedPrice()
                .multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getTaxAmount() {
        return getUnitTaxAmount()
                .multiply(BigDecimal.valueOf(quantity));
    }

    private BigDecimal getUnitTaxedPrice() {
        return netPrice
                .add(getUnitTaxAmount())
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private BigDecimal getUnitTaxAmount() {
        BigDecimal taxAmount = netPrice.multiply(inflation);
        return roundAmountToTheNearestFiveCents(taxAmount);
    }

    private BigDecimal roundAmountToTheNearestFiveCents(BigDecimal taxes) {
        return taxes
                .divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(0.05))
                .setScale(2);
    }

    private String getImportedLabel() {
        return isImported ? "imported " : "";
    }
}
