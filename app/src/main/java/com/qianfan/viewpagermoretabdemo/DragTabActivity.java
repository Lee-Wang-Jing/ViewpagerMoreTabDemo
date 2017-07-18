package com.qianfan.viewpagermoretabdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.qianfan.viewpagermoretabdemo.adapter.ChooseTabActivityAdapter;
import com.wangjing.recyclerview_drag.DragRecyclerView;
import com.wangjing.recyclerview_drag.touch.OnItemMoveListener;
import com.wangjing.recyclerview_drag.touch.OnItemMovementListener;
import com.wangjing.recyclerview_drag.touch.OnItemStateChangedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者：Created by WangJing on 2017/7/6.
 * 邮箱：wangjinggm@gmail.com
 * 描述：TODO
 * 最近修改：2017/7/6 10:11 by WangJing
 */

public class DragTabActivity extends AppCompatActivity {
    private DragRecyclerView recyclerview;
    private ChooseTabActivityAdapter adapter;

    private final String[] mTitles = {
            "头条", "视频"
            , "娱乐"
            , "体育"
            , "北京"
            , "网易号"
            , "24小时热点"
            , "今日头条"
            , "活动"
            , "好友动态"
            , "外链"
            , "本地圈小视频"
            , "薄荷"
            , "财经"
            , "科技"
            , "汽车"
            , "社会"
            , "军事"
            , "时尚"
            , "直播"
            , "图片"
            , "跟帖"
            , "NBA"
            , "热点"
    };

    private List<String> infos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosttab);
        recyclerview = (DragRecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        adapter = new ChooseTabActivityAdapter(recyclerview);
        recyclerview.setAdapter(adapter);
        recyclerview.setLongPressDragEnabled(true);
        recyclerview.setOnItemMoveListener(onItemMoveListener);// 监听拖拽，更新UI。
        recyclerview.setOnItemStateChangedListener(mOnItemStateChangedListener);
        recyclerview.setOnItemMovementListener(onItemMovementListener);

        infos = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            infos.add(mTitles[i]);
        }
        adapter.addData(infos);
    }


    /**
     * 当Item移动的时候。
     */
    private OnItemMoveListener onItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            Log.e("onItemMove", "onItemMove");
            if (toPosition == 0) {// 保证第一个不被挤走。
                return false;
            }
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++)
                    Collections.swap(infos, i, i + 1);
            } else {
                for (int i = fromPosition; i > toPosition; i--)
                    Collections.swap(infos, i, i - 1);
            }
            adapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(int position) {
            infos.remove(position);
            adapter.notifyItemRemoved(position);
            Toast.makeText(DragTabActivity.this, "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
        }

    };

    /**
     * Item的拖拽/侧滑删除时，手指状态发生变化监听。
     */
    private OnItemStateChangedListener mOnItemStateChangedListener = (viewHolder, actionState) -> {
        if (actionState == OnItemStateChangedListener.ACTION_STATE_DRAG) {
            Log.e("StateChangedListener", "状态：拖拽");
            // 拖拽的时候背景就透明了，这里我们可以添加一个特殊背景。
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(this, R.color.white_pressed));
        } else if (actionState == OnItemStateChangedListener.ACTION_STATE_SWIPE) {
            Log.e("StateChangedListener", "状态：滑动删除");
        } else if (actionState == OnItemStateChangedListener.ACTION_STATE_IDLE) {
            Log.e("StateChangedListener", "状态：手指松开");
            // 在手松开的时候还原背景。
            ViewCompat.setBackground(viewHolder.itemView, ContextCompat.getDrawable(this, R.drawable.select_white));
            for (int i = 0; i < adapter.getData().size(); i++) {
                Log.e("StateChangedListener", i + "==>" + adapter.getData().get(i));
            }
        }
    };


    /**
     * 当Item被移动之前。
     */
    public static OnItemMovementListener onItemMovementListener = new OnItemMovementListener() {
        /**
         * 当Item在移动之前，获取拖拽的方向。
         * @param recyclerView     {@link RecyclerView}.
         * @param targetViewHolder target ViewHolder.
         * @return
         */
        @Override
        public int onDragFlags(RecyclerView recyclerView, RecyclerView.ViewHolder targetViewHolder) {
            // 我们让第一个不能拖拽。
            if (targetViewHolder.getAdapterPosition() == 0) {
                return OnItemMovementListener.INVALID;// 返回无效的方向。
            }

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//            if (layoutManager instanceof LinearLayoutManager) {// 如果是LinearLayoutManager。
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
//                if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {// 横向的List。
//                    return (OnItemMovementListener.LEFT | OnItemMovementListener.RIGHT); // 只能左右拖拽。
//                } else {// 竖向的List。
//                    return OnItemMovementListener.UP | OnItemMovementListener.DOWN; // 只能上下拖拽。
//                }
//            }
//            else
                if (layoutManager instanceof GridLayoutManager) {// 如果是Grid。
                return OnItemMovementListener.LEFT | OnItemMovementListener.RIGHT | OnItemMovementListener.UP |
                        OnItemMovementListener.DOWN; // 可以上下左右拖拽。
            }
            return OnItemMovementListener.INVALID;// 返回无效的方向。
        }

        @Override
        public int onSwipeFlags(RecyclerView recyclerView, RecyclerView.ViewHolder targetViewHolder) {
//            // 我们让第一个不能滑动删除。
//            if (targetViewHolder.getAdapterPosition() == 0) {
//                return OnItemMovementListener.INVALID;// 返回无效的方向。
//            }
//
//            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//            if (layoutManager instanceof LinearLayoutManager) {// 如果是LinearLayoutManager
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
//                if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {// 横向的List。
//                    return OnItemMovementListener.UP | OnItemMovementListener.DOWN; // 只能上下滑动删除。
//                } else {// 竖向的List。
//                    return OnItemMovementListener.LEFT | OnItemMovementListener.RIGHT; // 只能左右滑动删除。
//                }
//            }
            return OnItemMovementListener.INVALID;// 其它均返回无效的方向。
        }
    };
}
