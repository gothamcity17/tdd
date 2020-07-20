import Category.CatalogExt;
import Tax.TaxRateImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.assertEquals;

public class DeskTest {

    private Desk desk;

    @Before
    public void setup() {
        Desk = new Desk(
                new Receipt(),
                new ProductParser(),
                new TaxRateImp(),
                new CatalogExt()
        );
    }

    @Test
    public void sellingOneBook() {
        cashDesk.scanProducts("1 book at 12.49");

        String expected =
                "1 book: 12.49" + " " +
                        "Sales Taxes: 0.00" + " " +
                        "Total: 12.49";

        assertReceipt(expected);
    }

    @Test
    public void sellingOneChocolateBar() {
        cashDesk.scanProducts("1 chocolate bar at 0.85");

        String expected =
                "1 chocolate bar: 0.85" + " " +
                        "Sales Taxes: 0.00" + " " +
                        "Total: 0.85";

        assertReceipt(expected);
    }

    @Test
    public void sellingOneMusicCD() {
        cashDesk.scanProducts("1 music CD at 14.99");

        String expected =
                "1 music CD: 16.49" + " " +
                        "Sales Taxes: 1.50" + " " +
                        "Total: 16.49";

        assertReceipt(expected);
    }

    @Test
    public void sellingTwoProducts() {
        cashDesk.scanProducts("1 book at 12.49");
        cashDesk.scanProducts("1 music CD at 14.99");

        String expected =
                "1 book: 12.49" + " " +
                        "1 music CD: 16.49" + " " +
                        "Sales Taxes: 1.50" + " " +
                        "Total: 28.98";

        assertReceipt(expected);
    }

    @Test
    public void sellingAnImportedFoodProduct() {
        cashDesk.scanProducts("1 imported box of chocolates at 10.00");

        String expected =
                "1 imported box of chocolates: 10.50" + " " +
                        "Sales Taxes: 0.50" + " " +
                        "Total: 10.50";

        assertReceipt(expected);
    }

    @Test
    public void sellingAnImportedStandardTaxableProduct() {
        cashDesk.scanProducts("1 imported bottle of perfume at 47.50");

        String expected =
                "1 imported bottle of perfume: 54.65" + " " +
                        "Sales Taxes: 7.15" + " " +
                        "Total: 54.65";

        assertReceipt(expected);
    }

    @Test
    public void importedProductsCouldHaveDifferentNameFormat() {
        cashDesk.scanProducts("1 box of imported chocolates at 11.25");

        String expected =
                "1 imported box of chocolates: 11.85" + " " +
                        "Sales Taxes: 0.60" + " " +
                        "Total: 11.85";

        assertReceipt(expected);
    }

    @Test
    public void sellingMultipleUnits() {
        cashDesk.scanProducts("2 imported bottle of perfume at 47.50");
        cashDesk.scanProducts("1 music CD at 14.99");
        cashDesk.scanProducts("3 book at 12.49");

        String expected =
                "2 imported bottle of perfume: 109.30" + " " +
                        "1 music CD: 16.49" + " " +
                        "3 book: 37.47" + " " +
                        "Sales Taxes: 15.80" + " " +
                        "Total: 163.26";

        assertReceipt(expected);
    }

    @Test
    public void sellingNewProducts() {
        cashDesk.scanProducts("1 Atomic Habits at 12.50");
        cashDesk.scanProducts("2 Aspirins at 7.50");
        cashDesk.scanProducts("3 Cafe Mocha's at 10.00");
        cashDesk.scanProducts("2 Croissants at 2.00");
        cashDesk.scanProducts("1 Coca-Cola 2.00");

        String expected =
                "1 Atomic Habits: 12.50" + " " +
                        "2 Aspirins: 7.50" + " " +
                        "3 Cafe Mocha's: 10.00" + " " +
                        "2 Croissants: 2.00" + " " +
                        "1 Coca-Colae: 2.00" + " " +
                        "Total: 34.00";

        assertReceipt(expected);
    }

    private void assertReceipt(String expected) {
        String receipt = cashDesk.produceReceipt();
        assertEquals(expected, receipt);
    }
}