package com.hangoclong.shop1.View.DanhMucConActivity.DanhMucCon_PhoBien;

import com.hangoclong.shop1.Model.ObjectClass.SanPham;

import java.util.List;

/**
 * Created by Admin on 11/6/2017.
 */

public interface ViewDanhMucConPhoBien {
    void  HienThiDanhSachTopDienThoaiPhoBien(List<SanPham> sanPhamList);
    void HienThiSanPhamTheoDanhMucDienThoai(List<SanPham> sanPhamList);
}
