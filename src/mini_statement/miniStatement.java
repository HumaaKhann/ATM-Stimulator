package mini_statement;
    
    // we have our transaction history stored in a fiel transcation.txt
    // so we will be reading that file and then on the bases of the id of the user we will be showing the mini statement of that user
    // we also have to see if it is a deposit or a withdrawl and then we will be showing the mini statement accordingly


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class miniStatement {

    void showMiniStatement(int userId) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("transaction.txt"));
            String line;

            System.out.println("Mini Statement for User ID: " + userId);
            System.out.println("--------------------------------------");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                int senderId = Integer.parseInt(data[1]);
                int receiverId = Integer.parseInt(data[2]);
                double amount = Double.parseDouble(data[3]);
                String timestamp = data[4];
                String receiverName = data[5];
                String senderName = data[6];

                // Check if this transaction belongs to user
                if (userId == senderId) {
                    System.out.println("Withdrawn: " + amount +
                            " To: " + receiverName +
                            " | Time: " + timestamp);
                } 
                else if (userId == receiverId) {
                    System.out.println("Deposited: " + amount +
                            " From: " + senderName +
                            " | Time: " + timestamp);
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
    

