package com.lll.weidustore.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApp extends Application {
    /** 主线程ID */
    private static int mMainThreadId = -1;
    /** 主线程ID */
    private static Thread mMainThread;
    /** 主线程Handler */
    private static Handler mMainThreadHandler;
    /** 主线程Looper */
    private static Looper mMainLooper;

    /**
     * context 全局唯一的上下文
     */

    private static SharedPreferences sharedPreferences;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = this;
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        sharedPreferences = getSharedPreferences("share.xml",MODE_PRIVATE);
    }
    public static Context getContext(){
        return context;
    }
    public static SharedPreferences getShare(){
        return sharedPreferences;
    }
    /** 获取主线程ID */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /** 获取主线程 */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /** 获取主线程的handler */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /** 获取主线程的looper */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }
}
