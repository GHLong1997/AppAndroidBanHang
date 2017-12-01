package com.hangoclong.shop1.Presenter.DanhGia;

import com.hangoclong.shop1.Model.DanhGia.ModelDanhGia;
import com.hangoclong.shop1.Model.ObjectClass.DanhGia;
import com.hangoclong.shop1.View.DanhGia.ViewDanhGia;

import java.util.List;

/**
 * Created by Admin on 11/11/2017.
 */

public class PresenterLogicDanhGia  implements  IPresenterDanhGia{
    ModelDanhGia modelDanhGia;
    ViewDanhGia viewDanhGia;
    public  PresenterLogicDanhGia(ViewDanhGia viewDanhGia){
        this.viewDanhGia = viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.ThemDanhGia("ThemDanhGia",danhGia);
        if(kiemtra){
            viewDanhGia.DanhGiaThanhCong();
        }else {
            viewDanhGia.DanhGiaThatBai();
        }
    }

    @Override
    public void LayDanhSachDanhDanh(int masp) {

        List<DanhGia> danhGias1 = modelDanhGia.LayDanhSachDanhGia("LayDanhSachDanhGia","DANHSACHDANHGIA",masp);
        if(danhGias1.size()>0){
            viewDanhGia.HienThiDanhSachDanhGia(danhGias1);
            viewDanhGia.HienThiTongDanhGia(danhGias1);
        }
    }
}
