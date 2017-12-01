package com.hangoclong.shop1.View.GioHang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hangoclong.shop1.R;

/**
 * Created by Admin on 11/17/2017.
 */

public class DiaLogXoa extends AppCompatActivity implements View.OnClickListener{
    Button txtCo,txtKhong;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_xoasanphamgiohang);
        txtCo = (Button) findViewById(R.id.btnCo);
        txtKhong = (Button) findViewById(R.id.btnKhong);
        txtCo.setOnClickListener(this);
        txtKhong.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCo:
                Intent intent = new Intent();
                intent.putExtra("kiemtra",1);
                setResult(Activity.RESULT_OK,intent);
                finish();
        }
    }
}
