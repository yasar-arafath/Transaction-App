package com.example.basicbankingapp.banking;

public class Transaction {

    private long transactionId;
    private long senderAcc;
    private long receiverAcc;
    private long time;
    private double amount;

    public Transaction(long transactionId, long senderAcc, long receiverAcc, long time, double amount) {
        this.transactionId = transactionId;
        this.senderAcc = senderAcc;
        this.receiverAcc = receiverAcc;
        this.time = time;
        this.amount = amount;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public long getSenderAcc() {
        return senderAcc;
    }

    public long getReceiverAcc() {
        return receiverAcc;
    }

    public long getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }
}
