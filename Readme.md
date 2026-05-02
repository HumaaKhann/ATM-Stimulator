# ATM Simulator

A console-based ATM simulator built in Java. It supports user login, deposits, withdrawals, money transfers, balance checks, and mini statements — all backed by flat-file storage (no database required).

---

## Features

- **Login** — authenticate with user ID and PIN
- **Deposit** — add funds to your account
- **Withdraw** — deduct funds with balance validation
- **Send Money** — transfer funds to another user by ID
- **Check Balance** — view your current balance
- **Mini Statement** — view your full transaction history (sent, received, deposited, withdrawn)

---

## Project Structure

```
ATM-Simulator/
├── data/
│   ├── user.txt            # Stores user records (id, name, pin, balance)
│   └── transaction.txt     # Stores all transaction records
│
├── src/
│   ├── Main.java           # Entry point — login flow and ATM menu
│   │
│   ├── model/
│   │   ├── usermodel.java          # User data model (id, name, pin, balance)
│   │   └── transactionmodel.java   # Transaction data model (id, sender, receiver, amount, timestamp, names)
│   │
│   ├── transaction/
│   │   └── transaction.java        # Core transaction logic (deposit, withdraw, send)
│   │
│   ├── mini_statement/
│   │   └── miniStatement.java      # Reads transaction.txt and prints user history
│   │
│   ├── user/
│   │   └── user.java               # User-related operations (placeholder/extensible)
│   │
│   └── utils/
│       ├── UserFileUtil.java        # Save new users and check balance
│       ├── UserSearch.java          # Find a user by ID from user.txt
│       ├── ChangeBalanceById.java   # Update a user's balance in user.txt
│       ├── SaveTrascationIntoFile.java  # Append a transaction to transaction.txt
│       ├── DeleteUserById.java      # Remove a user record from user.txt
│       ├── GenerateId.java          # Generate a random 6-digit transaction ID
│       └── FileOpener.java          # File utility helper
│
└── out/                    # Compiled .class files (generated at build time)
```

---

## Data Format

**`data/user.txt`** — one user per line:
```
id,name,pin,balance
1,rasim,1234,950.0
2,ali,5678,3300.0
```

**`data/transaction.txt`** — one transaction per line:
```
id,senderId,receiverId,amount,timestamp,receiverName,senderName
570973,1,0,1000.0,2026-05-02T10:37:48,CASH,rasim
```

> Special receiver IDs: `0` = cash deposit, `1` = cash withdrawal

---

## How to Run

### macOS / Linux

```bash
cd /Users/rasim/Projects/ATM-Stimulator && rm -rf out/* && javac -d out $(find src -name "*.java") && java -cp out Main
```

### Windows (PowerShell)

```powershell
cd "C:\Users\Nahila\OneDrive\Documents\DevProjects\Java\ATM_Stimulator"; Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue; mkdir out; javac -d out (Get-ChildItem -Recurse -Filter *.java src).FullName; java -cp out Main
```

> Make sure you have **JDK 11+** installed and `javac` is available in your PATH.

---

## Usage

After running, you'll be prompted to log in:

```
Enter ID: 1
Enter PIN: 1234
Login successful!

===== ATM MENU =====
1. Deposit
2. Withdraw
3. Send Money
4. Mini Statement
5. Check Balance
6. Exit
```

Use the existing users in `data/user.txt` to log in, or add your own following the data format above.

---

## Requirements

- Java JDK 11 or higher
- No external libraries or build tools needed
