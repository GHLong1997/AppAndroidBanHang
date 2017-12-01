package com.hangoclong.shop1.View.ThanhToan.DiaChi.ThemDiaChi;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hangoclong.shop1.Model.ObjectClass.DiaChi;
import com.hangoclong.shop1.Presenter.ThanhToan.DiaChi.PresenterLogicDiaChi;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.ThanhToan.DiaChi.CapNhatDiaChi.CapnhatDiaChiActivity;
import com.hangoclong.shop1.View.ThanhToan.DiaChi.DiaChiActivity;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

public class ThemDiaChiActivity extends AppCompatActivity implements  TextWatcher,ViewThemDiaChi {
    Toolbar toolbar;
    TransitionDrawable transitionDrawable;
    EditText edTen,edSDT,edTinh,edHuyen,edPhuong,edDiaChiCuThe;
    Button btnHoanThanh;
    SwitchCompat scMacDinh;
    LinearLayout lnXoaDiaChi;
    PresenterLogicDiaChi presenterLogicDiaChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dia_chi);
        btnHoanThanh = (Button) findViewById(R.id.btnHoanThanh);
        edTen = (EditText) findViewById(R.id.edTen);
        edSDT = (EditText) findViewById(R.id.edSDT);
        edTinh = (EditText) findViewById(R.id.edTinh);
        edHuyen = (EditText) findViewById(R.id.edQuan);
        edPhuong = (EditText) findViewById(R.id.edPhuong);
        edDiaChiCuThe = (EditText) findViewById(R.id.edDiaChiCuThe);
        scMacDinh = (SwitchCompat) findViewById(R.id.swMacDinh);
        lnXoaDiaChi = (LinearLayout) findViewById(R.id.lnXoaDiaChi);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Địa chỉ mới");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        transitionDrawable = (TransitionDrawable) btnHoanThanh.getBackground();
        edTen.addTextChangedListener(this);
        edSDT.addTextChangedListener(this);
        edHuyen.addTextChangedListener(this);
        edPhuong.addTextChangedListener(this);
        edTinh.addTextChangedListener(this);
        edDiaChiCuThe.addTextChangedListener(this);
        presenterLogicDiaChi = new PresenterLogicDiaChi(this,this);
        btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edTen.getText().toString().trim().length()>0 && edSDT.getText().toString().trim().length()>0&&edTinh.getText().toString().trim().length()>0&&edPhuong.getText().toString().trim().length()>0
                        &&edHuyen.getText().toString().trim().length()>0&&edDiaChiCuThe.getText().toString().trim().length()>0){
                    DiaChi diaChi = new DiaChi();
                    diaChi.setTenNguoiGui(edTen.getText().toString());
                    diaChi.setSoDienThoai(edSDT.getText().toString());
                    diaChi.setTinh(edTinh.getText().toString());
                    diaChi.setQuan(edHuyen.getText().toString());
                    diaChi.setPhuong(edPhuong.getText().toString());
                    diaChi.setDiaChiCuThe(edDiaChiCuThe.getText().toString());
                    presenterLogicDiaChi.ThemDiaChi(ThemDiaChiActivity.this,diaChi);
                }
            }
        });


        if(getIntent().getIntExtra("xoa",0)!=0){
            final DiaChi diaChi = (DiaChi) getIntent().getSerializableExtra("object");
            edTen.setText(diaChi.getTenNguoiGui());
            edSDT.setText(diaChi.getSoDienThoai());
            edTinh.setText(diaChi.getTinh());
            edHuyen.setText(diaChi.getQuan());
            edPhuong.setText(diaChi.getPhuong());
            edDiaChiCuThe.setText(diaChi.getDiaChiCuThe());
            lnXoaDiaChi.setVisibility(View.VISIBLE);
            lnXoaDiaChi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenterLogicDiaChi.XoaDiaChi(TrangChuActivity.HINHANH,diaChi.getMaDiaChi());
                }
            });

            //set lại sự kiện cập nhật cho thăng btn
            btnHoanThanh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    diaChi.setTenNguoiGui(edTen.getText().toString());
                    diaChi.setSoDienThoai(edSDT.getText().toString());
                    diaChi.setTinh(edTinh.getText().toString());
                    diaChi.setQuan(edHuyen.getText().toString());
                    diaChi.setPhuong(edPhuong.getText().toString());
                    diaChi.setDiaChiCuThe(edDiaChiCuThe.getText().toString());
                    presenterLogicDiaChi.CapNhatDiaChi(TrangChuActivity.HINHANH,diaChi);
                }
            });
        }
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(edTen.getText().toString().trim().length()>0 && edSDT.getText().toString().trim().length()>0&&edTinh.getText().toString().trim().length()>0&&edPhuong.getText().toString().trim().length()>0
                &&edHuyen.getText().toString().trim().length()>0&&edDiaChiCuThe.getText().toString().trim().length()>0){
            transitionDrawable.startTransition(0);
            btnHoanThanh.setEnabled(true);
        }else {
            transitionDrawable.resetTransition();
            btnHoanThanh.setEnabled(false);
        }
    }

    @Override
    public void ThemDiaChiThanhCong() {
        Toast.makeText(this,"Thêm địa chỉ thành công",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ThemDiaChiActivity.this, DiaChiActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
    }

    @Override
    public void ThemDiaChiThatBai() {
        Toast.makeText(this,"Thêm địa chỉ thất bại",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void CapNhatThanhCong() {
        Toast.makeText(ThemDiaChiActivity.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ThemDiaChiActivity.this, CapnhatDiaChiActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
    }

    @Override
    public void XoaDiaChiThanhCong() {
        Toast.makeText(ThemDiaChiActivity.this,"Xoa thành công",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ThemDiaChiActivity.this, CapnhatDiaChiActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
        super.onBackPressed();
    }
}
