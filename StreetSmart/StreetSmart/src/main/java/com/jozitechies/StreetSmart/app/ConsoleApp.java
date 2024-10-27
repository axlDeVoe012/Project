package com.jozitechies.StreetSmart.app;

import com.jozitechies.StreetSmart.entities.User;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        UserServices userServices = new UserServices();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add User");
            System.out.println("2. List Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1: // Add User
                    User newUser = new User();
                    System.out.print("Enter name: ");
                    newUser.setName(scanner.nextLine());
                    System.out.print("Enter surname: ");
                    newUser.setSurname(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newUser.setEmail(scanner.nextLine());
                    System.out.print("Enter phone: ");
                    newUser.setPhone(scanner.nextLine());
                    System.out.print("Enter password: "); // New password prompt
                    newUser.setPassword(scanner.nextLine());
                    userServices.addUser(newUser);
                    break;

                case 2: // List Users
                    List<User> users = userServices.getAllUsers();
                    if (users != null) {
                        for (User user : users) {
                            System.out.println(user.getId() + ": " + user.getName() + " " + user.getSurname());
                        }
                    }
                    break;

                case 3: // Update User
                    System.out.print("Enter user ID to update: ");
                    Long userIdToUpdate = scanner.nextLong();
                    scanner.nextLine(); // Consume newline
                    User userToUpdate = new User();
                    userToUpdate.setId(userIdToUpdate);
                    System.out.print("Enter new name: ");
                    userToUpdate.setName(scanner.nextLine());
                    System.out.print("Enter new surname: ");
                    userToUpdate.setSurname(scanner.nextLine());
                    System.out.print("Enter new email: ");
                    userToUpdate.setEmail(scanner.nextLine());
                    System.out.print("Enter new phone: ");
                    userToUpdate.setPhone(scanner.nextLine());
                    System.out.print("Enter new password: "); // New password prompt
                    userToUpdate.setPassword(scanner.nextLine());
                    userServices.updateUser(userToUpdate);
                    break;

                case 4: // Delete User
                    System.out.print("Enter user ID to delete: ");
                    Long userIdToDelete = scanner.nextLong();
                    userServices.deleteUser(userIdToDelete);
                    break;

                case 5: // Exit
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
