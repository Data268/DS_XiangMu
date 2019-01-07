package com.lll.weidustore.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidustore.R;
import com.lll.weidustore.SearchActivity;
import com.lll.weidustore.adapter.FinAdapter;
import com.lll.weidustore.adapter.LifAdapter;
import com.lll.weidustore.adapter.NewsAdapter;
import com.lll.weidustore.bean.GoodsBanner;
import com.lll.weidustore.bean.GoodsList;
import com.lll.weidustore.bean.Result;
import com.lll.weidustore.core.DataCall;
import com.lll.weidustore.core.exception.ApiException;
import com.lll.weidustore.presenter.BannerPresenter;
import com.lll.weidustore.presenter.GoodsListPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag_01 extends Fragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recy_news)
    RecyclerView recyNews;
    @BindView(R.id.recy_fin)
    RecyclerView recyFin;
    @BindView(R.id.recy_lif)
    RecyclerView recyLif;
    @BindView(R.id.search_text)
    TextView searchtext;
    Unbinder unbinder;
    private BannerPresenter bannerPresenter;
    private GoodsListPresenter goodsListPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_01, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.search_text)
    public void onclick(){
        startActivity(new Intent(getActivity(),SearchActivity.class));
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bannerPresenter = new BannerPresenter(new BannerCall());
        bannerPresenter.reqeust();
        goodsListPresenter = new GoodsListPresenter(new GoodsCall());
        goodsListPresenter.reqeust();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class BannerCall implements DataCall<Result<List<GoodsBanner>>> {


        @Override
        public void success(Result<List<GoodsBanner>> data) {
            if (data.getStatus().equals("0000")){
                List<String> list = new ArrayList<>();
                List<GoodsBanner> result = (List<GoodsBanner>) data.getResult();
                for (int i = 0; i < result.size(); i++) {
                    list.add(result.get(i).getImageUrl());
                }
                banner.setImages(list);
                banner.setImageLoader(new MyBanner());
                banner.start();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
        }
    }

    private class MyBanner extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Uri parse = Uri.parse((String) path);
            imageView.setImageURI(parse);
        }

        @Override
        public ImageView createImageView(Context context) {
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) View.inflate(getContext(), R.layout.banner_layout, null);
            return simpleDraweeView;
        }
    }

    private class GoodsCall implements DataCall<Result<GoodsList>> {

        @Override
        public void success(Result<GoodsList> data) {
            if(data.getStatus().equals("0000")){
                List<GoodsList.RxxpBean> rxxp = data.getResult().getRxxp();
                List<GoodsList.RxxpBean.CommodityListBean> commodityList = rxxp.get(0).getCommodityList();
                List<GoodsList.PzshBean> pzsh = data.getResult().getPzsh();
                List<GoodsList.PzshBean.CommodityListBeanX> commodityList1 = pzsh.get(0).getCommodityList();
                List<GoodsList.MlssBean> mlss = data.getResult().getMlss();
                List<GoodsList.MlssBean.CommodityListBeanXX> commodityList2 = mlss.get(0).getCommodityList();

                //自定义适配器
                NewsAdapter newsAdapter = new NewsAdapter();
                FinAdapter finAdapter = new FinAdapter();
                LifAdapter lifAdapter = new LifAdapter();

                newsAdapter.rxaddList(commodityList);
                finAdapter.rxaddList(commodityList2);
                lifAdapter.rxaddList(commodityList1);

                //设置瀑布流
                StaggeredGridLayoutManager news = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                StaggeredGridLayoutManager fin = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                StaggeredGridLayoutManager lif = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                //设置适配器
                recyNews.setLayoutManager(news);
                recyNews.setAdapter(newsAdapter);
                recyFin.setLayoutManager(fin);
                recyFin.setAdapter(finAdapter);
                recyLif.setLayoutManager(lif);
                recyLif.setAdapter(lifAdapter);
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getContext(), e.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
