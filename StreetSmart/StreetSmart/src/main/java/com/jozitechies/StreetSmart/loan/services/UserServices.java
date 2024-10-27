package com.jozitechies.StreetSmart.loan.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jozitechies.StreetSmart.entities.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserServices {
    private static final String BASE_URL = "http://localhost:8080";

    // Get all users
    public List<User> getAllUsers() {
        try {
            URL url = new URL(BASE_URL + "/users/all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(connection.getInputStream(),
                        mapper.getTypeFactory().constructCollectionType(List.class, User.class));
            } else {
                System.out.println("Error: Unable to retrieve users. Response code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return null;
    }

    // Add a new user
    public void addUser(User user) {
        try {
            URL url = new URL(BASE_URL + "/addUser");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(user);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            if (connection.getResponseCode() != 200) {
                System.out.println("Failed to add user. Response code: " + connection.getResponseCode());
            } else {
                System.out.println("User added successfully");
            }
        } catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    // Update user
    public void updateUser(User user) {
        try {
            URL url = new URL(BASE_URL + "/updateUser/" + user.getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(user);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            if (connection.getResponseCode() != 200) {
                System.out.println("Failed to update user. Response code: " + connection.getResponseCode());
            } else {
                System.out.println("User updated successfully");
            }
        } catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    // Delete user by ID
    public void deleteUser(Long id) {
        try {
            URL url = new URL(BASE_URL + "/deleteUser/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            if (connection.getResponseCode() != 200) {
                System.out.println("Failed to delete user. Response code: " + connection.getResponseCode());
            } else {
                System.out.println("User deleted successfully");
            }
        } catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    // Login user
    public boolean login(String email, String password) {
        try {
            URL url = new URL(BASE_URL + "/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // JSON payload with email and password
            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(new LoginRequest(email, password));

            // Write JSON to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Check response
            if (connection.getResponseCode() == 200) {
                System.out.println("Login successful");
                return true;
            } else {
                System.out.println("Login failed. Response code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return false;
    }

    // Helper class for login request payload
    private static class LoginRequest {
        private String email;
        private String password;

        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        // Getters and setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}

