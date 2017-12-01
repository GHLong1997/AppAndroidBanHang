package com.hangoclong.shop1.Model.ThanhToan.DiaChi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hangoclong.shop1.Model.ObjectClass.DiaChi;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/25/2017.
 */

public class ModelDiaChi {
    DataDiaChi dataDiaChi;
    SQLiteDatabase sqLiteDatabase;


    public void  MoKetNoi(Context context){
        dataDiaChi = new DataDiaChi(context);
        sqLiteDatabase = dataDiaChi.getWritableDatabase();
    }

    public  int   ThemDiaChi(DiaChi diaChi){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataDiaChi.TB_DIACHI_TENNGUOIGUI,diaChi.getTenNguoiGui());
        contentValues.put(DataDiaChi.TB_DIACHI_SODT,diaChi.getSoDienThoai());
        contentValues.put(DataDiaChi.TB_DIACHI_TINH,diaChi.getTinh());
        contentValues.put(DataDiaChi.TB_DIACHI_HUYEN,diaChi.getQuan());
        contentValues.put(DataDiaChi.TB_DIACHI_PHUONG,diaChi.getPhuong());
        contentValues.put(DataDiaChi.TB_DIACHI_MACDINH,diaChi.getMacDinh());
        contentValues.put(DataDiaChi.TB_DIACHI_DIACHICUTHE,diaChi.getDiaChiCuThe());
        contentValues.put(DataDiaChi.TB_DIACHI_IDNGUOIDANGNHAP, TrangChuActivity.HINHANH);

        long id = sqLiteDatabase.insert(DataDiaChi.TB_DIACHI,null,contentValues);
        if(id>0){
            return 1;
        }
        return  0;
    }

    public List<DiaChi> LayDanhSachDiaChiTheoIDDangNhap(String id){
        List<DiaChi> diaChis = new ArrayList<>();
        String sql = " SELECT * FROM "+ DataDiaChi.TB_DIACHI + " WHERE " + DataDiaChi.TB_DIACHI_IDNGUOIDANGNHAP + " like " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DiaChi diaChi = new DiaChi();
            diaChi.setMaDiaChi(cursor.getInt(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_MADIACHI)));
            diaChi.setTenNguoiGui(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_TENNGUOIGUI)));
            diaChi.setSoDienThoai(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_SODT)));
            diaChi.setTinh(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_TINH)));
            diaChi.setQuan(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_HUYEN)));
            diaChi.setPhuong(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_PHUONG)));
            diaChi.setDiaChiCuThe(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_DIACHICUTHE)));
            diaChi.setMacDinh(cursor.getInt(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_MACDINH)));
            diaChis.add(diaChi);
            cursor.moveToNext();
        }
        return  diaChis;
    }

    public void DeleteDiaChi(){

        sqLiteDatabase.delete(DataDiaChi.TB_DIACHI,null,null);
    }

    public  int CapNhapDiaChiTheoIDNguoiDangNhap(String idNguoiDangNhap,int idDiaChi,DiaChi diaChi){
        Log.d("iiiiiii",idDiaChi+"");
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataDiaChi.TB_DIACHI_TENNGUOIGUI,diaChi.getTenNguoiGui());
        contentValues.put(DataDiaChi.TB_DIACHI_SODT,diaChi.getSoDienThoai());
        contentValues.put(DataDiaChi.TB_DIACHI_TINH,diaChi.getTinh());
        contentValues.put(DataDiaChi.TB_DIACHI_HUYEN,diaChi.getQuan());
        contentValues.put(DataDiaChi.TB_DIACHI_PHUONG,diaChi.getPhuong());
        contentValues.put(DataDiaChi.TB_DIACHI_MACDINH,diaChi.getMacDinh());
        contentValues.put(DataDiaChi.TB_DIACHI_DIACHICUTHE,diaChi.getDiaChiCuThe());

        long i =  sqLiteDatabase.update(DataDiaChi.TB_DIACHI,contentValues,DataDiaChi.TB_DIACHI_IDNGUOIDANGNHAP
                + " like " + idNguoiDangNhap + " AND "+ DataDiaChi.TB_DIACHI_MADIACHI + " = " + idDiaChi ,null);
        if(i>0){
            return  1;
        }
        return  0;
    }

    public  int CapNhatDiaChiMatDinhTheoIDNguoiDangNhap(String idNguoiDangNhap,int idDiaChi,int macdinh){
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(DataDiaChi.TB_DIACHI_MACDINH,0);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataDiaChi.TB_DIACHI_MACDINH,macdinh);

        //cho tất cả thăng đều = 0;
        sqLiteDatabase.update(DataDiaChi.TB_DIACHI,contentValues1,DataDiaChi.TB_DIACHI_IDNGUOIDANGNHAP + " like " + idNguoiDangNhap ,null);
        //chỉ có 1 thằng ==1 , tức là mặc định
       long i =  sqLiteDatabase.update(DataDiaChi.TB_DIACHI,contentValues,DataDiaChi.TB_DIACHI_IDNGUOIDANGNHAP
               + " like " + idNguoiDangNhap + " AND "+ DataDiaChi.TB_DIACHI_MADIACHI + " = " + idDiaChi ,null);
        if(i>0){
            return  1;
        }
        return  0;
    }

    public  int XoaDiaChiTheoIDNguoiDangNhap(String idNguoiDangNhap,int maDiaChi){
       long i = sqLiteDatabase.delete(DataDiaChi.TB_DIACHI,DataDiaChi.TB_DIACHI_IDNGUOIDANGNHAP
                + " like " + idNguoiDangNhap + " AND "+ DataDiaChi.TB_DIACHI_MADIACHI + " = " + maDiaChi,null);
        if(i>0){
            return  1;
        }
        return  0;
    }

    public  DiaChi LayDiaChiMacDinhTheoIDNguoiDangNhap(String idNguoiDangNhap){
        String query = "SELECT * FROM "+ DataDiaChi.TB_DIACHI +" WHERE " + DataDiaChi.TB_DIACHI_MACDINH + " = 1 AND " + DataDiaChi.TB_DIACHI_IDNGUOIDANGNHAP + " = " + TrangChuActivity.HINHANH;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();
        DiaChi diaChi = new DiaChi();
        diaChi.setMaDiaChi(cursor.getInt(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_MADIACHI)));
        diaChi.setTenNguoiGui(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_TENNGUOIGUI)));
        diaChi.setSoDienThoai(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_SODT)));
        diaChi.setTinh(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_TINH)));
        diaChi.setQuan(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_HUYEN)));
        diaChi.setPhuong(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_PHUONG)));
        diaChi.setDiaChiCuThe(cursor.getString(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_DIACHICUTHE)));
        diaChi.setMacDinh(cursor.getInt(cursor.getColumnIndex(DataDiaChi.TB_DIACHI_MACDINH)));
        return  diaChi;

    }


}
