package Category;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Catalog {

    protected final Map<Category, Set<String>> categoryMap;

    public Catalog() {
        categoryMap = new HashMap<>();
        fillCategoryMap();
    }

    public Category fromProductName(String name) {
        for(Map.Entry<Category, Set<String>> entry : categoryMap.entrySet()) {
            Category category = entry.getKey();
            Set<String> names = entry.getValue();

            if(names.contains(name))
                return category;
        }
        return Category.Goods;
    }
    protected abstract void fillCategoryMap();
}
