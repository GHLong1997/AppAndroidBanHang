package com.hangoclong.shop1.Presenter.ThanhToan.DiaChi;

import android.content.Context;

import com.hangoclong.shop1.Model.ObjectClass.DiaChi;

/**
 * Created by Admin on 11/25/2017.
 */

public interface IPresenterDiaChi {
    void  ThemDiaChi(Context context,DiaChi diaChi);
    void LayDanhSachDiaChi();
    void CapNhatDiaChi(String idNguoiDangNhap,DiaChi diaChi);
    void XoaDiaChi(String idNguoiDangNhap,int maDiaChi);
}
