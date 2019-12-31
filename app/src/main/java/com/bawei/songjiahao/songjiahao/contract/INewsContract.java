package com.bawei.songjiahao.songjiahao.contract;

import com.bawei.songjiahao.songjiahao.base.BaseModel;
import com.bawei.songjiahao.songjiahao.base.BaseView;
import com.bawei.songjiahao.songjiahao.model.entity.UserEntity;

/**
 * 时间：2019/12/31 0031
 * 作者：Songjiahao
 * 类的作用：
 */
public interface INewsContract {
    interface IModel extends BaseModel{
        interface ModelCallBack{
            void success(UserEntity userEntity);
            void failure(Throwable throwable);
        }
        void getUser(String url,ModelCallBack modelCallBack);
    }
    interface IView extends BaseView {
        void success(UserEntity userEntity);
        void failure(Throwable throwable);
    }
    interface IPresenter{
        void getUser(String url);
    }
}
