package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DrinkBankTest {
    private DrinkBank d1;
    private DrinkBank d2;

    @BeforeEach
    public void setUp() {
        d1 = DrinkBank.getInstance();
    }

    @Test
    public void testGetInstance() {
        d2 = DrinkBank.getInstance();
        assertEquals(d1,d2);
    }

    @Test
    public void testSetListOfDrinksEmpty() {
        DrinkBank.getInstance().setListOfDrinksEmpty();
        Drink d1 = new Drink("Hi",0);
        Drink d2 = new Drink("Bye",0);
        DrinkBank.getInstance().addDrink(d1);
        DrinkBank.getInstance().addDrink(d2);
        assertEquals(2,DrinkBank.getInstance().getListOfDrinks().size());
        DrinkBank.getInstance().setListOfDrinksEmpty();
        assertEquals(0,DrinkBank.getInstance().getListOfDrinks().size());
    }

    @Test
    public void testDrinkBankEmpty() {
        DrinkBank.getInstance().setListOfDrinksEmpty();
        assertTrue(d1.getListOfDrinks().isEmpty());
    }

    @Test
    public void testAddDrinkOnce() {
        DrinkBank.getInstance().setListOfDrinksEmpty();
        Drink d1 = new Drink("Hi",0);
        DrinkBank.getInstance().addDrink(d1);
        assertEquals(1,DrinkBank.getInstance().getListOfDrinks().size());
    }

    @Test
    public void testAddDrinkMultipleTimes() {
        DrinkBank.getInstance().setListOfDrinksEmpty();
        Drink d1 = new Drink("Hi",0);
        Drink d2 = new Drink("Bye",0);
        DrinkBank.getInstance().addDrink(d1);
        DrinkBank.getInstance().addDrink(d2);
        assertEquals(2,DrinkBank.getInstance().getListOfDrinks().size());
    }

    @Test
    public void testAddBaseDrinksInitialEmpty() {
        DrinkBank.addBaseDrinks(d1);
        assertEquals(6,d1.getListOfDrinks().size());
        assertEquals("Water",d1.getListOfDrinks().get(0).getName());
        assertEquals(0,d1.getListOfDrinks().get(0).getRarity());
    }

    @Test
    public void testAddBaseDrinksInitialNonEmpty() {
        d1.setListOfDrinksEmpty();
        Drink drink = new Drink("Cola",2);
        DrinkBank.getInstance().addDrink(drink);
        assertEquals(1,d1.getListOfDrinks().size());
        DrinkBank.addBaseDrinks(d1);
        assertEquals(1,d1.getListOfDrinks().size());
    }



}
