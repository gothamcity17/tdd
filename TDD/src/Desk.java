import Tax.TaxRate;

import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.List;

public class Desk {

    private Receipt receipt;
    private ProductParser productParser;
    private TaxRate taxRate;
    private Catalog catalog;

    private List<Product> scannedProducts;

    public Desk(Receipt r, ProductParser pp, TaxRate tr, Catalog c) {
        this.receipt = r;
        this.productParser = pp;
        this.taxRate = tr;
        this.catalog = c;

        this.scannedProducts = new ArrayList<>();
    }

    public String produceReceipt() {
        return receipt.produce(scannedProducts);
    }

    public void scanProducts(String productsString) {
        for(String product : productParser.splitProductsString(productsString))
            scanSingleProduct(product);
    }

    private void scanSingleProduct(String product) {
        Product p = productParser
                .productFromString(product)
                .applyCategory(catalog)
                .applyTaxRule(taxRate);

        this.scannedProducts.add(p);
    }





}
