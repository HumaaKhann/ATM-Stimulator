# ATM Simulator

A console-based ATM simulator built in Java. It supports user login, deposits, withdrawals, money transfers, balance checks, and mini statements — all backed by flat-file storage (no database required). The interface uses ANSI colour codes to deliver a clean, modern terminal UI.

---

## Features

- **Login** — authenticate with user ID and PIN; displays a welcome banner with your name and ID on success
- **Deposit** — add funds to your account
- **Withdraw** — deduct funds with balance validation
- **Send Money** — transfer funds to another user by ID, with null-safety on the receiver
- **Check Balance** — view your current balance in a formatted box
- **Mini Statement** — colour-coded transaction history (green = credit, red = debit, yellow = sent)
- **Styled UI** — ANSI box-drawing characters, colour-coded prompts, success/error indicators throughout

---

## Project Structure

```
ATM-Simulator/
├── data/
│   ├── user.txt            # Stores user records (id, name, pin, balance)
│   └── transaction.txt     # Stores all transaction records
│
├── src/
│   ├── Main.java           # Entry point — login, welcome banner, ATM menu loop, UI helpers
│   │
│   ├── model/
│   │   ├── usermodel.java          # User data model (id, name, pin, balance)
│   │   └── transactionmodel.java   # Transaction data model (id, sender, receiver, amount, timestamp, names)
│   │
│   ├── transaction/
│   │   └── transaction.java        # Core transaction logic (deposit, withdraw, send)
│   │
│   ├── mini_statement/
│   │   └── miniStatement.java      # Colour-coded transaction history with account header
│   │
│   ├── user/
│   │   └── user.java               # User-related operations (extensible)
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

## UI Preview

```
  ╔═══════════════════════════════════════╗
  ║           ATM  SIMULATOR              ║
  ╚═══════════════════════════════════════╝
  ─────────────────────────────────────────
  ›  Enter User ID : 1
  ›  Enter PIN     : 1234
  ─────────────────────────────────────────

  ╔═══════════════════════════════════════╗
  ║           LOGIN SUCCESSFUL            ║
  ╚═══════════════════════════════════════╝
  Welcome, RASIM!
  ➜  User ID  : 1
  ➜  Account  : rasim

  ┌─────────────────────────────────────┐
  │           A T M   M E N U           │
  ├─────────────────────────────────────┤
  │  1.  Deposit                        │
  │  2.  Withdraw                       │
  │  3.  Send Money                     │
  │  4.  Mini Statement                 │
  │  5.  Check Balance                  │
  │  6.  Exit                           │
  └─────────────────────────────────────┘
```

---

## Recent Changes (UI Update)

| Area | Change |
|---|---|
| `Main.java` | Added ANSI colour constants and helper methods (`header`, `line`, `success`, `error`, `info`, `prompt`) |
| `Main.java` | Welcome banner now shows user name and ID after login |
| `Main.java` | ATM menu uses box-drawing characters instead of plain `=====` |
| `Main.java` | Balance display rendered in a formatted bordered box with PKR label |
| `Main.java` | Receiver null-check added before Send Money to prevent crash |
| `Main.java` | Removed leftover debug strings (`"Deposit logic here"`, etc.) |
| `Main.java` | `Scanner` properly closed on all exit paths (fixes resource leak) |
| `miniStatement.java` | Accepts `userName` parameter; shows account holder name and ID in statement header |
| `miniStatement.java` | Colour-coded output: green = deposit/received, red = withdraw, yellow = sent |
| `miniStatement.java` | Timestamps trimmed and formatted (`T` replaced with spaces, nanoseconds removed) |
| `miniStatement.java` | Malformed lines in `transaction.txt` are skipped gracefully |
| `miniStatement.java` | "No transactions found" message shown when history is empty |

---

## Requirements

- Java JDK 11 or higher
- No external libraries or build tools needed
- Terminal with ANSI colour support (macOS Terminal, Windows Terminal, most Linux terminals)
