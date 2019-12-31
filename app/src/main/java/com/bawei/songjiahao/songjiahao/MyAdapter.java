package com.bawei.songjiahao.songjiahao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.songjiahao.songjiahao.model.entity.UserEntity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;

/**
 * 时间：2019/12/31 0031
 * 作者：Songjiahao
 * 类的作用：
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final Context context;
    private final List<UserEntity.RankingBean> ranking;

    public MyAdapter(Context context, List<UserEntity.RankingBean> ranking) {
        this.context = context;
        this.ranking = ranking;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(ranking.get(position).getAvatar())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .circleCrop()
                .into(holder.iv);
        holder.tv.setText(ranking.get(position).getName());
        holder.tv1.setText(ranking.get(position).getRank());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+ranking.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv1)
        TextView tv1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.tv);
            tv1=itemView.findViewById(R.id.tv1);
        }
    }

}
