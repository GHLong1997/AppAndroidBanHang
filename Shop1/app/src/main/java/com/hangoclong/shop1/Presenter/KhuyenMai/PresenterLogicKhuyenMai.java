package com.hangoclong.shop1.Presenter.KhuyenMai;

import com.hangoclong.shop1.Model.KhuyenMai.ModelKhuyenMai;
import com.hangoclong.shop1.Model.ObjectClass.KhuyenMai;
import com.hangoclong.shop1.View.TrangChu_Mua.ViewKhuyenMai;

import java.util.List;

/**
 * Created by Admin on 11/1/2017.
 */

public class PresenterLogicKhuyenMai implements  IPresenterKhuyenMai {
    ModelKhuyenMai modelKhuyenMai;
    ViewKhuyenMai viewKhuyenMai;
    public  PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai){
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai = new ModelKhuyenMai();
    }

    @Override
    public void LayDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList = modelKhuyenMai.LayDanhSachKhuyenMai("LayDanhSachKhuyenMai","DANHSACHKHUYENMAI");

        if(khuyenMaiList.size()>=1){
            viewKhuyenMai.HienThiSliderKhuyenMai(khuyenMaiList);
            viewKhuyenMai.HienThiDanhSachKhuyenMai(khuyenMaiList);

        }
    }
}
