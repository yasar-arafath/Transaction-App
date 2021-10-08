package com.example.basicbankingapp.database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.basicbankingapp.banking.Customer;
import com.example.basicbankingapp.banking.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transactions extends Database{

    private static String dbName = "BANKING";
    private static int dbVersion = 1;
    private static String tableName = "TRANSACTIONS";

    private static String transactionId = "_transaction_ID";
    private static String senderAcc = "sender_account_ID";
    private static String receiverAcc = "receiver_account_ID";
    private static String time = "time";
    private static String amount = "transfer_amount";

    private static String columnNameWithDataType = transactionId + " INTEGER primary key, " + senderAcc + " INTEGER, " + receiverAcc + " INTEGER, " + time + " INTEGER unique, " + amount + " REAL";

    Transactions(Context context) {
        super(context, dbName, dbVersion, tableName, columnNameWithDataType);
    }

    public boolean save(Transaction transaction){
        boolean success = false;
        try{
            database = getWritableDatabase();
            Date dateTimeNow = new Date();
            database.execSQL("insert into " + tableName + " ("+ transactionId + ", " + senderAcc + ", " + receiverAcc + ", " + dateTimeNow + ", " + amount + ") values(" + transaction.getTransactionId() + "," + transaction.getSenderAcc() + "," + transaction.getReceiverAcc() + "," + dateTimeNow.getTime() + "," + transaction.getAmount() + ")");
            success = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    public List<Transaction> allTransactions(){
        try (Cursor cursor = database.rawQuery("select * from " + tableName + ";", null)) {
            List<Transaction> transactionList = new ArrayList<>();
            while (cursor.moveToNext()) {
                transactionList.add(new Transaction(cursor.getLong(0),cursor.getLong(1),cursor.getLong(2),cursor.getLong(3),cursor.getDouble(4)));
                Log.e("t", cursor.getLong(2) + "  " + cursor.getDouble(3) + "");
            }
            return transactionList;
        }
    }

    public Transaction detailOfTransaction(long transactionID){
        try(Cursor cursor = database.rawQuery("select * from " + tableName + " where " + Transactions.transactionId + " = " + transactionID + ";",null)){
            return new Transaction(cursor.getLong(0),cursor.getLong(1),cursor.getLong(2),cursor.getLong(3),cursor.getDouble(4));
        }
    }
}
