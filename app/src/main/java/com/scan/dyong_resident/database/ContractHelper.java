//package com.scan.dyong_resident.database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.scan.dyong_resident.models.Contract;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ContractHelper extends SQLiteOpenHelper {
//
//    private static final String DATABASE_NAME = "contracts.db";
//    private static final int DATABASE_VERSION = 3;  // Cập nhật version
//
//    public static final String TABLE_CONTRACT = "contracts";
//    public static final String COLUMN_ID = "id";
//    public static final String COLUMN_PHONG = "phong";
//    public static final String COLUMN_CHU_HOP_DONG = "chu_hop_dong";
//    public static final String COLUMN_SO_LUONG = "so_luong";
//    public static final String COLUMN_NGAY_KY = "ngay_ky";
//    public static final String COLUMN_NGAY_BAT_DAU = "ngay_bat_dau";
//    public static final String COLUMN_NGAY_KET_THUC = "ngay_ket_thuc";
//    public static final String COLUMN_TIEN_NHA = "tien_nha";
//    public static final String COLUMN_TIEN_COC = "tien_coc";
//    public static final String COLUMN_INTERNET = "internet";
//    public static final String COLUMN_NUOC_UONG = "nuoc_uong";
//    public static final String COLUMN_NUOC_UONG_THEO_NGUOI = "nuoc_uong_theo_nguoi";
//    public static final String COLUMN_TIEN_DIEN = "tien_dien";
//    public static final String COLUMN_MAY_GIAT = "may_giat";
//    public static final String COLUMN_KHAC = "khac";
//    public static final String COLUMN_NGUOI_THUE = "nguoi_thue"; // Thêm cột người thuê
//
//    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTRACT + " (" +
//            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            COLUMN_PHONG + " TEXT, " +
//            COLUMN_CHU_HOP_DONG + " TEXT, " +
//            COLUMN_SO_LUONG + " INTEGER, " +
//            COLUMN_NGAY_KY + " TEXT, " +
//            COLUMN_NGAY_BAT_DAU + " TEXT, " +
//            COLUMN_NGAY_KET_THUC + " TEXT, " +
//            COLUMN_TIEN_NHA + " REAL, " +
//            COLUMN_TIEN_COC + " REAL, " +
//            COLUMN_INTERNET + " REAL, " +
//            COLUMN_NUOC_UONG + " REAL, " +
//            COLUMN_NUOC_UONG_THEO_NGUOI + " REAL, " +
//            COLUMN_TIEN_DIEN + " REAL, " +
//            COLUMN_MAY_GIAT + " REAL, " +
//            COLUMN_KHAC + " TEXT, " +
//            COLUMN_NGUOI_THUE + " TEXT" +  // Thêm cột cho người thuê
//            ");";
//
//    public ContractHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion < 3) {
//            // Cập nhật bảng, thêm cột "nguoi_thue"
//            db.execSQL("ALTER TABLE " + TABLE_CONTRACT + " ADD COLUMN " + COLUMN_NGUOI_THUE + " TEXT;");
//        }
//    }
//
//    public boolean addContract(Contract contract) {
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_PHONG, contract.getRoom());
//        values.put(COLUMN_CHU_HOP_DONG, contract.getOwner());
//        values.put(COLUMN_SO_LUONG, contract.getQuantity());
//        values.put(COLUMN_NGAY_KY, contract.getNgayKy());
//        values.put(COLUMN_NGAY_BAT_DAU, contract.getNgayBatDau());
//        values.put(COLUMN_NGAY_KET_THUC, contract.getNgayKetThuc());
//        values.put(COLUMN_TIEN_NHA, contract.getTienNha());
//        values.put(COLUMN_TIEN_COC, contract.getTienCoc());
//        values.put(COLUMN_INTERNET, contract.getInternet());
//        values.put(COLUMN_NUOC_UONG, contract.getNuocUong());
//        values.put(COLUMN_NUOC_UONG_THEO_NGUOI, contract.getNuocUongTheoNguoi());
//        values.put(COLUMN_TIEN_DIEN, contract.getTienDien());
//        values.put(COLUMN_MAY_GIAT, contract.getMayGiat());
//        values.put(COLUMN_KHAC, contract.getKhac());
//        values.put(COLUMN_NGUOI_THUE, contract.getTenant());  // Thêm người thuê vào values
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        long result = db.insert(TABLE_CONTRACT, null, values);
//        db.close();
//
//        return result != -1;
//    }
//
//    public List<Contract> getAllContracts() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        List<Contract> contractList = new ArrayList<>();
//        Cursor cursor = null;
//
//        try {
//            cursor = db.query(TABLE_CONTRACT, null, null, null, null, null, null);
//
//            if (cursor != null && cursor.moveToFirst()) {
//                do {
//                    int id = getIntFromCursor(cursor, COLUMN_ID);
//                    String room = getStringFromCursor(cursor, COLUMN_PHONG);
//                    String owner = getStringFromCursor(cursor, COLUMN_CHU_HOP_DONG);
//                    int quantity = getIntFromCursor(cursor, COLUMN_SO_LUONG);
//                    String ngayKy = getStringFromCursor(cursor, COLUMN_NGAY_KY);
//                    String ngayBatDau = getStringFromCursor(cursor, COLUMN_NGAY_BAT_DAU);
//                    String ngayKetThuc = getStringFromCursor(cursor, COLUMN_NGAY_KET_THUC);
//                    double tienNha = getDoubleFromCursor(cursor, COLUMN_TIEN_NHA);
//                    double tienCoc = getDoubleFromCursor(cursor, COLUMN_TIEN_COC);
//                    double internet = getDoubleFromCursor(cursor, COLUMN_INTERNET);
//                    double nuocUong = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG);
//                    double nuocUongTheoNguoi = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG_THEO_NGUOI);
//                    double tienDien = getDoubleFromCursor(cursor, COLUMN_TIEN_DIEN);
//                    double mayGiat = getDoubleFromCursor(cursor, COLUMN_MAY_GIAT);
//                    String khac = getStringFromCursor(cursor, COLUMN_KHAC);
//                    String tenant = getStringFromCursor(cursor, COLUMN_NGUOI_THUE); // Lấy giá trị người thuê
//
//                    contractList.add(new Contract(id, room, owner, quantity, ngayKy, ngayBatDau, ngayKetThuc,
//                            tienNha, tienCoc, internet, nuocUong, nuocUongTheoNguoi,
//                            tienDien, mayGiat, khac, tenant)); // Thêm tenant vào constructor
//                } while (cursor.moveToNext());
//            }
//        } finally {
//            if (cursor != null) cursor.close();
//            db.close();
//        }
//
//        return contractList;
//    }
//
//    public Contract getContractByRoom(String roomName) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = null;
//        Contract contract = null;
//
//        try {
//            cursor = db.query(TABLE_CONTRACT, null, COLUMN_PHONG + " = ?", new String[]{roomName}, null, null, null);
//
//            if (cursor != null && cursor.moveToFirst()) {
//                int id = getIntFromCursor(cursor, COLUMN_ID);
//                String room = getStringFromCursor(cursor, COLUMN_PHONG);
//                String owner = getStringFromCursor(cursor, COLUMN_CHU_HOP_DONG);
//                int quantity = getIntFromCursor(cursor, COLUMN_SO_LUONG);
//                String ngayKy = getStringFromCursor(cursor, COLUMN_NGAY_KY);
//                String ngayBatDau = getStringFromCursor(cursor, COLUMN_NGAY_BAT_DAU);
//                String ngayKetThuc = getStringFromCursor(cursor, COLUMN_NGAY_KET_THUC);
//                double tienNha = getDoubleFromCursor(cursor, COLUMN_TIEN_NHA);
//                double tienCoc = getDoubleFromCursor(cursor, COLUMN_TIEN_COC);
//                double internet = getDoubleFromCursor(cursor, COLUMN_INTERNET);
//                double nuocUong = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG);
//                double nuocUongTheoNguoi = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG_THEO_NGUOI);
//                double tienDien = getDoubleFromCursor(cursor, COLUMN_TIEN_DIEN);
//                double mayGiat = getDoubleFromCursor(cursor, COLUMN_MAY_GIAT);
//                String khac = getStringFromCursor(cursor, COLUMN_KHAC);
//                String tenant = getStringFromCursor(cursor, COLUMN_NGUOI_THUE);
//
//                contract = new Contract(id, room, owner, quantity, ngayKy, ngayBatDau, ngayKetThuc,
//                        tienNha, tienCoc, internet, nuocUong, nuocUongTheoNguoi,
//                        tienDien, mayGiat, khac, tenant);
//            }
//        } finally {
//            if (cursor != null) cursor.close();
//            db.close();
//        }
//
//        return contract;
//    }
//
//
//    public boolean updateContract(Contract contract) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_PHONG, contract.getRoom());
//        values.put(COLUMN_CHU_HOP_DONG, contract.getOwner());
//        values.put(COLUMN_SO_LUONG, contract.getQuantity());
//        values.put(COLUMN_NGAY_KY, contract.getNgayKy());
//        values.put(COLUMN_NGAY_BAT_DAU, contract.getNgayBatDau());
//        values.put(COLUMN_NGAY_KET_THUC, contract.getNgayKetThuc());
//        values.put(COLUMN_TIEN_NHA, contract.getTienNha());
//        values.put(COLUMN_TIEN_COC, contract.getTienCoc());
//        values.put(COLUMN_INTERNET, contract.getInternet());
//        values.put(COLUMN_NUOC_UONG, contract.getNuocUong());
//        values.put(COLUMN_NUOC_UONG_THEO_NGUOI, contract.getNuocUongTheoNguoi());
//        values.put(COLUMN_TIEN_DIEN, contract.getTienDien());
//        values.put(COLUMN_MAY_GIAT, contract.getMayGiat());
//        values.put(COLUMN_KHAC, contract.getKhac());
//        values.put(COLUMN_NGUOI_THUE, contract.getTenant()); // Thêm người thuê vào giá trị
//
//        int rowsAffected = db.update(TABLE_CONTRACT, values, COLUMN_ID + " = ?", new String[]{String.valueOf(contract.getId())});
//        db.close();
//
//        return rowsAffected > 0;
//    }
//
//    private int getIntFromCursor(Cursor cursor, String columnName) {
//        int index = cursor.getColumnIndex(columnName);
//        return (index != -1) ? cursor.getInt(index) : 0;
//    }
//
//    private String getStringFromCursor(Cursor cursor, String columnName) {
//        int index = cursor.getColumnIndex(columnName);
//        return (index != -1) ? cursor.getString(index) : "";
//    }
//
//    private double getDoubleFromCursor(Cursor cursor, String columnName) {
//        int index = cursor.getColumnIndex(columnName);
//        return (index != -1) ? cursor.getDouble(index) : 0.0;
//    }
//
//    public void deleteContract(Contract contract) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CONTRACT, COLUMN_ID + " = ?", new String[]{String.valueOf(contract.getId())});
//        db.close();
//    }
//}
//package com.scan.dyong_resident.database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.scan.dyong_resident.models.Contract;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ContractHelper extends SQLiteOpenHelper {
//
//    private static final String DATABASE_NAME = "contracts.db";
//    private static final int DATABASE_VERSION = 4;  // Cập nhật version
//
//    public static final String TABLE_CONTRACT = "contracts";
//    public static final String COLUMN_ID = "id";
//    public static final String COLUMN_PHONG = "phong";
//    public static final String COLUMN_CHU_HOP_DONG = "chu_hop_dong";
//    public static final String COLUMN_SO_LUONG = "so_luong";
//    public static final String COLUMN_NGAY_KY = "ngay_ky";
//    public static final String COLUMN_NGAY_BAT_DAU = "ngay_bat_dau";
//    public static final String COLUMN_NGAY_KET_THUC = "ngay_ket_thuc";
//    public static final String COLUMN_TIEN_NHA = "tien_nha";
//    public static final String COLUMN_TIEN_COC = "tien_coc";
//    public static final String COLUMN_INTERNET = "internet";
//    public static final String COLUMN_NUOC_UONG = "nuoc_uong";
//    public static final String COLUMN_NUOC_UONG_THEO_NGUOI = "nuoc_uong_theo_nguoi";
//    public static final String COLUMN_TIEN_DIEN = "tien_dien";
//    public static final String COLUMN_MAY_GIAT = "may_giat";
//    public static final String COLUMN_KHAC = "khac";
//    public static final String COLUMN_NGUOI_THUE = "nguoi_thue";  // Cột người thuê
//    public static final String COLUMN_EMAIL_NGUOI_THUE = "email_nguoi_thue"; // Thêm cột email người thuê
//
//    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTRACT + " (" +
//            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            COLUMN_PHONG + " TEXT, " +
//            COLUMN_CHU_HOP_DONG + " TEXT, " +
//            COLUMN_SO_LUONG + " INTEGER, " +
//            COLUMN_NGAY_KY + " TEXT, " +
//            COLUMN_NGAY_BAT_DAU + " TEXT, " +
//            COLUMN_NGAY_KET_THUC + " TEXT, " +
//            COLUMN_TIEN_NHA + " REAL, " +
//            COLUMN_TIEN_COC + " REAL, " +
//            COLUMN_INTERNET + " REAL, " +
//            COLUMN_NUOC_UONG + " REAL, " +
//            COLUMN_NUOC_UONG_THEO_NGUOI + " REAL, " +
//            COLUMN_TIEN_DIEN + " REAL, " +
//            COLUMN_MAY_GIAT + " REAL, " +
//            COLUMN_KHAC + " TEXT, " +
//            COLUMN_NGUOI_THUE + " TEXT, " +
//            COLUMN_EMAIL_NGUOI_THUE + " TEXT" +  // Thêm cột email người thuê
//            ");";
//
//    public ContractHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion < 4) {
//            // Cập nhật bảng, thêm cột "email_nguoi_thue"
//            db.execSQL("ALTER TABLE " + TABLE_CONTRACT + " ADD COLUMN " + COLUMN_EMAIL_NGUOI_THUE + " TEXT;");
//        }
//    }
//
//    public boolean addContract(Contract contract) {
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_PHONG, contract.getRoom());
//        values.put(COLUMN_CHU_HOP_DONG, contract.getOwner());
//        values.put(COLUMN_SO_LUONG, contract.getQuantity());
//        values.put(COLUMN_NGAY_KY, contract.getNgayKy());
//        values.put(COLUMN_NGAY_BAT_DAU, contract.getNgayBatDau());
//        values.put(COLUMN_NGAY_KET_THUC, contract.getNgayKetThuc());
//        values.put(COLUMN_TIEN_NHA, contract.getTienNha());
//        values.put(COLUMN_TIEN_COC, contract.getTienCoc());
//        values.put(COLUMN_INTERNET, contract.getInternet());
//        values.put(COLUMN_NUOC_UONG, contract.getNuocUong());
//        values.put(COLUMN_NUOC_UONG_THEO_NGUOI, contract.getNuocUongTheoNguoi());
//        values.put(COLUMN_TIEN_DIEN, contract.getTienDien());
//        values.put(COLUMN_MAY_GIAT, contract.getMayGiat());
//        values.put(COLUMN_KHAC, contract.getKhac());
//        values.put(COLUMN_NGUOI_THUE, contract.getTenant());  // Thêm người thuê vào values
//        values.put(COLUMN_EMAIL_NGUOI_THUE, contract.getEmailNguoiThue());  // Thêm email người thuê
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        long result = db.insert(TABLE_CONTRACT, null, values);
//        db.close();
//
//        return result != -1;
//    }
//
//    public List<Contract> getAllContracts() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        List<Contract> contractList = new ArrayList<>();
//        Cursor cursor = null;
//
//        try {
//            cursor = db.query(TABLE_CONTRACT, null, null, null, null, null, null);
//
//            if (cursor != null && cursor.moveToFirst()) {
//                do {
//                    int id = getIntFromCursor(cursor, COLUMN_ID);
//                    String room = getStringFromCursor(cursor, COLUMN_PHONG);
//                    String owner = getStringFromCursor(cursor, COLUMN_CHU_HOP_DONG);
//                    int quantity = getIntFromCursor(cursor, COLUMN_SO_LUONG);
//                    String ngayKy = getStringFromCursor(cursor, COLUMN_NGAY_KY);
//                    String ngayBatDau = getStringFromCursor(cursor, COLUMN_NGAY_BAT_DAU);
//                    String ngayKetThuc = getStringFromCursor(cursor, COLUMN_NGAY_KET_THUC);
//                    double tienNha = getDoubleFromCursor(cursor, COLUMN_TIEN_NHA);
//                    double tienCoc = getDoubleFromCursor(cursor, COLUMN_TIEN_COC);
//                    double internet = getDoubleFromCursor(cursor, COLUMN_INTERNET);
//                    double nuocUong = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG);
//                    double nuocUongTheoNguoi = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG_THEO_NGUOI);
//                    double tienDien = getDoubleFromCursor(cursor, COLUMN_TIEN_DIEN);
//                    double mayGiat = getDoubleFromCursor(cursor, COLUMN_MAY_GIAT);
//                    String khac = getStringFromCursor(cursor, COLUMN_KHAC);
//                    String tenant = getStringFromCursor(cursor, COLUMN_NGUOI_THUE);
//                    String emailNguoiThue = getStringFromCursor(cursor, COLUMN_EMAIL_NGUOI_THUE);  // Lấy giá trị email
//
//                    contractList.add(new Contract(id, room, owner, quantity, ngayKy, ngayBatDau, ngayKetThuc,
//                            tienNha, tienCoc, internet, nuocUong, nuocUongTheoNguoi,
//                            tienDien, mayGiat, khac, tenant, emailNguoiThue));  // Thêm email vào constructor
//                } while (cursor.moveToNext());
//            }
//        } finally {
//            if (cursor != null) cursor.close();
//            db.close();
//        }
//
//        return contractList;
//    }
//
//    public Contract getContractByRoom(String roomName) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = null;
//        Contract contract = null;
//
//        try {
//            cursor = db.query(TABLE_CONTRACT, null, COLUMN_PHONG + " = ?", new String[]{roomName}, null, null, null);
//
//            if (cursor != null && cursor.moveToFirst()) {
//                int id = getIntFromCursor(cursor, COLUMN_ID);
//                String room = getStringFromCursor(cursor, COLUMN_PHONG);
//                String owner = getStringFromCursor(cursor, COLUMN_CHU_HOP_DONG);
//                int quantity = getIntFromCursor(cursor, COLUMN_SO_LUONG);
//                String ngayKy = getStringFromCursor(cursor, COLUMN_NGAY_KY);
//                String ngayBatDau = getStringFromCursor(cursor, COLUMN_NGAY_BAT_DAU);
//                String ngayKetThuc = getStringFromCursor(cursor, COLUMN_NGAY_KET_THUC);
//                double tienNha = getDoubleFromCursor(cursor, COLUMN_TIEN_NHA);
//                double tienCoc = getDoubleFromCursor(cursor, COLUMN_TIEN_COC);
//                double internet = getDoubleFromCursor(cursor, COLUMN_INTERNET);
//                double nuocUong = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG);
//                double nuocUongTheoNguoi = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG_THEO_NGUOI);
//                double tienDien = getDoubleFromCursor(cursor, COLUMN_TIEN_DIEN);
//                double mayGiat = getDoubleFromCursor(cursor, COLUMN_MAY_GIAT);
//                String khac = getStringFromCursor(cursor, COLUMN_KHAC);
//                String tenant = getStringFromCursor(cursor, COLUMN_NGUOI_THUE);
//                String emailNguoiThue = getStringFromCursor(cursor, COLUMN_EMAIL_NGUOI_THUE);
//
//                contract = new Contract(id, room, owner, quantity, ngayKy, ngayBatDau, ngayKetThuc,
//                        tienNha, tienCoc, internet, nuocUong, nuocUongTheoNguoi,
//                        tienDien, mayGiat, khac, tenant, emailNguoiThue);
//            }
//        } finally {
//            if (cursor != null) cursor.close();
//            db.close();
//        }
//
//        return contract;
//    }
//
//    public boolean updateContract(Contract contract) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_PHONG, contract.getRoom());
//        values.put(COLUMN_CHU_HOP_DONG, contract.getOwner());
//        values.put(COLUMN_SO_LUONG, contract.getQuantity());
//        values.put(COLUMN_NGAY_KY, contract.getNgayKy());
//        values.put(COLUMN_NGAY_BAT_DAU, contract.getNgayBatDau());
//        values.put(COLUMN_NGAY_KET_THUC, contract.getNgayKetThuc());
//        values.put(COLUMN_TIEN_NHA, contract.getTienNha());
//        values.put(COLUMN_TIEN_COC, contract.getTienCoc());
//        values.put(COLUMN_INTERNET, contract.getInternet());
//        values.put(COLUMN_NUOC_UONG, contract.getNuocUong());
//        values.put(COLUMN_NUOC_UONG_THEO_NGUOI, contract.getNuocUongTheoNguoi());
//        values.put(COLUMN_TIEN_DIEN, contract.getTienDien());
//        values.put(COLUMN_MAY_GIAT, contract.getMayGiat());
//        values.put(COLUMN_KHAC, contract.getKhac());
//        values.put(COLUMN_NGUOI_THUE, contract.getTenant()); // Thêm người thuê vào giá trị
//        values.put(COLUMN_EMAIL_NGUOI_THUE, contract.getEmailNguoiThue()); // Thêm email người thuê
//
//        int rowsAffected = db.update(TABLE_CONTRACT, values, COLUMN_ID + " = ?", new String[]{String.valueOf(contract.getId())});
//        db.close();
//
//        return rowsAffected > 0;
//    }
//
//    private int getIntFromCursor(Cursor cursor, String columnName) {
//        int index = cursor.getColumnIndex(columnName);
//        return (index != -1) ? cursor.getInt(index) : 0;
//    }
//
//    private String getStringFromCursor(Cursor cursor, String columnName) {
//        int index = cursor.getColumnIndex(columnName);
//        return (index != -1) ? cursor.getString(index) : "";
//    }
//
//    private double getDoubleFromCursor(Cursor cursor, String columnName) {
//        int index = cursor.getColumnIndex(columnName);
//        return (index != -1) ? cursor.getDouble(index) : 0.0;
//    }
//
//    public void deleteContract(Contract contract) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CONTRACT, COLUMN_ID + " = ?", new String[]{String.valueOf(contract.getId())});
//        db.close();
//    }
//}

//package com.scan.dyong_resident.database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.scan.dyong_resident.models.Contract;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ContractHelper extends SQLiteOpenHelper {
//
//    private static final String DATABASE_NAME = "contracts.db";
//    private static final int DATABASE_VERSION = 4;  // Cập nhật version
//
//    public static final String TABLE_CONTRACT = "contracts";
//    public static final String COLUMN_ID = "id";
//    public static final String COLUMN_PHONG = "phong";
//    public static final String COLUMN_CHU_HOP_DONG = "chu_hop_dong";
//    public static final String COLUMN_SO_LUONG = "so_luong";
//    public static final String COLUMN_NGAY_KY = "ngay_ky";
//    public static final String COLUMN_NGAY_BAT_DAU = "ngay_bat_dau";
//    public static final String COLUMN_NGAY_KET_THUC = "ngay_ket_thuc";
//    public static final String COLUMN_TIEN_NHA = "tien_nha";
//    public static final String COLUMN_TIEN_COC = "tien_coc";
//    public static final String COLUMN_INTERNET = "internet";
//    public static final String COLUMN_NUOC_UONG = "nuoc_uong";
//    public static final String COLUMN_NUOC_UONG_THEO_NGUOI = "nuoc_uong_theo_nguoi";
//    public static final String COLUMN_TIEN_DIEN = "tien_dien";
//    public static final String COLUMN_MAY_GIAT = "may_giat";
//    public static final String COLUMN_KHAC = "khac";
//    public static final String COLUMN_NGUOI_THUE = "nguoi_thue";  // Cột người thuê
//    public static final String COLUMN_EMAIL_NGUOI_THUE = "email_nguoi_thue"; // Thêm cột email người thuê
//
//    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTRACT + " (" +
//            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            COLUMN_PHONG + " TEXT, " +
//            COLUMN_CHU_HOP_DONG + " TEXT, " +
//            COLUMN_SO_LUONG + " INTEGER, " +
//            COLUMN_NGAY_KY + " TEXT, " +
//            COLUMN_NGAY_BAT_DAU + " TEXT, " +
//            COLUMN_NGAY_KET_THUC + " TEXT, " +
//            COLUMN_TIEN_NHA + " REAL, " +
//            COLUMN_TIEN_COC + " REAL, " +
//            COLUMN_INTERNET + " REAL, " +
//            COLUMN_NUOC_UONG + " REAL, " +
//            COLUMN_NUOC_UONG_THEO_NGUOI + " REAL, " +
//            COLUMN_TIEN_DIEN + " REAL, " +
//            COLUMN_MAY_GIAT + " REAL, " +
//            COLUMN_KHAC + " TEXT, " +
//            COLUMN_NGUOI_THUE + " TEXT, " +
//            COLUMN_EMAIL_NGUOI_THUE + " TEXT" +  // Thêm cột email người thuê
//            ");";
//
//    public ContractHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion < 4) {
//            // Cập nhật bảng, thêm cột "email_nguoi_thue"
//            db.execSQL("ALTER TABLE " + TABLE_CONTRACT + " ADD COLUMN " + COLUMN_EMAIL_NGUOI_THUE + " TEXT;");
//        }
//    }
//
//    public boolean addContract(Contract contract) {
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_PHONG, contract.getRoom());
//        values.put(COLUMN_CHU_HOP_DONG, contract.getOwner());
//        values.put(COLUMN_SO_LUONG, contract.getQuantity());
//        values.put(COLUMN_NGAY_KY, contract.getNgayKy());
//        values.put(COLUMN_NGAY_BAT_DAU, contract.getNgayBatDau());
//        values.put(COLUMN_NGAY_KET_THUC, contract.getNgayKetThuc());
//        values.put(COLUMN_TIEN_NHA, contract.getTienNha());
//        values.put(COLUMN_TIEN_COC, contract.getTienCoc());
//        values.put(COLUMN_INTERNET, contract.getInternet());
//        values.put(COLUMN_NUOC_UONG, contract.getNuocUong());
//        values.put(COLUMN_NUOC_UONG_THEO_NGUOI, contract.getNuocUongTheoNguoi());
//        values.put(COLUMN_TIEN_DIEN, contract.getTienDien());
//        values.put(COLUMN_MAY_GIAT, contract.getMayGiat());
//        values.put(COLUMN_KHAC, contract.getKhac());
//        values.put(COLUMN_NGUOI_THUE, contract.getTenant());  // Thêm người thuê vào values
//        values.put(COLUMN_EMAIL_NGUOI_THUE, contract.getEmailNguoiThue());  // Thêm email người thuê
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        long result = db.insert(TABLE_CONTRACT, null, values);
//        db.close();
//
//        return result != -1;
//    }
//
//    public List<Contract> getAllContracts() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        List<Contract> contractList = new ArrayList<>();
//        Cursor cursor = null;
//
//        try {
//            cursor = db.query(TABLE_CONTRACT, null, null, null, null, null, null);
//
//            if (cursor != null && cursor.moveToFirst()) {
//                do {
//                    int id = getIntFromCursor(cursor, COLUMN_ID);
//                    String room = getStringFromCursor(cursor, COLUMN_PHONG);
//                    String owner = getStringFromCursor(cursor, COLUMN_CHU_HOP_DONG);
//                    int quantity = getIntFromCursor(cursor, COLUMN_SO_LUONG);
//                    String ngayKy = getStringFromCursor(cursor, COLUMN_NGAY_KY);
//                    String ngayBatDau = getStringFromCursor(cursor, COLUMN_NGAY_BAT_DAU);
//                    String ngayKetThuc = getStringFromCursor(cursor, COLUMN_NGAY_KET_THUC);
//                    double tienNha = getDoubleFromCursor(cursor, COLUMN_TIEN_NHA);
//                    double tienCoc = getDoubleFromCursor(cursor, COLUMN_TIEN_COC);
//                    double internet = getDoubleFromCursor(cursor, COLUMN_INTERNET);
//                    double nuocUong = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG);
//                    double nuocUongTheoNguoi = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG_THEO_NGUOI);
//                    double tienDien = getDoubleFromCursor(cursor, COLUMN_TIEN_DIEN);
//                    double mayGiat = getDoubleFromCursor(cursor, COLUMN_MAY_GIAT);
//                    String khac = getStringFromCursor(cursor, COLUMN_KHAC);
//                    String tenant = getStringFromCursor(cursor, COLUMN_NGUOI_THUE);
//                    String emailNguoiThue = getStringFromCursor(cursor, COLUMN_EMAIL_NGUOI_THUE);  // Lấy giá trị email
//
//                    contractList.add(new Contract(id, room, owner, quantity, ngayKy, ngayBatDau, ngayKetThuc,
//                            tienNha, tienCoc, internet, nuocUong, nuocUongTheoNguoi,
//                            tienDien, mayGiat, khac, tenant, emailNguoiThue));  // Thêm email vào constructor
//                } while (cursor.moveToNext());
//            }
//        } finally {
//            if (cursor != null) cursor.close();
//            db.close();
//        }
//
//        return contractList;
//    }
//
//    public Contract getContractByRoom(String roomName) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = null;
//        Contract contract = null;
//
//        try {
//            cursor = db.query(TABLE_CONTRACT, null, COLUMN_PHONG + " = ?", new String[]{roomName}, null, null, null);
//
//            if (cursor != null && cursor.moveToFirst()) {
//                int id = getIntFromCursor(cursor, COLUMN_ID);
//                String room = getStringFromCursor(cursor, COLUMN_PHONG);
//                String owner = getStringFromCursor(cursor, COLUMN_CHU_HOP_DONG);
//                int quantity = getIntFromCursor(cursor, COLUMN_SO_LUONG);
//                String ngayKy = getStringFromCursor(cursor, COLUMN_NGAY_KY);
//                String ngayBatDau = getStringFromCursor(cursor, COLUMN_NGAY_BAT_DAU);
//                String ngayKetThuc = getStringFromCursor(cursor, COLUMN_NGAY_KET_THUC);
//                double tienNha = getDoubleFromCursor(cursor, COLUMN_TIEN_NHA);
//                double tienCoc = getDoubleFromCursor(cursor, COLUMN_TIEN_COC);
//                double internet = getDoubleFromCursor(cursor, COLUMN_INTERNET);
//                double nuocUong = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG);
//                double nuocUongTheoNguoi = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG_THEO_NGUOI);
//                double tienDien = getDoubleFromCursor(cursor, COLUMN_TIEN_DIEN);
//                double mayGiat = getDoubleFromCursor(cursor, COLUMN_MAY_GIAT);
//                String khac = getStringFromCursor(cursor, COLUMN_KHAC);
//                String tenant = getStringFromCursor(cursor, COLUMN_NGUOI_THUE);
//                String emailNguoiThue = getStringFromCursor(cursor, COLUMN_EMAIL_NGUOI_THUE);
//
//                contract = new Contract(id, room, owner, quantity, ngayKy, ngayBatDau, ngayKetThuc,
//                        tienNha, tienCoc, internet, nuocUong, nuocUongTheoNguoi,
//                        tienDien, mayGiat, khac, tenant, emailNguoiThue);
//            }
//        } finally {
//            if (cursor != null) cursor.close();
//            db.close();
//        }
//
//        return contract;
//    }
//
//    // Helper methods to extract data from Cursor
//    private int getIntFromCursor(Cursor cursor, String columnName) {
//        return cursor.getInt(cursor.getColumnIndex(columnName));
//    }
//
//    private String getStringFromCursor(Cursor cursor, String columnName) {
//        return cursor.getString(cursor.getColumnIndex(columnName));
//    }
//
//    private double getDoubleFromCursor(Cursor cursor, String columnName) {
//        return cursor.getDouble(cursor.getColumnIndex(columnName));
//    }
//
//    // Cập nhật trạng thái phòng
//    public void updateRoomStatusToRented(String room) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("trang_thai", "Phòng đã cho thuê");  // Cập nhật trạng thái phòng
//        db.update("rooms", values, "room_name = ?", new String[]{room});
//        db.close();
//    }
//}

package com.scan.dyong_resident.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scan.dyong_resident.models.Contract;
import com.scan.dyong_resident.models.Room;

import java.util.ArrayList;
import java.util.List;

public class ContractHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contracts.db";
    private static final int DATABASE_VERSION = 4;  // Cập nhật version

    public static final String TABLE_CONTRACT = "contracts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PHONG = "phong";
    public static final String COLUMN_CHU_HOP_DONG = "chu_hop_dong";
    public static final String COLUMN_SO_LUONG = "so_luong";
    public static final String COLUMN_NGAY_KY = "ngay_ky";
    public static final String COLUMN_NGAY_BAT_DAU = "ngay_bat_dau";
    public static final String COLUMN_NGAY_KET_THUC = "ngay_ket_thuc";
    public static final String COLUMN_TIEN_NHA = "tien_nha";
    public static final String COLUMN_TIEN_COC = "tien_coc";
    public static final String COLUMN_INTERNET = "internet";
    public static final String COLUMN_NUOC_UONG = "nuoc_uong";
    public static final String COLUMN_NUOC_UONG_THEO_NGUOI = "nuoc_uong_theo_nguoi";
    public static final String COLUMN_TIEN_DIEN = "tien_dien";
    public static final String COLUMN_MAY_GIAT = "may_giat";
    public static final String COLUMN_KHAC = "khac";
    public static final String COLUMN_NGUOI_THUE = "nguoi_thue";  // Cột người thuê
    public static final String COLUMN_EMAIL_NGUOI_THUE = "email_nguoi_thue"; // Thêm cột email người thuê

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTRACT + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PHONG + " TEXT, " +
            COLUMN_CHU_HOP_DONG + " TEXT, " +
            COLUMN_SO_LUONG + " INTEGER, " +
            COLUMN_NGAY_KY + " TEXT, " +
            COLUMN_NGAY_BAT_DAU + " TEXT, " +
            COLUMN_NGAY_KET_THUC + " TEXT, " +
            COLUMN_TIEN_NHA + " REAL, " +
            COLUMN_TIEN_COC + " REAL, " +
            COLUMN_INTERNET + " REAL, " +
            COLUMN_NUOC_UONG + " REAL, " +
            COLUMN_NUOC_UONG_THEO_NGUOI + " REAL, " +
            COLUMN_TIEN_DIEN + " REAL, " +
            COLUMN_MAY_GIAT + " REAL, " +
            COLUMN_KHAC + " TEXT, " +
            COLUMN_NGUOI_THUE + " TEXT, " +
            COLUMN_EMAIL_NGUOI_THUE + " TEXT" +  // Thêm cột email người thuê
            ");";

    private Context context;

    public ContractHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 4) {
            // Cập nhật bảng, thêm cột "email_nguoi_thue"
            db.execSQL("ALTER TABLE " + TABLE_CONTRACT + " ADD COLUMN " + COLUMN_EMAIL_NGUOI_THUE + " TEXT;");
        }
    }



    // Phương thức thêm hợp đồng mới
    public boolean addContract(Contract contract) {
        RoomHelper roomHelper = new RoomHelper(context); // Tạo đối tượng RoomHelper để làm việc với phòng
        SQLiteDatabase db = this.getWritableDatabase();

        // Kiểm tra xem phòng có trạng thái "Phòng còn trống" không
        if (!roomHelper.isRoomAvailable(contract.getRoom())) {
            db.close();
            return false; // Phòng không còn trống, không thể tạo hợp đồng
        }

        // Thêm hợp đồng vào bảng contracts
        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONG, contract.getRoom());
        values.put(COLUMN_CHU_HOP_DONG, contract.getOwner());
        values.put(COLUMN_SO_LUONG, contract.getQuantity());
        values.put(COLUMN_NGAY_KY, contract.getNgayKy());
        values.put(COLUMN_NGAY_BAT_DAU, contract.getNgayBatDau());
        values.put(COLUMN_NGAY_KET_THUC, contract.getNgayKetThuc());
        values.put(COLUMN_TIEN_NHA, contract.getTienNha());
        values.put(COLUMN_TIEN_COC, contract.getTienCoc());
        values.put(COLUMN_INTERNET, contract.getInternet());
        values.put(COLUMN_NUOC_UONG, contract.getNuocUong());
        values.put(COLUMN_NUOC_UONG_THEO_NGUOI, contract.getNuocUongTheoNguoi());
        values.put(COLUMN_TIEN_DIEN, contract.getTienDien());
        values.put(COLUMN_MAY_GIAT, contract.getMayGiat());
        values.put(COLUMN_KHAC, contract.getKhac());
        values.put(COLUMN_NGUOI_THUE, contract.getTenant());
        values.put(COLUMN_EMAIL_NGUOI_THUE, contract.getEmailNguoiThue());

        long result = db.insert(TABLE_CONTRACT, null, values);

        // Kiểm tra kết quả thêm hợp đồng thành công
        if (result != -1) {
            // Cập nhật trạng thái phòng thành "Phòng đã cho thuê"
            roomHelper.updateRoomStatus(contract.getRoom(), "Phòng đã cho thuê");
            db.close();
            return true; // Thành công
        }

        db.close();
        return false; // Thêm hợp đồng thất bại
    }
    // Phương thức cập nhật hợp đồng
    public boolean updateContract(Contract contract) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONG, contract.getRoom());
        values.put(COLUMN_CHU_HOP_DONG, contract.getOwner());
        values.put(COLUMN_SO_LUONG, contract.getQuantity());
        values.put(COLUMN_NGAY_KY, contract.getNgayKy());
        values.put(COLUMN_NGAY_BAT_DAU, contract.getNgayBatDau());
        values.put(COLUMN_NGAY_KET_THUC, contract.getNgayKetThuc());
        values.put(COLUMN_TIEN_NHA, contract.getTienNha());
        values.put(COLUMN_TIEN_COC, contract.getTienCoc());
        values.put(COLUMN_INTERNET, contract.getInternet());
        values.put(COLUMN_NUOC_UONG, contract.getNuocUong());
        values.put(COLUMN_NUOC_UONG_THEO_NGUOI, contract.getNuocUongTheoNguoi());
        values.put(COLUMN_TIEN_DIEN, contract.getTienDien());
        values.put(COLUMN_MAY_GIAT, contract.getMayGiat());
        values.put(COLUMN_KHAC, contract.getKhac());
        values.put(COLUMN_NGUOI_THUE, contract.getTenant());
        values.put(COLUMN_EMAIL_NGUOI_THUE, contract.getEmailNguoiThue());

        // Cập nhật hợp đồng theo ID
        int rowsUpdated = db.update(TABLE_CONTRACT, values, COLUMN_ID + " = ?", new String[]{String.valueOf(contract.getId())});
        db.close();

        return rowsUpdated > 0; // Trả về true nếu có ít nhất một bản ghi được cập nhật
    }



    // Phương thức lấy hợp đồng theo tên phòng
    public Contract getContractByRoom(String roomName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Contract contract = null;
        Cursor cursor = null;

        try {
            // Truy vấn hợp đồng theo phòng
            cursor = db.query(TABLE_CONTRACT, null, COLUMN_PHONG + " = ?", new String[]{roomName}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                // Lấy các thông tin của hợp đồng từ Cursor
                int id = getIntFromCursor(cursor, COLUMN_ID);
                String room = getStringFromCursor(cursor, COLUMN_PHONG);
                String owner = getStringFromCursor(cursor, COLUMN_CHU_HOP_DONG);
                int quantity = getIntFromCursor(cursor, COLUMN_SO_LUONG);
                String ngayKy = getStringFromCursor(cursor, COLUMN_NGAY_KY);
                String ngayBatDau = getStringFromCursor(cursor, COLUMN_NGAY_BAT_DAU);
                String ngayKetThuc = getStringFromCursor(cursor, COLUMN_NGAY_KET_THUC);
                double tienNha = getDoubleFromCursor(cursor, COLUMN_TIEN_NHA);
                double tienCoc = getDoubleFromCursor(cursor, COLUMN_TIEN_COC);
                double internet = getDoubleFromCursor(cursor, COLUMN_INTERNET);
                double nuocUong = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG);
                double nuocUongTheoNguoi = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG_THEO_NGUOI);
                double tienDien = getDoubleFromCursor(cursor, COLUMN_TIEN_DIEN);
                double mayGiat = getDoubleFromCursor(cursor, COLUMN_MAY_GIAT);
                String khac = getStringFromCursor(cursor, COLUMN_KHAC);
                String tenant = getStringFromCursor(cursor, COLUMN_NGUOI_THUE);
                String emailNguoiThue = getStringFromCursor(cursor, COLUMN_EMAIL_NGUOI_THUE);

                // Khởi tạo đối tượng Contract và trả về
                contract = new Contract(id, room, owner, quantity, ngayKy, ngayBatDau, ngayKetThuc,
                        tienNha, tienCoc, internet, nuocUong, nuocUongTheoNguoi,
                        tienDien, mayGiat, khac, tenant, emailNguoiThue);
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return contract; // Trả về hợp đồng tìm được (nếu có)
    }


    // Phương thức xóa hợp đồng
    public boolean deleteContract(Contract contract) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Xóa hợp đồng theo ID
        int rowsDeleted = db.delete(TABLE_CONTRACT, COLUMN_ID + " = ?", new String[]{String.valueOf(contract.getId())});
        db.close();

        return rowsDeleted > 0; // Trả về true nếu có ít nhất một bản ghi bị xóa
    }



    // Lấy tất cả hợp đồng
    public List<Contract> getAllContracts() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Contract> contractList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_CONTRACT, null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = getIntFromCursor(cursor, COLUMN_ID);
                    String room = getStringFromCursor(cursor, COLUMN_PHONG);
                    String owner = getStringFromCursor(cursor, COLUMN_CHU_HOP_DONG);
                    int quantity = getIntFromCursor(cursor, COLUMN_SO_LUONG);
                    String ngayKy = getStringFromCursor(cursor, COLUMN_NGAY_KY);
                    String ngayBatDau = getStringFromCursor(cursor, COLUMN_NGAY_BAT_DAU);
                    String ngayKetThuc = getStringFromCursor(cursor, COLUMN_NGAY_KET_THUC);
                    double tienNha = getDoubleFromCursor(cursor, COLUMN_TIEN_NHA);
                    double tienCoc = getDoubleFromCursor(cursor, COLUMN_TIEN_COC);
                    double internet = getDoubleFromCursor(cursor, COLUMN_INTERNET);
                    double nuocUong = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG);
                    double nuocUongTheoNguoi = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG_THEO_NGUOI);
                    double tienDien = getDoubleFromCursor(cursor, COLUMN_TIEN_DIEN);
                    double mayGiat = getDoubleFromCursor(cursor, COLUMN_MAY_GIAT);
                    String khac = getStringFromCursor(cursor, COLUMN_KHAC);
                    String tenant = getStringFromCursor(cursor, COLUMN_NGUOI_THUE);
                    String emailNguoiThue = getStringFromCursor(cursor, COLUMN_EMAIL_NGUOI_THUE);

                    contractList.add(new Contract(id, room, owner, quantity, ngayKy, ngayBatDau, ngayKetThuc,
                            tienNha, tienCoc, internet, nuocUong, nuocUongTheoNguoi,
                            tienDien, mayGiat, khac, tenant, emailNguoiThue));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return contractList;
    }

    public List<String> getAllEmails() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> emailList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_CONTRACT, new String[]{COLUMN_EMAIL_NGUOI_THUE}, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String email = getStringFromCursor(cursor, COLUMN_EMAIL_NGUOI_THUE);
                    emailList.add(email);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return emailList;
    }

    // Phương thức lấy hợp đồng theo email người thuê
    public Contract getContractByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Contract contract = null;
        Cursor cursor = null;

        try {
            // Truy vấn hợp đồng theo email người thuê
            cursor = db.query(TABLE_CONTRACT, null, COLUMN_EMAIL_NGUOI_THUE + " = ?", new String[]{email}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                // Lấy các thông tin của hợp đồng từ Cursor
                int id = getIntFromCursor(cursor, COLUMN_ID);
                String room = getStringFromCursor(cursor, COLUMN_PHONG);
                String owner = getStringFromCursor(cursor, COLUMN_CHU_HOP_DONG);
                int quantity = getIntFromCursor(cursor, COLUMN_SO_LUONG);
                String ngayKy = getStringFromCursor(cursor, COLUMN_NGAY_KY);
                String ngayBatDau = getStringFromCursor(cursor, COLUMN_NGAY_BAT_DAU);
                String ngayKetThuc = getStringFromCursor(cursor, COLUMN_NGAY_KET_THUC);
                double tienNha = getDoubleFromCursor(cursor, COLUMN_TIEN_NHA);
                double tienCoc = getDoubleFromCursor(cursor, COLUMN_TIEN_COC);
                double internet = getDoubleFromCursor(cursor, COLUMN_INTERNET);
                double nuocUong = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG);
                double nuocUongTheoNguoi = getDoubleFromCursor(cursor, COLUMN_NUOC_UONG_THEO_NGUOI);
                double tienDien = getDoubleFromCursor(cursor, COLUMN_TIEN_DIEN);
                double mayGiat = getDoubleFromCursor(cursor, COLUMN_MAY_GIAT);
                String khac = getStringFromCursor(cursor, COLUMN_KHAC);
                String tenant = getStringFromCursor(cursor, COLUMN_NGUOI_THUE);
                String emailNguoiThue = getStringFromCursor(cursor, COLUMN_EMAIL_NGUOI_THUE);

                // Khởi tạo đối tượng Contract và trả về
                contract = new Contract(id, room, owner, quantity, ngayKy, ngayBatDau, ngayKetThuc,
                        tienNha, tienCoc, internet, nuocUong, nuocUongTheoNguoi,
                        tienDien, mayGiat, khac, tenant, emailNguoiThue);
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return contract; // Trả về hợp đồng tìm được (nếu có)
    }





    // Helper methods to extract data from Cursor
    private int getIntFromCursor(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    private String getStringFromCursor(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    private double getDoubleFromCursor(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }
}

