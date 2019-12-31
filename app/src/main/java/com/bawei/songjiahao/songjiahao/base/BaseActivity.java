package com.bawei.songjiahao.songjiahao.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bawei.songjiahao.songjiahao.contract.INewsContract;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity<P extends BaasePresenter> extends AppCompatActivity implements BaseView {
    public  P presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());

        presenter=initPresenter();
        if (presenter!=null){
            presenter.attach(this);
        }
        initView();
        initData();
    }

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.deatch();
        }

    }
}
