package com.hangoclong.shop1.View.ThanhToan.ThanhToan;

import com.hangoclong.shop1.Model.ObjectClass.SanPham;

import java.util.List;

/**
 * Created by Admin on 10/8/2017.
 */

public interface ViewThanhToan {
    void  DatHangThanhCong();
    void DatHangThatBai();
    void  LayDanhSachSanPhamTrongGiohang(List<SanPham> sanPhamList);
}
