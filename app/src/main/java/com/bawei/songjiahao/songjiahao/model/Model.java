package com.bawei.songjiahao.songjiahao.model;

import com.bawei.songjiahao.songjiahao.contract.INewsContract;
import com.bawei.songjiahao.songjiahao.model.entity.UserEntity;
import com.bawei.songjiahao.songjiahao.utils.OkHttpUtils;
import com.google.gson.Gson;

/**
 * 时间：2019/12/31 0031
 * 作者：Songjiahao
 * 类的作用：
 */
public class Model implements INewsContract.IModel {

    @Override
    public void getUser(String url, ModelCallBack modelCallBack) {
        OkHttpUtils.getInstance().doGet(url, new OkHttpUtils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                UserEntity userEntity = new Gson().fromJson(s, UserEntity.class);
                modelCallBack.success(userEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                modelCallBack.failure(throwable);
            }
        });
    }
}
