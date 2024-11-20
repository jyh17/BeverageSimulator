package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import persistence.Writable;

// Source: UniqueNameProvider Singleton Starter from Class
// Source: In class Workroom App Example
// A bank of all available drinks in the game and drinks to be drunk
public class DrinkBank implements Writable {
    private static DrinkBank singletonDrink;
    private ArrayList<Drink> listOfDrinks;

    // EFFECTS: creates a list of available drinks in the game
    private DrinkBank() {
        listOfDrinks = new ArrayList<>();
    }

    // EFFECTS: gets instance of statistics
    public static DrinkBank getInstance() {
        if (singletonDrink == null) {
            singletonDrink = new DrinkBank();
        }
        return singletonDrink;
    }

    public ArrayList<Drink> getListOfDrinks() {
        return listOfDrinks;
    }

    // MODIFIES: this
    // EFFECTS: adds given drink to list of available drinks
    public void addDrink(Drink d) {
        listOfDrinks.add(d);
    }

    // MODIFIES: this
    // EFFECTS: removes everything from list of drinks
    public void setListOfDrinksEmpty() {
        listOfDrinks.clear();
    }

    // MODIFIES: this
    // EFFECTS: adds base drinks into the drink bank
    public static void addBaseDrinks(DrinkBank db) {
        if (db.getListOfDrinks().isEmpty()) {
            DrinkBank l = DrinkBank.getInstance();
            Drink w = new Drink("Water",0);
            Drink bc = new Drink("Black Coffee", 0);
            Drink mo = new Drink("Mocha", 1);
            Drink ma = new Drink("Matcha", 1);
            Drink lem = new Drink("Lemonade", 2);
            Drink hor = new Drink("Horchata", 3);
            l.addDrink(w);
            l.addDrink(ma);
            l.addDrink(bc);
            l.addDrink(mo);
            l.addDrink(lem);
            l.addDrink(hor);
            CurrentDrink c = CurrentDrink.getInstance();
            c.setCurrentDrink(w);
        }
    }

    public ArrayList<Drink> getShiftedDrinks() {
        ArrayList<Drink> availableDrinks = DrinkBank.getInstance().getListOfDrinks();
        Drink lastDrink = availableDrinks.remove(availableDrinks.size() - 1);
        availableDrinks.add(0,lastDrink);
        EventLog.getInstance().logEvent(new model.Event("Shifted Drinks"));
        return availableDrinks;
    }

    public ArrayList<Drink> getFilteredDrinks(char filter) {
        ArrayList<Drink> filteredDrinks = new ArrayList<>();
        for (Drink d : DrinkBank.getInstance().getListOfDrinks()) {
            if (d.getName().charAt(0) == filter) {
                filteredDrinks.add(d);
            }
        }
        EventLog.getInstance().logEvent(new model.Event("Filter Drinks by Character" + " " + filter));
        return filteredDrinks;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("name", singletonDrink);
        json.put("drinks", drinksToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray drinksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Drink d : listOfDrinks) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }

}
