
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import mini_statement.miniStatement;
import model.transactionmodel;
import model.usermodel;
import transaction.transaction;
import utils.*;


public class Main {

    // ─────────────────────────────────────────────
    //  UI Helpers
    // ─────────────────────────────────────────────

    static final String RESET  = "\u001B[0m";
    static final String BOLD   = "\u001B[1m";
    static final String CYAN   = "\u001B[36m";
    static final String GREEN  = "\u001B[32m";
    static final String RED    = "\u001B[31m";
    static final String YELLOW = "\u001B[33m";
    static final String DIM    = "\u001B[2m";

    static void line() {
        System.out.println(DIM + "  ─────────────────────────────────────────" + RESET);
    }

    static void header() {
        System.out.println();
        System.out.println(CYAN + BOLD +
            "  ╔═══════════════════════════════════════╗\n" +
            "  ║           ATM  SIMULATOR              ║\n" +
            "  ╚═══════════════════════════════════════╝" + RESET);
    }

    static void success(String msg) {
        System.out.println(GREEN + "  ✔  " + msg + RESET);
    }

    static void error(String msg) {
        System.out.println(RED + "  ✘  " + msg + RESET);
    }

    static void info(String msg) {
        System.out.println(CYAN + "  ➜  " + msg + RESET);
    }

    static void prompt(String msg) {
        System.out.print(YELLOW + "  ›  " + msg + RESET);
    }

    // ─────────────────────────────────────────────
    //  Login
    // ─────────────────────────────────────────────

    public static boolean login(int id, int pin) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/user.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 3) continue;
                int fileId  = Integer.parseInt(data[0].trim());
                int filePin = Integer.parseInt(data[2].trim());
                if (fileId == id && filePin == pin) return true;
            }
        } catch (Exception e) {
            error("Could not read user data.");
        }
        return false;
    }

    // ─────────────────────────────────────────────
    //  Main
    // ─────────────────────────────────────────────

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GenerateId   idGenerator = new GenerateId();
        UserSearch   userSearch  = new UserSearch();

        header();
        line();

        // ── Login ──
        prompt("Enter User ID : "); int id  = sc.nextInt();
        prompt("Enter PIN     : "); int pin = sc.nextInt();

        line();

        if (!login(id, pin)) {
            error("Invalid credentials. Access denied.");
            System.out.println();
            sc.close();
            return;
        }

        usermodel user = userSearch.findUserById(id);
        miniStatement miniStatement = new miniStatement();

        // ── Welcome banner ──
        System.out.println();
        System.out.println(GREEN + BOLD +
            "  ╔═══════════════════════════════════════╗\n" +
            "  ║           LOGIN SUCCESSFUL            ║\n" +
            "  ╚═══════════════════════════════════════╝" + RESET);
        System.out.println(BOLD + "  Welcome, " + user.getName().toUpperCase() + "!" + RESET);
        info("User ID  : " + user.getId());
        info("Account  : " + user.getName());
        line();

        // ── ATM Loop ──
        while (true) {

            System.out.println();
            System.out.println(CYAN + BOLD + "  ┌─────────────────────────────────────┐");
            System.out.println(          "  │           A T M   M E N U           │");
            System.out.println(          "  ├─────────────────────────────────────┤" + RESET);
            System.out.println(BOLD +    "  │  1.  Deposit                        │");
            System.out.println(          "  │  2.  Withdraw                       │");
            System.out.println(          "  │  3.  Send Money                     │");
            System.out.println(          "  │  4.  Mini Statement                 │");
            System.out.println(          "  │  5.  Check Balance                  │");
            System.out.println(          "  │  6.  Exit                           │");
            System.out.println(CYAN +    "  └─────────────────────────────────────┘" + RESET);
            System.out.println();
            prompt("Select option: ");

            int choice = sc.nextInt();
            line();

            switch (choice) {

                case 1:
                    prompt("Enter deposit amount: PKR ");
                    int deposit = sc.nextInt();

                    transactionmodel td = new transactionmodel(
                        idGenerator.generateRandomId(), id, 0, deposit,
                        java.time.LocalDateTime.now().toString(), "CASH", user.getName()
                    );
                    new transaction(td, deposit).deposit_money();
                    line();
                    break;

                case 2:
                    prompt("Enter withdrawal amount: PKR ");
                    int withdrawAmount = sc.nextInt();

                    transactionmodel tw = new transactionmodel(
                        idGenerator.generateRandomId(), id, 1, withdrawAmount,
                        java.time.LocalDateTime.now().toString(), "CASH", user.getName()
                    );
                    new transaction(tw, withdrawAmount).widthdraw_money();
                    line();
                    break;

                case 3:
                    prompt("Enter receiver ID  : ");
                    int rid = sc.nextInt();

                    usermodel receiver = userSearch.findUserById(rid);
                    if (receiver == null) {
                        error("Receiver not found.");
                        line();
                        break;
                    }

                    prompt("Enter amount: PKR ");
                    int amount = sc.nextInt();

                    transactionmodel ts = new transactionmodel(
                        idGenerator.generateRandomId(), id, rid, amount,
                        java.time.LocalDateTime.now().toString(),
                        receiver.getName(), user.getName()
                    );
                    new transaction(ts, amount).send_money();
                    line();
                    break;

                case 4:
                    miniStatement.showMiniStatement(id, user.getName());
                    line();
                    break;

                case 5:
                    double balance = new UserFileUtil().checkBalance(id);
                    if (balance >= 0) {
                        System.out.println();
                        System.out.println(CYAN + BOLD +
                            "  ┌─────────────────────────────────────┐\n" +
                            "  │           ACCOUNT BALANCE           │\n" +
                            "  ├─────────────────────────────────────┤" + RESET);
                        System.out.printf(GREEN + BOLD +
                            "  │   PKR  %,-30.2f│%n" + RESET, balance);
                        System.out.println(CYAN +
                            "  └─────────────────────────────────────┘" + RESET);
                    } else {
                        error("Could not retrieve balance.");
                    }
                    line();
                    break;

                case 6:
                    System.out.println();
                    success("Session ended. Thank you, " + user.getName().toUpperCase() + "!");
                    info("Please collect your card. Have a great day.");
                    System.out.println();
                    sc.close();
                    return;

                default:
                    error("Invalid option. Please choose 1–6.");
                    line();
            }
        }
    }
}
