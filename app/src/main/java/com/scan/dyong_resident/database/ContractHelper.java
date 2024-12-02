package com.scan.dyong_resident.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scan.dyong_resident.models.Contract;

import java.util.ArrayList;
import java.util.List;

public class ContractHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contracts.db";
    private static final int DATABASE_VERSION = 1;

    // Table and columns
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
            COLUMN_KHAC + " TEXT" +
            ");";

    public ContractHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTRACT);
        onCreate(db);
    }

    public boolean addContract(Contract contract) {
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

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_CONTRACT, null, values);
        db.close();

        return result != -1;
    }

    public List<Contract> getAllContracts() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Contract> contractList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_CONTRACT, null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
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

                    contractList.add(new Contract(room, owner, quantity, ngayKy, ngayBatDau, ngayKetThuc,
                            tienNha, tienCoc, internet, nuocUong, nuocUongTheoNguoi,
                            tienDien, mayGiat, khac));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return contractList;
    }

    private String getStringFromCursor(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return columnIndex != -1 ? cursor.getString(columnIndex) : null;
    }

    private int getIntFromCursor(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
    }

    private double getDoubleFromCursor(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return columnIndex != -1 ? cursor.getDouble(columnIndex) : 0.0;
    }
}
