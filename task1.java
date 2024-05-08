package ca_2;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class task1 {
    private final ArrayList<Person> peopleList = new ArrayList<>();

    public enum MenuOption {
        SORT(1, "Sort"),
        SEARCH(2, "Search");

        private final int value;
        private final String label;

        MenuOption(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }
        
        // Method to retrieve MenuOption by value
        public static MenuOption getByValue(int value) {
            for (MenuOption option : MenuOption.values()) {
                if (option.getValue() == value) {
                    return option;
                }
            }
            return null; // If no option found
        }
    }

    public static void main(String[] args) {
        task1 task = new task1();
        System.out.println("Command line Example 1:");
        System.out.println("Please enter the filename to read:");

        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();

        if (task.loadPeopleListFromFile(filename)) {
            System.out.println("File read successfully");
            System.out.println("Do You wish to SORT or SEARCH:");

            for (MenuOption option : MenuOption.values()) {
                System.out.println(option.getValue() + ". " + option.getLabel());
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            MenuOption selectedOption = MenuOption.getByValue(choice);
            if (selectedOption != null) {
                switch (selectedOption) {
                    case SORT:
                        task.sortPeopleList();
                        break;
                    case SEARCH:
                        task.searchPerson(scanner);
                        break;
                }
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        } else {
            System.out.println("Failed to read the file.");
        }
    }

    private boolean loadPeopleListFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // Skip the first line (header)
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip the header
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String firstName = parts[1].trim(); // First name
                String lastName = parts[2].trim(); // Last name
                String fullName = firstName + " " + lastName;
                String coachType = ""; // Assuming coachType is not provided in the file
                String team = ""; // Assuming team is not provided in the file
                Person person = new Person(fullName, coachType, team);
                peopleList.add(person);
            }
            scanner.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return false;
        }
    }

    private void sortPeopleList() {
        quicksort(peopleList, 0, peopleList.size() - 1); // Call your existing quicksort implementation
        System.out.println("List of people sorted successfully:");
        int count = Math.min(20, peopleList.size());
        for (int i = 0; i < count; i++) {
            System.out.println(peopleList.get(i));
        }
    }

    private void quicksort(ArrayList<Person> list, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(list, low, high);
            quicksort(list, low, partitionIndex - 1);
            quicksort(list, partitionIndex + 1, high);
        }
    }

    private int partition(ArrayList<Person> list, int low, int high) {
        Person pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(ArrayList<Person> list, int i, int j) {
        Person temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private void searchPerson(Scanner scanner) {
        System.out.print("Enter the name to search: ");
        String searchName = scanner.nextLine();

        // Convert search name to lowercase for case-insensitive comparison
        searchName = searchName.trim().toLowerCase();

        boolean found = false;
        for (Person person : peopleList) {
            if (person.getName().toLowerCase().contains(searchName)) {
                System.out.println("Person found:");
                System.out.println(person);
                found = true;
                break; // Break the loop after finding the first occurrence
            }
        }

        if (!found) {
            System.out.println(searchName + " not found in the list.");
        }
    }

    static class Person implements Comparable<Person> {
        private String name;
        private String coachType;
        private String team;

        public Person(String name, String coachType, String team) {
            this.name = name;
            this.coachType = coachType;
            this.team = team;
        }

        public String getName() {
            return name;
        }

        public String getCoachType() {
            return coachType;
        }

        public String getTeam() {
            return team;
        }

        @Override
        public int compareTo(Person other) {
            return this.name.compareToIgnoreCase(other.getName());
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Coach Type: " + coachType + ", Team: " + team;
        }
    }
}
