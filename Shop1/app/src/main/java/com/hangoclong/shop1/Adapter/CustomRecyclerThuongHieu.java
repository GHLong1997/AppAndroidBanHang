package com.hangoclong.shop1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hangoclong.shop1.Model.ObjectClass.ThuongHieu;
import com.hangoclong.shop1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 10/31/2017.
 */

public class CustomRecyclerThuongHieu extends RecyclerView.Adapter<CustomRecyclerThuongHieu.ViewHolderTH> {
    Context context;
    List<ThuongHieu> thuongHieuList;

    public CustomRecyclerThuongHieu(Context context, List<ThuongHieu> thuongHieuList){
        this.context = context;
        this.thuongHieuList = thuongHieuList;
    }
    public class ViewHolderTH extends RecyclerView.ViewHolder {
        ImageView imThuongHieu;

        public ViewHolderTH(View itemView) {
            super(itemView);
            imThuongHieu = itemView.findViewById(R.id.imThuongHieu);

        }
    }

    @Override
    public ViewHolderTH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycler_thuonghieu,parent,false);
        ViewHolderTH viewHolderTH = new ViewHolderTH(view);
        return viewHolderTH;
    }

    @Override
    public void onBindViewHolder(ViewHolderTH holder, int position) {
        ThuongHieu thuongHieu = thuongHieuList.get(position);
        Picasso.with(context).load(thuongHieu.getHinhThuongHieu()).into(holder.imThuongHieu);
        holder.imThuongHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, DanhMucActivity.class);
//                Log.d("ssss",(int)view.getTag()+"");
//                intent.putExtra("mathuonghieu",(int)view.getTag());
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }


}
