package ui;

import model.DrinkBank;
import model.Drink;
import model.CurrentDrink;

import java.io.FileNotFoundException;

// Source: Code referenced from Teller App Given in class
// Main sets up all the available drinks and main menu
public class Main {
    public static void main(String[] args) {
        try {
            new DrinkHub();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
