package com.example.basicbankingapp.banking;

public class Customer {
    private long accID;
    private String name;
    private long dob;
    private String email;
    private long mobNum;
    private double balance;
    private String address;

    public Customer(long accID, String name, long dob, String email, long mobNum, double balance, String address) {
        this.accID = accID;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.mobNum = mobNum;
        this.balance = balance;
        this.address = address;
    }

    public long getAccID() {
        return accID;
    }

    public String getName() {
        return name;
    }

    public long getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public long getMobNum() {
        return mobNum;
    }

    public double getBalance() {
        return balance;
    }

    public String getAddress() {
        return address;
    }
}
