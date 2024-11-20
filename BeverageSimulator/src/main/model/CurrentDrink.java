package model;

// Source: UniqueNameProvider Singleton Starter from Class
// represents current drink which would be consumed if the user chose to drink
public class CurrentDrink {
    private static CurrentDrink singletonCurrentDrink;
    private Drink currentDrink;

    // EFFECTS: empty constructor for current drink
    private CurrentDrink() {

    }

    // EFFECTS: returns single instance of Current Drink
    public static CurrentDrink getInstance() {
        if (singletonCurrentDrink == null) {
            singletonCurrentDrink = new CurrentDrink();
        }
        return singletonCurrentDrink;
    }

    public void setCurrentDrink(Drink d) {
        this.currentDrink = d;
    }

    public Drink getCurrentDrink() {
        return currentDrink;
    }

}
