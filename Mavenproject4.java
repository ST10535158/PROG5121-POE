/*package quickchat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Before
    public void setUp() {

        Message.sentMessages.clear();
        Message.disregardedMessages.clear();
        Message.storedMessages.clear();
        Message.messageHashes.clear();
        Message.messageIDs.clear();

        Message.totalMessages = 0;
    }

    // ==========================
    // MESSAGE LENGTH TESTS
    // ==========================

    @Test
    public void testMessageLengthSuccess() {

        String message =
                "Hi Mike, can you join us for dinner tonight?";

        assertTrue(message.length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {

        String message = "a".repeat(260);

        assertTrue(message.length() > 250);
    }

    // ==========================
    // RECIPIENT TESTS
    // ==========================

    @Test
    public void testRecipientSuccess() {

        Message msg =
                new Message("+27718693002", "Hello");

        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {

        Message msg =
                new Message("0718693002", "Hello");

        assertFalse(msg.checkRecipientCell());
    }

    // ==========================
    // MESSAGE ID TEST
    // ==========================

    @Test
    public void testMessageIDLength() {

        Message msg =
                new Message("+27718693002", "Hello");

        assertTrue(msg.checkMessageID());
    }

    // ==========================
    // MESSAGE HASH TEST
    // ==========================

    @Test
    public void testCreateMessageHash() {

        Message msg =
                new Message(
                        "+27718693002",
                        "Hi Mike can you join us tonight");

        String hash = msg.createMessageHash();

        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }

    // ==========================
    // SEND MESSAGE TEST
    // ==========================

    @Test
    public void testSendMessage() {

        Message msg =
                new Message("+27718693002", "Hello");

        String result = msg.sentMessage(1);

        assertEquals(
                "Message successfully sent.",
                result);
    }

    // ==========================
    // DISREGARD MESSAGE TEST
    // ==========================

    @Test
    public void testDisregardMessage() {

        Message msg =
                new Message("+27718693002", "Hello");

        String result = msg.sentMessage(2);

        assertEquals(
                "Message disregarded.",
                result);
    }

    // ==========================
    // STORE MESSAGE TEST
    // ==========================

    @Test
    public void testStoreMessage() {

        Message msg =
                new Message("+27718693002", "Hello");

        String result = msg.sentMessage(3);

        assertEquals(
                "Message successfully stored.",
                result);
    }

    // ==========================
    // SENT ARRAY TEST
    // ==========================

    @Test
    public void testSentMessagesArray() {

        Message msg =
                new Message("+27718693002", "Hello");

        msg.sentMessage(1);

        assertEquals(
                1,
                Message.sentMessages.size());
    }

    // ==========================
    // DISREGARDED ARRAY TEST
    // ==========================

    @Test
    public void testDisregardedMessagesArray() {

        Message msg =
                new Message("+27718693002", "Hello");

        msg.sentMessage(2);

        assertEquals(
                1,
                Message.disregardedMessages.size());
    }

    // ==========================
    // STORED ARRAY TEST
    // ==========================

    @Test
    public void testStoredMessagesArray() {

        Message msg =
                new Message("+27718693002", "Hello");

        msg.sentMessage(3);

        assertEquals(
                1,
                Message.storedMessages.size());
    }

    // ==========================
    // HASH ARRAY TEST
    // ==========================

    @Test
    public void testMessageHashArray() {

        Message msg =
                new Message("+27718693002", "Hello");

        msg.sentMessage(1);

        assertEquals(
                1,
                Message.messageHashes.size());
    }

    // ==========================
    // MESSAGE ID ARRAY TEST
    // ==========================

    @Test
    public void testMessageIDArray() {

        Message msg =
                new Message("+27718693002", "Hello");

        msg.sentMessage(1);

        assertEquals(
                1,
                Message.messageIDs.size());
    }

    // ==========================
    // TOTAL MESSAGE COUNT TEST
    // ==========================

    @Test
    public void testReturnTotalMessages() {

        Message.totalMessages = 5;

        assertEquals(
                5,
                Message.returnTotalMessages());
    }

    // ==========================
    // PRINT MESSAGE TEST
    // ==========================

    @Test
    public void testPrintMessages() {

        Message msg =
                new Message(
                        "+27718693002",
                        "Hello World");

        String details = msg.printMessages();

        assertTrue(details.contains("Message ID"));
        assertTrue(details.contains("Recipient"));
        assertTrue(details.contains("Hello World"));
    }
}
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject4;

/**
 *
 * @author Student
 */
public class Mavenproject4 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
