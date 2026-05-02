package utils;

import java.io.FileWriter;
import model.usermodel;

public class UserFileUtil {

    public static void saveUser(usermodel user) {
        try {
            FileWriter writer = new FileWriter("data/user.txt", true); // true = append

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

    public double checkBalance(int userId) {
        try {
            usermodel user = new UserSearch().findUserById(userId);
            return user != null ? user.getBalance() : -1;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }
}
