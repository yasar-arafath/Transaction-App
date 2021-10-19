package com.example.basicbankingapp.logic;

import android.content.Context;

import com.example.basicbankingapp.banking.Customer;
import com.example.basicbankingapp.banking.Transaction;
import com.example.basicbankingapp.database.Customers;
import com.example.basicbankingapp.database.Transactions;

import java.math.BigDecimal;
import java.util.Date;

public class MakeTransaction {

    private Transaction transaction;
    private Transactions transactionsDB;
    private Customer sender;
    private Customer receiver;
    private Customers customersDB;
    private Context context;

    public MakeTransaction(Context context, Transaction transactDone){
        this.context = context;
        transaction = transactDone;
        transactionsDB = new Transactions(context);
        customersDB = new Customers(context);
        sender = customersDB.detailOfCustomer(transactDone.getSenderAcc());
        receiver = customersDB.detailOfCustomer(transactDone.getReceiverAcc());
    }

    public boolean make(){
        boolean success=false;
        if(sender.getBalance()>=transaction.getAmount()){
            try {
                transaction = new Transaction(transaction,new Date().getTime());
                transaction.generateTransactionID(context);
                transactionsDB.save(transaction);
                double senderNewBalance = sender.getBalance()-transaction.getAmount();
                double receiverNewBalance = receiver.getBalance()+transaction.getAmount();
                customersDB.updateBalance(sender.getAccID(),new BigDecimal(senderNewBalance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                customersDB.updateBalance(receiver.getAccID(),new BigDecimal(receiverNewBalance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                success = true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return success;
    }

}
