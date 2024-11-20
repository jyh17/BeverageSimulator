package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentDrinkTest {
    private CurrentDrink c1;
    private CurrentDrink c2;

    @Test
    public void testGetInstance() {
        c1 = CurrentDrink.getInstance();
        c2 = CurrentDrink.getInstance();
        assertEquals(c1,c2);
    }

    @Test
    public void testSetAndGetCurrentDrink() {
        c1 = CurrentDrink.getInstance();
        Drink d = new Drink("Chicken Soup", 3);
        c1.setCurrentDrink(d);
        assertEquals(d,c1.getCurrentDrink());
    }
}
