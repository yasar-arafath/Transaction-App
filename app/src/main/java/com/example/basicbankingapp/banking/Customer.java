package com.example.basicbankingapp.banking;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private long accID;
    private String name;
    private long dob;
    private String email;
    private long mobNum;
    private double balance;
    private String address;

    private static List<Customer> defaultCustomerList = new ArrayList<Customer>(){
        {
            add(new Customer(1234567,"Ratul Dutta",getDate("25-08-2001"),"duttaratul@gmail.com",7834742,20000.00,"Cuttack, Odisha"));
            add(new Customer(2345678,"Akira",getDate("28-11-2001"),"akiraofficial@gmail.com",9829347,20000.00,"Bhubaneswar, Odisha"));
            add(new Customer(3456789,"Rajesh Wagle",getDate("23-04-1967"),"rajesh123@gmail.com",8938908,20000.00,"Borivali, Maharashtra"));
            add(new Customer(4567890,"Haseena Malik",getDate("31-05-1978"),"malik3105@gmail.com",8920947,20000.00,"Lucknow, Uttar Pradesh"));
            add(new Customer(5678901,"Veer Nanda",getDate("12-09-2000"),"vnanda@gmail.com",9474676,20000.00,"Silchar, Assam"));
            add(new Customer(6789012,"Aditi Jamwal",getDate("21-10-1981"),"adi1981@gmail.com",9458492,20000.00,"Kolkata, West Bengal"));
            add(new Customer(7890123,"Sarangdhar Pandey",getDate("15-07-1945"),"sarang1234pandey@gmail.com",9383474,20000.00,"Mathura, Uttar Pradesh"));
            add(new Customer(8901234,"Karishma Singh",getDate("08-12-1970"),"karishma1970official@gmail.com",8374764,20000.00,"Nagpur, Maharashtra"));
            add(new Customer(9012345,"Alia Shroff",getDate("17-03-1958"),"shroff1703alia@gmail.com",7883847,20000.00,"Mumbai, Maharashtra"));
            add(new Customer(1123456,"Daljeet Bagha",getDate("27-09-1976"),"dallu2709@gmail.com",9355783,20000.00,"Patiala, Punjab"));
        }
    };

    public static List<Customer> getDefaultCustomerList() {
        return defaultCustomerList;
    }

    @SuppressLint("SimpleDateFormat")
    private static long getDate(String source) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return (Objects.requireNonNull(dateFormat.parse(source)).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Customer(){
        this.accID = 0;
        this.name = null;
        this.dob = 0;
        this.email = null;
        this.mobNum = 0;
        this.balance = 0.0;
        this.address = null;
    }

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
