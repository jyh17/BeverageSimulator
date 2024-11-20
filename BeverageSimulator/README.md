# My Drink Simulator

## A simulator where you try to collect drinks and drink them for coins and use those coins to get more drinks.

The application will be a kind of game where you can get drinks and drink them. Drinking
them will earn your coins which you can use to get more beverages. Someone would use my application if
they were really bored and wanted to play a game that doesn't require much thinking.

This project is an interest to me because I'm guilty of playing these kinds of simple games when I was 
younger. Also, while I was in lecture the other day, two students in front of me had two drinks with them.
A matcha latte and a mocha so that inspired me to make a stupid application related to drinks.

**User Stories** :
- *As a user*, I want to be able to buy a randomized drink
- *As a user*, I want to be able to drink the beverage and gain coins from doing so
- *As a user*, I want to be able to buy the ability to create my own drink and add it to the list of drinks available.
- *As a user*, I want to be able to see the statistics of what drinks I've tried and how many times I've had it.
- *As a user*, I want to be able to save the custom drinks ive made for the game.
- *As a user*, I want to be able to load the custom drinks ive made for the game.

# Instructions for Grader

- You can generate the first action of shifting the last drink to the front by clicking on "look at available drinks"
- button, then you click on the "move last drink to front" button
- You can generate the second action of filtering drinks by their first character by clicking on "look at available
- drinks" button, then you click on the "filter by first letter" button
- You can locate my visual component by starting my program, the first thing you see before the main menu is a picture
- of a ceiling, then just exit out of that window to get to the menu button
- You can save the state of my application by clicking the "save your drinks" button
- You can reload the state of my application by clicking the "load your drinks" button

# Phase 4: Task 2
- Mon Aug 07 19:35:10 PDT 2023
- Shifted Drinks
- Mon Aug 07 19:35:13 PDT 2023
- Filter Drinks by Character !
- Mon Aug 07 19:35:14 PDT 2023
- Shifted Drinks
- Mon Aug 07 19:35:16 PDT 2023
- Shifted Drinks
- Mon Aug 07 19:35:18 PDT 2023
- Filter Drinks by Character A
- Mon Aug 07 19:35:22 PDT 2023
- Filter Drinks by Character M

# Phase 4: Task 3

Some refactoring I could do based on my project is getting rid of the association between my DrinkHub and Statistics, as
well as between DrinkHub and DrinkBank. I could make this alteration as both Statistics and DrinkBank are designed using
the singleton pattern. Thus, rather than having a field for those classes in DrinkHub, I can just call
ClassName.getInstance(). This reduces moderate coupling as my DrinkHub has fewer associations between other classes.