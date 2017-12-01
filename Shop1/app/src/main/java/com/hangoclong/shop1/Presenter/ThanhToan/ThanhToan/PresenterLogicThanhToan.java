package com.hangoclong.shop1.Presenter.ThanhToan.ThanhToan;

import android.content.Context;

import com.hangoclong.shop1.Model.GioHang.ModelGioHang;
import com.hangoclong.shop1.Model.ObjectClass.DiaChi;
import com.hangoclong.shop1.Model.ObjectClass.HoaDon;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.Model.ThanhToan.DiaChi.ModelDiaChi;
import com.hangoclong.shop1.Model.ThanhToan.ThanhToan.ModelThanhToan;
import com.hangoclong.shop1.View.ThanhToan.ThanhToan.ViewThanhToan;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import java.util.List;

/**
 * Created by Admin on 11/26/2017.
 */

public class PresenterLogicThanhToan implements  IPresenterThanhToan {
    ViewThanhToan viewThanhToan;
    ModelThanhToan modelThanhToan;
    ModelGioHang modelGioHang;
    ModelDiaChi modelDiaChi;
    List<SanPham> sanPhamList;
    public  PresenterLogicThanhToan(ViewThanhToan viewThanhToan){
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
        modelDiaChi = new ModelDiaChi();
    }
    @Override
    public void ThemHoaDon(Context context,HoaDon hoaDon) {
        modelDiaChi.MoKetNoi(context);                                   //HINHANH ở đây chính là ID thằng facebook
        DiaChi diaChi = modelDiaChi.LayDiaChiMacDinhTheoIDNguoiDangNhap(TrangChuActivity.HINHANH);
        hoaDon.setTenNguoiNhan(diaChi.getTenNguoiGui());//diachi.getTennguoinhan , ghi nhầm :v
        hoaDon.setSoDT(diaChi.getSoDienThoai());
        hoaDon.setDiaChi(diaChi.getQuan()+", " + diaChi.getPhuong()+" ," + diaChi.getTinh()+", Địa chỉ cụ thể : " + diaChi.getDiaChiCuThe());
        if(modelThanhToan.ThemHoaDon(hoaDon)){

            viewThanhToan.DatHangThanhCong();
            for(int i = 0 ; i < hoaDon.getChiTietHoaDonList().size();i++){
                modelGioHang.XoaSanPhamTrongGioHang(hoaDon.getChiTietHoaDonList().get(i).getMaSP());//vi thang này nhan vào masp en chúng ta phai duyt tung cái san phâm trong cái list
            }
        }else{
            viewThanhToan.DatHangThatBai();
        }

    }

    @Override
    public void LayDanhSachSanPhamTrongGiohang(Context context) {
        modelGioHang.MoKetNoi(context);
        sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGiohang();
        viewThanhToan.LayDanhSachSanPhamTrongGiohang(sanPhamList);
    }
}
