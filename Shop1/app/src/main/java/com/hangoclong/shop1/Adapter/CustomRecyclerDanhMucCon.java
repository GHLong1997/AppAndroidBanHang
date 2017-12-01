package com.hangoclong.shop1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hangoclong.shop1.Model.ObjectClass.LoaiSanPham;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.DanhMucConActivity.DanhMucConActivity;

import java.util.List;

/**
 * Created by Admin on 10/31/2017.
 */

public class CustomRecyclerDanhMucCon extends RecyclerView.Adapter<CustomRecyclerDanhMucCon.ViewHolderDMCCC>{
    int temp=1;
    List<LoaiSanPham> loaiSanPhamList;
    Context context;
    Activity activity;
    public CustomRecyclerDanhMucCon(Context context, List<LoaiSanPham> loaiSanPhamList, Activity activity){
        this.context = context;
        this.loaiSanPhamList = loaiSanPhamList;
        this.activity = activity;
    }

    public class ViewHolderDMCCC extends RecyclerView.ViewHolder {

        Button btnDMC;
        public ViewHolderDMCCC(View itemView) {
            super(itemView);
            btnDMC = itemView.findViewById(R.id.btnDanhMucConCuaCon);
        }
    }

    @Override
    public ViewHolderDMCCC onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycler_danhmucon,parent,false);

        ViewHolderDMCCC viewHolderDMCCC = new ViewHolderDMCCC(view);
        return viewHolderDMCCC;
    }

    @Override
    public void onBindViewHolder(final ViewHolderDMCCC holder, int position) {
        LoaiSanPham loaiSanPham = loaiSanPhamList.get(position);
        holder.btnDMC.setText(loaiSanPham.getTENLOAISP());


        holder.btnDMC.setBackgroundResource(R.drawable.vienchunhat_xanh +temp );
        temp++;
        if(temp>3){
            temp=1;
        }
        holder.btnDMC.setTag(loaiSanPham.getMALOAISP());
        holder.btnDMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhMucConActivity.class);
                intent.putExtra("malspham",(int)view.getTag());
                Log.d("tataaaa",(int)view.getTag()+"");
                context.startActivity(intent);
               activity.overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });

    }

    @Override
    public int getItemCount() {
        return loaiSanPhamList.size();
    }



}
