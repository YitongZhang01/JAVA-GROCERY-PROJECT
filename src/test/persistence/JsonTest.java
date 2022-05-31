package persistence;

import model.Items;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItems(String name, int quantity,Items items) {
        assertEquals(name, items.getName());
        assertEquals(quantity,items.getQuantity());
    }
}