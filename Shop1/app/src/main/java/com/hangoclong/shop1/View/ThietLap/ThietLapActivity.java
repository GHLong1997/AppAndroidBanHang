package com.hangoclong.shop1.View.ThietLap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DangNhap.DiaLogDangXuatActivity;

/**
 * Created by Admin on 10/20/2017.
 */

public class ThietLapActivity extends AppCompatActivity  implements View.OnClickListener{
    Toolbar toolbar;
    Button btnDangXuat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thietlap);
        toolbar = (Toolbar) findViewById(R.id.idtoolbar);
        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Thiết lập tài khoản");
        ActionBar actionBar = getSupportActionBar();//Hiện thi HomeButton và phat đặt sau  setSupportActionBar(toolbar)
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnDangXuat.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chitiet,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ThietLapActivity.this, DiaLogDangXuatActivity.class);
        startActivity(intent);
    }

}
