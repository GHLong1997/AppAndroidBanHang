package com.hangoclong.shop1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 11/3/2017.
 */

public class CustomRecyclerKhoangGiaTienTopSanPham extends RecyclerView.Adapter<CustomRecyclerKhoangGiaTienTopSanPham.ViewHolderK> {
    Context context;
    List<SanPham> sanPhamList;
    public  CustomRecyclerKhoangGiaTienTopSanPham(Context context,List<SanPham> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;
    }
    public class ViewHolderK extends RecyclerView.ViewHolder {
        ImageView imSP;
        TextView txtGia,txtKhoang;
        public ViewHolderK(View itemView) {
            super(itemView);
            imSP = itemView.findViewById(R.id.imSP);
            txtGia = itemView.findViewById(R.id.txtGiaTien);
            txtKhoang = itemView.findViewById(R.id.txtKhoang);
        }
    }

    @Override
    public ViewHolderK onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.custom_recycler_topsanphambanchay,parent,false);
        ViewHolderK viewHolderK = new ViewHolderK(view);
        return viewHolderK;
    }

    @Override
    public void onBindViewHolder(ViewHolderK holder, int position) {
        SanPham sanPham = sanPhamList.get(position);

        if(sanPham.getGia()<500000){
            holder.txtGia.setText("< 500,000VNĐ");
            holder.txtKhoang.setText("Từ đ115.000");
            Picasso.with(context).load(sanPham.getAnhLon()).into(holder.imSP);
        }else  if(sanPham.getGia()>=500000 && sanPham.getGia()<1500000){
            holder.txtGia.setText("500,000 ~ 1,500,000VNĐ");
            holder.txtKhoang.setText("Từ đ500.000");
            Picasso.with(context).load(sanPham.getAnhLon()).into(holder.imSP);
        }else  if(sanPham.getGia()>=1500000 && sanPham.getGia()<3000000){
            holder.txtGia.setText("1,500,000 ~ 3,000,000VNĐ");
            holder.txtKhoang.setText("Từ đ1,500.000");
            Picasso.with(context).load(sanPham.getAnhLon()).into(holder.imSP);
        }else  if(sanPham.getGia()>=3000000 && sanPham.getGia()<6000000){
            holder.txtGia.setText("3,000,000 ~ 6,000,000VNĐ");
            holder.txtKhoang.setText("Từ đ3,000.000");
            Picasso.with(context).load(sanPham.getAnhLon()).into(holder.imSP);
        }else  if(sanPham.getGia()>6000000){
            holder.txtGia.setText("> 6,000,000VNĐ");
            holder.txtKhoang.setText("Từ đ6.000.000");
            Picasso.with(context).load(sanPham.getAnhLon()).into(holder.imSP);
        }
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
