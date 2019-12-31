
package com.bawei.songjiahao.songjiahao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bawei.songjiahao.songjiahao.base.BaseActivity;
import com.bawei.songjiahao.songjiahao.contract.INewsContract;
import com.bawei.songjiahao.songjiahao.model.entity.UserEntity;
import com.bawei.songjiahao.songjiahao.presenter.Presenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity<Presenter> implements INewsContract.IView {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tvv)
    TextView tvv;
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        tvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getUser("http://blog.zhaoliang5156.cn/api/news/ranking.json");

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(UserEntity userEntity) {
        List<UserEntity.RankingBean> ranking = userEntity.getRanking();
        MyAdapter myAdapter = new MyAdapter(this,ranking);
        rv.setAdapter(myAdapter);
    }

    @Override
    public void failure(Throwable throwable) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();



    }
}
