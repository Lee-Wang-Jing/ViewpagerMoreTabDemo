package com.qianfan.viewpagermoretabdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.qianfan.viewpagermoretabdemo.R;
import com.wangjing.recyclerview_drag.DragRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Created by WangJing on 2017/7/6.
 * 邮箱：wangjinggm@gmail.com
 * 描述：TODO
 * 最近修改：2017/7/6 10:48 by WangJing
 */

public class ChooseTabActivityAdapter extends RecyclerView.Adapter<ChooseTabActivityAdapter.ItemViewHolder> {

    private List<String> infos;

    DragRecyclerView recyclerview;

    public ChooseTabActivityAdapter() {
        this.infos = new ArrayList<>();
    }

    public ChooseTabActivityAdapter(DragRecyclerView recyclerview) {
        this.infos = new ArrayList<>();
        this.recyclerview = recyclerview;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choosetabadapter, parent, false);
        return new ItemViewHolder(view);
    }

    public void addData(List<String> infos) {
        if (infos != null) {
            this.infos.clear();
            this.infos.addAll(infos);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.btn_name.setText("" + infos.get(position));
    }

    @Override
    public int getItemCount() {
        return infos != null ? infos.size() : 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        Button btn_name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            btn_name = itemView.findViewById(R.id.btn_name);
            btn_name.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN: {
                    recyclerview.startDrag(this);
                    break;
                }
            }
            return false;
        }
    }
}
