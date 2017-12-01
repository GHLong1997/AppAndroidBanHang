package com.hangoclong.shop1.View.ThanhToan.ThanhToan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

public class XacNhanActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan);
        btnTroVe = (Button) findViewById(R.id.btnTroVeTrangChu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Xác nhận");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XacNhanActivity.this, TrangChuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
            }
        });

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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }
}
