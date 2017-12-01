package com.hangoclong.shop1.Model.DangNhap;

import android.util.Log;

import com.hangoclong.shop1.ConnectInternet.DownLoadJSON;
import com.hangoclong.shop1.Model.ObjectClass.NhanVien;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 10/25/2017.
 */

public class ModelDangKy {

    public  boolean DangKyTaiKhoan(NhanVien nhanVien){
        boolean kiemtra = false;
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","DangKyTaiKhoan");

        HashMap<String,String> hsTenDN = new HashMap<>();
        hsTenDN.put("tendangnhap",nhanVien.getTenDN());

        HashMap<String,String> hsMk = new HashMap<>();
        hsMk.put("matkhau",nhanVien.getMatKhau());

        HashMap<String,String> hsSodt = new HashMap<>();
        hsSodt.put("sodt",nhanVien.getSodt());

        HashMap<String,String> hsEmail = new HashMap<>();
        hsEmail.put("email",nhanVien.getEmail());


        HashMap<String,String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("maloainv",String.valueOf(nhanVien.getMaLoaiNV()));
        Log.d("ttt",nhanVien.getTenDN());

        attrs.add(hsHam);
        attrs.add(hsTenDN);
        attrs.add(hsMk);
        attrs.add(hsSodt);
        attrs.add(hsEmail);
        attrs.add(hsMaLoai);

        Log.d("aaaa",nhanVien.getTenDN()  + " " + nhanVien.getMatKhau() + " " + nhanVien.getSodt() + " " + nhanVien.getEmail() + " " + nhanVien.getMaLoaiNV());

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        downLoadJSON.execute();

        try {
            String dulieuJSON = downLoadJSON.get();
            Log.d("kiemtra",dulieuJSON.toString());

            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.equals("true")){
                kiemtra  = true;
            }else {
                kiemtra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return  kiemtra;
    }
}
