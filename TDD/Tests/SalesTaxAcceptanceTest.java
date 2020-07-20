import Category.CatalogExt;
import Tax.TaxRateImp;
import org.junit.Test;

import static org.junit.Assert.*;

public class SalesTaxesAcceptanceTests {

    @Test
    public void Input1AcceptanceTest() {
        String input =
                "1 book at 12.49" + " " +
                        "1 music CD at 14.99" + " " +
                        "1 chocolate bar at 0.85";
        String expected =
                "1 book: 12.49" + " " +
                        "1 music CD: 16.49" + " " +
                        "1 chocolate bar: 0.85" + " " +
                        "Sales Taxes: 1.50" + " " +
                        "Total: 29.83";

        assertSalesTaxesAT(input, expected);
    }

    @Test
    public void Input2AcceptanceTest() {
        String input =
                "1 imported box of chocolates at 10.00" + " " +
                        "1 imported bottle of perfume at 47.50";
        String expected =
                "1 imported box of chocolates: 10.50" + " " +
                        "1 imported bottle of perfume: 54.65" + " " +
                        "Sales Taxes: 7.65" + " " +
                        "Total: 65.15";

        assertSalesTaxesAT(input, expected);
    }

    @Test
    public void Input3AcceptanceTest() {
        String input =
                "1 imported bottle of perfume at 27.99" + " " +
                        "1 bottle of perfume at 18.99" + " " +
                        "1 packet of headache pills at 9.75" + " " +
                        "1 box of imported chocolates at 11.25";
        String expected =
                "1 imported bottle of perfume: 32.19" + " " +
                        "1 bottle of perfume: 20.89" + " " +
                        "1 packet of headache pills: 9.75" + " " +
                        "1 imported box of chocolates: 11.85" + " " +
                        "Sales Taxes: 6.70" + " " +
                        "Total: 74.68";

        assertSalesTaxesAT(input, expected);
    }

    private void assertSalesTaxesAT(String input, String expected) {
        CashDesk cd = new CashDesk(
                new Printer(),
                new ProductParser(),
                new TaxImp(),
                new CatalogExt()
        );

        cd.scanProducts(input);

        String receipt = cd.produceReceipt();
        assertEquals(expected, receipt);
    }
}