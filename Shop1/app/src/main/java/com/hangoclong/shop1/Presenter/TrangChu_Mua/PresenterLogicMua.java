package com.hangoclong.shop1.Presenter.TrangChu_Mua;

import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.Model.ObjectClass.ThuongHieu;
import com.hangoclong.shop1.Model.TrangChu_Mua.ModelMua;
import com.hangoclong.shop1.View.ViewMua;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/30/2017.
 */

public class PresenterLogicMua implements  IPresenterMua {
    ViewMua viewMua;
    ModelMua modelMua;
    public  PresenterLogicMua(ViewMua viewMua){
        this.viewMua = viewMua;
        modelMua = new ModelMua();
    }
    @Override
    public void LayDanhSachLoaiSP() {
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        loaiSanPhamList = modelMua.LayDanhSachLoaiSanPhamCha("LayDanhSachLoaiSPCha","DANHSACHLOAISANPHAMCHA");
        if(loaiSanPhamList.size()>=1){
            viewMua.LayDanhSachLoaiSPThanhCong(loaiSanPhamList);
        }else{
            viewMua.LayDanhSachLoaiSPThatBai();
        }
    }

    @Override
    public void LayDanhSachThuongHieu() {
        List<ThuongHieu> thuongHieuList  = modelMua.LayDanhSachThuongHieu("LayDanhSachThuongHieu","DANHSACHTHUONGHIEU");
        if(thuongHieuList.size()>=1){
            viewMua.LayDanhSachThuongHieuThanhCong(thuongHieuList);
        }else {
            viewMua.LayDanhSachThuongHieuThatBai();
        }
    }

    @Override
    public void LayDanhSachKhuyenMai() {

    }

    @Override
    public void LayDanhSachSanPhamPhoBien() {
        List<SanPham> sanPhamList = modelMua.LayDanhSachSanPhamPhoBien("LayDanhSachSanPhamPhoBien","DANHSACHSANPHAMPHOBIEN");
        if(sanPhamList.size()>=1){
            viewMua.HienThiDanhSachSanPhamPhoBien(sanPhamList);
        }
    }

    @Override
    public void LayDanhSachSanPhamMoiVe() {
        List<SanPham> sanPhamList = modelMua.LayDanhSachSanPhamPhoBien("LayDanhSachSanPhamMoiVe","DANHSACHSANPHAMMOIVE");
        if(sanPhamList.size()>=1){
            viewMua.HienThiDanhSachSanPhamMoiVe(sanPhamList);
        }
    }

}
