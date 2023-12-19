import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExpenseTracker {

    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Expense Summaries");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    expenseSummaries();
                    break;
                case 4:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private static void addExpense() {
        System.out.print("Enter expense description: ");
        String description = scanner.nextLine();

        System.out.print("Enter expense amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter expense category: ");
        String category = scanner.nextLine();

        Expense expense = new Expense(description, amount, category);
        expenses.add(expense);

        System.out.println("Expense added successfully!\n");
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.\n");
        } else {
            System.out.println("List of Expenses:");
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
            System.out.println();
        }
    }

    private static void expenseSummaries() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.\n");
            return;
        }

        System.out.println("Expense Summaries:");
        System.out.println("1. Total Expenses");
        System.out.println("2. Expenses by Category");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                double totalExpenses = 0;
                for (Expense expense : expenses) {
                    totalExpenses += expense.getAmount();
                }
                System.out.println("Total Expenses: $" + totalExpenses + "\n");
                break;
            case 2:
                Map<String, Double> categoryExpenses = new HashMap<>();
                for (Expense expense : expenses) {
                    categoryExpenses.put(expense.getCategory(),
                            categoryExpenses.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
                }
                System.out.println("Expenses by Category:");
                for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
                    System.out.println(entry.getKey() + ": $" + entry.getValue());
                }
                System.out.println();
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.\n");
        }
    }
}

class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Amount: $" + amount + ", Category: " + category;
    }
}
