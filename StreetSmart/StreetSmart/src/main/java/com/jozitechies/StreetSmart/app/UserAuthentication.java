package com.jozitechies.StreetSmart.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jozitechies.StreetSmart.entities.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UserAuthentication {
    private static final String BASE_URL = "http://localhost:8080";

    // Login method
    public boolean login(String email, String password) {
        try {
            URL url = new URL(BASE_URL + "/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create a JSON object with email and password
            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(new LoginRequest(email, password));

            // Send JSON data in the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Check response code
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

    // A helper class to represent the login request data
    private static class LoginRequest {
        private String email;
        private String password;

        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
