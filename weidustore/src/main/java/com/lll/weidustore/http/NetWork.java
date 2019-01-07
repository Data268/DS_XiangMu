package com.lll.weidustore.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWork {

    private static NetWork netWork = new NetWork();
    private Retrofit retrofit;

    private NetWork(){
        init();
    }
    public static NetWork netWork(){
        if (netWork == null){
            netWork = new NetWork();
        }
        return netWork;
    }


    private void init() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://172.17.8.100/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用Rxjava对回调数据进行处理
                .addConverterFactory(GsonConverterFactory.create())//响应结果的解析器，包含gson，xml，protobuf
                .build();
    }
    public <T> T create(final Class<T> service){
        return retrofit.create(service);
    }
}
