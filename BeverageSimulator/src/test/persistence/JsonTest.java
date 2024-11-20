package persistence;

import model.Drink;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Source: In class Workroom App Example
public class JsonTest {
    protected void checkDrink(String name, int rarity, Drink d) {
        assertEquals(name,d.getName());
        assertEquals(rarity, d.getRarity());
    }
}
