package com.hangoclong.shop1.Model.ObjectClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hangoclong.shop1.View.DanhMucConActivity.Fragment.FragmentPhoBien;

/**
 * Created by Admin on 10/3/2017.
 */

public class LoadMoreScroll extends RecyclerView.OnScrollListener {
    int itemandautien = 0;
    int tongitem = 0;
    int itemloadtruoc = 6;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;
    //chúng ta phải nhận vvaof thăng layoutMnager vi nó là giao dien custom cua thwang recycleview
    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore){
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;//chúng ta làm thằng này là vi sau khi mấy thằng khác kế thừa thăng ILoadMỏe thì khi scroll nó gặp thằng này thì nó tiến hành download dữ liệu tiếp và gắn vào cho chúng ta
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongitem = layoutManager.getItemCount();

        //chúng ta phải hiểu rằng thằng layoutmanager nhận vào được hiển thì dạng nào
        if(layoutManager instanceof LinearLayoutManager){
            //nhàn vào thằng item ẩn phía trên đầu khi mà scroll lên(khi scroll len là mấy  thăng  vừa scroll năm trên đầu và bị ẩn đi)
            itemandautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }else  if (layoutManager instanceof GridLayoutManager){
            itemandautien = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        //lấy tổng item trong layoutmanager(vi du co 10 san phầm thì no tra ra 10 san pham , mắc dù hiện tại đang hien thi 6)
        if(tongitem<= (itemandautien + itemloadtruoc)){//khi ma thawng tong 20 =20 thi no load them 20 item nua , luc do tongitme =40, cu the no laod tiep
            Log.d("kiemtratong", tongitem + "-" + itemandautien);
            FragmentPhoBien.DEM+=2;
            iLoadMore.LoadMore(FragmentPhoBien.DEM);
        }

        //ví dụ có 20 san phầm , mà gaio dien ta custom hien thi dc 6 san pham trực tiếp và khi chúng ta scroll hết thi itemandautien=14(nó bị an lên đầu), còn 6 item còn lại đang hiện thị trực tiếp
    }
}
