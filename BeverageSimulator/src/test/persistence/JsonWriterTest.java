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
public class JsonWriterTest extends JsonTest {
    private DrinkBank db;

    @BeforeEach
    public void setUp() {
        db = DrinkBank.getInstance();
    }


    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDrinkBank() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDrinkBank.json");
            writer.open();
            writer.write(db);
            writer.close();

            db.setListOfDrinksEmpty();
            JsonReader reader = new JsonReader("./data/testWriterEmptyDrinkBank.json");
            db = reader.read();
            assertEquals(0, db.getListOfDrinks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDrinkBank() {
        try {
            db.addDrink(new Drink("Water", 0));
            db.addDrink(new Drink("Mocha", 1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDrinkBank.json");
            writer.open();
            writer.write(db);
            writer.close();

            db.setListOfDrinksEmpty();
            JsonReader reader = new JsonReader("./data/testWriterGeneralDrinkBank.json");
            db = reader.read();
            List<Drink> drinks = db.getListOfDrinks();
            assertEquals(2, drinks.size());
            checkDrink("Water", 0, drinks.get(0));
            checkDrink("Mocha", 1, drinks.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
