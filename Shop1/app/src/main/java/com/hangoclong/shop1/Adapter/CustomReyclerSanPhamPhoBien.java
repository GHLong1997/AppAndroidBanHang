package com.hangoclong.shop1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Admin on 11/2/2017.
 */

public class CustomReyclerSanPhamPhoBien extends RecyclerView.Adapter<CustomReyclerSanPhamPhoBien.ViewHolderSPPB> {
    Context context;
    List<SanPham> sanPhamList;

    public  CustomReyclerSanPhamPhoBien(Context context, List<SanPham> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    public class ViewHolderSPPB extends RecyclerView.ViewHolder {
        ImageView imSP;
        TextView txtTenSP,txtGia;
        FrameLayout frLayout;
        public ViewHolderSPPB(View itemView) {
            super(itemView);
            imSP = itemView.findViewById(R.id.imSanPhamPhoBien);
            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            txtGia = itemView.findViewById(R.id.txtGia);
            frLayout = itemView.findViewById(R.id.frLayout);
        }
    }
    @Override
    public ViewHolderSPPB onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_sanphamphobien,parent,false);
        ViewHolderSPPB viewHolderSPPB = new ViewHolderSPPB(view);
        return viewHolderSPPB;
    }

    @Override
    public void onBindViewHolder(ViewHolderSPPB holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getAnhLon()).into(holder.imSP);

        TextView txt = new TextView(context);
        txt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(25)});
        txt.setText(sanPham.getTenSP());
        holder.txtTenSP.setText(txt.getText().toString()+"...");

        String underline = "<u>đ</u>";

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGia());
        holder.txtGia.setText(Html.fromHtml(String.format(underline) +" " + gia));
        holder.frLayout.setTag(sanPham.getMaSP());
        holder.frLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                Log.d("maspsp",(int)view.getTag()+"");
                intent.putExtra("masp",(int)view.getTag());
                context.startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);


            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
