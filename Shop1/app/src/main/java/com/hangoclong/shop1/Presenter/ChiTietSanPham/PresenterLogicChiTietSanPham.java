package com.hangoclong.shop1.Presenter.ChiTietSanPham;

import android.content.Context;
import android.util.Log;

import com.hangoclong.shop1.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.hangoclong.shop1.Model.GioHang.ModelGioHang;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

/**
 * Created by Admin on 11/9/2017.
 */

public class PresenterLogicChiTietSanPham implements  IPresenterChiTietSanPham{
    ModelChiTietSanPham modelChiTietSanPham;
    ViewChiTietSanPham viewChiTietSanPham;
    ModelGioHang modelGioHang;
    public  PresenterLogicChiTietSanPham(){
        modelGioHang = new ModelGioHang();
    }
    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham){
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSanPham("LayChiTietSanPham","LAYCHITIETSANPHAM",masp);

        if(sanPham.getMaSP()>0){
            Log.d("talamasp",sanPham.getMaSP()+"");
            viewChiTietSanPham.HienThiSliderChiTietSanPham(sanPham);
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);

        }
    }



    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        int kiemtra = modelGioHang.ThemGioHang(sanPham);
        if(kiemtra==1){
            viewChiTietSanPham.ThemGioHangThanhCong();
        }else {

            viewChiTietSanPham.ThemGioHangThatBai();
        }
    }

    public  int DemSanPhamCoTrongGioHang(Context context){
        modelGioHang.MoKetNoi(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGiohang();
        int soluong = 0;
        if(sanPhamList.size() > 0){
            for(int i = 0 ; i < sanPhamList.size(); i++){
                soluong += sanPhamList.get(i).getSoluong();
            }
            return  soluong;
        }
        return  0;
    }


}
