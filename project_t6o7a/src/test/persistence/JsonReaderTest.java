package persistence;

import model.Items;
import model.ShoppingCart;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ShoppingCart sc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyShoppingCart() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyShoppingCart.json");
        try {
            ShoppingCart sc = reader.read();
            assertEquals("erica", sc.getCustomerName());
            assertEquals(0, sc.getTotal());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralShoppingCart() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingCart.json");
        try {
            ShoppingCart sc = reader.read();
            assertEquals("erica", sc.getCustomerName());
            List<Items> items = sc .getNewCart();
            assertEquals(2, items.size());
            checkItems("apple", 2, items.get(0));
            checkItems("banana", 3,items.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}