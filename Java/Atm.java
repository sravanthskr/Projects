// ATM based Project using Java

import java.util.*; // Import necessary packages for using collections and scanner

class Atm {
    // Create a Scanner object to read user input
    Scanner sc = new Scanner(System.in);
    
    // Default PIN for the ATM
    int pin = 7492; 
    
    // Initial balance in the ATM
    double balance = 70000; 
    
    // List to store transaction history
    List<String> transactionHistory = new ArrayList<>(); 

    // Method for authenticating the user
    public void auth() {
        System.out.print("Please enter your PIN: ");
        int attempts = 0; // Counter for attempts
        // Allow up to 3 attempts to enter the correct PIN
        while (attempts < 3) { 
            int password = sc.nextInt(); // Read the user's PIN
            // Check if the entered PIN is correct
            if (password == pin) {
                select(); // Move to the main menu if authenticated
                return; // Exit the method after successful authentication
            } else {
                attempts++; // Increment the attempts counter
                System.out.println("Incorrect PIN. Try again."); // Prompt for retry
            }
        }
        // If the user fails to enter the correct PIN after 3 attempts
        System.out.println("Too many incorrect attempts. Exiting.");
        System.exit(0); // Exit the program
    }

    // Method for displaying the main menu and handling user selections
    public void select() {
        // Loop to keep showing the menu until the user exits
        while (true) { 
            // Display the menu options
            System.out.println("\nSelect an option:" +
                    "\n1. View Balance" +
                    "\n2. Withdraw" +
                    "\n3. Deposit" +
                    "\n4. Change PIN" +
                    "\n5. View Transaction History" +
                    "\n6. Exit");
            int option = sc.nextInt(); // Read user's menu selection
            
            // Process the selected option using a switch statement
            switch (option) {
                case 1: // View Balance option
                    viewBalance();
                    break;
                case 2: // Withdraw option
                    withdraw();
                    break;
                case 3: // Deposit option
                    deposit();
                    break;
                case 4: // Change PIN option
                    changePin();
                    break;
                case 5: // View Transaction History option
                    viewTransactionHistory();
                    break;
                case 6: // Exit option
                    // Confirm if the user really wants to exit
                    if (confirmExit()) {
                        exit(); // Execute exit method
                        return; // Exit from the select method
                    }
                    break;
                default: // Handle invalid menu selection
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to display the current balance
    public void viewBalance() {
        // Print the current balance formatted to two decimal places
        System.out.printf("Your current balance is: %.2f\n", balance);
    }

    // Method to handle withdrawal of funds
    public void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = sc.nextDouble(); // Read the withdrawal amount
        // Check if the withdrawal amount is positive
        if (withdrawAmount <= 0) {
            System.out.println("Withdrawal amount must be positive!");
        } else if (withdrawAmount > balance) { // Check if funds are sufficient
            System.out.println("Insufficient funds!");
        } else {
            balance -= withdrawAmount; // Deduct the amount from balance
            transactionHistory.add("Withdrew: " + withdrawAmount); // Record transaction
            System.out.printf("Please take your cash: %.2f\n", withdrawAmount); // Notify user
        }
    }

    // Method to handle deposit of funds
    public void deposit() {
        System.out.print("Enter amount to deposit: ");
        double depositAmount = sc.nextDouble(); // Read the deposit amount
        // Check if the deposit amount is positive
        if (depositAmount <= 0) {
            System.out.println("Deposit amount must be positive!");
        } else {
            balance += depositAmount; // Add the amount to balance
            transactionHistory.add("Deposited: " + depositAmount); // Record transaction
            System.out.printf("You have successfully deposited: %.2f\n", depositAmount); // Notify user
        }
    }

    // Method to change the user's PIN
    public void changePin() {
        while (true) { // Loop until a new PIN is set or user chooses to go back
            System.out.print("Enter your new PIN (4 digits) or type -1 to go back: ");
            int newPin = sc.nextInt(); // Read the new PIN
            
            if (newPin == -1) { // Check if the user wants to go back
                System.out.println("Going back to the main menu.");
                return; // Exit the method to go back
            } else if (String.valueOf(newPin).length() != 4) { // Validate PIN length
                System.out.println("PIN must be exactly 4 digits!");
            } else if (newPin == pin) { // Check if new PIN is the same as old one
                System.out.println("New PIN must be different from the old PIN! Try again.");
            } else {
                pin = newPin; // Update the PIN
                System.out.println("Your new PIN is successfully updated."); // Notify user
                return; // Exit the method after successfully changing the PIN
            }
        }
    }

    // Method to view transaction history
    public void viewTransactionHistory() {
        System.out.println("Transaction History:");
        if (transactionHistory.isEmpty()) { // Check if there are no transactions
            System.out.println("No transactions found.");
        } else {
            // Print each transaction from the history
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Method to confirm exit from the ATM
    public boolean confirmExit() {
        System.out.print("Are you sure you want to exit? (yes/no): ");
        String response = sc.next(); // Read user confirmation
        // Return true if user confirms exit, false otherwise
        return response.equalsIgnoreCase("yes");
    }

    // Method to exit the ATM application
    public void exit() {
        System.out.println("Thanks for using the ATM!"); // Thank user before exit
    }

    // Main method to run the ATM application
    public static void main(String[] args) {
        Atm a = new Atm(); // Create an ATM object
        a.auth(); // Start the authentication process
    }
}
