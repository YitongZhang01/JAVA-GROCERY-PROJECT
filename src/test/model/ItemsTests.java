package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemsTests {
    private Items testItem1;
    private Items testItem2;
    private Items testItem3;
    private Items testItem4;
    private Items testItem5;

    @BeforeEach
    void runBefore(){
        testItem1=new Items("Apple",4);
        testItem2=new Items("Banana",2);
        testItem3=new Items("Watermelon",2);
        testItem4=new Items("Peach",2);
        testItem5=new Items("Orange",2);




    }

    @Test
    void constructorTest(){
        assertEquals("Apple",testItem1.getName());
        assertEquals(4,testItem1.getQuantity());
        assertEquals(2.99,testItem1.getPrice());

        assertEquals("Banana",testItem2.getName());
        assertEquals(2,testItem2.getQuantity());
        assertEquals(1.39,testItem2.getPrice());

        assertEquals("Watermelon",testItem3.getName());
        assertEquals(2,testItem3.getQuantity());
        assertEquals(4.99,testItem3.getPrice());

        assertEquals("Peach",testItem4.getName());
        assertEquals(2,testItem4.getQuantity());
        assertEquals(5.69,testItem4.getPrice());

        assertEquals("Orange",testItem5.getName());
        assertEquals(2,testItem5.getQuantity());
        assertEquals(3.89,testItem5.getPrice());

    }



    @Test
    void itemPriceTest(){
        assertEquals(2.99,testItem1.getPrice());
        assertEquals(1.39,testItem2.getPrice());
        assertEquals(4.99,testItem3.getPrice());
        assertEquals(5.69,testItem4.getPrice());
        assertEquals(3.89,testItem5.getPrice());
        testItem1=new Items("Grape",2);
        assertEquals(0,testItem1.getPrice());


    }

    @Test
    void setQuantityTest(){
        testItem1.setQuantity(1);
        assertEquals(1,testItem1.getQuantity());
        testItem1.setQuantity(3);
        assertEquals(3,testItem1.getQuantity());
        testItem2.setQuantity(3);
        assertEquals(3,testItem2.getQuantity());
        testItem3.setQuantity(3);
        assertEquals(3,testItem3.getQuantity());
        testItem4.setQuantity(3);
        assertEquals(3,testItem4.getQuantity());
        testItem5.setQuantity(3);
        assertEquals(3,testItem5.getQuantity());

    }



}
