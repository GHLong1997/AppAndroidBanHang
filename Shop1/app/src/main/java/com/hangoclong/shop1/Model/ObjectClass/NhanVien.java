package com.hangoclong.shop1.Model.ObjectClass;

import java.io.Serializable;

/**
 * Created by Admin on 10/25/2017.
 */

public class NhanVien implements Serializable {
    int MaNV,MaLoaiNV;
    String TenDN;
    String MatKhau;
    String Email;
    String Sodt;

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public int getMaLoaiNV() {
        return MaLoaiNV;
    }

    public void setMaLoaiNV(int maLoaiNV) {
        MaLoaiNV = maLoaiNV;
    }

    public String getTenDN() {
        return TenDN;
    }

    public void setTenDN(String tenDN) {
        TenDN = tenDN;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSodt() {
        return Sodt;
    }

    public void setSodt(String sodt) {
        Sodt = sodt;
    }
}
