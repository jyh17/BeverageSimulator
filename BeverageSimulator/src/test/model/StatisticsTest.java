package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTest {
    private Statistics s1;
    private Statistics s2;
    private Drink d;

//    @Test
//    public void testConstructor() {
//        s1 = new Statistics();
//        assertEquals(0,s1.getDrinksHad());
//        assertNull(s1.getLastDrinkHad());
//    }

    @Test
    public void testGetInstance() {
        s1 = Statistics.getInstance();
        s2 = Statistics.getInstance();
        assertEquals(s1,s2);
    }

    @Test
    public void testSetDrinksHadZero() {
        s1 = Statistics.getInstance();
        Statistics.getInstance().setDrinksHadZero();
        assertEquals(0,s1.getDrinksHad());
        Statistics.getInstance().incrementDrinksHad();
        assertEquals(1,s1.getDrinksHad());
        Statistics.getInstance().incrementDrinksHad();
        assertEquals(2,s1.getDrinksHad());
        Statistics.getInstance().setDrinksHadZero();
        assertEquals(0,s1.getDrinksHad());
    }

    @Test
    public void testIncrementDrinksHadOnce() {
        s1 = Statistics.getInstance();
        Statistics.getInstance().setDrinksHadZero();
        Statistics.getInstance().incrementDrinksHad();
        assertEquals(1,s1.getDrinksHad());
    }

    @Test
    public void testIncrementDrinksHadMultipleTimes() {
        s1 = Statistics.getInstance();
        Statistics.getInstance().setDrinksHadZero();
        Statistics.getInstance().incrementDrinksHad();
        assertEquals(1,s1.getDrinksHad());
        Statistics.getInstance().incrementDrinksHad();
        assertEquals(2,s1.getDrinksHad());
        Statistics.getInstance().incrementDrinksHad();
        assertEquals(3,s1.getDrinksHad());
    }

    @Test
    public void testLastDrinkHad() {
        d = new Drink("Water",0);
        s1 = Statistics.getInstance();
        s1.setLastDrinkHadNull();
        Statistics.getInstance().setLastDrinkHad(d);
        assertEquals(d.getName(), s1.getLastDrinkHadName());
    }

    @Test
    public void testLastDrinkHadNull() {
        s1 = Statistics.getInstance();
        s1.setLastDrinkHadNull();
        assertEquals("No Drinks Yet!", s1.getLastDrinkHadName());
    }

    @Test
    public void testSetCoinsZero() {
        s1 = Statistics.getInstance();
        s1.setCoinsZero();
        Statistics.getInstance().addCoins(1);
        assertEquals(1,s1.getCoins());
        s1.setCoinsZero();
        assertEquals(0,s1.getCoins());
    }
    @Test
    public void testAddCoinsBoundary() {
        s1 = Statistics.getInstance();
        s1.setCoinsZero();
        Statistics.getInstance().addCoins(1);
        assertEquals(1,s1.getCoins());
    }

    @Test
    public void testAddCoinsLargerCase() {
        s1 = Statistics.getInstance();
        s1.setCoinsZero();
        Statistics.getInstance().addCoins(10);
        assertEquals(10,s1.getCoins());
    }

    @Test
    public void testAddCoinsMultipleTimes() {
        s1 = Statistics.getInstance();
        s1.setCoinsZero();
        Statistics.getInstance().addCoins(23);
        assertEquals(23,s1.getCoins());
        Statistics.getInstance().addCoins(41);
        assertEquals(64,s1.getCoins());
    }

    @Test
    public void testDeductCoinsBoundarySecondCase() {
        s1 = Statistics.getInstance();
        s1.setCoinsZero();
        Statistics.getInstance().addCoins(40);
        Statistics.getInstance().deductCoins(1);
        assertEquals(39,s1.getCoins());
    }

    @Test
    public void testDeductCoinsBoundaryFirstCase() {
        s1 = Statistics.getInstance();
        s1.setCoinsZero();
        Statistics.getInstance().addCoins(40);
        Statistics.getInstance().deductCoins(40);
        assertEquals(0,s1.getCoins());
    }

    @Test
    public void testDeductCoinsMiddleCase() {
        s1 = Statistics.getInstance();
        s1.setCoinsZero();
        Statistics.getInstance().addCoins(40);
        Statistics.getInstance().deductCoins(20);
        assertEquals(20,s1.getCoins());
    }

    @Test
    public void testDeductCoinsMultipleTimes() {
        s1 = Statistics.getInstance();
        s1.setCoinsZero();
        Statistics.getInstance().addCoins(40);
        Statistics.getInstance().deductCoins(11);
        assertEquals(29,s1.getCoins());
        Statistics.getInstance().deductCoins(6);
        assertEquals(23,s1.getCoins());
    }

}
