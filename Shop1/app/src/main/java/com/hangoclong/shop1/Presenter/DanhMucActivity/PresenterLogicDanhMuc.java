package com.hangoclong.shop1.Presenter.DanhMucActivity;

import com.hangoclong.shop1.Model.DanhMucActivity.ModelDanhMuc;
import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.View.DanhMucActivity.ViewDanhMuc;
import com.hangoclong.shop1.View.ViewMua;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/30/2017.
 */

public class PresenterLogicDanhMuc implements IPresenterDanhMuc {

    ViewMua viewMua;
    ViewDanhMuc viewDanhMuc;
    ModelDanhMuc modelDanhMuc;
    public PresenterLogicDanhMuc(ViewMua viewMua, ViewDanhMuc viewDanhMuc){
        this.viewMua = viewMua;
        this.viewDanhMuc = viewDanhMuc;
        modelDanhMuc = new ModelDanhMuc();
    }
    @Override
    public void LayDanhSachLoaiSP(int maloaisp) {
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        loaiSanPhamList = modelDanhMuc.LayDanhSachLoaiSanPhamCon("LayDanhSachLoaiSPCon","DANHSACHLOAISANPHAMCON",maloaisp);
        if(loaiSanPhamList.size()>=1){
            viewMua.LayDanhSachLoaiSPThanhCong(loaiSanPhamList);
        }else{
            viewMua.LayDanhSachLoaiSPThatBai();
        }
    }

    @Override
    public void LayKhoangGiaTienTopDienThoai() {
        List<SanPham> sanPhams = modelDanhMuc.LayKhoangGiaTienTopDienThoaiBanChay("LayKhoangGiaTienTopSanPhamBanChay","KHOANGGIATIENTOPSANPHAMBANCHAY");
        if(sanPhams.size()>=1){
            viewDanhMuc.HienThiKhoangGiaTienTopDienThoaiBanChay(sanPhams);
        }
    }
}
