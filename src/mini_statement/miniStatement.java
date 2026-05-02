package mini_statement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class miniStatement {

    private static final int CASH_DEPOSIT_RECEIVER_ID  = 0;
    private static final int CASH_WITHDRAW_RECEIVER_ID = 1;

    // ANSI colours (same palette as Main)
    private static final String RESET  = "\u001B[0m";
    private static final String BOLD   = "\u001B[1m";
    private static final String CYAN   = "\u001B[36m";
    private static final String GREEN  = "\u001B[32m";
    private static final String RED    = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String DIM    = "\u001B[2m";

    public void showMiniStatement(int userId, String userName) {

        try (BufferedReader br = new BufferedReader(new FileReader("data/transaction.txt"))) {

            System.out.println();
            System.out.println(CYAN + BOLD +
                "  ┌─────────────────────────────────────────────┐\n" +
                "  │              MINI  STATEMENT                │\n" +
                "  ├─────────────────────────────────────────────┤" + RESET);
            System.out.println(BOLD +
                "  │  Account Holder : " + padRight(userName.toUpperCase(), 26) + "│" + RESET);
            System.out.println(BOLD +
                "  │  User ID        : " + padRight(String.valueOf(userId), 26) + "│" + RESET);
            System.out.println(CYAN +
                "  └─────────────────────────────────────────────┘" + RESET);
            System.out.println();

            boolean hasRecords = false;
            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                if (data.length < 7) continue;

                try {
                    int    senderId    = Integer.parseInt(data[1].trim());
                    int    receiverId  = Integer.parseInt(data[2].trim());
                    double amount      = Double.parseDouble(data[3].trim());
                    String timestamp   = data[4].trim();
                    String receiverName = data[5].trim();
                    String senderName   = data[6].trim();

                    // Shorten timestamp for display (remove nanoseconds)
                    if (timestamp.length() > 19) timestamp = timestamp.substring(0, 19);
                    timestamp = timestamp.replace("T", "  ");

                    if (userId == senderId) {
                        hasRecords = true;
                        if (receiverId == CASH_DEPOSIT_RECEIVER_ID) {
                            System.out.printf(GREEN + "  %-10s  PKR %,-12.2f  %s%n" + RESET,
                                "DEPOSIT", amount, DIM + timestamp + RESET);
                        } else if (receiverId == CASH_WITHDRAW_RECEIVER_ID) {
                            System.out.printf(RED + "  %-10s  PKR %,-12.2f  %s%n" + RESET,
                                "WITHDRAW", amount, DIM + timestamp + RESET);
                        } else {
                            System.out.printf(YELLOW + "  %-10s  PKR %,-12.2f  →  %-12s  %s%n" + RESET,
                                "SENT", amount, receiverName, DIM + timestamp + RESET);
                        }
                    } else if (userId == receiverId) {
                        hasRecords = true;
                        System.out.printf(GREEN + "  %-10s  PKR %,-12.2f  ←  %-12s  %s%n" + RESET,
                            "RECEIVED", amount, senderName, DIM + timestamp + RESET);
                    }

                } catch (NumberFormatException ignored) {
                    // skip malformed lines
                }
            }

            if (!hasRecords) {
                System.out.println(DIM + "  No transactions found for this account." + RESET);
            }

        } catch (IOException e) {
            System.out.println("\u001B[31m  ✘  Error reading transaction file: " + e.getMessage() + RESET);
        }
    }

    // Overload for backward compatibility (called without userName)
    public void showMiniStatement(int userId) {
        showMiniStatement(userId, "User " + userId);
    }

    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}
