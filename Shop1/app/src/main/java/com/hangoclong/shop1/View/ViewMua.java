package com.hangoclong.shop1.View;

import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.Model.ObjectClass.ThuongHieu;

import java.util.List;

/**
 * Created by Admin on 10/30/2017.
 */

public interface ViewMua {
    void  LayDanhSachLoaiSPThanhCong(List<LoaiSanPham> loaiSanPhamList);
    void  LayDanhSachLoaiSPThatBai();
    void  LayDanhSachThuongHieuThanhCong(List<ThuongHieu> thuongHieuList);
    void  LayDanhSachThuongHieuThatBai();
    void  HienThiDanhSachSanPhamPhoBien(List<SanPham> sanPhamList);
    void  HienThiDanhSachSanPhamMoiVe(List<SanPham> sanPhamList);



}
