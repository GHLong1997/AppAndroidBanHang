package com.hangoclong.shop1.View.DangNhap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.hangoclong.shop1.Model.DangNhap.ModelDangNhap;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

/**
 * Created by Admin on 10/20/2017.
 */

public class DiaLogDangXuatActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnDongY,btnKhong;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
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
                modelDangNhap.CapNhatCachedDangNhap(this,"");
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(DiaLogDangXuatActivity.this,TrangChuActivity.class);
                startActivity(intent);
                break;
        }
    }
}
