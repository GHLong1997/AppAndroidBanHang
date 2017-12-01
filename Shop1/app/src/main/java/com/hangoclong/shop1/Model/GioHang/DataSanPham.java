package com.hangoclong.shop1.Model.GioHang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 11/16/2017.
 */

public class DataSanPham extends SQLiteOpenHelper {
    public  static  String DB_GIOHANG ="QUANLYGIOHANG";
    public  static  String TB_GIOHANG ="GIOHANG";
    public  static  String TB_GIOHANG_MASP ="MASP";
    public  static  String TB_GIOHANG_TENSP = "TENSP";
    public  static  String TB_GIOHANG_GIATIEN ="GIATIEN";
    public  static  String TB_GIOHANG_HINHANH ="HINHANH";
    public  static  String TB_GIOHANG_SOLUONG ="SOLUONG";
    public  static  String TB_GIOHANG_SOLUONGTONKHO ="SOLUONGTONKHO";
    public  static  String TB_GIOHANG_MASHOP ="MASHOP";
    public  static  String TB_GIOHANG_TENSHOP = "TENSHOP";
    public  static  String TB_GIOHANGDACHECK ="GIOHANGDACHECK";
    public DataSanPham(Context context) {
        super(context, DB_GIOHANG, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {    // hinh co sawn been ben roi nen ko can tai ,chon kieu la blob
        String tbGioHang ="CREATE TABLE " +TB_GIOHANG +"( "
                +TB_GIOHANG_MASP+" INTEGER  PRIMARY KEY, "+TB_GIOHANG_TENSP +"  TEXT,"
                +TB_GIOHANG_GIATIEN+"  REAL,HINHANH BLOB,"+ TB_GIOHANG_SOLUONG +" INTEGER ,"+ TB_GIOHANG_SOLUONGTONKHO +
                " INTEGER," + TB_GIOHANG_MASHOP +" INTEGER," + TB_GIOHANG_TENSHOP+" TEXT  );";
        sqLiteDatabase.execSQL(tbGioHang);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql ="DROP TABLE  IF EXISTS GIOHANG";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
}
}
