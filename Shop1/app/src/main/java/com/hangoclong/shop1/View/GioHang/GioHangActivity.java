package com.hangoclong.shop1.View.GioHang;

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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hangoclong.shop1.Adapter.CustomRecyclerGioHang;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.Presenter.GioHang.PresenterLogicGioHang;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.ThanhToan.DiaChi.DiaChiActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class GioHangActivity extends AppCompatActivity implements  ViewGioHang,CompoundButton.OnCheckedChangeListener,View.OnClickListener{
    RecyclerView recyclerGioHang;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;
    Button btnMuaHang;
    public static CheckBox cbTatCa;
    public  static boolean KIEMTRA = true;
    public  static  int TONGTIEN=0;
    public  static TextView txtTongTien;
    public  static  int temp=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerGioHang = (RecyclerView) findViewById(R.id.recyclerGioHang);
        cbTatCa = (CheckBox) findViewById(R.id.cbTatCa);
        txtTongTien = (TextView) findViewById(R.id.txtTongTien);
        btnMuaHang = (Button) findViewById(R.id.btnMuaHang);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);
        cbTatCa.setOnCheckedChangeListener(this);
        btnMuaHang.setOnClickListener(this);
        toolbar.setTitle("Giỏ hàng");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
//        upArrow.setColorFilter(getResources().getColor(R.color.bgToolbar), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);



    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {
        TONGTIEN=0;
        temp = sanPhamList.size();
        for(int i = 0 ; i < sanPhamList.size();i++){
            TONGTIEN+= sanPhamList.get(i).getGia()*sanPhamList.get(i).getSoluong();
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String underline = "<u>đ</u>";
            String gia = numberFormat.format(TONGTIEN);
            txtTongTien.setText( Html.fromHtml(String.format(underline)+" " +gia));

        }
        CustomRecyclerGioHang customRecyclerGioHang = new CustomRecyclerGioHang(this,sanPhamList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerGioHang.setLayoutManager(layoutManager);
        recyclerGioHang.setAdapter(customRecyclerGioHang);
        customRecyclerGioHang.notifyDataSetChanged();

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            for(int i = 0 ; i <temp;i++){
                View v = recyclerGioHang.getLayoutManager().findViewByPosition(i);
                CheckBox checkBox = v.findViewById(R.id.cbSanPham);
                checkBox.setChecked(true);
            }
        }else if (KIEMTRA==true){
            for(int i = 0 ; i <temp;i++){

                View v = recyclerGioHang.getLayoutManager().findViewByPosition(i);
                CheckBox checkBox = v.findViewById(R.id.cbSanPham);

                checkBox.setChecked(false);
            }
        }


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMuaHang:
                Intent intent = new Intent(GioHangActivity.this, DiaChiActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
        }
    }
}
