package model;

public class transactionmodel {

    // Fields (Properties)
    private int id;
    private int senderId;
    private int receiverId;
    private double amount;
    private String timestamp;
    private String receiverName;
    private String senderName;

    // Default Constructor
    public transactionmodel() {
    }

    
    public transactionmodel(int id, int senderId, int receiverId, double amount, String timestamp, String receiverName, String senderName) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.receiverName = receiverName;
        this.senderName = senderName;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}