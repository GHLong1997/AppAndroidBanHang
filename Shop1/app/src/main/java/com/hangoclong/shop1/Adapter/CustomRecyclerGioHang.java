package com.hangoclong.shop1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.InputFilter;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hangoclong.shop1.Model.GioHang.ModelGioHang;
import com.hangoclong.shop1.Model.ObjectClass.SanPham;
import com.hangoclong.shop1.R;
import com.hangoclong.shop1.View.GioHang.GioHangActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/17/2017.
 */

public class CustomRecyclerGioHang extends RecyclerView.Adapter<CustomRecyclerGioHang.ViewHolderGH>implements PreferenceManager.OnActivityResultListener {
    Context context;
    List<SanPham> sanPhamList;
    ModelGioHang modelGioHang;
    Activity activity;
    int kiemtra=0;
    public  static  List<Integer> DANHSACHCHECKED = new ArrayList<>();

    private SparseBooleanArray checkedState = new SparseBooleanArray();
    public CustomRecyclerGioHang(Context context, List<SanPham> sanPhamList,Activity activity){
        this.context  = context;
        this.sanPhamList = sanPhamList;
        this.activity = activity;
        modelGioHang = new ModelGioHang();
        for(int i =0;i<sanPhamList.size();i++){
            DANHSACHCHECKED.add(i);
        }


    }



    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent intent) {
        kiemtra = intent.getIntExtra("kiemtra",0);
        Log.d("kiemtttt",kiemtra+"");

        return true;
    }

    public class ViewHolderGH extends RecyclerView.ViewHolder {
   CheckBox cbSanPham;
        ImageView imSanPham;
        TextView txtTenSanPham,txtSoLuongSanPham,txtGia;
        ImageButton imGiam,imTang,imXoa;
        public ViewHolderGH(View itemView) {
            super(itemView);
            cbSanPham = itemView.findViewById(R.id.cbSanPham);
            imSanPham = itemView.findViewById(R.id.imSP);
            txtTenSanPham = itemView.findViewById(R.id.txtTenSP);
            txtSoLuongSanPham = itemView.findViewById(R.id.txtSoLuongSanPham);
            txtGia = itemView.findViewById(R.id.txtGia);
            imGiam = itemView.findViewById(R.id.imGiam);
            imTang = itemView.findViewById(R.id.imTang);
            imXoa = itemView.findViewById(R.id.imXoaSanPham);
        }
    }
    @Override
    public ViewHolderGH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycler_giohang,parent,false);
        ViewHolderGH viewHolderGH = new ViewHolderGH(view);
        return viewHolderGH;
    }

    @Override
    public void onBindViewHolder(final ViewHolderGH holder, final int position) {

        modelGioHang.MoKetNoi(context);
        final SanPham sanPham = sanPhamList.get(position);

                TextView txt = new TextView(context);
        txt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(28)});
        txt.setText(sanPham.getTenSP());
        holder.txtTenSanPham.setText(txt.getText().toString()+"...");

        //img san pham cua chung ta kieu byte nen phai chuyen ve kieu bitmap;
        byte[] hinhsanphamgiohang = sanPham.getHinhgiohang();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhsanphamgiohang,0,sanPham.getHinhgiohang().length);
        holder.imSanPham.setImageBitmap(bitmap);

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String underline = "<u>đ</u>";
        String gia = numberFormat.format(sanPham.getGia());
        holder.txtGia.setText(Html.fromHtml(String.format(underline) +" " + gia));

        holder.txtSoLuongSanPham.setText(sanPham.getSoluong()+"");

        holder.imXoa.setTag(sanPham.getMaSP());
        holder.imXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(activity, DiaLogXoa.class);
//                activity.startActivityForResult(intent,1);
                GioHangActivity.TONGTIEN-= (sanPhamList.get(position).getGia()*sanPhamList.get(position).getSoluong());
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String underline = "<u>đ</u>";
                String gia = numberFormat.format(GioHangActivity.TONGTIEN);
                GioHangActivity.txtTongTien.setText( Html.fromHtml(String.format(underline)+" " +gia));
                modelGioHang.XoaSanPhamTrongGioHang((int)view.getTag());
                sanPhamList.remove(position);
                notifyDataSetChanged();
                DANHSACHCHECKED = new ArrayList<Integer>();
                for(int i =0;i<sanPhamList.size();i++){

                    DANHSACHCHECKED.add(i);
                }
                GioHangActivity.temp--;

            }
        });
        holder.imTang.setTag(sanPham.getSoLuongTonKho());

        holder.imTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong =  Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());

                int soluongtonkho = (int)view.getTag();
                Log.d("soluontonkho",soluongtonkho+"");
                if(soluong < soluongtonkho){
                    soluong++;
                    GioHangActivity.TONGTIEN+=sanPham.getGia();
                    NumberFormat numberFormat = new DecimalFormat("###,###");
                    String underline = "<u>đ</u>";
                    String gia = numberFormat.format(GioHangActivity.TONGTIEN);

                    if(holder.cbSanPham.isChecked()){
                        GioHangActivity.txtTongTien.setText( Html.fromHtml(String.format(underline)+" " +gia));
                    }
                    holder.txtSoLuongSanPham.setText(String.valueOf(soluong));
                    sanPham.setSoluong(soluong);
                }else {
                    Toast.makeText(context,"Số lượng mua vượt quá số lượng tồn kho",Toast.LENGTH_SHORT).show();
                }


            }
        });

        holder.imGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong =  Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());
                if(soluong>1){
                    soluong--;
                    GioHangActivity.TONGTIEN-=sanPham.getGia();
                    NumberFormat numberFormat = new DecimalFormat("###,###");
                    String underline = "<u>đ</u>";
                    String gia = numberFormat.format(GioHangActivity.TONGTIEN);
                    if(holder.cbSanPham.isChecked()){
                        GioHangActivity.txtTongTien.setText( Html.fromHtml(String.format(underline)+" " +gia));
                    }

                    modelGioHang.CapNhatSoLuongSanPhamTrongGioHang(sanPham.getMaSP(),soluong);
                    holder.txtSoLuongSanPham.setText(String.valueOf(soluong));
                    sanPham.setSoluong(soluong);
                }else {
                    Toast.makeText(context,"Số lượng sản phẩm không thế thấp hơn 1",Toast.LENGTH_SHORT).show();
                }



            }
        });

        holder.cbSanPham.setTag(sanPham.getMaSP());

        holder.cbSanPham.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    DANHSACHCHECKED.add(position);
                    Log.d("kiemtttt",DANHSACHCHECKED.size() +"  " + sanPhamList.size());
                    if(DANHSACHCHECKED.size()==sanPhamList.size()){
                        GioHangActivity.KIEMTRA = true;
                        GioHangActivity.cbTatCa.setChecked(true);
                    }
                    GioHangActivity.TONGTIEN+=(sanPham.getGia()*sanPham.getSoluong());
                    NumberFormat numberFormat = new DecimalFormat("###,###");
                    String underline = "<u>đ</u>";
                    String gia = numberFormat.format(GioHangActivity.TONGTIEN);
                    GioHangActivity.txtTongTien.setText( Html.fromHtml(String.format(underline)+" " +gia));




                }else{
                    GioHangActivity.KIEMTRA= false;
                    GioHangActivity.cbTatCa.setChecked(false);
                    GioHangActivity.TONGTIEN -= (sanPham.getGia()*sanPham.getSoluong());
                    NumberFormat numberFormat = new DecimalFormat("###,###");
                    String underline = "<u>đ</u>";
                    String gia = numberFormat.format(GioHangActivity.TONGTIEN);
                    GioHangActivity.txtTongTien.setText( Html.fromHtml(String.format(underline)+" " +gia));

                    for(int i = 0;i<DANHSACHCHECKED.size();i++){
                        if(DANHSACHCHECKED.get(i)==position){

                            DANHSACHCHECKED.remove(i);
                            Log.d("bocheck",DANHSACHCHECKED.size()+"");
                        }
                    }
//                    Log.d("snapham",integerList.size()+"");

                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }




}
