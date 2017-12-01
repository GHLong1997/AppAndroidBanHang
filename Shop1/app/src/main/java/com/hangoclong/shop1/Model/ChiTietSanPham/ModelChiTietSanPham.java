package com.hangoclong.shop1.Model.ChiTietSanPham;

import android.util.Log;

import com.hangoclong.shop1.ConnectInternet.DownLoadJSON;
import com.hangoclong.shop1.Model.ObjectClass.ChiTietSanPham;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 11/9/2017.
 */

public class ModelChiTietSanPham {
    public SanPham LayChiTietSanPham(String ham, String tenmangJSON,int masp){
        SanPham sanPham = new SanPham();
        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",ham);

        HashMap<String,String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp",String.valueOf(masp));

        attrs.add(hsHam);
        attrs.add(hsMaSP);
        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);

        downLoadJSON.execute();
        try {
            String dataJSON = downLoadJSON.get();
            Log.d("kiemtrachitiet",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmangJSON);

            int count = jsonArray.length();
            for(int i = 0 ; i < count; i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                sanPham.setMaSP(jsonObject1.getInt("MASP"));
                sanPham.setTenSP(jsonObject1.getString("TENSP"));
                sanPham.setSoluong(jsonObject1.getInt("SOLUONG"));
                sanPham.setGia(jsonObject1.getInt("GIA"));
                sanPham.setLuotMua(jsonObject1.getInt("LUOTMUA"));
                sanPham.setAnhLon(jsonObject1.getString("ANHLON"));
                sanPham.setMaShop(jsonObject1.getInt("MASHOP"));
                sanPham.setTenShop(jsonObject1.getString("TENSHOP"));
                sanPham.setLuotMua(jsonObject1.getInt("LUOTMUA"));
                sanPham.setAnhNho(jsonObject1.getString("ANHNHO"));
                sanPham.setThongTin(jsonObject1.getString("THONGTIN"));

                JSONArray jsonArrayChiTiet = jsonObject1.getJSONArray("CHITIETSANPHAM");

                for(int j = 0 ;j<jsonArrayChiTiet.length();j++){
                    ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

                    JSONObject jsonObject2 =  jsonArrayChiTiet.getJSONObject(j);
                    chiTietSanPham.setTenChiTiet(jsonObject2.getString("TENCHITIET"));
                    chiTietSanPham.setGiaTri(jsonObject2.getString("GIATRI"));
                    chiTietSanPhamList.add(chiTietSanPham);
                }
                sanPham.setChiTietSanPhams(chiTietSanPhamList);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPham;
    }
}
