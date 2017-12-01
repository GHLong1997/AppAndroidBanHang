package com.hangoclong.shop1.View.ThanhToan.VanChuyen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hangoclong.shop1.Adapter.CustomRecyclerGioHang;
import com.hangoclong.shop1.Adapter.CustomReyclerVanChuyen;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.Presenter.GioHang.PresenterLogicGioHang;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.GioHang.ViewGioHang;
import com.hangoclong.shop1.View.ThanhToan.ThanhToan.ThanhToanActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class VanChuyenActivity extends AppCompatActivity implements ViewGioHang{
    Toolbar toolbar;
    RecyclerView reyclerVanChuyen;
    TextView txtTongTien;
    PresenterLogicGioHang presenterLogicGioHang;
    List<SanPham> sanPhamList = new ArrayList<>();
    Button btnTiepTheo;
    public static int tongTien =0 ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_van_chuyen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        reyclerVanChuyen = (RecyclerView) findViewById(R.id.recyclerVanChuyen);
        txtTongTien = (TextView) findViewById(R.id.txtTongTien);
        btnTiepTheo = (Button) findViewById(R.id.btnTiepTheo);
        toolbar.setTitle("Vận chuyển");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);
        btnTiepTheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VanChuyenActivity.this, ThanhToanActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() ==  android.R.id.home ){
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
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {


        if(sanPhamList.size()!=CustomRecyclerGioHang.DANHSACHCHECKED.size()) {
            for (int i = 0; i < CustomRecyclerGioHang.DANHSACHCHECKED.size(); i++) {
               this.sanPhamList.add(sanPhamList.get(CustomRecyclerGioHang.DANHSACHCHECKED.get(i)));
                //vd CustomRecyclerGioHang.DANHSACHCHECKED.get(i) == 2 thi thằng this.sanphamList sẽ add object có vitri 2
            }
        }else {
            this.sanPhamList = sanPhamList;
        }

        for(int i = 0 ; i < this.sanPhamList.size();i++){
           tongTien += (this.sanPhamList.get(i).getSoluong()*this.sanPhamList.get(i).getGia());
        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String underline = "<u>đ</u>";
        String gia = numberFormat.format(tongTien);
        txtTongTien.setText(Html.fromHtml(String.format(underline)) + " " + gia);
        CustomReyclerVanChuyen customReyclerVanChuyen = new CustomReyclerVanChuyen(this,this.sanPhamList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        reyclerVanChuyen.setLayoutManager(layoutManager);
        reyclerVanChuyen.setAdapter(customReyclerVanChuyen);
        customReyclerVanChuyen.notifyDataSetChanged();

    }
}
