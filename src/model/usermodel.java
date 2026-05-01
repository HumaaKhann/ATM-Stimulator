package model;

import java.util.List;


public class usermodel {
    private int id;
    private String name;
    private String pin;
    private double balance;
    private List<transactionmodel> transaction_history;

    // Getters and Setters
    public usermodel() {
    }

    public usermodel(int id, String name, String pin, double balance, List<transactionmodel> transaction_history) {
        this.id = id;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.transaction_history = transaction_history; 
    }   

    public usermodel(int id, String name, String pin, double balance) {
        this.id = id;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        
    } 

    public void setId(int id) {
        this.id = id;
    }
     
    public void setName(String name) {
        this.name = name;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setTransaction_history(List<transactionmodel> transaction_history) {
        this.transaction_history = transaction_history;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPin() {
        return pin;
    }
    public List<transactionmodel> getTransaction_history() {
        return transaction_history;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

}






// 1.usermodel
//     1.id
//     2.name
//     3.pin
//     4.transaction_history
    


// 2.transactionmodel
//     1.id
//     2.sender_id
//     3.receiver_id
//     4.amount
//     5.timestamp
//     6.reciever_name
//     7.sender_name

// 3.accountmodel
//     1.id
//     2.user_id
//     3.balance