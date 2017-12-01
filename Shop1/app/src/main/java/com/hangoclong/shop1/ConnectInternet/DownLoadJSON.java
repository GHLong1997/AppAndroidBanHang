package com.hangoclong.shop1.ConnectInternet;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 10/25/2017.
 */

public class DownLoadJSON extends AsyncTask<String,Void,String> {
    String duongdan;
    List<HashMap<String,String>> attrs;
    StringBuilder dulieu;
    boolean method=true;
    public  DownLoadJSON(String duongdan){
        this.duongdan = duongdan;
        method = true;
    }
    public  DownLoadJSON(String duongdan, List<HashMap<String,String>> attrs){
        this.duongdan = duongdan;
        this.attrs = attrs;
        method = false;
    }
    @Override
    protected String doInBackground(String... strings) {
        String data="";
        try {
            URL url = new URL(duongdan);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(!method){
                data= methodPost(httpURLConnection);
            }else {
                data=   methodGet(httpURLConnection);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public  String methodGet(HttpURLConnection httpURLConnection){
        String data="";
        InputStream inputStream = null;
        try {
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            dulieu = new StringBuilder();
            String line="";
            while ((line=reader.readLine())!=null){
                dulieu.append(line);
            }

            data = dulieu.toString();
            reader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  data;

    }

    public  String methodPost(HttpURLConnection httpURLConnection){
        String data="";
        String key="";
        String value = "";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            Uri.Builder builder = new Uri.Builder();

            int count = attrs.size();
            for(int i = 0;i< count;i++){
                for(Map.Entry<String,String> values: attrs.get(i).entrySet()){
                    key  = values.getKey();
                    value = values.getValue();
                }
                builder.appendQueryParameter(key,value);
            }
            String query = builder.build().getEncodedQuery();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);
            writer.write(query);
            writer.flush();

            writer.close();
            outputStreamWriter.close();
            outputStream.close();

            data = methodGet(httpURLConnection);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  data;
    }
}
