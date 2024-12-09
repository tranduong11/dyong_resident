package com.scan.dyong_resident.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scan.dyong_resident.models.User;

import java.util.ArrayList;
import java.util.List;

public class RoleHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "role_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role";

    public RoleHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng users nếu chưa tồn tại
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_ROLE + " TEXT"  // Thêm cột role
                + ")";
        db.execSQL(CREATE_USERS_TABLE);

        // Thêm tài khoản admin mặc định
        addDefaultAdmin(db);
    }

    private void addDefaultAdmin(SQLiteDatabase db) {
        // Kiểm tra xem admin đã tồn tại chưa
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_ID}, COLUMN_ROLE + "=?", new String[]{"admin"}, null, null, null);
        if (cursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, "Admin");
            values.put(COLUMN_EMAIL, "admin@dyong.com");  // Email admin mặc định
            values.put(COLUMN_PASSWORD, "admin123");  // Mật khẩu admin mặc định
            values.put(COLUMN_ROLE, "admin");  // Vai trò là admin
            db.insert(TABLE_USERS, null, values);  // Thêm admin vào bảng
        }
        cursor.close();
    }




    // Trong RoleHelper.java

    // Phương thức lấy email của người dùng đã đăng nhập
    public String getUserEmail() {
        SQLiteDatabase db = this.getReadableDatabase();

        // Giả sử bạn biết ID người dùng đã đăng nhập, có thể từ SharedPreferences hoặc từ biến toàn cục nào đó
        // Ví dụ: Bạn có thể thay thế "1" bằng ID của người dùng hiện tại nếu bạn lưu trong phiên làm việc
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_EMAIL},
                COLUMN_ID + "=?",
                new String[]{"1"}, // Giả sử bạn lưu ID người dùng trong biến hoặc SharedPreferences
                null, null, null);

        String email = null;
        if (cursor != null && cursor.moveToFirst()) {
            email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
        }

        cursor.close();
        db.close();
        return email; // trả về email của người dùng
    }


    public List<String> getAllRegisteredEmails() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> emails = new ArrayList<>();

        // Truy vấn để lấy tất cả email từ bảng users
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_EMAIL}, null, null, null, null, null);

        // Duyệt qua tất cả các email và thêm vào danh sách
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                emails.add(email);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return emails;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean registerUser(String name, String email, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_ROLE, role);  // Lưu vai trò

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    public boolean loginUser(Context context, String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_ID},
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password},
                null, null, null);

        boolean isValid = cursor.moveToFirst();
        if (isValid) {
            // Lưu email vào SharedPreferences sau khi đăng nhập thành công
            SharedPreferences sharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user_email", email);  // Lưu email người dùng
            editor.apply();
        }

        cursor.close();
        db.close();
        return isValid; // Trả về true nếu đăng nhập thành công, ngược lại false
    }


//    public boolean loginUser(String email, String password) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_USERS,
//                new String[]{COLUMN_ID},
//                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
//                new String[]{email, password},
//                null, null, null);
//        boolean isValid = cursor.moveToFirst();
//        cursor.close();
//        db.close();
//        return isValid;
//    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_ID},
                COLUMN_EMAIL + "=?",
                new String[]{email},
                null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return exists;
    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getFullName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_ROLE, user.getRole()); // Lưu vai trò người dùng

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result;
    }

    public boolean isUserValid(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_ID},
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password},
                null, null, null);
        boolean isValid = cursor.moveToFirst();
        cursor.close();
        db.close();
        return isValid;
    }

    public String getUserRole(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_ROLE},  // Chỉ cần lấy cột "role"
                COLUMN_EMAIL + "=?",
                new String[]{email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String role = cursor.getString(cursor.getColumnIndex(COLUMN_ROLE));
            cursor.close();
            db.close();
            return role;
        }
        cursor.close();
        db.close();
        return null;
    }
}
