package com.hangoclong.shop1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hangoclong.shop1.Model.ObjectClass.DanhGia;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 11/11/2017.
 */

public class CustomRecyclerDanhGia extends RecyclerView.Adapter<CustomRecyclerDanhGia.ViewHolder> {
    Context context;
    List<DanhGia> danhGias;
    public  CustomRecyclerDanhGia(Context context,List<DanhGia> danhGias){
        this.context = context;
        this.danhGias = danhGias;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imNguoiDanhGia;
        TextView txtTenNguoiDG,txtNoiDungDG,txtNgayDG;
        View vLine;
        RatingBar rdDanhGia;
        public ViewHolder(View itemView) {
            super(itemView);
            imNguoiDanhGia = itemView.findViewById(R.id.imNguoiDanhGia);
            txtTenNguoiDG = itemView.findViewById(R.id.txtTenNguoiDanhGia);
            txtNoiDungDG = itemView.findViewById(R.id.txtNoiDungDG);
            txtNgayDG = itemView.findViewById(R.id.txtNgayDanhGia);
            rdDanhGia = itemView.findViewById(R.id.rdDanhGia);
            vLine = itemView.findViewById(R.id.vLine);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_reycler_danhgia,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DanhGia danhGia = danhGias.get(position);
        if(!danhGia.getHINHDANGNHAP().equals("")){
            Picasso.with(context).load("https://graph.facebook.com/"+danhGia.getHINHDANGNHAP() +"/picture?type=large").into(holder.imNguoiDanhGia);
        }else {
            Picasso.with(context).load(R.drawable.acc).into(holder.imNguoiDanhGia);
        }
        holder.txtTenNguoiDG.setText(danhGia.getTENDANGNHAP());
        holder.txtNgayDG.setText(danhGia.getNGAYDANHGIA());
        holder.txtNoiDungDG.setText(danhGia.getNOIDUNG());
        holder.rdDanhGia.setRating((float) danhGia.getSOSAO());
        if(ChiTietSanPhamActivity.LINE==1){
            holder.vLine.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return danhGias.size();
    }

}
