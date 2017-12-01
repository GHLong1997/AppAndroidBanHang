package com.hangoclong.shop1.Model.DanhMucConActivity.DanhMucCon_PhoBien;

import android.util.Log;

import com.hangoclong.shop1.ConnectInternet.DownLoadJSON;
import com.hangoclong.shop1.Model.ObjectClass.DanhGia;
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
 * Created by Admin on 11/6/2017.
 */

public class ModelDanhMucConPhoBien {
    public List<SanPham> LayDanhSachTopDienThoaiPhoBien(String ham, String tenmangJSON, int maloaisp,int limit){
        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",ham);

        HashMap<String,String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("maloaisp",String.valueOf(maloaisp));

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit",String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsMaLoai);
        attrs.add(hsLimit);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);

        downLoadJSON.execute();
        try {
            String dataJSON = downLoadJSON.get();
            Log.d("danhmuccon11",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmangJSON);

            int count = jsonArray.length();
            for(int i = 0 ; i < count; i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMaSP(jsonObject1.getInt("MASP"));
                sanPham.setTenSP(jsonObject1.getString("TENSP"));
                sanPham.setGia(jsonObject1.getInt("GIA"));
                sanPham.setAnhLon(jsonObject1.getString("ANHLON"));
                sanPham.setLuotMua(jsonObject1.getInt("LUOTMUA"));


                List<DanhGia> danhGias = new ArrayList<>();
                JSONArray jsonArrayDanhGia = jsonObject1.getJSONArray("DANHSACHDANHGIA");
                for(int j = 0 ; j < jsonArrayDanhGia.length();j++){
                    JSONObject jsonObject2 = jsonArrayDanhGia.getJSONObject(j);
                    DanhGia danhGia = new DanhGia();
                    danhGia.setSOSAO(jsonObject2.getInt("SOSAO"));
                    danhGia.setNOIDUNG(jsonObject2.getString("NOIDUNG"));
                    danhGias.add(danhGia);
                }
                sanPham.setDanhGias(danhGias);
                sanPhamList.add(sanPham);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPhamList;
    }
}
