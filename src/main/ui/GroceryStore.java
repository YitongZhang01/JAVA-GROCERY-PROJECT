package ui;

import model.Event;
import model.EventLog;
import model.Items;
import model.ShoppingCart;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Grocery shopping cart application
public class GroceryStore {
    private static final String JSON_STORE = "./data/shoppingcart.json ";
    private ShoppingCart shoppingCart;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //MODIFIES:this
    //EFFECTS:initialize shopping cart
    private void init() throws FileNotFoundException {
        shoppingCart = new ShoppingCart("Erica", 0.0);
        System.out.println("Account name：" + shoppingCart.getCustomerName());
        System.out.println("Shopping cart current total: " + shoppingCart.getTotal());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");


    }


    //EFFECTS: run the ShoppingCart application
    public GroceryStore() {
        runShoppingCart();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void runShoppingCart() {
        System.out.println("---------------------------------------------");
        System.out.println("Welcome to TT's Grocery store! ");
        System.out.println("---------------------------------------------");

        boolean run = true;
        String keyIn = null;

        try {
            init();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (run) {
            displayMenu();
            keyIn = input.next();

            if (keyIn.equals("q")) {
                run = false;
            } else {
                processInput(keyIn);
            }
        }



        System.out.println("\nGoodbye!");
        System.exit(0);
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processInput(String keyIn) {
        if (keyIn.equalsIgnoreCase("a")) {
            doAddItem();
        } else if (keyIn.equalsIgnoreCase("c")) {
            while (!keyIn.equalsIgnoreCase("y")) {
                doDiscount();
                System.out.println("Do you want to check out? (Y or N)");
                keyIn = input.next();
                if (keyIn.equalsIgnoreCase("y")) {
                    doCheckOut();
                    System.exit(0);
                }
                break;
            }


        } else if (keyIn.equalsIgnoreCase("l")) {
            loadShoppingCart();
        } else if (keyIn.equalsIgnoreCase("s")) {
            saveShoppingCart();
        } else {
            System.out.println("Selection not valid...");
        }

    }


    //EFFECTS: Prompt user and input
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add Item");
        System.out.println("\tc -> Check total and Checkout");
        System.out.println("\ts -> Save shopping cart to file");
        System.out.println("\tl -> Load shopping cart from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: A method that print out the List.

    public void printOutMenu() {

        List<String> fruitMenu = new ArrayList<>(5);
        fruitMenu.add("Apple");
        fruitMenu.add("Banana");
        fruitMenu.add("Watermelon");
        fruitMenu.add("Peach");
        fruitMenu.add("Orange");
        for (int i = 0; i < fruitMenu.size(); i++) {
            System.out.println(i + 1 + ".\t" + fruitMenu.get(i));
        }
    }

    //EFFECT: Print out shopping cart

    public void printOut() {

        System.out.println("-------------------Receipt-----------------");
        for (int i = 0; i < shoppingCart.getNewCart().size(); i++) {
            System.out.print(shoppingCart.getNewCart().get(i).getName());
            System.out.print("\t Quantity: " + shoppingCart.getNum().get(i));
            System.out.println("");
        }
        System.out.println("Total: " + shoppingCart.getTotal());

        System.out.println("Thank You for your purchase!");
    }

    //EFFECT:Check if user enter the correct input
    private boolean indexName(String name) {
        if (name.equalsIgnoreCase("Banana")) {
            return true;
        } else if (name.equalsIgnoreCase("Apple")) {
            return true;
        } else if (name.equalsIgnoreCase("peach")) {
            return true;
        } else if (name.equalsIgnoreCase("Watermelon")) {
            return true;
        } else if (name.equalsIgnoreCase("Orange")) {
            return true;
        } else {
            return false;
        }

    }

    //MODIFIES:this
    // EFFECTS: Add item to the shopping cart

    private void doAddItem() {
        System.out.println("\n Add which following items to your shopping cart?  ");
        printOutMenu();
        String garbage = input.nextLine();

        String name = input.nextLine();


        if (indexName(name)) {

            System.out.print("Please enter the quantities: ");

            int num = input.nextInt();

            shoppingCart.addItem(new Items(name, num));

        } else {
            System.out.println("Wrong input, please enter the correct item... ");
        }

    }




    //MODIFIES:this
    //EFFECTS: Check user's membership and apply discount

    private void doDiscount() {
        System.out.println("Do you have a membership with us? (Y or N) ");
        String garbage = input.nextLine();
        String member = input.nextLine();

        if (member.equalsIgnoreCase("Y")) {
            shoppingCart.applyDiscount();
            System.out.println("20% discount applies! Your total price is:" + shoppingCart.getTotal());
        } else {
            System.out.println("Sorry, no membership discount will apply...Your total price is:"
                    + shoppingCart.getTotal());
        }
    }

    //EFFECT: Show the items in the shopping cart with the final price.
    private void doCheckOut() {
        System.out.println("\n" + shoppingCart.getCustomerName());
        printOut();
    }


    // EFFECTS: saves the workroom to file
    private void saveShoppingCart() {
        try {
            jsonWriter.open();
            jsonWriter.write(shoppingCart);
            jsonWriter.close();
            System.out.println("Saved " + shoppingCart.getCustomerName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads shopping cart from file
    private void loadShoppingCart() {
        try {
            shoppingCart = jsonReader.read();
            System.out.println("Loaded " + shoppingCart.getCustomerName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

