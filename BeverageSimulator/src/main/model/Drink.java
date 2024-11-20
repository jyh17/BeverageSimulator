package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Drink that can be consumed coins, a drink has a name and a rarity and a value
// Source: In class Workroom App Example
public class Drink implements Writable {
    private String name;
    private int rarity;
    private int drinkValue;
    private static int commonAmount = 10;
    private static int uncommonAmount = 50;
    private static int rareAmount = 100;
    private static int legendaryAmount = 300;

    // REQUIRES: 0 <= rarity <= 3
    // EFFECTS: constructs a drink with a name, with rarity 0,1,2,3 and a monetary value gained from drinking it.
    // 0 represents common, 1 represents uncommon, 2 represents rare, 3 represents legendary
    public Drink(String name, int rarity) {
        this.name = name;
        this.rarity = rarity;
        if (rarity == 0) {
            this.drinkValue = commonAmount;
        } else if (rarity == 1) {
            this.drinkValue = uncommonAmount;
        } else if (rarity == 2) {
            this.drinkValue = rareAmount;
        } else  {
            this.drinkValue = legendaryAmount;
        }
    }

    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }

    public int getDrinkValue() {
        return drinkValue;
    }

    public int getCommonAmount() {
        return commonAmount;
    }

    public int getUncommonAmount() {
        return uncommonAmount;
    }

    public int getRareAmount() {
        return rareAmount;
    }

    public int getLegendaryAmount() {
        return legendaryAmount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rarity", rarity);
        return json;
    }

}
