package com.example.basicbankingapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.basicbankingapp.banking.Customer;

import java.util.ArrayList;
import java.util.List;

public class Customers extends Database{

    private static final String dbName = "BANKING";
    private static final int dbVersion = 1;
    private static final String tableName = CUSTOMER_TABLE;

    public Customers(Context context) {
        super(context, dbName, dbVersion);
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
        database = getReadableDatabase();
        try (Cursor cursor = database.rawQuery("select * from " + tableName + ";", null)) {
            List<Customer> customersList = new ArrayList<>();
            while (cursor.moveToNext()) {
                customersList.add(new Customer(cursor.getLong(0), cursor.getString(1), cursor.getLong(2), cursor.getString(3), cursor.getLong(4), cursor.getDouble(5), cursor.getString(6)));
            }
            return customersList;
        }
    }

    public Customer detailOfCustomer(long customerID){
        database = getReadableDatabase();
        Customer customer = new Customer();
        try{
            try(Cursor cursor = database.rawQuery("select * from " + tableName + " where " + accID + "=" + customerID + ";",null)){
                cursor.moveToFirst();
                customer = new Customer(cursor.getLong(0), cursor.getString(1), cursor.getLong(2), cursor.getString(3), cursor.getLong(4), cursor.getDouble(5), cursor.getString(6));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return customer;
    }

    public void updateBalance(long accID,double newBalance){
        try{
            database = getWritableDatabase();
            database.execSQL("update " + tableName + " set " + balance + "=" + newBalance + " where " + Customers.accID + "=" + accID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static long count(Context context){
        long count = 0;
        try{
            Customers customers = new Customers(context);
            SQLiteDatabase database = customers.getReadableDatabase();
            try (Cursor cursor = database.rawQuery("select count(*) from " + tableName + ";", null)) {
                cursor.moveToFirst();
                count= cursor.getLong(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public static void updateDBIfDefaultNotFound(Context context){
        try {
            long count = count(context);
            Customers customers = new Customers(context);
            if (count != 10){
                for (Customer customer: Customer.getDefaultCustomerList()){
                    customers.save(customer);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
