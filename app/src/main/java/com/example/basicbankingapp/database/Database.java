package com.example.basicbankingapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedHashMap;

public class Database extends SQLiteOpenHelper {

    private String dbName;
    private int dbVersion;
    private Context context;

    private String tableName;
    private String columnNameWithDataType;

    protected SQLiteDatabase database;

    Database(Context context, String dbName, int dbVersion, String tableName, String columnNameWithDataType){
        super(context,dbName,null,dbVersion);
        this.context = context;
        this.dbName = dbName;
        this.dbVersion = dbVersion;
        this.tableName = tableName;
        this.columnNameWithDataType = columnNameWithDataType;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table " + tableName + " (" + columnNameWithDataType + ");");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table if exists " + tableName);
            dbVersion = newVersion;
            onCreate(db);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
