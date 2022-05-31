package persistence;

import model.Items;
import model.ShoppingCart;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads Shopping cart from JSON data stored in file
public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads shopping cart from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShoppingCart read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShoppingCart(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ShoppingCart parseShoppingCart(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double total = jsonObject.getDouble("total");

        ShoppingCart sc = new ShoppingCart(name,total);
        addItems(sc, jsonObject);
        return sc;
    }

    // MODIFIES: sc
    // EFFECTS: parses Items from JSON object and adds them to shoppingCart
    private void addItems(ShoppingCart sc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItems = (JSONObject) json;
            addItem(sc, nextItems);
        }
    }

    // MODIFIES: sc
    // EFFECTS: parses item from JSON object and adds it to workroom
    private void addItem(ShoppingCart sc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int quantity = jsonObject.getInt("quantity");
        Items item = new Items(name, quantity);
        sc.addItem(item);
    }
}

