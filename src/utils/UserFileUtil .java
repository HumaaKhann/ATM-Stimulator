package utils;

import java.io.FileWriter;
import model.usermodel;

public class UserFileUtil{

    public static void saveUser(usermodel user) {
        try {
            FileWriter writer = new FileWriter("user.txt", true); // true = append

            String data = user.getId() + "," +
                          user.getName() + "," +
                          user.getPin() + "," +
                          user.getBalance();

            writer.write(data + "\n");
            writer.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}