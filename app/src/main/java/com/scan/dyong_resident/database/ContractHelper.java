package com.scan.dyong_resident.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scan.dyong_resident.models.ContractData;

public class ContractHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contract_db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTRACT = "contract";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ROOM = "room";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_RENT = "rent";
    public static final String COLUMN_PAYMENT_CYCLE = "payment_cycle";
    public static final String COLUMN_START_PAYMENT = "start_payment";
    public static final String COLUMN_DEPOSIT = "deposit";
    public static final String COLUMN_PROMO = "promo";
    public static final String COLUMN_PROMO_MONTHS = "promo_months";
    public static final String COLUMN_DISCOUNT = "discount";
    public static final String COLUMN_OTHER = "other";

    public ContractHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTRACT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ROOM + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_START_DATE + " TEXT,"
                + COLUMN_END_DATE + " TEXT,"
                + COLUMN_NOTE + " TEXT,"
                + COLUMN_RENT + " TEXT,"
                + COLUMN_PAYMENT_CYCLE + " TEXT,"
                + COLUMN_START_PAYMENT + " TEXT,"
                + COLUMN_DEPOSIT + " TEXT,"
                + COLUMN_PROMO + " TEXT,"
                + COLUMN_PROMO_MONTHS + " TEXT,"
                + COLUMN_DISCOUNT + " TEXT,"
                + COLUMN_OTHER + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTRACT);
        onCreate(db);
    }

    public void addContract(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CONTRACT, null, values);
        db.close();
    }

    public Cursor getAllContracts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_CONTRACT, null, null, null, null, null, null);
    }

    public ContractData getContractById(int contractId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTRACT, null, COLUMN_ID + " = ?",
                new String[]{String.valueOf(contractId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            ContractData contract = null;
            try {
                contract = new ContractData(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROOM)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_START_DATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_END_DATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAYMENT_CYCLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_START_PAYMENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEPOSIT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROMO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROMO_MONTHS)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DISCOUNT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OTHER))
                );
            } catch (IllegalArgumentException e) {
                // Handle exception: if column doesn't exist or is missing in database schema
                e.printStackTrace();
            }

            cursor.close();
            db.close();
            return contract;
        }

        db.close();
        return null;
    }
}
