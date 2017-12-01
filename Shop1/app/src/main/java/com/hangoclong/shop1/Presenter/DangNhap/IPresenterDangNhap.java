package com.hangoclong.shop1.Presenter.DangNhap;

import com.facebook.AccessToken;
import com.hangoclong.shop1.Model.ObjectClass.NhanVien;

/**
 * Created by Admin on 10/20/2017.
 */

public interface IPresenterDangNhap {
    AccessToken LayTokenFacebookHienTai();
    void DangNhapTaiKhoan(NhanVien nhanVien);

}
