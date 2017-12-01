package com.hangoclong.shop1.Model.ObjectClass;

import java.io.Serializable;

/**
 * Created by Admin on 11/25/2017.
 */

public class DiaChi implements Serializable{
    int MaDiaChi,MacDinh;
    String TenNguoiGui,SoDienThoai,Tinh,Quan,Phuong,DiaChiCuThe;

    public int getMaDiaChi() {
        return MaDiaChi;
    }

    public void setMaDiaChi(int maDiaChi) {
        MaDiaChi = maDiaChi;
    }

    public int getMacDinh() {
        return MacDinh;
    }

    public void setMacDinh(int macDinh) {
        MacDinh = macDinh;
    }

    public String getTenNguoiGui() {
        return TenNguoiGui;
    }

    public void setTenNguoiGui(String tenNguoiGui) {
        TenNguoiGui = tenNguoiGui;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getTinh() {
        return Tinh;
    }

    public void setTinh(String tinh) {
        Tinh = tinh;
    }

    public String getQuan() {
        return Quan;
    }

    public void setQuan(String quan) {
        Quan = quan;
    }

    public String getPhuong() {
        return Phuong;
    }

    public void setPhuong(String phuong) {
        Phuong = phuong;
    }

    public String getDiaChiCuThe() {
        return DiaChiCuThe;
    }

    public void setDiaChiCuThe(String diaChiCuThe) {
        DiaChiCuThe = diaChiCuThe;
    }
}
