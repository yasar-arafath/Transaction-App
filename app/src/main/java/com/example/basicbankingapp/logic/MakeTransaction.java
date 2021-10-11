package com.example.basicbankingapp.logic;

import android.content.Context;

import com.example.basicbankingapp.banking.Customer;
import com.example.basicbankingapp.banking.Transaction;
import com.example.basicbankingapp.database.Customers;
import com.example.basicbankingapp.database.Transactions;

public class MakeTransaction {

    private Transaction transaction;
    private Transactions transactionsDB;
    private Customer sender;
    private Customer receiver;
    private Customers customersDB;

    public MakeTransaction(Context context, Transaction transactDone, long senderID, long receiverID){
        transaction = transactDone;
        transactionsDB = new Transactions(context);
        customersDB = new Customers(context);
        sender = customersDB.detailOfCustomer(senderID);
        receiver = customersDB.detailOfCustomer(receiverID);
    }

    public void make(){
        try {
            transactionsDB.save(transaction);
            customersDB.updateBalance(sender.getAccID(),sender.getBalance()-transaction.getAmount());
            customersDB.updateBalance(receiver.getAccID(),receiver.getBalance()+transaction.getAmount());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
