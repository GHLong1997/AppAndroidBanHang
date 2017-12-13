package com.hangoclong.shop1.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.hangoclong.shop1.Adapter.ViewPagerAdapterTrangChu;
import com.hangoclong.shop1.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.hangoclong.shop1.Presenter.DangNhap.PresenterLogicDangKy;
import com.hangoclong.shop1.Presenter.DangNhap.PresenterLogicDangNhap;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DangNhap.ViewDangNhap;
import com.hangoclong.shop1.View.GioHang.GioHangActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class TrangChuActivity extends AppCompatActivity implements View.OnClickListener,ViewDangNhap{

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PresenterLogicDangNhap presenterLogicDangNhap;
    PresenterLogicDangKy presenterLogicDangKy;
    AccessToken accessToken;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
    TextView txtGioHang;
    public  static  final String SERVER_NAME="http://10.0.3.2/shop/xuly.php";
    public  static  final String SERVER="http://10.0.3.2/shop";

    public  static String TENDANGNHAP= "";
    public  static String HINHANH ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.trangchu_activity);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ViewPagerAdapterTrangChu adapterTrangChu = new ViewPagerAdapterTrangChu(getSupportFragmentManager());
        viewPager.setAdapter(adapterTrangChu);
        tabLayout.setupWithViewPager(viewPager);
        adapterTrangChu.notifyDataSetChanged();

        presenterLogicDangNhap = new PresenterLogicDangNhap(this,this);
        accessToken = presenterLogicDangNhap.LayTokenFacebookHienTai();


        if(accessToken!=null){
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {

                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        HINHANH = object.getString("id");
                       TENDANGNHAP = object.getString("name");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });
            Bundle parameter = new Bundle();//muôn lấy name thì pahir tạo ra 1 cai bundle
            parameter.putString("fields","id,name");//lấy mỗi tên người dùng
            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();
        }else {
            HINHANH = "";
            TENDANGNHAP ="";
        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu,menu);
        MenuItem itemGioHang = menu.findItem(R.id.giohang);
        View giaoDienCustomGioHang = itemGioHang.getActionView();
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TENDANGNHAP ==""){
                    Toast.makeText(TrangChuActivity.this,"Bạn chưa đăng nhập",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(TrangChuActivity.this, GioHangActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
                }

            }
        });
        return true;
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void DangNhapThanhCong() {

    }

    @Override
    public void DangNhapThatBai() {

    }
}
