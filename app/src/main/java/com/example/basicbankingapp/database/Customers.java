package com.example.basicbankingapp.database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.basicbankingapp.banking.Customer;

import java.util.ArrayList;
import java.util.List;

public class Customers extends Database{

    private static String dbName = "BANKING";
    private static int dbVersion = 1;
    private static String tableName = "CUSTOMERS";

    private static String accID = "_account_ID";
    private static String name = "customer_name";
    private static String dob = "DOB";
    private static String email = "email";
    private static String mobNum = "mobile_number";
    private static String balance = "account_balance";
    private static String address = "address";

    private static String columnNameWithDataType = accID + " INTEGER primary key, " + name + " TEXT, " + dob + " INTEGER, " + email + " TEXT, " + mobNum + " INTEGER, " + balance + " REAL, " + address + " TEXT";

    public Customers(Context context) {
        super(context, dbName, dbVersion, tableName, columnNameWithDataType);
    }

    public boolean save(Customer customer){
        boolean success = false;
        try{
            database = getWritableDatabase();
            database.execSQL("insert into " + tableName + " ("+ accID +", " + name + ", " + dob + ", " + email + ", " + mobNum + ", " + balance + ", " + address + ") values(" + customer.getAccID() + ",'" + customer.getName() + "'," + customer.getDob() + ",'" + customer.getEmail() + "'," + customer.getMobNum() + "," + customer.getBalance() + ",'" + customer.getAddress() + "')");
            success = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    public List<Customer> allCustomers(){
        try (Cursor cursor = database.rawQuery("select * from " + tableName + ";", null)) {
            List<Customer> customersList = new ArrayList<>();
            while (cursor.moveToNext()) {
                customersList.add(new Customer(cursor.getLong(0), cursor.getString(1), cursor.getLong(2), cursor.getString(3), cursor.getLong(4), cursor.getDouble(5), cursor.getString(6)));
                Log.e("t", cursor.getLong(2) + "  " + cursor.getDouble(3) + "");
            }
            return customersList;
        }
    }

    public Customer detailOfCustomer(long customerID){
        try(Cursor cursor = database.rawQuery("select * from " + tableName + " where " + accID + " = " + customerID + ";",null)){
            return new Customer(cursor.getLong(0), cursor.getString(1), cursor.getLong(2), cursor.getString(3), cursor.getLong(4), cursor.getDouble(5), cursor.getString(6));
        }
    }

    public void updateBalance(long accID,double newBalance){
        try{
            database = getWritableDatabase();
            database.execSQL("update " + tableName + " set " + balance + "=" + newBalance + " where " + Customers.accID + "=" + accID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
