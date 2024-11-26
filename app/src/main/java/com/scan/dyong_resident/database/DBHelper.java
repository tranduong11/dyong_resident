package com.scan.dyong_resident.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "dyong_resident", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String user = "CREATE TABLE USER(fullName TEXT, phoneNumber TEXT, password TEXT)";
        db.execSQL(user);

        String insertUser = "INSERT INTO USER " +
                "VALUES('nguyen trung duy', '0364554010', 'duynt12')," +
                "('tran trong duong', '0912987886', 'duongtt56')";
        db.execSQL(insertUser);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS USER");
            onCreate(db);
        }
    }
}
