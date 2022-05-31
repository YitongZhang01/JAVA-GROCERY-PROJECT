package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingCartTest {

    private ShoppingCart testCart;
    private ShoppingCart emptyTestCart;
    private Items cart;
    private Items cart2;

    @BeforeEach
    void runBefore(){
        testCart=new ShoppingCart("Lily",0);
    }


    @Test
    void testFinalVar(){
        assertEquals(0.8,testCart.getDiscount());
        assertTrue(testCart.getPrice().isEmpty());
        assertTrue(testCart.getNewCart().isEmpty());
        assertTrue(testCart.getNum().isEmpty());
    }
    @Test
    void testConstructor(){
        assertEquals("Lily",testCart.getCustomerName());
        assertEquals(0.0,testCart.getTotal());



    }

    @Test
    void testGetTotal(){
        testCart.setTotal(2.870000003);
        assertEquals(2.87, testCart.getTotal());
    }


    @Test
    void testAddItem() {
        emptyTestCart = new ShoppingCart("", 0);

        cart = new Items("Apple", 1);
        emptyTestCart.addItem(cart);
        assertEquals("Apple", emptyTestCart.getNewCart().get(0));
        assertEquals(1, emptyTestCart.getNum().get(0));
        assertEquals(2.99, emptyTestCart.getPrice().get(0));
        assertEquals(2.99, emptyTestCart.getTotal());

        cart2 = new Items("Banana", 1);
        emptyTestCart.addItem(cart2);
        assertEquals("Banana", emptyTestCart.getNewCart().get(1));
        assertEquals(1, emptyTestCart.getNum().get(1));
        assertEquals(1.39, emptyTestCart.getPrice().get(1));
        assertEquals(4.38,emptyTestCart.getTotal());


    }


    @Test
    void testApplyDiscount(){
        testCart.setTotal(20.99);
        testCart.applyDiscount();
        assertEquals(16.79,testCart.getTotal());

    }

}