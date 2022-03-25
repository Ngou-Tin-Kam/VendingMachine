package model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Audit implements Logging {
    ArrayList<String> auditList = new ArrayList<>();

    @Override
    public void logAction(String action) {
        String timeStamp = generateTimeStamp();
        auditList.add(timeStamp + ": " + action);
    }

    private String generateTimeStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormatter.format(now);
    }

    public void uploadAuditListToFile() {
        try {
            FileWriter fw = new FileWriter("Audit.txt");
            for (int i = 0; i < auditList.size(); i++) {
                fw.write(auditList.get(i) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
