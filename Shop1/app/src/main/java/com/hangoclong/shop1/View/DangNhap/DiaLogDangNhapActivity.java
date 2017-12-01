package com.hangoclong.shop1.View.DangNhap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hangoclong.shop1.Model.DangNhap.ModelDangNhap;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

/**
 * Created by Admin on 10/20/2017.
 */

public class DiaLogDangNhapActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnDongY,btnKhong;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogdangnhap);
        btnDongY = (Button) findViewById(R.id.btnCo);
        btnKhong = (Button) findViewById(R.id.btnKhong);
        btnKhong.setOnClickListener(this);
        btnDongY.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btnCo:
                ModelDangNhap modelDangNhap = new ModelDangNhap();
                modelDangNhap.LayCacheDangNhap(this);

                Intent intent = new Intent(DiaLogDangNhapActivity.this,TrangChuActivity.class);
                startActivity(intent);
                break;
        }
    }
}
