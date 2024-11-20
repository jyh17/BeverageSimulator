package model;

// Source: UniqueNameProvider Singleton Starter from Class
// Singleton Stats for User including amount of drinks had, last drink consumed and coins in bank
public class Statistics {
    private static Statistics singleton;
    private int drinksHad;
    private Drink lastDrinkHad;
    private int coins;

    // MODIFIES: this
    // EFFECTS: constructs stats of user, including amount of drinks consumed, and monetary balance
    private Statistics() {
        this.drinksHad = 0;
        this.lastDrinkHad = null;
        this.coins = 0;
    }

    public int getDrinksHad() {
        return drinksHad;
    }

    public void setLastDrinkHad(Drink d) {
        lastDrinkHad = d;
    }

    public void setLastDrinkHadNull() {
        lastDrinkHad = null;
    }

    public String getLastDrinkHadName() {
        if (lastDrinkHad == null) {
            return "No Drinks Yet!";
        }
        return lastDrinkHad.getName();
    }

    // MODIFIES: this
    // EFFECTS: increases drinks had by one
    public void incrementDrinksHad() {
        drinksHad++;
    }

    // MODIFIES: this
    // EFFECTS: sets drinks had as to 0
    public void setDrinksHadZero() {
        drinksHad = 0;
    }

    // EFFECTS: gets instance of statistics
    public static Statistics getInstance() {
        if (singleton == null) {
            singleton = new Statistics();
        }
        return singleton;
    }

    // REQUIRES: c >= 1
    // MODIFIES: this
    // EFFECTS: adds given coins to balance
    public void addCoins(int c) {
        coins = coins + c;
    }

    // REQUIRES: coins >= c && c >= 1
    // MODIFIES: this
    // EFFECTS: deducts given coins from balance
    public void deductCoins(int c) {
        coins = coins - c;
    }

    // MODIFIES: this
    // EFFECTS: sets coins to 0
    public void setCoinsZero() {
        coins = 0;
    }

    public int getCoins() {
        return coins;
    }
}
