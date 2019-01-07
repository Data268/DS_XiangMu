package com.lll.weidustore.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidustore.R;
import com.lll.weidustore.RecyclerGridView;
import com.lll.weidustore.SpacingItemDecoration;
import com.lll.weidustore.app.MyApp;
import com.lll.weidustore.bean.CircleBean;
import com.lll.weidustore.untils.DateUtils;
import com.lll.weidustore.untils.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.http.POST;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.MyHolder> {

    Context context;
    private List<CircleBean> list = new ArrayList<>();

    public CircleAdapter(Context context){
        this.context = context;
    }

    public void addAll(List<CircleBean> list){
        if (list!=null){
            this.list.addAll(list);
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.circle_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        CircleBean circleBean = list.get(i);
        myHolder.imageheader.setImageURI(Uri.parse(circleBean.getHeadPic()));
        myHolder.nickname.setText(circleBean.getNickName());
        try {
            myHolder.time_tv.setText(DateUtils.dateFormat(new Date(circleBean.getCreateTime()),DateUtils.MINUTE_PATTERN));
        } catch (Exception e) {
            e.printStackTrace();
        }
        myHolder.circle_content.setText(circleBean.getContent());

        if (StringUtils.isEmpty(circleBean.getImage())){
            myHolder.gridView.setVisibility(View.GONE);
        }else{
            myHolder.gridView.setVisibility(View.VISIBLE);
            String[] images = circleBean.getImage().split(",");
            int colNum;//列数
            if (images.length == 1){
                colNum = 1;
            }else if (images.length == 2||images.length == 4){
                colNum = 2;
            }else {
                colNum = 3;
            }
            myHolder.gridLayoutManager.setSpanCount(colNum);//设置列数
            myHolder.imageAdapter.clear();//清空
            myHolder.imageAdapter.addAll(Arrays.asList(images));
            myHolder.imageAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView imageheader;
        private final TextView nickname;
        private final TextView time_tv;
        private final TextView circle_content;
        private final RecyclerView gridView;
        private final ImageAdapter imageAdapter;
        private final GridLayoutManager gridLayoutManager;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageheader = itemView.findViewById(R.id.circle_header);
            nickname = itemView.findViewById(R.id.circle_nickname);
            time_tv = itemView.findViewById(R.id.circle_time);
            circle_content = itemView.findViewById(R.id.cirlce_tvcontent);
            gridView = itemView.findViewById(R.id.grid_view);
            imageAdapter = new ImageAdapter();
            int space = context.getResources().getDimensionPixelSize(R.dimen.dip_10);;//图片间距
            gridLayoutManager = new GridLayoutManager(context, 3);
            gridView.addItemDecoration(new SpacingItemDecoration(space));
            gridView.setLayoutManager(gridLayoutManager);
            gridView.setAdapter(imageAdapter);
        }
    }
}
