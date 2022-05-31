package model;

import org.json.JSONObject;
import persistence.Writable;

public class Items implements Writable {

    private final String name;
    private int quantity;
    private double price;

    final double priceApple = 2.99;
    final double priceBanana = 1.39;
    final double priceWatermelon = 4.99;
    final double pricePeach = 5.69;
    final double priceOrange = 3.89;

    /*
     *REQUIRE: quantity > 0
     *EFFECTS: Item with name and quantity,and fixed price for each item
     */

    public Items(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        itemPrice(name);
    }


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    /*
     *MODIFIES:this
     * EFFECTS: Load correct item price for each item.
     */
    public void itemPrice(String name) {
        if (name.equalsIgnoreCase("Banana")) {
            price = priceBanana;
        } else if (name.equalsIgnoreCase("Apple")) {
            price = priceApple;
        } else if (name.equalsIgnoreCase("Peach")) {
            price = pricePeach;
        } else if (name.equalsIgnoreCase("Watermelon")) {
            price = priceWatermelon;
        } else if (name.equalsIgnoreCase("Orange")) {
            price = priceOrange;
        } else {
            price = 0;
        }
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("quantity", quantity);
        json.put("price", price);
        return json;
    }

}

