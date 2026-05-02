package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class DeleteUserById {

    public void deleteUserById(int id) {
        Path inputPath = Path.of("data", "user.txt");
        Path tempPath = Path.of("data", "temp-" + System.nanoTime() + ".txt");

        try (
            BufferedReader reader = Files.newBufferedReader(inputPath);
            BufferedWriter writer = Files.newBufferedWriter(tempPath)
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
            return;
        }

        // Replace old file with updated file (more reliable on Windows than File#delete + renameTo)
        try {
            replaceWithRetry(tempPath, inputPath, 8, 80);
        } catch (IOException e) {
            System.out.println("Could not replace user file. Is it open in another program?");
            try {
                Files.deleteIfExists(tempPath);
            } catch (IOException ignored) {
            }
        }
    }

    private static void replaceWithRetry(Path tempPath, Path inputPath, int attempts, long sleepMs) throws IOException {
        IOException last = null;
        for (int i = 0; i < attempts; i++) {
            try {
                Files.move(tempPath, inputPath, StandardCopyOption.REPLACE_EXISTING);
                return;
            } catch (IOException e) {
                last = e;
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw e;
                }
            }
        }
        throw last;
    }
}
