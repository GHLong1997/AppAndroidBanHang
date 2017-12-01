package com.hangoclong.shop1.Presenter.DangNhap;

import android.content.Context;

import com.facebook.AccessToken;
import com.hangoclong.shop1.Model.DangNhap.ModelDangNhap;
import com.hangoclong.shop1.Model.ObjectClass.NhanVien;
import com.hangoclong.shop1.View.DangNhap.ViewDangNhap;

/**
 * Created by Admin on 10/20/2017.
 */

public class PresenterLogicDangNhap implements  IPresenterDangNhap {

    ModelDangNhap modelDangNhap;
    Context context;
    ViewDangNhap viewDangNhap;
    public  PresenterLogicDangNhap( Context context,ViewDangNhap viewDangNhap){
        modelDangNhap = new ModelDangNhap();
        this.context = context;
        this.viewDangNhap = viewDangNhap;
    }
    @Override
    public AccessToken LayTokenFacebookHienTai() {

        AccessToken accessToken = modelDangNhap.LayTokenFacebookHienTai();
        return  accessToken;
    }

    @Override
    public void DangNhapTaiKhoan(NhanVien nhanVien) {
        boolean kiemtra = modelDangNhap.DangNhapTaiKhoan(context,nhanVien.getTenDN(),nhanVien.getMatKhau(),nhanVien.getEmail(),nhanVien.getSodt());
        if(kiemtra){
           viewDangNhap.DangNhapThanhCong();
        }else {
            viewDangNhap.DangNhapThatBai();
        }
    }


}
