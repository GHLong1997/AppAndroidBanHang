package com.hangoclong.shop1.View.TrangChu_Mua;

import com.hangoclong.shop1.Model.ObjectClass.KhuyenMai;

import java.util.List;

/**
 * Created by Admin on 11/1/2017.
 */

public interface ViewKhuyenMai {
    void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList);
    void HienThiSliderKhuyenMai(List<KhuyenMai> khuyenMaiList);
}
