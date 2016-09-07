package com.ares.gear.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ares.gear.R;
import com.ares.gear.interfaces.MainRecycleItemListener;
import com.ares.gear.utils.Logs;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.MyViewHolder> {

    private List<String> titleList;
    private Context context;

    private MainRecycleItemListener clickListener;

    private MainRecycleItemListener clickLongListener;

    public MainRecycleAdapter(Context context, List<String> titleList) {
        this.titleList = titleList;
        this.context = context;
    }

    public void setClickListener(MainRecycleItemListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setClickLongListener(MainRecycleItemListener clickLongListener) {
        this.clickLongListener = clickLongListener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Logs.e("MainRecycleAdapter.onBindViewHolder-->"+(position % titleList.size()));
        holder.titleTv.setText(titleList.get(position));
        if (clickListener != null) {
            holder.main_recycle_item_title_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v, position);
                }
            });
        }
        if (clickLongListener != null) {
            holder.main_recycle_item_title_ll.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickLongListener.onItemLongClick(v, position);
                    return true;
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.main_recycle_title_item_layout, parent,
                false));
        return holder;
    }

    @Override
    public int getItemCount() {
        return (titleList != null && titleList.size() > 0) ? titleList.size() : Integer.MAX_VALUE;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        LinearLayout main_recycle_item_title_ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.main_recycle_item_title_tv);
            main_recycle_item_title_ll = (LinearLayout) itemView.findViewById(R.id.main_recycle_item_title_ll);
        }
    }
}