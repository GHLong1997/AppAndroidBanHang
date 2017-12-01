package com.hangoclong.shop1.View.ThanhToan.ThanhToan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hangoclong.shop1.Adapter.CustomRecyclerGioHang;
import com.hangoclong.shop1.Model.ObjectClass.ChiTietHoaDon;
import com.hangoclong.shop1.Model.ObjectClass.HoaDon;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.Presenter.ThanhToan.ThanhToan.PresenterLogicThanhToan;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.ThanhToan.VanChuyen.VanChuyenActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements  ViewThanhToan,View.OnClickListener{
    Toolbar toolbar;
    Button btnThanhToan;
    TextView txtTongTien;
    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDonList;
    List<SanPham> sanPhamList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        txtTongTien = (TextView) findViewById(R.id.txtTongTien);
        toolbar.setTitle("Lựa chọn thanh toán");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnThanhToan.setOnClickListener(this);

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String underline = "<u>đ</u>";
        String gia = numberFormat.format(VanChuyenActivity.tongTien);
        txtTongTien.setText(Html.fromHtml(String.format(underline)) + " " +gia);
        presenterLogicThanhToan = new PresenterLogicThanhToan(this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGiohang(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            onBackPressed();
            overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this,"Đặt hàng thành công",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ThanhToanActivity.this,XacNhanActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this,"Đặt hàng thất bại",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LayDanhSachSanPhamTrongGiohang(List<SanPham> sanPhamList) {
        if(sanPhamList.size()!= CustomRecyclerGioHang.DANHSACHCHECKED.size()) {
            for (int i = 0; i < CustomRecyclerGioHang.DANHSACHCHECKED.size(); i++) {
                this.sanPhamList.add(sanPhamList.get(CustomRecyclerGioHang.DANHSACHCHECKED.get(i)));
                //vd CustomRecyclerGioHang.DANHSACHCHECKED.get(i) == 2 thi thằng this.sanphamList sẽ add object có vitri 2
            }
        }else {
            this.sanPhamList = sanPhamList;
        }
        chiTietHoaDonList = new ArrayList<>();

        for(int i = 0 ; i< this.sanPhamList.size();i++){
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMaSP(this.sanPhamList.get(i).getMaSP());
            chiTietHoaDon.setSoLuong(this.sanPhamList.get(i).getSoluong());
            chiTietHoaDonList.add(chiTietHoaDon);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btnThanhToan:
                HoaDon hoaDon = new HoaDon();
                hoaDon.setChiTietHoaDonList(chiTietHoaDonList);
                presenterLogicThanhToan.ThemHoaDon(this,hoaDon);
        }
    }
}
