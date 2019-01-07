package com.lll.weidustore.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lll.weidustore.R;
import com.lll.weidustore.app.MyApp;
import com.lll.weidustore.bean.GoodsList;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyView> {
    private List<GoodsList.RxxpBean.CommodityListBean> list = new ArrayList<>();
    public void rxaddList(List<GoodsList.RxxpBean.CommodityListBean> user){
        if(user!=null){
            list.addAll(user);
        }
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_layout, viewGroup, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView myView, int i) {
        GoodsList.RxxpBean.CommodityListBean commodityListBean = list.get(i);
        myView.news_title.setText(commodityListBean.getCommodityName());
        myView.news_price.setText("￥"+commodityListBean.getPrice());
        Glide.with(MyApp.getContext()).load(commodityListBean.getMasterPic()).into(myView.news_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        private final ImageView news_image;
        private final TextView news_title;
        private final TextView news_price;

        public MyView(@NonNull View itemView) {
            super(itemView);
            news_image = itemView.findViewById(R.id.news_image);
            news_title = itemView.findViewById(R.id.news_title);
            news_price = itemView.findViewById(R.id.news_price);
        }
    }
}
