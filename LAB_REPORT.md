# Lab Report
## ATM Simulator — Console-Based Banking Application in Java

---

| Field | Details |
|---|---|
| **Course** | Object-Oriented Programming / Java Programming |
| **Submitted By** | [Your Name] |
| **Student ID** | [Your Student ID] |
| **Submitted To** | [Professor's Name] |
| **Department** | [Your Department] |
| **Date** | May 2, 2026 |

---

## 1. Objective

The objective of this lab project is to design and implement a console-based ATM Simulator using Java. The application demonstrates core Object-Oriented Programming (OOP) principles including encapsulation, class design, and separation of concerns, while simulating real-world banking operations such as login authentication, deposits, withdrawals, fund transfers, and transaction history — all without the use of a database, relying instead on flat-file (`.txt`) persistence.

---

## 2. Introduction

An Automated Teller Machine (ATM) is an electronic banking terminal that allows customers to perform basic financial transactions without the need for a human teller. This project simulates the core functionality of an ATM in a terminal/console environment.

The system reads and writes user and transaction data from plain text files, making it self-contained and portable. The project was developed incrementally, with a focus on clean code structure, modular design, and a user-friendly interface using ANSI terminal formatting.

---

## 3. Tools and Technologies

| Tool / Technology | Purpose |
|---|---|
| Java (JDK 11+) | Primary programming language |
| `javac` | Java compiler |
| `java` CLI | Runtime execution |
| Plain text files (`.txt`) | Persistent data storage |
| ANSI Escape Codes | Terminal UI colour and formatting |
| VS Code / Any IDE | Development environment |

---

## 4. System Design

### 4.1 Architecture Overview

The project follows a layered, package-based architecture:

```
┌──────────────────────────────────────────┐
│                Main.java                 │  ← Entry point, UI, session control
├──────────────────────────────────────────┤
│   transaction/    │   mini_statement/    │  ← Business logic layer
├──────────────────────────────────────────┤
│              model/                      │  ← Data models (usermodel, transactionmodel)
├──────────────────────────────────────────┤
│              utils/                      │  ← File I/O utilities
├──────────────────────────────────────────┤
│         data/user.txt                    │  ← Flat-file persistence
│         data/transaction.txt             │
└──────────────────────────────────────────┘
```

### 4.2 Class Descriptions

| Class | Package | Responsibility |
|---|---|---|
| `Main` | default | Application entry point; handles login, menu loop, and UI rendering |
| `usermodel` | `model` | Data model representing a bank user (id, name, pin, balance) |
| `transactionmodel` | `model` | Data model representing a transaction (id, sender, receiver, amount, timestamp, names) |
| `transaction` | `transaction` | Executes deposit, withdrawal, and send-money operations |
| `miniStatement` | `mini_statement` | Reads and displays a user's transaction history from file |
| `UserFileUtil` | `utils` | Saves new users to file; provides balance lookup |
| `UserSearch` | `utils` | Searches `user.txt` for a user by ID |
| `ChangeBalanceById` | `utils` | Updates a user's balance in `user.txt` |
| `SaveTrascationIntoFile` | `utils` | Appends a new transaction record to `transaction.txt` |
| `DeleteUserById` | `utils` | Removes a user record from `user.txt` |
| `GenerateId` | `utils` | Generates a random 6-digit transaction ID |

### 4.3 Data Models

**User Record** (`data/user.txt`):
```
id , name , pin , balance
1  , rasim, 1234, 950.0
```

**Transaction Record** (`data/transaction.txt`):
```
id     , senderId, receiverId, amount , timestamp          , receiverName, senderName
570973 , 1       , 0         , 1000.0 , 2026-05-02T10:37:48, CASH        , rasim
```

> Convention: `receiverId = 0` → cash deposit | `receiverId = 1` → cash withdrawal

---

## 5. Features Implemented

### 5.1 User Authentication
The system reads `user.txt` line by line and matches the entered ID and PIN. Access is denied if no match is found. On success, the user's name and ID are displayed in a welcome banner.

### 5.2 Deposit
The user enters an amount. A `transactionmodel` is created with `receiverId = 0` (cash deposit marker). The `transaction.deposit_money()` method credits the amount to the account and saves the record.

### 5.3 Withdrawal
Similar to deposit but uses `receiverId = 1` (withdrawal marker). The system validates that the account has sufficient funds before deducting.

### 5.4 Send Money
The user provides a receiver ID and amount. The system:
1. Validates the receiver exists (null-check added)
2. Checks sender has sufficient balance
3. Deducts from sender, credits receiver
4. Saves the transaction record

### 5.5 Check Balance
Reads the current balance from `user.txt` for the logged-in user and displays it in a formatted box.

### 5.6 Mini Statement
Reads all records from `transaction.txt` and filters those belonging to the logged-in user. Displays each transaction with type, amount, counterparty name, and timestamp — colour-coded by type.

### 5.7 Terminal UI
A set of ANSI-based helper methods (`header`, `line`, `success`, `error`, `info`, `prompt`) provide consistent, colour-coded output across the entire application without adding complexity to business logic.

---

## 6. OOP Concepts Applied

| Concept | Where Applied |
|---|---|
| **Encapsulation** | All model fields are private with public getters/setters (`usermodel`, `transactionmodel`) |
| **Classes & Objects** | Each feature is a separate class; objects are instantiated per operation |
| **Separation of Concerns** | UI logic in `Main`, business logic in `transaction`, data access in `utils` |
| **Method Overloading** | `showMiniStatement(int)` and `showMiniStatement(int, String)` in `miniStatement` |
| **Constructor Overloading** | `usermodel` has multiple constructors for different initialization scenarios |
| **Single Responsibility** | Each utility class does exactly one thing (search, save, delete, generate ID) |

---

## 7. File I/O Strategy

Since no database is used, all persistence is handled through plain text files:

- **Reading**: `BufferedReader` + `FileReader` for efficient line-by-line reading
- **Writing (append)**: `FileWriter` with `append = true` for adding new records
- **Updating**: A temp-file strategy is used — the file is rewritten line by line, skipping or modifying the target record, then the temp file replaces the original (`DeleteUserById`, `ChangeBalanceById`)
- **Error handling**: All file operations are wrapped in try-catch blocks; malformed lines are skipped gracefully

---

## 8. UI Design

The interface uses ANSI escape codes for colour and box-drawing Unicode characters for structure. No external library is required — these are standard terminal capabilities.

**Colour scheme:**

| Colour | Meaning |
|---|---|
| Cyan | Headers, borders, informational prompts |
| Green | Success messages, deposits, received funds |
| Red | Errors, withdrawals |
| Yellow | Input prompts, sent transactions |
| Dim/Grey | Timestamps, dividers |

**Sample screens:**

```
  ╔═══════════════════════════════════════╗
  ║           ATM  SIMULATOR              ║
  ╚═══════════════════════════════════════╝

  ╔═══════════════════════════════════════╗
  ║           LOGIN SUCCESSFUL            ║
  ╚═══════════════════════════════════════╝
  Welcome, RASIM!
  ➜  User ID  : 1
  ➜  Account  : rasim

  ┌─────────────────────────────────────────────┐
  │              MINI  STATEMENT                │
  ├─────────────────────────────────────────────┤
  │  Account Holder : RASIM                     │
  │  User ID        : 1                         │
  └─────────────────────────────────────────────┘

  DEPOSIT    PKR  1,000.00      2026-05-02  10:37:48
  WITHDRAW   PKR    200.00      2026-05-02  11:17:46
  SENT       PKR    300.00  →  ali           2026-05-02  10:37:56
```

---

## 9. Challenges and Solutions

| Challenge | Solution |
|---|---|
| No database available | Used CSV-formatted `.txt` files with a consistent schema |
| Updating a single record in a flat file | Implemented temp-file rewrite strategy with retry logic for Windows file locks |
| Nanosecond timestamps cluttering output | Trimmed timestamps to 19 characters (`yyyy-MM-ddTHH:mm:ss`) before display |
| Crash when receiver ID doesn't exist | Added null-check on `userSearch.findUserById(rid)` before proceeding |
| Resource leak on `Scanner` | Added `sc.close()` on all exit paths |
| Distinguishing deposit vs withdrawal in history | Used reserved `receiverId` values (`0` = deposit, `1` = withdrawal) as markers |

---

## 10. Sample Test Cases

| Test | Input | Expected Output |
|---|---|---|
| Valid login | ID: 1, PIN: 1234 | Welcome banner with name RASIM |
| Invalid login | ID: 1, PIN: 0000 | `✘ Invalid credentials. Access denied.` |
| Deposit | Amount: 500 | Balance increases by 500; transaction saved |
| Withdraw (sufficient funds) | Amount: 200 | Balance decreases by 200; transaction saved |
| Withdraw (insufficient funds) | Amount: 99999 | `Transaction failed` |
| Send Money (valid receiver) | Receiver: 2, Amount: 300 | Sender debited, receiver credited |
| Send Money (invalid receiver) | Receiver: 9999 | `✘ Receiver not found.` |
| Mini Statement | — | Colour-coded list of all user transactions |
| Check Balance | — | Formatted box showing current PKR balance |

---

## 11. Limitations

- **No PIN masking** — the PIN is visible as it is typed (console limitation without a GUI)
- **No account creation via UI** — new users must be added manually to `user.txt`
- **Concurrent access** — flat files are not safe for simultaneous multi-user access
- **Integer amounts only** — deposit and withdrawal inputs are `int`; decimal amounts are not accepted at input (though stored as `double`)
- **No session timeout** — the session stays open indefinitely until the user selects Exit

---

## 12. Conclusion

This project successfully demonstrates a functional ATM simulation using core Java and OOP principles. The system handles all primary banking operations — authentication, deposits, withdrawals, transfers, and statement generation — through a clean, modular codebase. The use of flat-file storage eliminates external dependencies while the ANSI-based UI delivers a polished, modern terminal experience. The project reinforced practical skills in file I/O, class design, encapsulation, and building user-facing console applications in Java.

---

## 13. References

- Oracle Java SE 11 Documentation — https://docs.oracle.com/en/java/javase/11/
- ANSI Escape Codes Reference — https://en.wikipedia.org/wiki/ANSI_escape_code
- Java `BufferedReader` / `FileWriter` API — https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
