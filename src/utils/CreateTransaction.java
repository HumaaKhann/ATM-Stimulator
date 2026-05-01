package utils;

import model.transactionmodel;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class CreateTrascation {
    transactionmodel transaction; 

    CreateTrascation(transactionmodel transaction) {
        this.transaction = transaction;
    }

    void createTransaction() {
        try {
            FileWriter fw = new FileWriter("transaction.txt", true); // true = append mode
            BufferedWriter bw = new BufferedWriter(fw);

            // Convert object → string
            String data = transaction.getId() + "," +
                          transaction.getSenderId() + "," +
                          transaction.getReceiverId() + "," +
                          transaction.getAmount() + "," +
                          transaction.getTimestamp() + "," +
                          transaction.getReceiverName() + "," +
                          transaction.getSenderName();

            bw.write(data);
            bw.newLine(); // go to next line
            bw.close();

            System.out.println("Transaction saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}