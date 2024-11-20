package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import model.DrinkBank;
import model.Drink;

// Source: In class Workroom App Example
public class JsonReaderTest extends JsonTest {
    private DrinkBank db;

    @BeforeEach
    public void setUp() {
        db = DrinkBank.getInstance();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DrinkBank db = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDrinkBank() {
        db.setListOfDrinksEmpty();
        JsonReader reader = new JsonReader("./data/testReaderEmptyDrinkBank.json");
        try {
            DrinkBank db = reader.read();
            assertEquals(0, db.getListOfDrinks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDrinkBank() {
        db.setListOfDrinksEmpty();
        JsonReader reader = new JsonReader("./data/testReaderGeneralDrinkBank.json");
        try {
            DrinkBank db = reader.read();
            List<Drink> drinks = db.getListOfDrinks();
            assertEquals(2, drinks.size());
            checkDrink("Water", 0, drinks.get(0));
            checkDrink("Mocha", 1, drinks.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
