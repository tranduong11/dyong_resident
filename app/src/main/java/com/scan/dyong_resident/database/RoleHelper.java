package com.scan.dyong_resident.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scan.dyong_resident.models.User;

import java.util.ArrayList;
import java.util.List;

public class RoleHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RoleManagement.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "Users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FULL_NAME = "full_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role";

    public RoleHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FULL_NAME + " TEXT, "
                + COLUMN_EMAIL + " TEXT UNIQUE, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_ROLE + " TEXT)";
        db.execSQL(CREATE_TABLE_USERS);

        addDefaultAdmin(db);
    }

    private void addDefaultAdmin(SQLiteDatabase db) {
        ContentValues adminValues = new ContentValues();
        adminValues.put(COLUMN_FULL_NAME, "Admin");
        adminValues.put(COLUMN_EMAIL, "admin@dyong.com");
        adminValues.put(COLUMN_PASSWORD, "admin123");
        adminValues.put(COLUMN_ROLE, "admin");
        db.insert(TABLE_USERS, null, adminValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, user.getFullName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_ROLE, user.getRole());
        return db.insert(TABLE_USERS, null, values);
    }

    public User authenticate(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null,
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            User user = new User(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FULL_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
            );
            cursor.close();
            return user;
        }

        return null;
    }

    public int updateUserRole(int userId, String newRole) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROLE, newRole);
        return db.update(TABLE_USERS, values, COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);

        if (cursor.moveToFirst()) {
            do {
                userList.add(new User(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FULL_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return userList;
    }

    public int deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USERS, COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
    }

    public boolean isUserValid(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password}, null, null, null);

        boolean isValid = cursor != null && cursor.getCount() > 0;
        if (cursor != null) cursor.close();
        return isValid;
    }
}
