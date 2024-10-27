package com.jozitechies.StreetSmart.app;

import com.jozitechies.StreetSmart.entities.User;
import com.jozitechies.StreetSmart.loan.services.UserServices;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class ConsoleApp {
    private static UserServices userService = new UserServices();
    private static Scanner scanner = new Scanner(in);
    private static UserAuthentication auth = new UserAuthentication();
    private static boolean isAuthenticated = false;

    public static void main(String[] args) throws IOException {
        // Attempt login at the start
        login();

        // Main application loop, accessible only if authenticated
        while (isAuthenticated) {
            out.println("\nUser Management System");
            out.println("1. List all users");
            out.println("2. Add a new user");
            out.println("3. Update a user");
            out.println("4. Delete a user");
            out.println("5. Logout and exit");
            out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1 -> listUsers();
                case 2 -> addUser();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> {
                    out.println("Logging out...");
                    isAuthenticated = false;
                    exit(0);
                }
                default -> out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        out.println("Login to access the User Management System");
        out.print("Enter email: ");
        String email = scanner.nextLine();
        out.print("Enter password: ");
        String password = scanner.nextLine();

        isAuthenticated = auth.login(email, password);

        if (!isAuthenticated) {
            out.println("Login failed. Please check your credentials.");
            exit(0);
        } else {
            out.println("Login successful. Welcome!");
        }
    }

    private static void listUsers() throws IOException {
        List<User> users = userService.getAllUsers();
        if (users != null) {
            users.forEach(out::println);
        } else {
            out.println("Error fetching users.");
        }
    }

    private static void addUser() throws IOException {
        User user = new User();
        out.print("Enter name: ");
        user.setName(scanner.nextLine());
        out.print("Enter surname: ");
        user.setSurname(scanner.nextLine());
        out.print("Enter email: ");
        user.setEmail(scanner.nextLine());
        out.print("Enter phone: ");
        user.setPhone(scanner.nextLine());
        out.print("Enter password: ");
        user.setPassword(scanner.nextLine());

        userService.addUser(user);
    }

    private static void updateUser() throws IOException {
        out.print("Enter user ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        User user = new User();
        user.setId(id);
        out.print("Enter new name: ");
        user.setName(scanner.nextLine());
        out.print("Enter new surname: ");
        user.setSurname(scanner.nextLine());
        out.print("Enter new email: ");
        user.setEmail(scanner.nextLine());
        out.print("Enter new phone: ");
        user.setPhone(scanner.nextLine());
        out.print("Enter new password: ");
        user.setPassword(scanner.nextLine());

        userService.updateUser(user);
    }

    private static void deleteUser() throws IOException {
        out.print("Enter user ID to delete: ");
        Long id = scanner.nextLong();
        userService.deleteUser(id);
    }
}
