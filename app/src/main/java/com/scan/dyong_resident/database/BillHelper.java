package com.scan.dyong_resident.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scan.dyong_resident.models.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bills.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_BILL = "bills";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PHONG = "phong";
    public static final String COLUMN_CHU_HOP_DONG = "chu_hop_dong";
    public static final String COLUMN_KY_THANH_TOAN = "ky_thanh_toan";
    public static final String COLUMN_NGAY_LAP = "ngay_lap";
    public static final String COLUMN_HAN_THANH_TOAN = "han_thanh_toan";
    public static final String COLUMN_SO_LUONG = "so_luong";
    public static final String COLUMN_TIEN_PHONG = "tien_phong";
    public static final String COLUMN_TIEN_DIEN = "tien_dien";
    public static final String COLUMN_TIEN_NUOC = "tien_nuoc";
    public static final String COLUMN_TIEN_MANG = "tien_mang";
    public static final String COLUMN_TIEN_THANG_MAY = "tien_thang_may";
    public static final String COLUMN_TIEN_RAC = "tien_rac";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_BILL + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PHONG + " TEXT, " +
            COLUMN_CHU_HOP_DONG + " TEXT, " +
            COLUMN_KY_THANH_TOAN + " TEXT, " +
            COLUMN_NGAY_LAP + " TEXT, " +
            COLUMN_HAN_THANH_TOAN + " TEXT, " +
            COLUMN_SO_LUONG + " INTEGER, " +
            COLUMN_TIEN_PHONG + " REAL, " +
            COLUMN_TIEN_DIEN + " REAL, " +
            COLUMN_TIEN_NUOC + " REAL, " +
            COLUMN_TIEN_MANG + " REAL, " +
            COLUMN_TIEN_THANG_MAY + " REAL, " +
            COLUMN_TIEN_RAC + " REAL" +
            ");";

    public BillHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
        }
    }

    public boolean addBill(Bill bill) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONG, bill.getPhong());
        values.put(COLUMN_CHU_HOP_DONG, bill.getChuHopDong());
        values.put(COLUMN_KY_THANH_TOAN, bill.getKyThanhToan());
        values.put(COLUMN_NGAY_LAP, bill.getNgayLap());
        values.put(COLUMN_HAN_THANH_TOAN, bill.getHanThanhToan());
        values.put(COLUMN_SO_LUONG, bill.getSoLuong());
        values.put(COLUMN_TIEN_PHONG, bill.getTienPhong());
        values.put(COLUMN_TIEN_DIEN, bill.getTienDien());
        values.put(COLUMN_TIEN_NUOC, bill.getTienNuoc());
        values.put(COLUMN_TIEN_MANG, bill.getTienMang());
        values.put(COLUMN_TIEN_THANG_MAY, bill.getTienThangMay());
        values.put(COLUMN_TIEN_RAC, bill.getTienRac());

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_BILL, null, values);
        db.close();

        return result != -1;
    }

    public List<Bill> getAllBills() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Bill> billList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_BILL, null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = getIntFromCursor(cursor, COLUMN_ID);
                    String phong = getStringFromCursor(cursor, COLUMN_PHONG);
                    String chuHopDong = getStringFromCursor(cursor, COLUMN_CHU_HOP_DONG);
                    String kyThanhToan = getStringFromCursor(cursor, COLUMN_KY_THANH_TOAN);
                    String ngayLap = getStringFromCursor(cursor, COLUMN_NGAY_LAP);
                    String hanThanhToan = getStringFromCursor(cursor, COLUMN_HAN_THANH_TOAN);
                    int soLuong = getIntFromCursor(cursor, COLUMN_SO_LUONG);
                    double tienPhong = getDoubleFromCursor(cursor, COLUMN_TIEN_PHONG);
                    double tienDien = getDoubleFromCursor(cursor, COLUMN_TIEN_DIEN);
                    double tienNuoc = getDoubleFromCursor(cursor, COLUMN_TIEN_NUOC);
                    double tienMang = getDoubleFromCursor(cursor, COLUMN_TIEN_MANG);
                    double tienThangMay = getDoubleFromCursor(cursor, COLUMN_TIEN_THANG_MAY);
                    double tienRac = getDoubleFromCursor(cursor, COLUMN_TIEN_RAC);

                    billList.add(new Bill(id, phong, chuHopDong, kyThanhToan, ngayLap, hanThanhToan,
                            soLuong, tienPhong, tienDien, tienNuoc, tienMang, tienThangMay, tienRac));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return billList;
    }

    public boolean updateBill(Bill bill) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONG, bill.getPhong());
        values.put(COLUMN_CHU_HOP_DONG, bill.getChuHopDong());
        values.put(COLUMN_KY_THANH_TOAN, bill.getKyThanhToan());
        values.put(COLUMN_NGAY_LAP, bill.getNgayLap());
        values.put(COLUMN_HAN_THANH_TOAN, bill.getHanThanhToan());
        values.put(COLUMN_SO_LUONG, bill.getSoLuong());
        values.put(COLUMN_TIEN_PHONG, bill.getTienPhong());
        values.put(COLUMN_TIEN_DIEN, bill.getTienDien());
        values.put(COLUMN_TIEN_NUOC, bill.getTienNuoc());
        values.put(COLUMN_TIEN_MANG, bill.getTienMang());
        values.put(COLUMN_TIEN_THANG_MAY, bill.getTienThangMay());
        values.put(COLUMN_TIEN_RAC, bill.getTienRac());

        int rowsAffected = db.update(TABLE_BILL, values, COLUMN_ID + " = ?", new String[]{String.valueOf(bill.getId())});
        db.close();

        return rowsAffected > 0;
    }

    private int getIntFromCursor(Cursor cursor, String columnName) {
        int index = cursor.getColumnIndex(columnName);
        return (index != -1) ? cursor.getInt(index) : 0;
    }

    private String getStringFromCursor(Cursor cursor, String columnName) {
        int index = cursor.getColumnIndex(columnName);
        return (index != -1) ? cursor.getString(index) : "";
    }

    private double getDoubleFromCursor(Cursor cursor, String columnName) {
        int index = cursor.getColumnIndex(columnName);
        return (index != -1) ? cursor.getDouble(index) : 0.0;
    }

    public void deleteBill(Bill bill) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BILL, COLUMN_ID + " = ?", new String[]{String.valueOf(bill.getId())});
        db.close();
    }
}
