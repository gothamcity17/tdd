package Category;

import java.util.HashSet;

public class CatalogExt extends Catalog {

    @Override
    protected void fillCategoryMap() {
        categoryMap.put(Category.Books, new HashSet<>() {{
            add("Books");
            add("Atomic Habits");
        }}
        );

        categoryMap.put(Category.Food, new HashSet<>() {{
            add("Box of Chocolates");
            add("Chocolate Bar");
            add("Cafe Mocha");
            add("Croissant");
            add("Coca-Cola");
        }}
        );

        categoryMap.put(Category.Medical, new HashSet<>() {{
            add("Packet of Headache Pills");
            add("Aspirin");
        }}
        );
    }
}
