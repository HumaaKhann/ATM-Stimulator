
public class Main {

    public static boolean login(int id, int pin) {

    try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {

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

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        if (!login(id, pin)) {
            System.out.println("Invalid credentials!");
            return;
        }

        System.out.println("Login successful!");

        TransactionInterface t = new TransactionImpl();

        while (true) {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Send Money");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter amount: ");
                    double dep = sc.nextDouble();
                    System.out.println("Deposit logic here");
                    break;

                case 2:
                    System.out.print("Enter amount: ");
                    double wd = sc.nextDouble();
                    System.out.println("Withdraw logic here");
                    break;

                case 3:
                    System.out.print("Enter receiver ID: ");
                    int rid = sc.nextInt();
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    System.out.println("Send money logic here");
                    break;

                case 4:
                    t.showMiniStatement(id);
                    break;

                case 5:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    
}