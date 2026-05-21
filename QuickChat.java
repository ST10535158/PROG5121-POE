/*package quickchat;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to QuickChat");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        // Simple login check
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Login failed.");
            return;
        }

        System.out.println("Login successful!");

        System.out.print("How many messages would you like to send? ");
        int totalMessages = input.nextInt();
        input.nextLine();

        int choice;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");

            System.out.print("Choose option: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:

                    for (int i = 0; i < totalMessages; i++) {

                        System.out.println("\nMessage " + (i + 1));

                        System.out.print("Enter recipient number: ");
                        String recipient = input.nextLine();

                        System.out.print("Enter message: ");
                        String messageText = input.nextLine();

                        Message message = new Message(recipient, messageText);

                        if (!message.checkRecipientCell()) {
                            System.out.println("Cell phone number is incorrectly formatted or does not contain an international code.");
                            continue;
                        }

                        if (messageText.length() > 250) {
                            int excess = messageText.length() - 250;
                            System.out.println("Message exceeds 250 characters by " + excess + ", please reduce the size.");
                            continue;
                        } else {
                            System.out.println("Message ready to send.");
                        }

                        System.out.println("\nChoose:");
                        System.out.println("1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message");

                        int sendChoice = input.nextInt();
                        input.nextLine();

                        String result = message.sentMessage(sendChoice);

                        System.out.println(result);

                        if (sendChoice == 1 || sendChoice == 3) {

                            System.out.println("\n----- MESSAGE DETAILS -----");
                            System.out.println("Message ID: " + message.getMessageID());
                            System.out.println("Message Hash: " + message.createMessageHash());
                            System.out.println("Recipient: " + recipient);
                            System.out.println("Message: " + messageText);

                            Message.totalMessages++;
                        }
                    }

                    System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());

                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 3);

    }
}
package quickchat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.json.simple.JSONObject;

public class Message {

    private String messageID;
    private String recipient;
    private String message;
    private int messageNumber;

    public static int totalMessages = 0;

    public Message(String recipient, String message) {

        this.recipient = recipient;
        this.message = message;

        generateMessageID();

        messageNumber = totalMessages;
    }

    // Generate random 10-digit ID
    private void generateMessageID() {

        Random random = new Random();

        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);

        messageID = String.valueOf(number);
    }

    // Check Message ID
    public boolean checkMessageID() {

        return messageID.length() <= 10;
    }

    // Check recipient number
    public boolean checkRecipientCell() {

        return recipient.startsWith("+") && recipient.length() <= 13;
    }

    // Create Message Hash
    public String createMessageHash() {

        String[] words = message.split(" ");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        String hash = messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;

        return hash;
    }

    // Send / Store / Delete
    public String sentMessage(int choice) {

        switch (choice) {

            case 1:
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    // Print messages
    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + createMessageHash()
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    // Return total messages
    public static int returnTotalMessages() {

        return totalMessages;
    }

    // Store JSON
    public void storeMessage() {

        JSONObject obj = new JSONObject();

        obj.put("MessageID", messageID);
        obj.put("Recipient", recipient);
        obj.put("Message", message);
        obj.put("MessageHash", createMessageHash());

        try {

            FileWriter file = new FileWriter("storedMessages.json", true);

            file.write(obj.toJSONString());
            file.write("\n");

            file.close();

        } catch (IOException e) {

            System.out.println("Error writing JSON file.");
        }
    }

    public String getMessageID() {
        return messageID;
    }
}package quickchat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.json.simple.JSONObject;

public class Message {

    private String messageID;
    private String recipient;
    private String message;
    private int messageNumber;

    public static int totalMessages = 0;

    public Message(String recipient, String message) {

        this.recipient = recipient;
        this.message = message;

        generateMessageID();

        messageNumber = totalMessages;
    }

    // Generate random 10-digit ID
    private void generateMessageID() {

        Random random = new Random();

        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);

        messageID = String.valueOf(number);
    }

    // Check Message ID
    public boolean checkMessageID() {

        return messageID.length() <= 10;
    }

    // Check recipient number
    public boolean checkRecipientCell() {

        return recipient.startsWith("+") && recipient.length() <= 13;
    }

    // Create Message Hash
    public String createMessageHash() {

        String[] words = message.split(" ");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        String hash = messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;

        return hash;
    }

    // Send / Store / Delete
    public String sentMessage(int choice) {

        switch (choice) {

            case 1:
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    // Print messages
    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + createMessageHash()
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    // Return total messages
    public static int returnTotalMessages() {

        return totalMessages;
    }

    // Store JSON
    public void storeMessage() {

        JSONObject obj = new JSONObject();

        obj.put("MessageID", messageID);
        obj.put("Recipient", recipient);
        obj.put("Message", message);
        obj.put("MessageHash", createMessageHash());

        try {

            FileWriter file = new FileWriter("storedMessages.json", true);

            file.write(obj.toJSONString());
            file.write("\n");

            file.close();

        } catch (IOException e) {

            System.out.println("Error writing JSON file.");
        }
    }

    public String getMessageID() {
        return messageID;
    }
package quickchat;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {

        String msg = "Hi Mike, can you join us for dinner tonight?";

        assertTrue(msg.length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {

        String msg = "a".repeat(260);

        assertTrue(msg.length() > 250);
    }

    @Test
    public void testRecipientSuccess() {

        Message message = new Message("+27718693002", "Hello");

        assertTrue(message.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {

        Message message = new Message("08575975889", "Hello");

        assertFalse(message.checkRecipientCell());
    }

    @Test
    public void testMessageHash() {

        Message message = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight");

        String hash = message.createMessageHash();

        assertNotNull(hash);
    }

    @Test
    public void testMessageID() {

        Message message = new Message("+27718693002", "Hello");

        assertTrue(message.checkMessageID());
    }

    @Test
    public void testSentMessage() {

        Message message = new Message("+27718693002", "Hello");

        assertEquals("Message successfully sent.",
                message.sentMessage(1));
    }
}package quickchat;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {

        String msg = "Hi Mike, can you join us for dinner tonight?";

        assertTrue(msg.length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {

        String msg = "a".repeat(260);

        assertTrue(msg.length() > 250);
    }

    @Test
    public void testRecipientSuccess() {

        Message message = new Message("+27718693002", "Hello");

        assertTrue(message.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {

        Message message = new Message("08575975889", "Hello");

        assertFalse(message.checkRecipientCell());
    }

    @Test
    public void testMessageHash() {

        Message message = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight");

        String hash = message.createMessageHash();

        assertNotNull(hash);
    }

    @Test
    public void testMessageID() {

        Message message = new Message("+27718693002", "Hello");

        assertTrue(message.checkMessageID());
    }

    @Test
    public void testSentMessage() {

        Message message = new Message("+27718693002", "Hello");

        assertEquals("Message successfully sent.",
                message.sentMessage(1));
    }
}
{
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

/**
 *
 * @author Student
 */
public class Mavenproject1 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
