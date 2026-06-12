/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationuser;

/**
 *
 * @author junio
 */
import java.util.Random;
import java.io.FileWriter;
import java.util.ArrayList;
public class MessageClass {
    
    private static int totalMessages = 0;
    
   ///Accessable only class
    private final int messageNumber;
    private final String recipient;
    private final String messageText;
    private final String messageHash;
    private final String messageId;
    
    private static ArrayList<String> sentMessages = new ArrayList<>();
    private static ArrayList<String> storedMessages = new ArrayList<>();
    private static ArrayList<String> disregardedMessages = new ArrayList<>();
    private static ArrayList<String> messageHashes= new ArrayList<>();
    private static ArrayList<String> messageIDs = new ArrayList<>();
    private static ArrayList<String> recipients = new ArrayList<>();
    
    
    
    ///"This " allows access to the objects instant variables///
    public MessageClass (String recipient, String messageText){
        this.messageId = generateMessageId();
        this.messageNumber = ++totalMessages;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }
    private String generateMessageId() {
        Random random = new Random();
        long number = 1000000000L + random.nextInt((int) 900000000L);
        return String.valueOf(number);
    }

    public boolean checkMessageId() {
        return messageId.length() <= 10;
    }

    public int checkMessageLength() {
        return messageText.length();
    }

    public String validateMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }

        int extraCharacters = messageText.length() - 250;
        return "Message exceeds 250 characters by " + extraCharacters + ", please reduce size.";
    }

    public boolean checkRecipientCell() {
        return recipient.matches("^\\+27\\d{9}$");
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return messageId.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord.toUpperCase()
                + lastWord.toUpperCase();
    }
    
    //Store message
    public void storeMessage() {
        storedMessages.add(printMessage()); ///stores full message once
        messageHashes.add(messageHash); // keep hash for lookup
        recipients.add(recipient); ///keep recipient for search
        System.out.println("Mesaage stored successfully ");
        
    }
    
    //Send message
    public void sendMessage() {
        sentMessages.add(printMessage());
        System.out.println("Message sent successfully");
        
    }
    public static ArrayList<String> getSentMessages(){
        return sentMessages;
    }
    
    //Disregard message
public void disregardMessage(){
    disregardedMessages.add(printMessage());
    System.out.print("Message disregarded");
    
}
//get longest Stored Message
public static String getLongestStoredMessage(){
    String longest = "";
    for (String msg : storedMessages) {
        if (msg.length() > longest.length()){
            longest = msg;
        }
    }
    return longest.isEmpty() ? "No stored messages found." : longest;
}
// Search by recipient
    public static void searchByRecipient(String recipientSearch) {
        boolean found = false;
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(recipientSearch)) {
                System.out.println("Message found:\n" + storedMessages.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for recipient: " + recipientSearch);
        }
            
    }
    
    // Delete by hash
    public static void deleteMessageByHash(String hash) {
        int index = messageHashes.indexOf(hash);
        if (index != -1) {
            storedMessages.remove(index);
            recipients.remove(index);
            messageHashes.remove(index);
            System.out.println("Message deleted successfully.");
        } else {
            System.out.println("No message found with hash: " + hash);
        }
    }
     // Display report
    public static void displayReport() {
        System.out.println("----- Message Report -----");
        System.out.println("Total Messages: " + totalMessages);
        System.out.println("Sent Messages: " + sentMessages.size());
        System.out.println("Stored Messages: " + storedMessages.size());
        System.out.println("Disregarded Messages: " + disregardedMessages.size());
    }

    

    
   
    
        
    
    


    public String printMessage() {
        return "Message ID: " + messageId
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    
        
    }
    public void saveMessageToJson() {
        
        ///Stores messages inside the file///

    String json = "{\n"
            + "  \"messageId\": \"" + messageId + "\",\n"
            + "  \"messageNumber\": " + messageNumber + ",\n"
            + "  \"recipient\": \"" + recipient + "\",\n"
            + "  \"messageText\": \"" + messageText + "\",\n"
            + "  \"messageHash\": \"" + messageHash + "\"\n"
            + "}";

    try {

        FileWriter writer = new FileWriter("message" + messageNumber + ".json");

        writer.write(json);

        writer.close();

        System.out.println("JSON file created successfully.");

    } catch (Exception e) {

        System.out.println("Error creating JSON file.");
        e.printStackTrace();
    }
}
    }
           
    
    
    
    
    
    

