package ca_2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class task2 {
    private final ArrayList<Person> peopleList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    // Define enums for menu options
    enum MenuOption {
        ADD_PLAYER("Add Player", 1),
        GENERATE_RANDOM_PLAYER("Generate Random Player", 2);

        private final String description;
        private final int value;

        MenuOption(String description, int value) {
            this.description = description;
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        task2 task = new task2();
        System.out.println("Command line Example 2:");
        task.displayMenu();

        int choice = task.getMenuChoice();
        task.handleMenuChoice(choice);
    }

    private void displayMenu() {
        System.out.println("Please select an option from the following:");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getValue() + ". " + option.getDescription());
        }
    }

    private int getMenuChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                addPlayer();
                break;
            case 2:
                generateRandomPlayer();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void addPlayer() {
        scanner.nextLine(); // Consume newline
        System.out.print("Please input the Player Name: ");
        String name = scanner.nextLine();

        System.out.println("Please select from the following Coach Staff:");
        System.out.println("Head Coach(1)");
        System.out.println("Assistant Coach(2)");
        System.out.println("Scrum Coach(3)");
        int coachChoice = scanner.nextInt();

        String coachType;
        switch (coachChoice) {
            case 1:
                coachType = "Head Coach";
                break;
            case 2:
                coachType = "Assistant Coach";
                break;
            case 3:
                coachType = "Scrum Coach";
                break;
            default:
                System.out.println("Invalid coach type. Setting coach type to 'Unknown'");
                coachType = "Unknown";
        }

        System.out.println("Please select the Teams:");
        System.out.println("A Squad(1)");
        System.out.println("B Squad(2)");
        System.out.println("Under-13 squad(3)");
        int teamChoice = scanner.nextInt();

        String team;
        switch (teamChoice) {
            case 1:
                team = "A Squad";
                break;
            case 2:
                team = "B Squad";
                break;
            case 3:
                team = "Under-13 squad";
                break;
            default:
                System.out.println("Invalid team. Setting team to 'Unknown'");
                team = "Unknown";
        }

        Person person = new Person(name, coachType, team);
        peopleList.add(person);
        System.out.println("\"" + name + "\" has been added as \"" + coachType + "\" to \"" + team + "\" successfully!");
    }

    private void generateRandomPlayer() {
        Random random = new Random();

        String[] names = {"Joerogan", "Bold", "Lawson", "Zata", "David", "Sarah", "Someone", "Love", "Hate", "Mamamia"};
        String[] coachTypes = {"Head Coach", "Assistant Coach", "Scrum Coach"};
        String[] teams = {"A Squad", "B Squad", "Under-13 squad"};

        String name = names[random.nextInt(names.length)];
        String coachType = coachTypes[random.nextInt(coachTypes.length)];
        String team = teams[random.nextInt(teams.length)];

        Person person = new Person(name, coachType, team);
        peopleList.add(person);
        System.out.println("Random player generated: " + person);
    }

    static class Person {
        private final String name;
        private final String coachType;
        private final String team;

        public Person(String name, String coachType, String team) {
            this.name = name;
            this.coachType = coachType;
            this.team = team;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Coach Type: " + coachType + ", Team: " + team;
        }
    }
}
