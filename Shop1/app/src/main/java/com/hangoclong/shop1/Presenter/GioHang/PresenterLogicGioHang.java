package com.hangoclong.shop1.Presenter.GioHang;

import android.content.Context;
import android.util.Log;

import com.hangoclong.shop1.Model.GioHang.ModelGioHang;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.View.GioHang.ViewGioHang;

import java.util.List;

/**
 * Created by Admin on 11/16/2017.
 */

public class PresenterLogicGioHang implements  IPresenterGioHang {
    ViewGioHang viewGioHang;
    ModelGioHang modelGioHang;
    public  PresenterLogicGioHang(ViewGioHang viewGioHang){
        this.viewGioHang = viewGioHang;
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoi(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGiohang();
        Log.d("sizzz",sanPhamList.size()+"");
        if(sanPhamList.size()>0){
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }
    }
}
