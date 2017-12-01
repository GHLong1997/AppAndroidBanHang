package com.hangoclong.shop1.Presenter.DangNhap;

import com.hangoclong.shop1.Model.DangNhap.ModelDangKy;
import com.hangoclong.shop1.Model.ObjectClass.NhanVien;
import com.hangoclong.shop1.View.DangNhap.ViewDangKy;

/**
 * Created by Admin on 10/25/2017.
 */

public class PresenterLogicDangKy implements  IPresenterDangKy {
    ModelDangKy modelDangKy;
    ViewDangKy viewDangKy;
    public  PresenterLogicDangKy(ViewDangKy viewDangKy){

        modelDangKy = new ModelDangKy();
        this.viewDangKy = viewDangKy;
    }
    @Override
    public void DangKyTaiKhoan(NhanVien nhanVien) {
        boolean kiemtra = modelDangKy.DangKyTaiKhoan(nhanVien);
        if(kiemtra){
            viewDangKy.DangKyThanhCong();
        }else {
            viewDangKy.DangKyThatBai();
        }
    }
}
