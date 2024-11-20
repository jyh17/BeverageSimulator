package ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.Drink;
import model.DrinkBank;

// Drinkmaker deals with the users own customized drink
public class DrinkMaker {

    // EFFECTS: takes user input to make their own drink
    public static void drinkMaker() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please Create Your Drink!");
        System.out.println("What is the name of your lovely drink?");
        String drinkName = input.nextLine();
        System.out.println("MMMM Sounds Delicious!!");
        System.out.println("How rare is this drink going to be?");
        System.out.println("Type 0 for common, 1 for uncommon, 2 for rare, and 3 for legendary!");
        int rarity = getRarityInput(input);
        Drink d = new Drink(drinkName,rarity);
        DrinkBank.getInstance().addDrink(d);
        System.out.println("You just made a drink called" + drinkName + "!");
    }

    // MODIFIES: rarityInput
    // EFFECTS: checks to see if given rarity input is a valid one and returns it if it is valid
    private static int getRarityInput(Scanner input) {
        int rarityInput = 4;
        boolean correctInput = false;
        while (!correctInput) {
            try {
                rarityInput = input.nextInt();
                if (rarityInput >= 0 && rarityInput <= 3) {
                    correctInput = true;
                } else {
                    System.out.println("Invalid Number, Please Type a Number Between 0-3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input Type, Please Type a Number");
                input.nextLine();
            }
        }
        return rarityInput;
    }

}
