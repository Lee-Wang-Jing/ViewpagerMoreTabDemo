package com.qianfan.viewpagermoretabdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfan.viewpagermoretabdemo.OnTabLongClick;
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

public class LongDragTabActivityAdapter extends RecyclerView.Adapter<LongDragTabActivityAdapter.ItemViewHolder> {

    private List<String> infos;

    DragRecyclerView recyclerview;

    private boolean needDrag = false;

    private OnTabLongClick onTabLongClick;

    public LongDragTabActivityAdapter() {
        this.infos = new ArrayList<>();
    }

    public LongDragTabActivityAdapter(DragRecyclerView recyclerview) {
        this.infos = new ArrayList<>();
        this.recyclerview = recyclerview;
    }

    public void setOnTabLongClick(OnTabLongClick onTabLongClick) {
        this.onTabLongClick = onTabLongClick;
    }

    public boolean isNeedDrag() {
        return needDrag;
    }

    public void setNeedDrag(boolean needDrag) {
        this.needDrag = needDrag;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_longdragadapter, parent, false);
        return new ItemViewHolder(view);
    }

    public void addData(List<String> infos) {
        if (infos != null) {
            this.infos = infos;
            notifyDataSetChanged();
        }
    }

    public List<String> getData() {
        if (infos == null) {
            infos = new ArrayList<>();
        }
        return this.infos;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.btn_name.setText("" + infos.get(position));
        holder.btn_name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                needDrag = true;
                if (onTabLongClick != null) {
                    onTabLongClick.onTabLongClick();
                }
                notifyDataSetChanged();
                return true;
            }
        });
        if (needDrag) {
            recyclerview.startDrag(holder);
            holder.imv_del.setVisibility(View.VISIBLE);
            holder.imv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("onClick", "position==>" + position);
                    Log.e("onClick", "getLayoutPosition==>" + holder.getLayoutPosition());
                    infos.remove(holder.getLayoutPosition());
                    notifyItemRemoved(holder.getLayoutPosition());
                    //notifyItemRemoved造成Position混乱的问题,remove之后需要刷新一下
//                    notifyItemRangeChanged(0, infos.size());
                }
            });
            holder.btn_name.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int action = motionEvent.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_DOWN: {
                            if (needDrag){
                                recyclerview.startDrag(holder);
                            }
                            break;
                        }
                    }
                    return false;
                }
            });
        } else {
            holder.imv_del.setVisibility(View.GONE);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return infos != null ? infos.size() : 0;
    }

    //    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView btn_name;
        ImageView imv_del;

        public ItemViewHolder(View itemView) {
            super(itemView);
            btn_name = itemView.findViewById(R.id.btn_name);
            imv_del = itemView.findViewById(R.id.imv_del);
            imv_del.setVisibility(View.GONE);
//            btn_name.setOnTouchListener(this);
        }

//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            int action = motionEvent.getAction();
//            switch (action) {
//                case MotionEvent.ACTION_DOWN: {
//                    recyclerview.startDrag(this);
//                    break;
//                }
//            }
//            return false;
//        }
    }
}
