package com.hangoclong.shop1.View.DangNhap.DangKy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hangoclong.shop1.Model.DangNhap.ModelDangNhap;
import com.hangoclong.shop1.Model.ObjectClass.NhanVien;
import com.hangoclong.shop1.Presenter.DangNhap.PresenterLogicDangKy;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DangNhap.ViewDangKy;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

/**
 * Created by Admin on 10/23/2017.
 */

public class ActivityDangky2 extends AppCompatActivity implements View.OnFocusChangeListener,ViewDangKy{
    EditText edMail;
    EditText edMatkhau,edXNMK,edTenDN;
    Button btnDangky;
    Toolbar toolbar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String email1;
    PresenterLogicDangKy presenterLogicDangKy;
    boolean kiemtrathongtin = false;
    private  int kiemtradk=0;
    String tendangnhap = "";
    String sdt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edMail = (EditText) findViewById(R.id.edMail);
        edMatkhau = (EditText) findViewById(R.id.edMK);
        edXNMK = (EditText) findViewById(R.id.edXNMK);
        edTenDN = (EditText) findViewById(R.id.edTenDN);
        btnDangky = (Button) findViewById(R.id.btndangky);

        edTenDN.setOnFocusChangeListener(this);
        edMatkhau.setOnFocusChangeListener(this);
        edXNMK.setOnFocusChangeListener(this);

        toolbar.setTitle("Đăng ký");
        toolbar.setTitleMarginStart(105);
        setSupportActionBar(toolbar);
        presenterLogicDangKy = new PresenterLogicDangKy(this);



        //from activiydangky1 send to
        if(getIntent().getStringExtra("email")!=null){
            final String email = getIntent().getStringExtra("email");
            if(!email.equals("")){
                edMail.setText(email.toString());
                edMail.setEnabled(false);
            }
        }else if(getIntent().getStringExtra("Tendangnhap")!=null){
            edMail.setHint("Tên đăng nhập");
            final String tendangnhap = getIntent().getStringExtra("tendangnhap");
            edTenDN.setText(tendangnhap);
            edTenDN.setEnabled(false);

        }else if(!getIntent().getStringExtra("sdt").equals("")){
             sdt = getIntent().getStringExtra("sdt");
            Log.d("sddt",sdt);
            edTenDN.setText(sdt);
            edTenDN.setEnabled(false);

        }





        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    email1 = user.getEmail().toString();

                } else {

                }
                // ...
            }
        };

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sdt!=null){
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setTenDN(edMail.getText().toString());
                    tendangnhap = edMail.getText().toString();
                    nhanVien.setSodt(sdt);
                    nhanVien.setMatKhau(edMatkhau.getText().toString());
                    nhanVien.setEmail("");
                    nhanVien.setMaLoaiNV(2);
                    presenterLogicDangKy.DangKyTaiKhoan(nhanVien);
                }


                dangky(edMail.getText().toString(),edMatkhau.getText().toString());



            }
        });

    }

    public  void dangky(final String email, final String pass){

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setEmail(edMail.getText().toString());
                    nhanVien.setTenDN(edTenDN.getText().toString());
                    nhanVien.setSodt("");
                    nhanVien.setMatKhau(edMatkhau.getText().toString());
                    nhanVien.setMaLoaiNV(2);
                    presenterLogicDangKy.DangKyTaiKhoan(nhanVien);
                    dangnhap(edMail.getText().toString(),edMatkhau.getText().toString());
                }else
                {
                    Toast.makeText(getApplicationContext(),"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void dangnhap(final String email, final String pass){
      mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){


                  Intent iTrangchu = new Intent(ActivityDangky2.this, TrangChuActivity.class);
                  iTrangchu.putExtra("email",email1);
                  startActivity(iTrangchu);


              }


          }
      });

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
mAuth.removeAuthStateListener(mAuthListener);
    }





    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.edTenDN:
                if(!b){//chua focus
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("")||chuoi.equals(null)){
                        edTenDN.setError("Bạn chưa nhập mục này");
                        kiemtrathongtin = false;
                    }else {
                        edTenDN.setError("");
                        kiemtrathongtin = true;

                    }
                }
                break;
            case  R.id.edXNMK:
                String chuoi = ((EditText)view).getText().toString();
                String matkhau = edMatkhau.getText().toString();
                if(!chuoi.equals(matkhau)){
                    edXNMK.setError("mật khẩu không trùng khớp");
                    kiemtrathongtin = false;
                }else {
                    kiemtrathongtin = true;
                }

        }
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        modelDangNhap.CapNhatCachedDangNhap(this,tendangnhap);
        Intent iTrangchu = new Intent(ActivityDangky2.this, TrangChuActivity.class);
        startActivity(iTrangchu);

    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    }
}
