package com.hangoclong.shop1.Presenter.DanhMucConActivity;

import android.content.Context;
import android.content.SharedPreferences;

import com.hangoclong.shop1.Model.DanhMucActivity.ModelDanhMuc;
import com.hangoclong.shop1.Model.DanhMucConActivity.ModelDanhMucCon;
import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
import com.hangoclong.shop1.View.DanhMucConActivity.ViewDanhMucCon;

import java.util.List;

/**
 * Created by Admin on 11/6/2017.
 */

public class PresenterLogicDanhMucCon implements IPresenterDanhMucCon {
    ModelDanhMuc modelDanhMuc;
    ModelDanhMucCon modelDanhMucCon;
    ViewDanhMucCon viewDanhMucCon;
    public PresenterLogicDanhMucCon(ViewDanhMucCon viewDanhMucCon){
        this.viewDanhMucCon = viewDanhMucCon;
        modelDanhMuc = new ModelDanhMuc();
        modelDanhMucCon = new ModelDanhMucCon();
    }

    @Override
    public void LayDanhSachLoaiSPCon(int maloaisp) {

        List<LoaiSanPham>  loaiSanPhamList = modelDanhMuc.LayDanhSachLoaiSanPhamCon("LayDanhSachLoaiSPCon","DANHSACHLOAISANPHAMCON",maloaisp);
        if(loaiSanPhamList.size()>=1){
            viewDanhMucCon.HienThiDanhMucCon(loaiSanPhamList);
        }
    }


}
