package com.hangoclong.shop1.Model.DanhMucConActivity;

import android.util.Log;

import com.hangoclong.shop1.ConnectInternet.DownLoadJSON;
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

public class ModelDanhMucCon {

    public List<SanPham> LayDanhSachTopDienThoaiPhoBien(String ham, String tenmangJSON,int maloaisp){
        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String duongdan= TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",ham);

        HashMap<String,String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("maloaisp",String.valueOf(maloaisp));

        attrs.add(hsHam);
        attrs.add(hsMaLoai);

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
