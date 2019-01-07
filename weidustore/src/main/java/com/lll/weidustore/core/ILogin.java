package com.lll.weidustore.core;

import com.lll.weidustore.bean.CircleBean;
import com.lll.weidustore.bean.GoodsBanner;
import com.lll.weidustore.bean.GoodsList;
import com.lll.weidustore.bean.Result;
import com.lll.weidustore.bean.SearchBean;
import com.lll.weidustore.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ILogin {

    /**
     * 登录
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("small/user/v1/login")
    Observable<Result<User>> login(@Field("phone") String mobile,
                                   @Field("pwd") String password);

    /**
     * 注册
     * @param mobile
     * @param password
     * @return
     */
    @POST("small/user/v1/register")
    @FormUrlEncoded
    Observable<Result> reg(@Field("phone") String mobile,
                                 @Field("pwd") String password);

    /**
     * banner轮播图
     * @return
     */
    @GET("small/commodity/v1/bannerShow")
    Observable<Result<List<GoodsBanner>>> bann();

    /**
     * 首页列表展示
     * @return
     */
    @GET("small/commodity/v1/commodityList")
    Observable<Result<GoodsList>> getlist();

    /**
     * 圈子
     * @param userId
     * @param sessionId
     * @param page
     * @param count
     * @return
     */
    @GET("small/circle/v1/findCircleList")
    Observable<Result<List<CircleBean>>> circle(
            @Header("userId") long userId,
            @Header("sessionId")String sessionId,
            @Query("page")int page,
            @Query("count")int count);

    /**
     * 搜索
     * @param keyword
     * @param page
     * @param count
     * @return
     */
    @GET("small/commodity/v1/findCommodityByKeyword")
    Observable<Result<List<SearchBean>>> search(@Query("keyword")String keyword,
                                                @Query("page")int page,
                                                @Query("count")int count);
}
