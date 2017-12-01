package com.hangoclong.shop1.View.ThanhToan.DiaChi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hangoclong.shop1.Adapter.CustomRecyclerDiaChi;
import com.hangoclong.shop1.Model.ObjectClass.DiaChi;
import com.hangoclong.shop1.Presenter.ThanhToan.DiaChi.PresenterLogicDiaChi;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.ThanhToan.DiaChi.CapNhatDiaChi.CapnhatDiaChiActivity;
import com.hangoclong.shop1.View.ThanhToan.DiaChi.ThemDiaChi.ThemDiaChiActivity;
import com.hangoclong.shop1.View.ThanhToan.VanChuyen.VanChuyenActivity;

import java.util.List;

public class DiaChiActivity extends AppCompatActivity implements View.OnClickListener,ViewDiaChi{
    Toolbar toolbar;
    TextView txtThemDiaChi;
    CustomRecyclerDiaChi customRecyclerDiaChi;
    RecyclerView recyclerDiaChi;
    PresenterLogicDiaChi presenterLogicDiaChi;
    Button btnTiepTheo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi);
        txtThemDiaChi = (TextView) findViewById(R.id.txtThemDiaChi);
        recyclerDiaChi = (RecyclerView) findViewById(R.id.recyclerDiaChi);
        btnTiepTheo = (Button) findViewById(R.id.btnTiepTheo);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Địa chỉ nhận hàng");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtThemDiaChi.setOnClickListener(this);
        btnTiepTheo.setOnClickListener(this);
        presenterLogicDiaChi = new PresenterLogicDiaChi(this,this);
        presenterLogicDiaChi.LayDanhSachDiaChi();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_diachi,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
        }
        switch (item.getItemId()){
            case R.id.suadiachi:
                Intent intent = new Intent(DiaChiActivity.this, CapnhatDiaChiActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtThemDiaChi:
                Intent intent = new Intent(DiaChiActivity.this,ThemDiaChiActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
                break;
            case R.id.btnTiepTheo:
                Intent intent1 = new Intent(DiaChiActivity.this, VanChuyenActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
        }
    }



    @Override
    public void HienThiDanhSachDiaChi(List<DiaChi> diaChis) {
        customRecyclerDiaChi = new CustomRecyclerDiaChi(this,diaChis);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerDiaChi.setLayoutManager(layoutManager);
        recyclerDiaChi.setAdapter(customRecyclerDiaChi);
        customRecyclerDiaChi.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }
}
