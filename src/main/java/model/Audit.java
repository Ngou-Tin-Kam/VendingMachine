package model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// A class called audit which implements the interface Logging
public class Audit implements Logging {
    ArrayList<String> auditList = new ArrayList<>(); // Declares a new ArrayList called auditList

    // Override logAction method from Logging interface to add a timeStamp in front of the action
    @Override
    public void logAction(String action) {
        String timeStamp = generateTimeStamp();
        auditList.add(timeStamp + ": " + action);
    }

    // Method to generate the timeStamp to the format of day/month/year and hours/minutes/seconds
    private String generateTimeStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss"); // Setting the format of the time stamp
        LocalDateTime now = LocalDateTime.now(); // Setting the Date and Time to the local time of the machine
        return dateTimeFormatter.format(now); // Returning the Date and Time with the set format
    }

    // Method to upload the AuditList to the text file with try-catch exception
    public void uploadAuditListToFile() {
        try {
            FileWriter fw = new FileWriter("Audit.txt"); // Generating a new file if one does not exist called Audit.txt
            for (int i = 0; i < auditList.size(); i++) { // For loop to go through the ArrayList
                fw.write(auditList.get(i) + "\n"); // Writing into the file with the .get(index)
            }
            fw.close(); // Closing the file
        } catch (IOException e) {
            e.printStackTrace(); // Printing the exception
        }
    }
}
