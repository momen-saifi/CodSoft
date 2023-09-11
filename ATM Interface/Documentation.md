
# Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Usage](#usage)
4. [Usage Examples](#usage-examples)
    - [Creating a New Account](#creating-a-new-account)
    - [Logging In](#logging-in)
    - [Checking Balance](#checking-balance)
    - [Depositing Money](#depositing-money)
    - [Withdrawing Money](#withdrawing-money)
    - [Viewing Transaction History](#viewing-transaction-history)
    - [Changing PIN](#changing-pin)
5.[Data Persistence](#data-persistence)
6. [BankAccount Class](#bankaccount-class)
7. [ATM Class](#atm-class)
8. [Main Method](#main-method)
9. [Contributing](#contributing)
10. [License](#license)
11. [Credits](#credits)

## Overview

The ATM Banking Application is a Java-based simulation of an automated teller machine (ATM) that allows users to create bank accounts, perform various banking operations, and manage their finances securely.

## Features

- Create a new bank account with a randomly generated account number and a 4-digit PIN.
- Log in securely to your bank account using your account number and PIN.
- Check your account balance.
- Deposit money into your account.
- Withdraw money from your account (if you have sufficient funds).
- View your transaction history to keep track of your financial activities.
- Change your 4-digit PIN for added security.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your machine.

### Installation

1. Clone this repository to your local machine:
   ```
   git clone https://github.com/momen-saifi/CodSoft.git
   ```
2. Navigate to the project directory:
   ```
   cd atm-banking-app
   ```
3. Compile the Java code:
   ```
   javac ATM.java
   ```

### Usage

Run the application:
```
java ATM
```

The application will display an ATM menu with options for login, creating an account, and other banking operations.

## Usage Examples

### Creating a New Account

1. Choose option 2 from the main menu to create a new bank account.
2. The application will generate a random account number and a 4-digit PIN.
3. Note down your account number and PIN for future logins.

### Logging In

1. Choose option 1 from the main menu to log in.
2. Enter your account number and the 4-digit PIN when prompted.
3. You will be logged in if the credentials are correct.

### Checking Balance

1. After logging in, choose option 1 to check your account balance.
2. The application will display your current balance.

### Depositing Money

1. After logging in, choose option 2 to deposit money.
2. Enter the amount you want to deposit.
3. The application will update your account balance accordingly.

### Withdrawing Money

1. After logging in, choose option 3 to withdraw money.
2. Enter the amount you want to withdraw (if you have sufficient funds).
3. The application will update your account balance and create a transaction history entry.

### Viewing Transaction History

1. After logging in, choose option 4 to view your transaction history.
2. The application will display a list of your recent transactions.

### Changing PIN

1. After logging in, choose option 5 to change your 4-digit PIN.
2. Enter your current PIN and the new PIN.
3. The application will update your PIN for added security.

### Data Persistence

This application uses file-based data persistence to save and load account information. Account data is stored in the "accounts.txt" file. When you exit the application, your account data will be saved, and when you run the application again, it will load your account data.

## BankAccount Class

The `BankAccount` class represents a user's bank account and contains methods for managing account-related operations.

## ATM Class

The `ATM` class is the main class of the application and orchestrates user interactions and banking operations.

## Main Method

The `main` method is the entry point of the application and initializes the ATM and user data loading.


## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch with your feature or bug fix: `git checkout -b feature/your-feature-name` or `git checkout -b bugfix/issue-number`.
3. Make your changes and commit them: `git commit -m 'Description of your changes'`.
4. Push your changes to your fork: `git push origin feature/your-feature-name` or `git push origin bugfix/issue-number`.
5. Create a pull request against the main branch of this repository.

## License

This ATM Banking Application is open-source software licensed under the MIT License.

## Credits

This application was created by momen and is maintained by the community of contributors.

