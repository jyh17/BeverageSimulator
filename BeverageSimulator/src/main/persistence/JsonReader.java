package persistence;

import model.DrinkBank;
import model.Drink;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads drinkbank from JSON data stored in file
// Source: In class Workroom App Example
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads drinkbank from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DrinkBank read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDrinkBank(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses drink bank from JSON object and returns it
    private DrinkBank parseDrinkBank(JSONObject jsonObject) {
        DrinkBank d = DrinkBank.getInstance();
        addDrinks(d, jsonObject);
        return d;
    }

    // EFFECTS: parses drinks from JSON object and adds them to drink bank
    private void addDrinks(DrinkBank d, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("drinks");
        for (Object json : jsonArray) {
            JSONObject nextDrink = (JSONObject) json;
            addDrink(d, nextDrink);
        }
    }

    // MODIFIES: db
    // EFFECTS: parses drink from JSON object and adds it to drink bank
    private void addDrink(DrinkBank db, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer rarity = jsonObject.getInt("rarity");
        Drink d = new Drink(name, rarity);
        db.addDrink(d);
    }
}
