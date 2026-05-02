
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import mini_statement.miniStatement;
import model.transactionmodel;
import model.usermodel;
import transaction.transaction;
import utils.*;


public class Main {

    public static boolean login(int id, int pin) {

    try (BufferedReader br = new BufferedReader(new FileReader("data/user.txt"))) {

        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            int fileId = Integer.parseInt(data[0]);
            int filePin = Integer.parseInt(data[2]);

            if (fileId == id && filePin == pin) {
                return true;
            }
        }

    } catch (Exception e) {
        System.out.println("Error reading users file");
    }

    return false;
}




    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GenerateId idGenerator = new GenerateId();
        UserSearch userSearch = new UserSearch();
        


        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        if (!login(id, pin)) {
            System.out.println("Invalid credentials!");
            return;
        }

        System.out.println("Login successful!");

        usermodel user = userSearch.findUserById(id);
        miniStatement miniStatement = new miniStatement();

        

        while (true) {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Send Money");
            System.out.println("4. Mini Statement");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter amount: ");
                    int deposit = sc.nextInt();

                    // receiverId=0 marks a cash deposit for mini statement
                    transactionmodel td = new transactionmodel(
                            idGenerator.generateRandomId(),
                            id,
                            0,
                            deposit,
                            java.time.LocalDateTime.now().toString(),
                            "CASH",
                            user.getName()
                    );

                    transaction transaction = new transaction( td , deposit);
                    transaction.deposit_money();

                    System.out.println("Deposit logic here");
                    break;




                case 2:
                    System.out.print("Enter amount: ");
                    int withdrawl_money = sc.nextInt();

                    // receiverId=1 marks a cash withdrawal for mini statement
                    transactionmodel tw = new transactionmodel(
                            idGenerator.generateRandomId(),
                            id,
                            1,
                            withdrawl_money,
                            java.time.LocalDateTime.now().toString(),
                            "CASH",
                            user.getName()
                    );
                    
                    transaction transaction2 = new transaction( tw , withdrawl_money);
                    transaction2.widthdraw_money();

                     

                    System.out.println("Withdraw logic here");
                    break;

                case 3:
                    System.out.print("Enter receiver ID: ");
                    int rid = sc.nextInt();
                    System.out.print("Enter amount: ");
                    int amount = sc.nextInt();

                    transactionmodel ts = new transactionmodel(
                            idGenerator.generateRandomId(),
                            id,
                            rid,
                            amount,
                            java.time.LocalDateTime.now().toString(),
                            userSearch.findUserById(rid).getName(),
                            user.getName()
                    );

                    transaction transaction3 = new transaction( ts , amount);
                    transaction3.send_money();

                    System.out.println("Send money logic here");
                    break;

                case 4:
                    miniStatement.showMiniStatement(id);
                    break;

                case 5:
                    System.out.println("Check balance logic here");
                        double balance = new UserFileUtil().checkBalance(id);
                        if (balance >= 0) {
                            System.out.println("Your balance is: " + balance);
                        } else {
                            System.out.println("Error retrieving balance");
                        }
                    break;
                    
                case 6:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

}
