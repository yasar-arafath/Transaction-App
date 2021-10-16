package com.example.basicbankingapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database extends SQLiteOpenHelper {

    private String dbName;
    private int dbVersion;
    private Context context;
    protected static final String CUSTOMER_TABLE = "CUSTOMERS";
    protected static final String TRANSACTION_TABLE = "TRANSACTIONS";

    private static final List<String> tableNames = new ArrayList<String>(){
        {
            add(CUSTOMER_TABLE);
            add(TRANSACTION_TABLE);
        }
    };

    //Customers data
    protected static String accID = "_account_ID";
    protected static String name = "customer_name";
    protected static String dob = "DOB";
    protected static String email = "email";
    protected static String mobNum = "mobile_number";
    protected static String balance = "account_balance";
    protected static String address = "address";

    //Transactions data
    protected static String transactionId = "_transaction_ID";
    protected static String senderAcc = "sender_ID";
    protected static String receiverAcc = "receiver_ID";
    protected static String time = "time";
    protected static String amount = "transfer_amount";

    private static final Map<String,String> columnNameWithDataType = new HashMap<String,String>(){
        {
            put(CUSTOMER_TABLE, accID + " INTEGER primary key, " + name + " TEXT, " + dob + " INTEGER, " + email + " TEXT, " + mobNum + " INTEGER, " + balance + " REAL, " + address + " TEXT");
            put(TRANSACTION_TABLE, transactionId + " TEXT primary key, " + senderAcc + " INTEGER, " + receiverAcc + " INTEGER, " + time + " INTEGER, " + amount + " REAL");
        }
    };

    protected SQLiteDatabase database;

    Database(Context context, String dbName, int dbVersion){
        super(context,dbName,null,dbVersion);
        this.context = context;
        this.dbName = dbName;
        this.dbVersion = dbVersion;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            for (String table: tableNames){
                db.execSQL("create table " + table + " (" + columnNameWithDataType.get(table) + ");");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            for (String table: tableNames){
                db.execSQL("drop table if exists " + table);
            }
            dbVersion = newVersion;
            onCreate(db);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
