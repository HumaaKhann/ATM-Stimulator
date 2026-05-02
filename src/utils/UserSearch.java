package utils;

import java.io.BufferedReader;
import java.io.FileReader;

import model.usermodel;

public class UserSearch {

    public  usermodel findUserById(int searchId) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/user.txt"));
            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);

                if (id == searchId) {
                    String name = parts[1];
                    String pin = parts[2];
                    double balance = Double.parseDouble(parts[3]);

                    return new usermodel(id, name, pin, balance);
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }
}
