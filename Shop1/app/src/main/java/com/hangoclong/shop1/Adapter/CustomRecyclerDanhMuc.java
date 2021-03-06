package com.hangoclong.shop1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
import com.hangoclong.shop1.R;

import com.hangoclong.shop1.View.DanhMucConActivity.DanhMucConActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 10/31/2017.
 */

public class CustomRecyclerDanhMuc extends RecyclerView.Adapter<CustomRecyclerDanhMuc.ViewHolder>{
    Context context;
    List<LoaiSanPham> loaiSanPhamList;
    int layout;

    public CustomRecyclerDanhMuc(Context context, List<LoaiSanPham> loaiSanPhams){
        this.context = context;;
        this.loaiSanPhamList = loaiSanPhams;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imLoaiSp;
        TextView txtLoaiSp;
        LinearLayout lnDanhMucCha;
        public ViewHolder(View itemView) {
            super(itemView);
            imLoaiSp = itemView.findViewById(R.id.imHinhLoaiSP);
            txtLoaiSp = itemView.findViewById(R.id.txtLoaiSp);
            lnDanhMucCha = itemView.findViewById(R.id.lnDanhMucCha);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_danhmuctong,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LoaiSanPham loaiSanPham = loaiSanPhamList.get(position);
        holder.txtLoaiSp.setText(loaiSanPham.getTENLOAISP());
        Picasso.with(context).load(loaiSanPham.getHINHLOAISP()).resize(120,120).into(holder.imLoaiSp);

        holder.lnDanhMucCha.setTag(loaiSanPham.getMALOAISP());
        holder.lnDanhMucCha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhMucConActivity.class);
                Log.d("ssss2",(int)view.getTag()+"");
                intent.putExtra("maloai",(int)view.getTag());

                SharedPreferences sharedPreferences = context.getSharedPreferences("maloaisp",context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("maloaisp",(int)view.getTag());
                editor.commit();
                context.startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
            }
        });
    }


    @Override
    public int getItemCount() {
        return loaiSanPhamList.size();
    }


}
