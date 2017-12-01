package com.hangoclong.shop1.View.DanhGia;

import com.hangoclong.shop1.Model.ObjectClass.DanhGia;

import java.util.List;

/**
 * Created by Admin on 11/11/2017.
 */

public interface ViewDanhGia {
    void  DanhGiaThanhCong();
    void  DanhGiaThatBai();
    void HienThiDanhSachDanhGia(List<DanhGia> danhGias);
    void HienThiTongDanhGia(List<DanhGia> danhGiaList);
}
