import java.io.*;
import java.util.*;

class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accountNumber;
    private String name;
    private String pin; // Change the PIN to a 4-digit String
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String name, String pin) {
        this.accountNumber = generateAccountNumber();
        this.name = name;
        this.pin = pin; // Store the 4-digit PIN as a String
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    private String generateAccountNumber() {
       
        Random random = new Random();
        int accountNumberInt = 100_000 + random.nextInt(900_000);
        return String.valueOf(accountNumberInt);
    }

    public void changePIN(String newPIN) {
        this.pin = newPIN;
        System.out.println("PIN changed successfully.");
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposit: +" + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
            return true;
        }
        return false;
    }
}

 public class ATM {
    private Map<String, BankAccount> accounts;
    private BankAccount currentAccount;
    private Scanner scanner;

    public ATM() {
        this.accounts = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        if (currentAccount == null) {
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
        } else {
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Change PIN");
            System.out.println("6. Logout");
        }
    }


    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (currentAccount == null) {
                        login();
                    } else {
                        checkBalance();
                    }
                    break;
                case 2:
                    if (currentAccount == null) {
                        createAccount();
                    } else {
                        deposit();
                    }
                    break;
                case 3:
                    if (currentAccount == null) {
                        System.out.println("Exiting the ATM. Thank you!");
                        scanner.close();
                        saveAccountsToFile();
                        return;
                    } else {
                        withdraw();
                    }
                    break;
                case 4:
                    if (currentAccount == null) {
                        System.out.println("Exiting the ATM. Thank you!");
                        scanner.close();
                        saveAccountsToFile();
                        return;
                    } else {
                        showTransactionHistory();
                    }
                    break;
                case 5:
                    changePIN(); // Handle the "Change PIN" option
                    break;
                case 6:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public void login() {
        if (currentAccount != null) {
            System.out.println("You are already logged in. Logout first.");
            return;
        }

        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter your 4-digit PIN: ");
        String pin = scanner.nextLine();

        for (BankAccount account : accounts.values()) {
            if (account.getAccountNumber().equals(accountNumber) && account.getPin().equals(pin)) {
                currentAccount = account;
                System.out.println("Login successful. Welcome, " + account.getName() + "!");
                return;
            }
        }

        System.out.println("Invalid account number or PIN. Login failed.");
    }

    public void createAccount() {
        if (currentAccount != null) {
            System.out.println("You are already logged in. Logout first.");
            return;
        }

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        String pin = generateRandom4DigitPIN(); // Generate a 4-digit random PIN

        BankAccount newAccount = new BankAccount(name, pin);
        accounts.put(newAccount.getAccountNumber(), newAccount);
        System.out.println("Account created successfully. Your account number is: " + newAccount.getAccountNumber());
        System.out.println("Your 4-digit PIN is: " + newAccount.getPin());
    }

    private String generateRandom4DigitPIN() {
        Random random = new Random();
        int pinInt = random.nextInt(9000) + 1000; // Generates a random 4-digit number between 1000 and 9999
        return String.valueOf(pinInt);
    }
    
    public void changePIN() {
        if (currentAccount == null) {
            System.out.println("You need to log in first.");
            return;
        }

        System.out.print("Enter your current 4-digit PIN: ");
        String currentPIN = scanner.nextLine();

        if (currentAccount.getPin().equals(currentPIN)) {
            System.out.print("Enter your new 4-digit PIN: ");
            String newPIN = scanner.nextLine();
            currentAccount.changePIN(newPIN);
        } else {
            System.out.println("Invalid current PIN. PIN change failed.");
        }
    }

    public void logout() {
        if (currentAccount == null) {
            System.out.println("You are not logged in.");
        } else {
            currentAccount = null;
            System.out.println("Logout successful.");
        }
    }


    public void checkBalance() {
        if (currentAccount != null) {
            System.out.println("Your balance is: " + currentAccount.getBalance());
        } else {
            System.out.println("You are not logged in.");
        }
    }

    public void deposit() {
        if (currentAccount != null) {
            System.out.print("Enter the deposit amount: ");
            double amount = scanner.nextDouble();
            currentAccount.deposit(amount);
            System.out.println("Deposit successful. New balance: " + currentAccount.getBalance());
        } else {
            System.out.println("You are not logged in.");
        }
    }

    public void withdraw() {
        if (currentAccount != null) {
            System.out.print("Enter the withdrawal amount: ");
            double amount = scanner.nextDouble();
            if (currentAccount.withdraw(amount)) {
                System.out.println("Withdrawal successful. New balance: " + currentAccount.getBalance());
            } else {
                System.out.println("Invalid withdrawal amount or insufficient balance.");
            }
        } else {
            System.out.println("You are not logged in.");
        }
    }

    public void showTransactionHistory() {
        if (currentAccount != null) {
            List<String> history = currentAccount.getTransactionHistory();
            if (history.isEmpty()) {
                System.out.println("No transaction history available.");
            } else {
                System.out.println("Transaction History:");
                for (String entry : history) {
                    System.out.println(entry);
                }
            }
        } else {
            System.out.println("You are not logged in.");
        }
    }
    private void saveAccountsToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("accounts.txt"))) {
            outputStream.writeObject(accounts);
            //System.out.println("Account data saved to accounts.txt");
        } catch (IOException e) {
            System.err.println("Error saving account data to file: " + e.getMessage());
        }
    }

    private void loadAccountsFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("accounts.txt"))) {
            Map<String, BankAccount> loadedAccounts = (Map<String, BankAccount>) inputStream.readObject();
            accounts = loadedAccounts;
           // System.out.println("Account data loaded from accounts.txt");
        } catch (FileNotFoundException e) {
           // System.err.println("Account data file not found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading account data from file: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.loadAccountsFromFile(); // Load account data from the file
        atm.run(); // Start the ATM application
    }

}
