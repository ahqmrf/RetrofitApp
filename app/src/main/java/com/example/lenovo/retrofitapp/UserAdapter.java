package com.example.lenovo.retrofitapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lenovo on 1/10/2017.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User.ItemsBean> itemsBeanList;

    public UserAdapter(List<User.ItemsBean> itemsBeanList) {
        this.itemsBeanList = itemsBeanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(itemsBeanList.get(position).getLogin());
        holder.score.setText(itemsBeanList.get(position).getScore() + "");
    }

    @Override
    public int getItemCount() {
        return itemsBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title, score;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            score = (TextView) itemView.findViewById(R.id.count);
        }
    }
}
