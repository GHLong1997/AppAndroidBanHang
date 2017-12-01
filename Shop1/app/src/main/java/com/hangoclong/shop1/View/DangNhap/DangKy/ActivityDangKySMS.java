package com.hangoclong.shop1.View.DangNhap.DangKy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.hangoclong.shop1.R;

public class ActivityDangKySMS extends AppCompatActivity {
    Toolbar toolbar;
    Button btnTiepTuc;
    EditText edSdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_sms);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnTiepTuc = (Button) findViewById(R.id.btnTieptuc);
        edSdt = (EditText) findViewById(R.id.edSdt);
        toolbar.setTitle("Số điện thoại");
        toolbar.setTitleMarginStart(95);

        setSupportActionBar(toolbar);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }
}
