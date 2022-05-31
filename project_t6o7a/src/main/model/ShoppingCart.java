package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Items;


// Represent item's SKU, name, price,quantity,total(in dollars)
public class ShoppingCart implements Writable {
    private String customerName;


    private double total;
    final double discount = 0.8;


    private List<Items> newCart;
    private List<Integer> num;
    private List<Double> price = new ArrayList<>();





    /*
     * REQUIRES: Customer's name can't be null,total price >0
     * EFFECTS: The default shopping cart is empty, with the name of the customer.
     */

    public ShoppingCart(String name,double total) {
        this.customerName = name;
        this.total = total;

        newCart = new ArrayList<>();
        num = new ArrayList<>();
    }

    public double getTotal() {
        DecimalFormat f = new DecimalFormat("##.00");
        return Double.parseDouble(f.format(total));
    }


    public void setTotal(double t) {
        total = t;
    }

    public double getDiscount() {
        return discount;
    }

    public List<Integer> getNum() {
        return num;
    }

    public List<Items> getNewCart() {
        return newCart;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Double> getPrice() {
        return price;
    }

    /*
     *REQUIRES: quantity >0,name >0
     * MODIFY: this
     * EFFECTS: add item to shopping cart and update the correct quantity to the cart.
     * Update the total in the cart.
     *
     */

    public void addItem(Items items) {

        newCart.add(items);
        num.add(items.getQuantity());
        price.add(items.getPrice());
        EventLog.getInstance().logEvent(new Event("Add the item " + items.getName()));
        EventLog.getInstance().logEvent(new Event("Quantity: " + items.getQuantity()
                + " to the shopping cart"));

        total += items.getPrice() * items.getQuantity();


    }


    /*
     *MODIFIES:this
     * EFFECT: read the total price and applies discount if user has a membership
     */
    public void applyDiscount() {
        total = total * discount;
        EventLog.getInstance().logEvent(new Event("Apply 20% discount"));

    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", customerName);
        json.put("total",total);
        json.put("items", newCartToJson());
        return json;
    }

    // EFFECTS: returns new cart in this shopping cart as a JSON array
    private JSONArray newCartToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Items i : newCart) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

}
