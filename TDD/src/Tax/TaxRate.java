package Tax;

import Category.Category;
import java.math.BigDecimal;

public interface TaxRate {
    BigDecimal calculateInflation(Category category, boolean isImported);
}
