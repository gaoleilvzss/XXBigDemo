package com.vinsuan.xiangxuelearnbigdemo.rxjava;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/3
 * desc :
 */
public class HttpUtils {
    public static final String TAG = "HttpUtils";
    public static String BASE_URL = "https://www.wanandroid.com/";

    public static void setBaseUrl(String url) {
        BASE_URL = url;
    }

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(getOkhttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static OkHttpClient getOkhttpClient() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient client = httpBuilder
                .readTimeout(10000, TimeUnit.SECONDS)
                .connectTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .build();
        return client;
    }

}
