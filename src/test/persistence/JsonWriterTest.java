package persistence;

import model.Items;
import model.ShoppingCart;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ShoppingCart sc = new ShoppingCart("erica",10.00);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyShoppingCart() {
        try {
            ShoppingCart sc = new ShoppingCart("erica",0.0);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShoppingCart.json");
            writer.open();
            writer.write(sc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShoppingCart.json");
            sc = reader.read();
            assertEquals("erica", sc.getCustomerName());
            assertEquals(0, sc.getTotal());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShoppingCart() {
        try {
            ShoppingCart sc = new ShoppingCart("erica",2.3);
            sc.addItem(new Items("apple", 2));
            sc.addItem(new Items("banana", 3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShoppingCart.json");
            writer.open();
            writer.write(sc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingCart.json");
            sc = reader.read();
            assertEquals("erica", sc.getCustomerName());
            List<Items> newCart = sc.getNewCart();
            assertEquals(2, newCart.size());
            checkItems("apple", 2, newCart.get(0));
            checkItems("banana", 3,newCart.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}