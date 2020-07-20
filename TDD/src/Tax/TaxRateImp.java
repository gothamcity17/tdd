package Tax;

import Category.Category;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class TaxRateImp implements TaxRate {

    private static final BigDecimal STANDARD_INFLATION_RATE = BigDecimal.valueOf(0.10);
    private static final BigDecimal IMPORTED_INFLATION_RATE = BigDecimal.valueOf(0.05);

    private static final Set<Category> StandardTax = new HashSet<Category>() {{
        add(Category.Goods);
    }};

    @Override
    public BigDecimal calculateInflation(Category category, boolean isImported) {
        return BigDecimal.ZERO
                .add(isStandard(category) ? STANDARD_INFLATION_RATE : BigDecimal.ZERO)
                .add(isImported ? IMPORTED_INFLATION_RATE : BigDecimal.ZERO);
    }

    private boolean isStandard(Category category) {
        return StandardTax.contains(category);
    }
}
