package com.jozitechies.StreetSmart.app;

import com.jozitechies.StreetSmart.app.UserServices;
import com.jozitechies.StreetSmart.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static UserServices userService = new UserServices();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("\nUser Management System");
            System.out.println("1. List all users");
            System.out.println("2. Add a new user");
            System.out.println("3. Update a user");
            System.out.println("4. Delete a user");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1 -> listUsers();
                case 2 -> addUser();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    UserAuthentication auth = new UserAuthentication();
    boolean isAuthenticated = auth.login("user@example.com", "password123");

        if (isAuthenticated) {
        System.out.println("Welcome to StreetSmart!");
    } else {
        System.out.println("Invalid credentials. Please try again.");
    }

    private static void listUsers() throws IOException {
        List<User> users = userService.getAllUsers();
        if (users != null) {
            users.forEach(System.out::println);
        } else {
            System.out.println("Error fetching users");
        }
    }

    private static void addUser() throws IOException {
        User user = new User();
        System.out.print("Enter name: ");
        user.setName(scanner.nextLine());
        System.out.print("Enter surname: ");
        user.setSurname(scanner.nextLine());
        System.out.print("Enter email: ");
        user.setEmail(scanner.nextLine());
        System.out.print("Enter phone: ");
        user.setPhone(scanner.nextLine());

        userService.addUser(user);
    }

    private static void updateUser() throws IOException {
        System.out.print("Enter user ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        User user = new User();
        user.setId(id);
        System.out.print("Enter new name: ");
        user.setName(scanner.nextLine());
        System.out.print("Enter new surname: ");
        user.setSurname(scanner.nextLine());
        System.out.print("Enter new email: ");
        user.setEmail(scanner.nextLine());
        System.out.print("Enter new phone: ");
        user.setPhone(scanner.nextLine());

        userService.updateUser(user);
    }

    private static void deleteUser() throws IOException {
        System.out.print("Enter user ID to delete: ");
        Long id = scanner.nextLong();
        userService.deleteUser(id);
    }


}
