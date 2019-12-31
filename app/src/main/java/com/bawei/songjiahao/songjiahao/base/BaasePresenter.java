package com.bawei.songjiahao.songjiahao.base;

import java.lang.ref.WeakReference;

/**
 * 时间：2019/12/31 0031
 * 作者：Songjiahao
 * 类的作用：
 */
public abstract class BaasePresenter<M extends BaseModel,V extends BaseView> {
    public M model;
    private WeakReference<V> weakReference;

    public BaasePresenter() {
        model=initModel();
    }

    protected abstract M initModel();

    public void attach(V v){
        weakReference = new WeakReference<>(v);
    }

    public V getView(){
        return weakReference.get();
    }
    public void deatch(){
        if (weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
    }
}
