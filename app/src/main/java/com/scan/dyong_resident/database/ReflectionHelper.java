package com.scan.dyong_resident.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scan.dyong_resident.models.Reflection;

import java.util.ArrayList;
import java.util.List;

public class ReflectionHelper extends SQLiteOpenHelper {

    // Tên cơ sở dữ liệu và tên bảng
    private static final String DATABASE_NAME = "phananh.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "phananh";

    // Cột của bảng
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEN_PHONG = "ten_phong";
    private static final String COLUMN_VI_TRI_SO_TANG = "vi_tri_so_tang";
    private static final String COLUMN_NGUOI_TAO = "nguoi_tao";
    private static final String COLUMN_NOI_DUNG = "noi_dung";

    // Câu lệnh tạo bảng
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TEN_PHONG + " TEXT, " +
            COLUMN_VI_TRI_SO_TANG + " TEXT, " +
            COLUMN_NGUOI_TAO + " TEXT, " +
            COLUMN_NOI_DUNG + " TEXT);";

    // Constructor
    public ReflectionHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    // Cập nhật bảng khi có thay đổi phiên bản cơ sở dữ liệu
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Lấy tất cả phản ánh từ cơ sở dữ liệu
    public List<Reflection> getAllReflections() {
        List<Reflection> reflections = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // Kiểm tra sự tồn tại của các cột trước khi lấy giá trị
                    int columnIndexTenPhong = cursor.getColumnIndex(COLUMN_TEN_PHONG);
                    int columnIndexViTriSoTang = cursor.getColumnIndex(COLUMN_VI_TRI_SO_TANG);
                    int columnIndexNguoiTao = cursor.getColumnIndex(COLUMN_NGUOI_TAO);
                    int columnIndexNoiDung = cursor.getColumnIndex(COLUMN_NOI_DUNG);

                    // Lấy giá trị từ cursor, sử dụng phương thức isNull để tránh lỗi khi cột có giá trị NULL
                    String tenPhong = cursor.isNull(columnIndexTenPhong) ? "" : cursor.getString(columnIndexTenPhong);
                    String viTriSoTang = cursor.isNull(columnIndexViTriSoTang) ? "" : cursor.getString(columnIndexViTriSoTang);
                    String nguoiTao = cursor.isNull(columnIndexNguoiTao) ? "" : cursor.getString(columnIndexNguoiTao);
                    String noiDung = cursor.isNull(columnIndexNoiDung) ? "" : cursor.getString(columnIndexNoiDung);

                    // Tạo đối tượng Reflection và thêm vào danh sách
                    Reflection reflection = new Reflection(tenPhong, viTriSoTang, nguoiTao, noiDung);
                    reflections.add(reflection);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return reflections;
    }

}
