package com.hangoclong.shop1.Model.ThanhToan.DiaChi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 11/25/2017.
 */

public class DataDiaChi extends SQLiteOpenHelper {
    public static  String TB_DIACHI="DIACHI";
    public static  String TB_DIACHI_MADIACHI="MADIACHI";
    public static  String TB_DIACHI_TENNGUOIGUI="TEN";
    public static  String TB_DIACHI_SODT="SODT";
    public static  String TB_DIACHI_TINH="TINH";
    public static  String TB_DIACHI_HUYEN="HUYEN";
    public static  String TB_DIACHI_PHUONG="PHUONG";
    public static  String TB_DIACHI_DIACHICUTHE="DIACHICUTHE";
    public static  String TB_DIACHI_MACDINH="MACDINH";
    public static  String TB_DIACHI_IDNGUOIDANGNHAP="IDNGUOIDANGNHAP";

    public DataDiaChi(Context context) {
        super(context,"Quản lý địa chỉ", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " CREATE TABLE "+ TB_DIACHI + " ( " +
                TB_DIACHI_MADIACHI + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " + TB_DIACHI_TENNGUOIGUI + " TEXT, " + TB_DIACHI_SODT + " TEXT, " + TB_DIACHI_TINH + " TEXT, "
                + TB_DIACHI_HUYEN + " TEXT, " + TB_DIACHI_PHUONG + " TEXT, "
                + TB_DIACHI_DIACHICUTHE + " TEXT, " + TB_DIACHI_MACDINH + " INTEGER DEFAULT 0, " + TB_DIACHI_IDNGUOIDANGNHAP + " TEXT );";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = " DROP TABLE IF EXISTS " + TB_DIACHI;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
