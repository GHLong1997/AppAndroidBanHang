package com.hangoclong.shop1.Model.ObjectClass;

import java.util.List;

/**
 * Created by Admin on 11/1/2017.
 */

public class KhuyenMai {
    int MaKM,MaLoaiSP;
    String TenKM,NgayBatDau,NgayKetThuc,HinhKhuyenMai,TenLoaiSP;
    List<SanPham> DanhSachSanPhamKhuyenMai;

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int maKM) {
        MaKM = maKM;
    }

    public int getMaLoaiSP() {
        return MaLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        MaLoaiSP = maLoaiSP;
    }

    public String getHinhKhuyenMai() {
        return HinhKhuyenMai;
    }

    public void setHinhKhuyenMai(String hinhKhuyenMai) {
        HinhKhuyenMai = hinhKhuyenMai;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String tenKM) {
        TenKM = tenKM;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public List<SanPham> getDanhSachSanPhamKhuyenMai() {
        return DanhSachSanPhamKhuyenMai;
    }

    public void setDanhSachSanPhamKhuyenMai(List<SanPham> danhSachSanPhamKhuyenMai) {
        DanhSachSanPhamKhuyenMai = danhSachSanPhamKhuyenMai;
    }

    public String getTenLoaiSP() {
        return TenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        TenLoaiSP = tenLoaiSP;
    }
}
