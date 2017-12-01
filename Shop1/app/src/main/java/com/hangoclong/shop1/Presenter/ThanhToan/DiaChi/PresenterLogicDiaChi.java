package com.hangoclong.shop1.Presenter.ThanhToan.DiaChi;

import android.content.Context;

import com.hangoclong.shop1.Model.ObjectClass.DiaChi;
import com.hangoclong.shop1.Model.ThanhToan.DiaChi.ModelDiaChi;
import com.hangoclong.shop1.View.ThanhToan.DiaChi.ThemDiaChi.ViewThemDiaChi;
import com.hangoclong.shop1.View.ThanhToan.DiaChi.ViewDiaChi;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import java.util.List;

/**
 * Created by Admin on 11/25/2017.
 */

public class PresenterLogicDiaChi implements IPresenterDiaChi {
    ViewDiaChi viewDiaChi;
    ModelDiaChi modelDiaChi;
    ViewThemDiaChi viewThemDiaChi;
    public  PresenterLogicDiaChi(Context context,ViewThemDiaChi viewThemDiaChi){
        modelDiaChi = new ModelDiaChi();
        modelDiaChi.MoKetNoi(context);
        this.viewThemDiaChi = viewThemDiaChi;
    }
    public  PresenterLogicDiaChi(Context context,ViewDiaChi viewDiaChi){
        modelDiaChi = new ModelDiaChi();
        modelDiaChi.MoKetNoi(context);
        this.viewDiaChi = viewDiaChi;
    }
    @Override
    public void ThemDiaChi(Context context,DiaChi diaChi) {
//            modelDiaChi.DeleteDiaChi();
        int i = modelDiaChi.ThemDiaChi(diaChi);
        if(i>0){
            viewThemDiaChi.ThemDiaChiThanhCong();
        }else {
            viewThemDiaChi.ThemDiaChiThatBai();
        }
    }

    @Override
    public void LayDanhSachDiaChi() {
     List<DiaChi> diaChis = modelDiaChi.LayDanhSachDiaChiTheoIDDangNhap(TrangChuActivity.HINHANH);
        if(diaChis.size()>0){
            viewDiaChi.HienThiDanhSachDiaChi(diaChis);
        }
    }

    @Override
    public void CapNhatDiaChi(String idNguoiDangNhap,DiaChi diaChi) {
      int i =   modelDiaChi.CapNhapDiaChiTheoIDNguoiDangNhap(idNguoiDangNhap,diaChi.getMaDiaChi(),diaChi);
        if(i>0){

            viewThemDiaChi.CapNhatThanhCong();
        }
    }

    @Override
    public void XoaDiaChi(String idNguoiDangNhap, int maDiaChi) {
        int i =   modelDiaChi.XoaDiaChiTheoIDNguoiDangNhap(idNguoiDangNhap,maDiaChi);
        if(i>0){

            viewThemDiaChi.XoaDiaChiThanhCong();
        }
    }
}
