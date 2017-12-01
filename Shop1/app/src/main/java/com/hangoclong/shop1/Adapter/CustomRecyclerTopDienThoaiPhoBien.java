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
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hangoclong.shop1.Model.ObjectClass.DanhGia;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Admin on 11/6/2017.
 */

public class CustomRecyclerTopDienThoaiPhoBien extends RecyclerView.Adapter<CustomRecyclerTopDienThoaiPhoBien.ViewHolderTT> {
    Context context;
    List<SanPham> sanPhamList;
    public  CustomRecyclerTopDienThoaiPhoBien(Context context,List<SanPham> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;

    }

    public class ViewHolderTT extends RecyclerView.ViewHolder {
        ImageView imSP;
        TextView txtTenSP,txtGia,txtTongBL,txtChuaDanhGia;
        LinearLayout lnTopDTPB;
        FrameLayout frDanhGia;
        RatingBar rdTongDanhGia;
        public ViewHolderTT(View itemView) {
            super(itemView);
            imSP = itemView.findViewById(R.id.imSanPham);
            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            txtGia = itemView.findViewById(R.id.txtGia);
            lnTopDTPB = itemView.findViewById(R.id.lnTopDienThoaiPB);
            txtTongBL = itemView.findViewById(R.id.txtTongBL);
            frDanhGia = itemView.findViewById(R.id.frDanhGia);
            rdTongDanhGia = itemView.findViewById(R.id.rdTongDanhGia);
            txtChuaDanhGia = itemView.findViewById(R.id.txtChuaDanhGia);

        }
    }


    @Override
    public ViewHolderTT onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycler_topdienthoaiphobien,parent,false);
        ViewHolderTT viewHolder = new ViewHolderTT(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderTT holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getAnhLon()).into(holder.imSP);

        TextView txt = new TextView(context);
        txt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(41)});
        txt.setText(sanPham.getTenSP());
        holder.txtTenSP.setText(txt.getText().toString()+"...");

        String underline = "<u>đ</u>";

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGia());
        holder.txtGia.setText(Html.fromHtml(String.format(underline) +" " + gia));
        holder.lnTopDTPB.setTag(sanPham.getMaSP());

        if(sanPham.getDanhGias().size()!=0)
        {
            List<DanhGia> danhGias = sanPham.getDanhGias();
            holder.txtChuaDanhGia.setVisibility(View.GONE);
            holder.frDanhGia.setVisibility(View.VISIBLE);
            float tongDG=0.0f;
            int tongBL = 0;
            for(int i = 0 ; i < danhGias.size();i++){

                tongDG += danhGias.get(i).getSOSAO();
                if(!danhGias.get(i).getNOIDUNG().equals("")){
                    tongBL+=1;
                }
            }
            float average = (float) tongDG/danhGias.size();
            holder.rdTongDanhGia.setRating(average);
            holder.txtTongBL.setText(" ("+tongBL+")");
        }
        holder.lnTopDTPB.setOnClickListener(new View.OnClickListener() {
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
