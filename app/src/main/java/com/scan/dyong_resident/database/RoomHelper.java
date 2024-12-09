package com.scan.dyong_resident.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scan.dyong_resident.models.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rooms.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ROOM = "room";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEN_PHONG = "tenPhong";
    public static final String COLUMN_VI_TRI_SO_TANG = "viTriSoTang";
    public static final String COLUMN_TRANG_THAI = "trangThai";
    public static final String COLUMN_MO_TA = "moTa";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_ROOM + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TEN_PHONG + " TEXT, " +
            COLUMN_VI_TRI_SO_TANG + " TEXT, " +
            COLUMN_TRANG_THAI + " TEXT, " +
            COLUMN_MO_TA + " TEXT" +
            ");";

    public RoomHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
        onCreate(db);
    }

    public boolean addRoom(Room room) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEN_PHONG, room.getTenPhong());
        values.put(COLUMN_VI_TRI_SO_TANG, room.getViTriSoTang());
        values.put(COLUMN_TRANG_THAI, room.getTrangThai());
        values.put(COLUMN_MO_TA, room.getMoTa());

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_ROOM, null, values);
        db.close();

        return result != -1;
    }

    public List<Room> getAllRooms() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Room> roomList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_ROOM, null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex(COLUMN_ID);
                    int tenPhongIndex = cursor.getColumnIndex(COLUMN_TEN_PHONG);
                    int viTriSoTangIndex = cursor.getColumnIndex(COLUMN_VI_TRI_SO_TANG);
                    int trangThaiIndex = cursor.getColumnIndex(COLUMN_TRANG_THAI);
                    int moTaIndex = cursor.getColumnIndex(COLUMN_MO_TA);

                    if (idIndex != -1 && tenPhongIndex != -1 && viTriSoTangIndex != -1 && trangThaiIndex != -1 && moTaIndex != -1) {
                        int id = cursor.getInt(idIndex);
                        String tenPhong = cursor.getString(tenPhongIndex);
                        String viTriSoTang = cursor.getString(viTriSoTangIndex);
                        String trangThai = cursor.getString(trangThaiIndex);
                        String moTa = cursor.getString(moTaIndex);

                        roomList.add(new Room(id, tenPhong, viTriSoTang, trangThai, moTa));
                    }
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return roomList;
    }

    public List<Room> getRoomsForRent() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Room> roomList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_ROOM, null, COLUMN_TRANG_THAI + " = ?", new String[]{"Phòng đã cho thuê"}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex(COLUMN_ID);
                    int tenPhongIndex = cursor.getColumnIndex(COLUMN_TEN_PHONG);
                    int viTriSoTangIndex = cursor.getColumnIndex(COLUMN_VI_TRI_SO_TANG);
                    int trangThaiIndex = cursor.getColumnIndex(COLUMN_TRANG_THAI);
                    int moTaIndex = cursor.getColumnIndex(COLUMN_MO_TA);

                    if (idIndex != -1 && tenPhongIndex != -1 && viTriSoTangIndex != -1 && trangThaiIndex != -1 && moTaIndex != -1) {
                        int id = cursor.getInt(idIndex);
                        String tenPhong = cursor.getString(tenPhongIndex);
                        String viTriSoTang = cursor.getString(viTriSoTangIndex);
                        String trangThai = cursor.getString(trangThaiIndex);
                        String moTa = cursor.getString(moTaIndex);

                        roomList.add(new Room(id, tenPhong, viTriSoTang, trangThai, moTa));
                    }
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return roomList;
    }

    public List<Room> getRoomNotPaid() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Room> roomList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_ROOM, null, COLUMN_TRANG_THAI + " = ?", new String[]{"Phòng còn trống"}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex(COLUMN_ID);
                    int tenPhongIndex = cursor.getColumnIndex(COLUMN_TEN_PHONG);
                    int viTriSoTangIndex = cursor.getColumnIndex(COLUMN_VI_TRI_SO_TANG);
                    int trangThaiIndex = cursor.getColumnIndex(COLUMN_TRANG_THAI);
                    int moTaIndex = cursor.getColumnIndex(COLUMN_MO_TA);

                    if (idIndex != -1 && tenPhongIndex != -1 && viTriSoTangIndex != -1 && trangThaiIndex != -1 && moTaIndex != -1) {
                        int id = cursor.getInt(idIndex);
                        String tenPhong = cursor.getString(tenPhongIndex);
                        String viTriSoTang = cursor.getString(viTriSoTangIndex);
                        String trangThai = cursor.getString(trangThaiIndex);
                        String moTa = cursor.getString(moTaIndex);

                        roomList.add(new Room(id, tenPhong, viTriSoTang, trangThai, moTa));
                    }
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return roomList;
    }

    // Phương thức kiểm tra xem phòng có hợp đồng chưa
    public boolean isRoomHasContract(String roomName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Kiểm tra hợp đồng đã tồn tại cho phòng này chưa (Giả sử bạn có bảng hợp đồng và mỗi hợp đồng có tên phòng)
            cursor = db.rawQuery("SELECT * FROM contract WHERE tenPhong = ?", new String[]{roomName});
            return cursor != null && cursor.moveToFirst();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
    }

    // Phương thức cập nhật trạng thái của phòng
    public boolean updateRoomStatus(String roomName, String newStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TRANG_THAI, newStatus);

        int rowsUpdated = db.update(TABLE_ROOM, values, COLUMN_TEN_PHONG + " = ?", new String[]{roomName});
        db.close();

        return rowsUpdated > 0;  // Trả về true nếu có ít nhất một bản ghi được cập nhật
    }



    public boolean isRoomAvailable(String roomName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        boolean isAvailable = false;

        try {
            // Kiểm tra phòng có trạng thái "Phòng còn trống"
            String query = "SELECT * FROM " + TABLE_ROOM + " WHERE " + COLUMN_TEN_PHONG + " = ? AND " + COLUMN_TRANG_THAI + " = ?";
            cursor = db.rawQuery(query, new String[]{roomName, "Phòng còn trống"});

            if (cursor != null && cursor.moveToFirst()) {
                isAvailable = true;
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return isAvailable;
    }

}
