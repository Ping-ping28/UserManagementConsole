package userManagement;
import userManagement.model.User;
import userManagement.service.UserManager;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManager userManager = new UserManager();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> createUser();
                case 2 -> searchUser();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> displayUsers();
                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== User Management Console ===");
        System.out.println("1. Create user");
        System.out.println("2. Search user by UUID");
        System.out.println("3. Update user");
        System.out.println("4. Delete user");
        System.out.println("5. Display all users");
        System.out.println("6. Exit");
    }

    private static void createUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User user = userManager.createUser(name, email);
        System.out.println("User created successfully!");
        System.out.println("UUID: " + user.getUuid());
    }

    private static void searchUser() {
        UUID uuid = getUUIDInput();
        userManager.findByUUID(uuid).ifPresentOrElse(
                user -> System.out.println("Found user:\n" + formatTableHeader() + "\n" + user),
                () -> System.out.println("User not found.")
        );
    }

    private static void updateUser() {
        UUID uuid = getUUIDInput();
        Optional<User> userOpt = userManager.findByUUID(uuid);

        if (userOpt.isEmpty()) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter new name (press Enter to skip): ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = null;

        System.out.print("Enter new email (press Enter to skip): ");
        String email = scanner.nextLine().trim();
        if (email.isEmpty()) email = null;

        System.out.print("Mark as deleted? (yes/no/skip): ");
        String deletedInput = scanner.nextLine().toLowerCase();
        Boolean isDeleted = switch (deletedInput) {
            case "yes" -> true;
            case "no" -> false;
            default -> null;
        };

        if (userManager.updateUser(uuid, name, email, isDeleted)) {
            System.out.println("User updated successfully!");
        }
    }

    private static void deleteUser() {
        UUID uuid = getUUIDInput();
        if (userManager.deleteUser(uuid)) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void displayUsers() {
        final int PAGE_SIZE = 5;
        int currentPage = 1;
        int totalPages = userManager.getTotalPages(PAGE_SIZE);

        while (true) {
            List<User> users = userManager.getActiveUsers(currentPage, PAGE_SIZE);
            if (users.isEmpty()) {
                System.out.println("No active users found.");
                return;
            }

            System.out.println("\nActive Users (Page " + currentPage + "/" + totalPages + "):");
            System.out.println(formatTableHeader());
            users.forEach(System.out::println);

            if (totalPages <= 1) return;

            System.out.print("\nEnter 'n' for next page, 'p' for previous page, or 'q' to quit: ");
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "n" -> currentPage = Math.min(currentPage + 1, totalPages);
                case "p" -> currentPage = Math.max(currentPage - 1, 1);
                case "q" -> {
                    return;
                }
                default -> System.out.println("Invalid input.");
            }
        }
    }

    private static UUID getUUIDInput() {
        while (true) {
            System.out.print("Enter UUID: ");
            try {
                return UUID.fromString(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid UUID format. Please try again.");
            }
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String formatTableHeader() {
        return "| ID    | UUID                                 | Name                 | Email                          |";
    }
}