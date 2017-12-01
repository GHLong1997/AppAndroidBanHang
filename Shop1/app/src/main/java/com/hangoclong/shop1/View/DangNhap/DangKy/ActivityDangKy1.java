package com.hangoclong.shop1.View.DangNhap.DangKy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hangoclong.shop1.R;

import java.util.Random;

/**
 * Created by Admin on 10/23/2017.
 */

public class ActivityDangKy1 extends AppCompatActivity {
    Button btnTieptuc;
    EditText edTieptuc,edNhapTheo;
    TextView txtNumber;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky1);
        txtNumber = (TextView) findViewById(R.id.txtNumber);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        edNhapTheo = (EditText) findViewById(R.id.edNhapTheo);
        edTieptuc = (EditText) findViewById(R.id.editTieptuc);
        btnTieptuc = (Button) findViewById(R.id.btnTieptuc);

        Random random = new Random();
        final int r = random.nextInt((999998-100000)+1) + 100000;
        txtNumber.setText(r+"");
        btnTieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nhaptheo = edNhapTheo.getText().toString();
                if(nhaptheo.equals(r+"")){
                    Intent iDangky2 = new Intent(ActivityDangKy1.this,ActivityDangky2.class);
                    iDangky2.putExtra("email",edTieptuc.getText().toString());
                    startActivity(iDangky2);
                }else{
                    Toast.makeText(getApplicationContext(),"Sai cú pháp",Toast.LENGTH_SHORT).show();
                }


            }
        });

        Typeface font1 = Typeface.createFromAsset(this.getAssets(),"fonts/Roboto-BoldItalic.ttf");
       txtNumber.setTypeface(font1);






    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
}
