package com.hangoclong.shop1.Presenter.ChiTietSanPham;

import android.content.Context;

import com.hangoclong.shop1.Model.ObjectClass.SanPham;

/**
 * Created by Admin on 11/9/2017.
 */

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void ThemGioHang(SanPham sanPham, Context context);
}
