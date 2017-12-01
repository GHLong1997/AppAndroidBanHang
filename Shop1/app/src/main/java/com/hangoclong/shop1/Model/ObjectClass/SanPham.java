package com.hangoclong.shop1.Model.ObjectClass;

import java.util.List;

/**
 * Created by Admin on 11/1/2017.
 */

public class SanPham {
    int MaSP,Gia,Soluong,MaThuonghieu,MaNV,LuotMua,MaShop;
    String AnhLon,AnhNho,ThongTin,TenSP,TenShop;
    ChiTietKhuyenMai chiTietKhuyenMai;
    List<ChiTietSanPham> chiTietSanPhams;
    List<DanhGia> danhGias;
    byte[] hinhgiohang ;
    int SoLuongTonKho;
    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public int getMaThuonghieu() {
        return MaThuonghieu;
    }

    public void setMaThuonghieu(int maThuonghieu) {
        MaThuonghieu = maThuonghieu;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public int getLuotMua() {
        return LuotMua;
    }

    public void setLuotMua(int luotMua) {
        LuotMua = luotMua;
    }

    public String getAnhLon() {
        return AnhLon;
    }

    public void setAnhLon(String anhLon) {
        AnhLon = anhLon;
    }

    public String getAnhNho() {
        return AnhNho;
    }

    public void setAnhNho(String anhNho) {
        AnhNho = anhNho;
    }

    public String getThongTin() {
        return ThongTin;
    }

    public void setThongTin(String thongTin) {
        ThongTin = thongTin;
    }

    public ChiTietKhuyenMai getChiTietKhuyenMai() {
        return chiTietKhuyenMai;
    }

    public void setChiTietKhuyenMai(ChiTietKhuyenMai chiTietKhuyenMai) {
        this.chiTietKhuyenMai = chiTietKhuyenMai;
    }

    public int getMaShop() {
        return MaShop;
    }

    public void setMaShop(int maShop) {
        MaShop = maShop;
    }

    public String getTenShop() {
        return TenShop;
    }

    public void setTenShop(String tenShop) {
        TenShop = tenShop;
    }

    public List<ChiTietSanPham> getChiTietSanPhams() {
        return chiTietSanPhams;
    }

    public void setChiTietSanPhams(List<ChiTietSanPham> chiTietSanPhams) {
        this.chiTietSanPhams = chiTietSanPhams;
    }

    public List<DanhGia> getDanhGias() {
        return danhGias;
    }

    public void setDanhGias(List<DanhGia> danhGias) {
        this.danhGias = danhGias;
    }

    public byte[] getHinhgiohang() {
        return hinhgiohang;
    }

    public void setHinhgiohang(byte[] hinhgiohang) {
        this.hinhgiohang = hinhgiohang;
    }

    public int getSoLuongTonKho() {
        return SoLuongTonKho;
    }

    public void setSoLuongTonKho(int soLuongTonKho) {
        SoLuongTonKho = soLuongTonKho;
    }
}
