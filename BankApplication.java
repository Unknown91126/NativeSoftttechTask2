import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private String accountHolderName;
    private int accountNumber;
    private double balance;
    private String password;
    private ArrayList<String> transactionHistory;

    public BankAccount(String accountHolderName, int accountNumber, double initialDeposit, String password) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
        this.password = password;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial deposit: $" + initialDeposit);
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount + " | Balance: $" + balance);
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount + " | Balance: $" + balance);
            System.out.println("Successfully withdrawn $" + amount);
        } else {
            System.out.println("Invalid or insufficient funds for withdrawal!");
        }
    }

    public void viewTransactionHistory() {
        System.out.println("Transaction History for Account Number: " + accountNumber);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class BankApplication {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static int accountCounter = 1000;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n======== Bank Application Menu ========");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Delete Account");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    deleteAccount();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting... Thank you for using the Bank Application!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account holder's name: ");
        scanner.nextLine(); // Consume the newline character
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Set a password for your account: ");
        String password = scanner.nextLine();

        BankAccount newAccount = new BankAccount(name, accountCounter++, initialDeposit, password);
        accounts.add(newAccount);
        System.out.println("Account created successfully! Account Number: " + newAccount.getAccountNumber());
    }

    private static void deposit() {
        BankAccount account = authenticateAccount();
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        }
    }

    private static void withdraw() {
        BankAccount account = authenticateAccount();
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        }
    }

    private static void checkBalance() {
        BankAccount account = authenticateAccount();
        if (account != null) {
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Current Balance: $" + account.getBalance());
        }
    }

    private static void viewTransactionHistory() {
        BankAccount account = authenticateAccount();
        if (account != null) {
            account.viewTransactionHistory();
        }
    }

    private static void deleteAccount() {
        System.out.print("Enter account number to delete: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter account password: ");
        String password = scanner.nextLine();

        BankAccount account = findAccount(accountNumber);
        if (account != null && account.authenticate(password)) {
            accounts.remove(account);
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Invalid account number or password. Unable to delete account.");
        }
    }

    private static BankAccount authenticateAccount() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter account password: ");
        String password = scanner.nextLine();

        BankAccount account = findAccount(accountNumber);
        if (account != null && account.authenticate(password)) {
            System.out.println("Authentication successful!");
            return account;
        } else {
            System.out.println("Authentication failed! Invalid account number or password.");
            return null;
        }
    }

    private static BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}