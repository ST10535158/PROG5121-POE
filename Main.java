/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.login;

/**
 *
 * @author Student
 */
import java.util.Scanner;

class Login {

    private String storedUsername;
    private String storedPassword;

    // Username validation
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password validation
    public boolean checkPassword(String password) {

        if (password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // Phone validation
    public boolean checkPhone(String phone) {

        if (!phone.startsWith("+27")) return false;
        if (phone.length() != 12) return false;

        for (int i = 3; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    // Register method
    public void register(Scanner sc) {

        System.out.println("=== REGISTER ===");

        // Username loop
        while (true) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();

            if (checkUserName(username)) {
                storedUsername = username;
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted. Try again.");
            }
        }

        // Password loop
        while (true) {
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            if (checkPassword(password)) {
                storedPassword = password;
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted. Try again.");
            }
        }

        // Phone loop
        while (true) {
            System.out.print("Enter cell phone (+27...): ");
            String phone = sc.nextLine();

            if (checkPhone(phone)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted. Try again.");
            }
        }

        System.out.println("Registration successful!\n");
    }

    // Login method
    public void login(Scanner sc) {

        // Check if user registered
        if (storedUsername == null || storedPassword == null) {
            System.out.println("No user registered yet. Please register first.");
            return;
        }

        System.out.println("=== LOGIN ===");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            System.out.println("Welcome back, " + storedUsername + "! Login successful.");
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
    }
}

public class Main{
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            Login user = new Login();
            
            int choice;
            
            do {
                System.out.println("\n=== MENU ===");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose option: ");
                
                choice = sc.nextInt();
                sc.nextLine(); // clear buffer
                
                switch (choice) {
                    case 1 -> user.register(sc);
                        
                    case 2 -> user.login(sc);
                        
                    case 3 -> System.out.println("Goodbye!");
                        
                    default -> System.out.println("Invalid choice.");
                }
                
            } while (choice != 3);
        }
    }
}
