package com.hangoclong.shop1.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hangoclong.shop1.Model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/16/2017.
 */

public class ModelGioHang {
    SQLiteDatabase sqLiteDatabase;



    public void  MoKetNoi(Context context){
        DataSanPham dataSanPham = new DataSanPham(context);
        sqLiteDatabase =  dataSanPham.getWritableDatabase();

    }

    public int ThemGioHang(SanPham sanPham){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_MASP,sanPham.getMaSP());
        contentValues.put(DataSanPham.TB_GIOHANG_TENSP,sanPham.getTenSP());
        contentValues.put(DataSanPham.TB_GIOHANG_GIATIEN,sanPham.getGia());
        contentValues.put(DataSanPham.TB_GIOHANG_HINHANH,sanPham.getHinhgiohang());
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG,sanPham.getSoluong());
        contentValues.put(DataSanPham.TB_GIOHANG_MASHOP,sanPham.getMaShop());
        contentValues.put(DataSanPham.TB_GIOHANG_TENSHOP,sanPham.getTenShop());
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONGTONKHO,sanPham.getSoLuongTonKho());
        long id = sqLiteDatabase.insert(DataSanPham.TB_GIOHANG,null,contentValues);
        if(id > 0){
            return  1;
        }else {

            String query = "SELECT * FROM " + DataSanPham.TB_GIOHANG +" WHERE " + DataSanPham.TB_GIOHANG_MASP + " = " + sanPham.getMaSP();
            Cursor cursor = sqLiteDatabase.rawQuery(query,null);
            cursor.moveToFirst();
            int soluong=0;
            while (!cursor.isAfterLast()){
                soluong = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONG));
                cursor.moveToNext();
            }
            soluong+=1;
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put(DataSanPham.TB_GIOHANG_SOLUONG,soluong);
            sqLiteDatabase.update(DataSanPham.TB_GIOHANG, contentValues1,DataSanPham.TB_GIOHANG_MASP + "=" + sanPham.getMaSP() , null);
            return  0;
        }

    }

    public List<SanPham> LayDanhSachSanPhamTrongGiohang(){
        List<SanPham>  sanPhamList = new ArrayList<>();

        String truyvan = "SELECT *  FROM " + DataSanPham.TB_GIOHANG;
        Cursor cursor = sqLiteDatabase.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int masp  = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_MASP));
            String  tensp  = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_TENSP));
            int giatien  = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_GIATIEN));
            byte[] hinhanh  = cursor.getBlob(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_HINHANH));
            int soluong = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONG));
            int soluongtonkho = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONGTONKHO));
            int mashop  = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_MASHOP));
            String  tenshop  = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_TENSHOP));

            SanPham sanPham = new SanPham();
            sanPham.setMaSP(masp);
            sanPham.setTenSP(tensp);
            sanPham.setGia(giatien);
            sanPham.setHinhgiohang(hinhanh);
            sanPham.setSoluong(soluong);
            sanPham.setSoLuongTonKho(soluongtonkho);
            sanPham.setMaShop(mashop);
            sanPham.setTenShop(tenshop);

            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }

        return  sanPhamList;
    }

    public  int  CapNhatSoLuongSanPhamTrongGioHang(int masp, int soluong){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG,soluong);

        int id =sqLiteDatabase.update(DataSanPham.TB_GIOHANG,contentValues,DataSanPham.TB_GIOHANG_MASP +" = " +masp,null );
        if(id > 0){
            return  1;
        }else {
            return  0;
        }
    }

    public  int XoaSanPhamTrongGioHang(int masp){
        int id = sqLiteDatabase.delete(DataSanPham.TB_GIOHANG,DataSanPham.TB_GIOHANG_MASP + " = " + masp,null);
        if(id > 0 ){
            return  1;
        }
        return  0;
    }









}
