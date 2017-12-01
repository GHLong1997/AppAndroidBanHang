package com.hangoclong.shop1.Presenter.ThanhToan.ThanhToan;

import android.content.Context;

import com.hangoclong.shop1.Model.ObjectClass.HoaDon;

/**
 * Created by Admin on 11/26/2017.
 */

public interface IPresenterThanhToan {
    void  ThemHoaDon(Context context,HoaDon hoaDon);
    void  LayDanhSachSanPhamTrongGiohang(Context context);
}
