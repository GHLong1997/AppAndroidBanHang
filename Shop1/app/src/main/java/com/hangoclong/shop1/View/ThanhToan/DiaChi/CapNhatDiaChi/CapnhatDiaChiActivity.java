package com.hangoclong.shop1.View.ThanhToan.DiaChi.CapNhatDiaChi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hangoclong.shop1.Adapter.CustomRecyclerCapNhatDiaChi;
import com.hangoclong.shop1.Model.ObjectClass.DiaChi;
import com.hangoclong.shop1.Presenter.ThanhToan.DiaChi.PresenterLogicDiaChi;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.ThanhToan.DiaChi.DiaChiActivity;
import com.hangoclong.shop1.View.ThanhToan.DiaChi.ViewDiaChi;

import java.util.List;

public class CapnhatDiaChiActivity extends AppCompatActivity implements ViewDiaChi {
    RecyclerView recyclerCapNhatDiaChi;
    Toolbar toolbar;
    CustomRecyclerCapNhatDiaChi customRecyclerCapNhatDiaChi;
    PresenterLogicDiaChi presenterLogicDiaChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhat_dia_chi);
        recyclerCapNhatDiaChi = (RecyclerView) findViewById(R.id.recyclerCapNhat);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Địa chỉ Shop");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenterLogicDiaChi = new PresenterLogicDiaChi(this,this);
        presenterLogicDiaChi.LayDanhSachDiaChi();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(CapnhatDiaChiActivity.this, DiaChiActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void HienThiDanhSachDiaChi(List<DiaChi> diaChis) {

        customRecyclerCapNhatDiaChi = new CustomRecyclerCapNhatDiaChi(this,diaChis);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerCapNhatDiaChi.setLayoutManager(layoutManager);
        recyclerCapNhatDiaChi.setAdapter(customRecyclerCapNhatDiaChi);
        customRecyclerCapNhatDiaChi.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }
}
