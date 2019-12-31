package com.bawei.songjiahao.songjiahao;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.songjiahao.songjiahao.base.BaasePresenter;
import com.bawei.songjiahao.songjiahao.base.BaseActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class Main2Activity extends BaseActivity {

    @BindView(R.id.ivv)
    ImageView ivv;
    @BindView(R.id.tb1)
    Button tb1;
    @BindView(R.id.tb2)
    Button tb2;
    private ImageView ivv1;

    @Override
    protected BaasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        CodeUtils.init(this);
        EventBus.getDefault().register(this);
        ivv1 = findViewById(R.id.ivv);
        String ss="宋家豪";

                Bitmap image = CodeUtils.createImage(ss, 150, 150, null);
                ivv1.setImageBitmap(image);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main2;
    }
    

    @OnClick({R.id.tb1, R.id.tb2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tb1:
                EventBus.getDefault().post("微信");
                break;
            case R.id.tb2:
                EventBus.getDefault().postSticky("QQ");
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getWeiXin(String s){
        Toast.makeText(Main2Activity.this, "微信"+s, Toast.LENGTH_SHORT).show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getQQ(String q){
        Toast.makeText(Main2Activity.this, "QQ"+q, Toast.LENGTH_SHORT).show();
    }
    
    @OnLongClick(R.id.ivv)
    public void getLong(){
        if (ivv!=null){
            CodeUtils.analyzeByImageView(ivv, new CodeUtils.AnalyzeCallback() {
                @Override
                public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                    Toast.makeText(Main2Activity.this, ""+result, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnalyzeFailed() {
                    Toast.makeText(Main2Activity.this, "解析失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
