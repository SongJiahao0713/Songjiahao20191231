package com.bawei.songjiahao.songjiahao.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 时间：2019/12/31 0031
 * 作者：Songjiahao
 * 类的作用：OkHttpUtils工具类
 */
public class OkHttpUtils {
    private static OkHttpUtils okHttp;
    Handler handler=new Handler();
    private final OkHttpClient okHttpClient;

    private OkHttpUtils() {
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(3, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

    }

    public static OkHttpUtils getInstance() {
        if (okHttp == null) {
            synchronized (OkHttpUtils.class){
                if (okHttp == null) {
                    okHttp=new OkHttpUtils();
                }
            }
        }
        return okHttp;
    }


    public void doGet(String url,OkHttpCallBack okHttpCallBack){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okHttpCallBack.failure(e);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                handler.post(new Runnable() {
                    String s = response.body().string();
                    @Override
                    public void run() {
                        okHttpCallBack.success(s);
                    }
                });
            }
        });
    }

    OkHttpCallBack okHttpCallBack;

    public void setOkHttpCallBack(OkHttpCallBack okHttpCallBack) {
        this.okHttpCallBack = okHttpCallBack;
    }

    public interface OkHttpCallBack{
        void success(String s);
        void failure(Throwable throwable);
    }
}
