package ui;

import model.*;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Scanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.util.List;

// Source: In class Workroom App Example
// Source: Code referenced from Teller App Given in class
// DrinkHub is the main menu portion of the application
public class DrinkHub extends JFrame implements ActionListener {
    private Scanner input;
    private Statistics stat;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workroom.json";
    private DrinkBank db;
    private JPanel panel;
    private JPanel drinksPanel;
    private JFrame drinksFrame;

    // EFFECTS: runs the drink game application
    public DrinkHub() throws FileNotFoundException {
        super("DrinkHub!!!");
        db = DrinkBank.getInstance();
        DrinkBank.addBaseDrinks(db);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        displayOpening();
        displayMenu();

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: processes button input
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("shop")) {
            doShop();
        } else if (e.getActionCommand().equals("drink")) {
            doDrink();
        } else if (e.getActionCommand().equals("stat")) {
            doStatistics();
        } else if (e.getActionCommand().equals("save")) {
            saveDrinkBank();
        } else if (e.getActionCommand().equals("load")) {
            loadDrinkBank();
        } else if (e.getActionCommand().equals("looksie")) {
            drinkDisplay();
        } else if (e.getActionCommand().equals("quit")) {
            for (Event event: EventLog.getInstance()) {
                System.out.println(event.toString());
            }
            System.exit(0);
        } else if (e.getActionCommand().equals("move")) {
            drinkDisplayMoved();
        } else if (e.getActionCommand().equals("filter")) {
            getFilterCharacter();
        }
    }

    // EFFECTS: shows picture for a few seconds before app starts
    private void displayOpening() {
        ImageIcon image = new ImageIcon("/Users/jksm/Downloads/project_q5x3e/data/image0-34.jpeg");
        JOptionPane.showMessageDialog(null,image);
    }


    // EFFECTS: displays menu of options to user
    @SuppressWarnings("methodlength")
    private void displayMenu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new JPanel();

        createButton("Shop", "shop");
        createButton("Start Drinking!", "drink");
        createButton("Check those Stats", "stat");
        createButton("Save Your Drinks", "save");
        createButton("Load Your Drinks", "load");
        createButton("Look at Available Drinks", "looksie");
        createButton("Quit", "quit");
    }

    // EFFECTS: creates button with given name and command name
    private void createButton(String buttonName, String commandName) {
        JButton button = new JButton(buttonName);
        button.setActionCommand(commandName);
        button.addActionListener(this);
        panel.add(button);
    }

    // EFFECTS: creates new window/panel with drinks available and drink related actions
    private void drinkDisplay() {
        drinksFrame = new JFrame();
        drinksPanel = new JPanel();
        updateDrinksPanel(DrinkBank.getInstance().getListOfDrinks());
    }

    // EFFECTS: creates new window/panel with drinks moved and drink related actions
    private void drinkDisplayMoved() {

        drinksFrame.dispose();
        updateDrinksPanel(DrinkBank.getInstance().getShiftedDrinks());


    }


    // EFFECTS: gets user input for choice of filter length before filtering drinks
    private void getFilterCharacter() {
        boolean isACharacter = false;
        String userInput = null;
        while (!isACharacter) {
            userInput = JOptionPane.showInputDialog(
                    "Please Enter the first single Character you want to filter the drinks list by: ");
            if (userInput.length() == 1) {
                isACharacter = true;
            }
        }
        drinkDisplayFiltered(userInput.charAt(0));
    }

    // EFFECTS: creates new window/panel with drinks filtered and drink related actions
    private void drinkDisplayFiltered(char filter) {

        drinksFrame.dispose();
        updateDrinksPanel(DrinkBank.getInstance().getFilteredDrinks(filter));

    }

    // EFFECTS: updates drink window with the new drink list
    private void updateDrinksPanel(List<Drink> drinks) {
        drinksPanel.removeAll();
        for (Drink d : drinks) {
            JLabel drinkLabel = new JLabel(d.getName());
            drinksPanel.add(drinkLabel);
        }
        drinksPanel.revalidate();
        drinksPanel.repaint();

        JButton button8 = new JButton("Move Last Drink to Front");
        button8.setActionCommand("move");
        button8.addActionListener(this);

        JButton button9 = new JButton("Filter by First Letter");
        button9.setActionCommand("filter");
        button9.addActionListener(this);

        drinksFrame = new JFrame();
        drinksFrame.add(drinksPanel);
        drinksPanel.add(button8);
        drinksPanel.add(button9);
        drinksFrame.pack();
        drinksFrame.setLocationRelativeTo(null);
        drinksFrame.setVisible(true);
        drinksFrame.setResizable(false);
    }

    // EFFECTS: display shop menu
    private void doShop() {
        boolean keepGoing = true;
        while (keepGoing) {
            displayShopOptions();
            input = new Scanner(System.in);
            String command = input.next().toLowerCase();
            int bcost = 5;
            if (command.equals("a")) {
                DrinkMaker d = new DrinkMaker();
                d.drinkMaker();
            } else if (command.equals("b")) {
                if (Statistics.getInstance().getCoins() >= bcost) {
                    Statistics.getInstance().deductCoins(bcost);
                    spinForDrink();
                } else {
                    System.out.println("Not enough coins!");
                }
            } else if (command.equals("x")) {
                System.out.println("Come back another time!");
                keepGoing = false;
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    private void displayShopOptions() {
        System.out.println("\nWhat Would You Like To Buy?");
        System.out.println("\n0 Coins:");
        System.out.println("\nPress A to Create Your Own Drink");
        System.out.println("\n5 Coins:");
        System.out.println("\nPress B to Spin for Drink");
        System.out.println("\nPress X to Exit Shop");
    }

    private void spinForDrink() {
        ArrayList<Drink> listOfAvailableDrinks = DrinkBank.getInstance().getListOfDrinks();
        Collections.shuffle(listOfAvailableDrinks);
        Drink spunDrink = listOfAvailableDrinks.get(0);
        CurrentDrink c = CurrentDrink.getInstance();
        c.setCurrentDrink(spunDrink);
        System.out.println("Your Randomized Drink is:" + spunDrink.getName() + "!!!");
    }

    private void doDrink() {
        Statistics.getInstance().addCoins(CurrentDrink.getInstance().getCurrentDrink().getDrinkValue());
        Statistics.getInstance().incrementDrinksHad();
        Statistics.getInstance().setLastDrinkHad(CurrentDrink.getInstance().getCurrentDrink());
        System.out.println("You just drank " + CurrentDrink.getInstance().getCurrentDrink().getName());
        System.out.println("You earned " + CurrentDrink.getInstance().getCurrentDrink().getDrinkValue() + " coins");
    }

    private void doStatistics() {
        stat = Statistics.getInstance();
        System.out.println("Here are your Drinking Statistics: ");
        System.out.println();
        System.out.println("Total Drinks Drunk: " + stat.getDrinksHad());
        System.out.println();
        System.out.println("Previous Drink Had: " + stat.getLastDrinkHadName());
        System.out.println();
        System.out.println("Balance: " + stat.getCoins());
        System.out.println();
    }

    // EFFECTS: saves the drink bank to file
    private void saveDrinkBank() {
        try {
            jsonWriter.open();
            jsonWriter.write(db);
            jsonWriter.close();
            //System.out.println("Saved Drink Bank to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            //System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads drink bank from file
    private void loadDrinkBank() {
        try {
            db = DrinkBank.getInstance();
            db.setListOfDrinksEmpty();
            db = jsonReader.read();
            //System.out.println("Loaded Drink Bank from " + JSON_STORE);
        } catch (IOException e) {
            //System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

//package ui;
//
//        import model.CurrentDrink;
//        import model.DrinkBank;
//        import model.Statistics;
//
//        import persistence.JsonReader;
//        import persistence.JsonWriter;
//
//        import java.io.FileNotFoundException;
//        import java.io.IOException;
//        import java.util.ArrayList;
//        import java.util.Collections;
//        import model.Drink;
//
//        import java.util.Scanner;
//
//// Source: In class Workroom App Example
//// Source: Code referenced from Teller App Given in class
//// DrinkHub is the main menu portion of the application
//public class DrinkHub {
//    private Scanner input;
//    private Statistics stat;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//    private static final String JSON_STORE = "./data/workroom.json";
//    private DrinkBank db;
//
//    // EFFECTS: runs the drink game application
//    public DrinkHub() throws FileNotFoundException {
//        db = DrinkBank.getInstance();
//        DrinkBank.addBaseDrinks(db);
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        runDrinkGame();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void runDrinkGame() {
//        boolean keepGoing = true;
//        String command = null;
//        while (keepGoing) {
//            displayMenu();
//            input = new Scanner(System.in);
//            input.useDelimiter("\n");
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//
//        System.out.println("\nGoodbye!");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("s")) {
//            doShop();
//        } else if (command.equals("d")) {
//            doDrink();
//        } else if (command.equals("c")) {
//            doStatistics();
//        } else if (command.equals("y")) {
//            saveDrinkBank();
//        } else if (command.equals("z")) {
//            loadDrinkBank();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tPress S to Shop");
//        System.out.println("\tPress D to Drink");
//        System.out.println("\tPress C to Check Statistics");
//        System.out.println("\tPress Y to Save Drinks to Drink Bank");
//        System.out.println("\tPress Z to Load Drinks into Drink Bank");
//        System.out.println("\tPress Q to Quit");
//    }
//
//    // EFFECTS:
//    private void doShop() {
//        boolean keepGoing = true;
//        while (keepGoing) {
//            displayShopOptions();
//            String command = input.next();
//            command = command.toLowerCase();
//            int bcost = 5;
//            if (command.equals("a")) {
//                DrinkMaker d = new DrinkMaker();
//                d.drinkMaker();
//            } else if (command.equals("b")) {
//                if (Statistics.getInstance().getCoins() >= bcost) {
//                    Statistics.getInstance().deductCoins(bcost);
//                    spinForDrink();
//                } else {
//                    System.out.println("Not enough coins!");
//                }
//            } else if (command.equals("x")) {
//                System.out.println("Come back another time!");
//                keepGoing = false;
//            } else {
//                System.out.println("Selection not valid...");
//            }
//        }
//    }
//
//    private void displayShopOptions() {
//        System.out.println("\nWhat Would You Like To Buy?");
//        System.out.println("\n0 Coins:");
//        System.out.println("\nPress A to Create Your Own Drink");
//        System.out.println("\n5 Coins:");
//        System.out.println("\nPress B to Spin for Drink");
//        System.out.println("\nPress X to Exit Shop");
//    }
//
//    private void spinForDrink() {
//        ArrayList<Drink> listOfAvailableDrinks = DrinkBank.getInstance().getListOfDrinks();
//        Collections.shuffle(listOfAvailableDrinks);
//        Drink spunDrink = listOfAvailableDrinks.get(0);
//        CurrentDrink c = CurrentDrink.getInstance();
//        c.setCurrentDrink(spunDrink);
//        System.out.println("Your Randomized Drink is:" + spunDrink.getName() + "!!!");
//    }
//
//    private void doDrink() {
//        Statistics.getInstance().addCoins(CurrentDrink.getInstance().getCurrentDrink().getDrinkValue());
//        Statistics.getInstance().incrementDrinksHad();
//        Statistics.getInstance().setLastDrinkHad(CurrentDrink.getInstance().getCurrentDrink());
//        System.out.println("You just drank " + CurrentDrink.getInstance().getCurrentDrink().getName());
//        System.out.println("You earned " + CurrentDrink.getInstance().getCurrentDrink().getDrinkValue() + " coins");
//    }
//
//    private void doStatistics() {
//        stat = Statistics.getInstance();
//        System.out.println("Here are your Drinking Statistics: ");
//        System.out.println();
//        System.out.println("Total Drinks Drunk: " + stat.getDrinksHad());
//        System.out.println();
//        System.out.println("Previous Drink Had: " + stat.getLastDrinkHadName());
//        System.out.println();
//        System.out.println("Balance: " + stat.getCoins());
//        System.out.println();
//    }
//
//    // EFFECTS: saves the drink bank to file
//    private void saveDrinkBank() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(db);
//            jsonWriter.close();
//            System.out.println("Saved Drink Bank to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads drink bank from file
//    private void loadDrinkBank() {
//        try {
//            db = DrinkBank.getInstance();
//            db.setListOfDrinksEmpty();
//            db = jsonReader.read();
//            System.out.println("Loaded Drink Bank from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//}


//    // EFFECTS: creates new window/panel with drinks filtered and drink related actions
//    private void drinkDisplayFiltered(char filter) {
//        drinksFrame.dispose();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        drinksPanel = new JPanel();
//        for (Drink d : DrinkBank.getInstance().getListOfDrinks()) {
//            if (d.getName().charAt(0) == filter) {
//                JLabel drink = new JLabel(d.getName());
//                drinksPanel.add(drink);
//            }
//        }
//
//        JButton button8 = new JButton("Move Last Drink to Front");
//        button8.setActionCommand("move");
//        button8.addActionListener(this);
//
//        JButton button9 = new JButton("Filter by Name Length");
//        button9.setActionCommand("length");
//        button9.addActionListener(this);
//
//        drinksFrame = new JFrame();
//        drinksFrame.add(drinksPanel);
//        drinksPanel.add(button8);
//        drinksPanel.add(button9);
//        drinksFrame.pack();
//        drinksFrame.setLocationRelativeTo(null);
//        drinksFrame.setVisible(true);
//        drinksFrame.setResizable(false);
//    }
//    // EFFECTS: creates new window/panel with drinks available and drink related actions
//    private void drinkDisplay() {
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        drinksPanel = new JPanel();
//        for (Drink d : DrinkBank.getInstance().getListOfDrinks()) {
//            JLabel drink = new JLabel(d.getName());
//            drinksPanel.add(drink);
//        }
//
//        JButton button8 = new JButton("Move Last Drink to Front");
//        button8.setActionCommand("move");
//        button8.addActionListener(this);
//
//        JButton button9 = new JButton("Filter by Name Length");
//        button9.setActionCommand("length");
//        button9.addActionListener(this);
//
//        drinksFrame = new JFrame();
//        drinksFrame.add(drinksPanel);
//        drinksPanel.add(button8);
//        drinksPanel.add(button9);
//        drinksFrame.pack();
//        drinksFrame.setLocationRelativeTo(null);
//        drinksFrame.setVisible(true);
//        drinksFrame.setResizable(false);
//    }
//    // EFFECTS: creates new window/panel with drinks moved and drink related actions
//    private void drinkDisplayMoved() {
//        drinksFrame.dispose();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        drinksPanel = new JPanel();
//        ArrayList<Drink> availableDrinks = DrinkBank.getInstance().getListOfDrinks();
//        Drink lastDrink = availableDrinks.remove(availableDrinks.size() - 1);
//        availableDrinks.add(0,lastDrink);
//        for (Drink d : DrinkBank.getInstance().getListOfDrinks()) {
//            JLabel drink = new JLabel(d.getName());
//            drinksPanel.add(drink);
//        }
//
//        JButton button8 = new JButton("Move Last Drink to Front");
//        button8.setActionCommand("move");
//        button8.addActionListener(this);
//
//        JButton button9 = new JButton("Filter by Name Length");
//        button9.setActionCommand("length");
//        button9.addActionListener(this);
//
//        drinksFrame = new JFrame();
//        drinksFrame.add(drinksPanel);
//        drinksPanel.add(button8);
//        drinksPanel.add(button9);
//        drinksFrame.pack();
//        drinksFrame.setLocationRelativeTo(null);
//        drinksFrame.setVisible(true);
//        drinksFrame.setResizable(false);
//    }