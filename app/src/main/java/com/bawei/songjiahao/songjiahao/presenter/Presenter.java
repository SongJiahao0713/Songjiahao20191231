package com.bawei.songjiahao.songjiahao.presenter;

import com.bawei.songjiahao.songjiahao.base.BaasePresenter;
import com.bawei.songjiahao.songjiahao.contract.INewsContract;
import com.bawei.songjiahao.songjiahao.model.Model;
import com.bawei.songjiahao.songjiahao.model.entity.UserEntity;

/**
 * 时间：2019/12/31 0031
 * 作者：Songjiahao
 * 类的作用：
 */
public class Presenter extends BaasePresenter<Model, INewsContract.IView> implements INewsContract.IPresenter {
    @Override
    protected Model initModel() {
        return new Model();
    }

    @Override
    public void getUser(String url) {
        model.getUser(url, new INewsContract.IModel.ModelCallBack() {
            @Override
            public void success(UserEntity userEntity) {
                getView().success(userEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                getView().failure(throwable);
            }
        });
    }
}
