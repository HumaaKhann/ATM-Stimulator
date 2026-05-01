package utils;

import java.io.*;

public class DeleteUserById {

    public void deleteUserById(int id) {
        File inputFile = new File("users.txt");
        File tempFile = new File("temp.txt");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                // Skip empty or invalid lines
                if (parts.length == 0) continue;

                int fileId = Integer.parseInt(parts[0]);

                // Keep only lines that DON'T match the id
                if (fileId != id) {
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace old file with updated file
        if (inputFile.delete()) {
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Error renaming temp file.");
            }
        } else {
            System.out.println("Could not delete original file.");
        }
    }
}