import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Transaction class to represent individual transactions
class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

// User class to represent ATM users
class User {
    private String userId;
    private String userPin;
    private List<Transaction> transactionHistory;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

// ATM class to handle ATM functionalities
class ATM {
    private User user;
    private double balance;

    public ATM(User user) {
        this.user = user;
        this.balance = 0.0;
    }

    public void displayMenu() {
        System.out.println("ATM Functionalities:");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
    }

    public void processChoice(int choice) {
        switch (choice) {
            case 1:
                displayTransactionHistory();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                deposit();
                break;
            case 4:
                transfer();
                break;
            case 5:
                System.out.println("Thank you for using the ATM.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void displayTransactionHistory() {
        List<Transaction> transactions = user.getTransactionHistory();
        System.out.println("Transaction History:");
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println("Type: " + transaction.getType() + ", Amount: " + transaction.getAmount());
            }
        }
    }

    private void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            user.getTransactionHistory().add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    private void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balance += amount;
            user.getTransactionHistory().add(new Transaction("Deposit", amount));
            System.out.println("Deposit successful. Updated balance: " + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void transfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the recipient's account number: ");
        String recipientAccount = scanner.next();
        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            user.getTransactionHistory().add(new Transaction("Transfer to " + recipientAccount, amount));
            System.out.println("Transfer successful. Remaining balance: " + balance);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }
}

// Main class to start the ATM application
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your user id: ");
        String userId = scanner.next();
        System.out.print("Enter your user pin: ");
        String userPin = scanner.next();

        // Assuming there is only one user in this example
        User user = new User(userId, userPin);
        ATM atm = new ATM(user);

        while (true) {
            atm.displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            atm.processChoice(choice);
        }
    }
}


