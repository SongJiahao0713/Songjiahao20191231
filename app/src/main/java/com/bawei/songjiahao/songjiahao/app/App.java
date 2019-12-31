package com.bawei.songjiahao.songjiahao.app;

import android.app.Application;
import android.content.Context;

/**
 * 时间：2019/12/31 0031
 * 作者：Songjiahao
 * 类的作用：
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
