package com.hangoclong.shop1.Model.DanhGia;

import android.util.Log;

import com.hangoclong.shop1.ConnectInternet.DownLoadJSON;
import com.hangoclong.shop1.Model.ObjectClass.DanhGia;
import com.hangoclong.shop1.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 11/11/2017.
 */

public class ModelDanhGia {
    public  boolean ThemDanhGia(String ham, DanhGia danhGia){
        boolean kiemtra = false;
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",ham);

        HashMap<String,String> hsMaDG = new HashMap<>();
        hsMaDG.put("madg",danhGia.getMADG());

        HashMap<String,String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp",String.valueOf(danhGia.getMASP()));

        HashMap<String,String> hsTenDN = new HashMap<>();
        hsTenDN.put("tendangnhap",danhGia.getTENDANGNHAP());

        HashMap<String,String> hsHinhDN = new HashMap<>();
        hsHinhDN.put("hinhdangnhap",danhGia.getHINHDANGNHAP());
        Log.d("hinhdangnhap",danhGia.getHINHDANGNHAP()+"");

        HashMap<String,String> hsSoSao = new HashMap<>();
        hsSoSao.put("sosao",String.valueOf(danhGia.getSOSAO()));

        HashMap<String,String> hsNoidung = new HashMap<>();
        hsNoidung.put("noidung",danhGia.getNOIDUNG());

        HashMap<String,String> hsTenThietBi = new HashMap<>();
        Log.d("toilolo",danhGia.getTENTHIETBI());
        hsTenThietBi.put("tenthietbi",danhGia.getTENTHIETBI());

        attrs.add(hsHam);
        attrs.add(hsMaDG);
        attrs.add(hsMaSP);
        attrs.add(hsTenDN);
        attrs.add(hsHinhDN);
        attrs.add(hsSoSao);
        attrs.add(hsNoidung);
        attrs.add(hsTenThietBi);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        downLoadJSON.execute();

        try {
            String dulieuJson = downLoadJSON.get();
            Log.d("danhgiaa",dulieuJson+"");
            JSONObject jsonObject = new JSONObject(dulieuJson);
            String kt = jsonObject.getString("ketqua");
            if(kt.equals("true")){
                kiemtra =true;
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

    public List<DanhGia> LayDanhSachDanhGia(String ham, String tenmangJSON, int masp){
        List<DanhGia> danhGias = new ArrayList<>();
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
            Log.d("danhsachdanhgia",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmangJSON);

            int count = jsonArray.length();
            for(int i = 0 ; i < count; i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                DanhGia danhGia = new DanhGia();
                danhGia.setMADG(jsonObject1.getString("MADG"));
                danhGia.setMASP(jsonObject1.getInt("MASP"));
                danhGia.setTENDANGNHAP(jsonObject1.getString("TENDANGNHAP"));
                danhGia.setHINHDANGNHAP(jsonObject1.getString("HINHDANGNHAP"));
                danhGia.setSOSAO(jsonObject1.getInt("SOSAO"));
                danhGia.setNOIDUNG(jsonObject1.getString("NOIDUNG"));
                danhGia.setTENTHIETBI(jsonObject1.getString("TENTHIETBI"));
                danhGia.setNGAYDANHGIA(jsonObject1.getString("NGAYDANHGIA"));
                danhGias.add(danhGia);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return danhGias;
    }
}
