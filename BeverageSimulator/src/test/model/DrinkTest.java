package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Drink;

public class DrinkTest {
    private Drink d0;
    private Drink d1;
    private Drink d2;
    private Drink d3;

    @BeforeEach
    public void setUp() {
        d0 = new Drink("Water",0);
        d1 = new Drink("Ice Water",1);
        d2 = new Drink("Delicious Water", 2);
        d3 = new Drink("Soup",3);
    }

    @Test
    public void testConstructorLowerBoundary() {
        assertEquals("Water", d0.getName());
        assertEquals(0, d0.getRarity());
        assertEquals(d0.getCommonAmount(), d0.getDrinkValue());
    }

    @Test
    public void testConstructorMiddleCaseOne() {
        assertEquals("Ice Water", d1.getName());
        assertEquals(1, d1.getRarity());
        assertEquals(d1.getUncommonAmount(), d1.getDrinkValue());
    }

    @Test
    public void testConstructorMiddleCaseTwo() {
        assertEquals("Delicious Water", d2.getName());
        assertEquals(2, d2.getRarity());
        assertEquals(d2.getRareAmount(), d2.getDrinkValue());
    }

    @Test
    public void testConstructorUpperBoundary() {
        assertEquals("Soup", d3.getName());
        assertEquals(3, d3.getRarity());
        assertEquals(d3.getLegendaryAmount(), d3.getDrinkValue());
    }
}
