package com.wangjing.recyclerview_drag;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.wangjing.recyclerview_drag.touch.DefaultItemTouchHelper;
import com.wangjing.recyclerview_drag.touch.OnItemMoveListener;
import com.wangjing.recyclerview_drag.touch.OnItemMovementListener;
import com.wangjing.recyclerview_drag.touch.OnItemStateChangedListener;

/**
 * 作者：Created by WangJing on 2017/7/6.
 * 邮箱：wangjinggm@gmail.com
 * 描述：拖拽RecyclerView
 * 最近修改：2017/7/6 10:53 by WangJing
 */

public class DragRecyclerView extends RecyclerView {

    private DefaultItemTouchHelper mDefaultItemTouchHelper;

    public DragRecyclerView(Context context) {
        this(context, null);
    }

    public DragRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initializeItemTouchHelper() {
        if (mDefaultItemTouchHelper == null) {
            mDefaultItemTouchHelper = new DefaultItemTouchHelper();
            mDefaultItemTouchHelper.attachToRecyclerView(this);
        }
    }

    /**
     * Set OnItemMoveListener.
     *
     * @param onItemMoveListener {@link OnItemMoveListener}.
     */
    public void setOnItemMoveListener(OnItemMoveListener onItemMoveListener) {
        initializeItemTouchHelper();
        mDefaultItemTouchHelper.setOnItemMoveListener(onItemMoveListener);
    }

    /**
     * Set OnItemMovementListener.
     *
     * @param onItemMovementListener {@link OnItemMovementListener}.
     */
    public void setOnItemMovementListener(OnItemMovementListener onItemMovementListener) {
        initializeItemTouchHelper();
        mDefaultItemTouchHelper.setOnItemMovementListener(onItemMovementListener);
    }

    /**
     * Set OnItemStateChangedListener.
     *
     * @param onItemStateChangedListener {@link OnItemStateChangedListener}.
     */
    public void setOnItemStateChangedListener(OnItemStateChangedListener onItemStateChangedListener) {
        this.mDefaultItemTouchHelper.setOnItemStateChangedListener(onItemStateChangedListener);
    }

    /**
     * Get OnItemStateChangedListener.
     *
     * @return {@link OnItemStateChangedListener}.
     */
    public OnItemStateChangedListener getOnItemStateChangedListener() {
        return this.mDefaultItemTouchHelper.getOnItemStateChangedListener();
    }


    /**
     * Set can long press drag.
     *
     * @param canDrag drag true, otherwise is can't.
     */
    public void setLongPressDragEnabled(boolean canDrag) {
        initializeItemTouchHelper();
        mDefaultItemTouchHelper.setLongPressDragEnabled(canDrag);
    }

    /**
     * Get can long press drag.
     *
     * @return drag true, otherwise is can't.
     */
    public boolean isLongPressDragEnabled() {
        initializeItemTouchHelper();
        return this.mDefaultItemTouchHelper.isLongPressDragEnabled();
    }

    /**
     * Start drag a item.
     *
     * @param viewHolder the ViewHolder to start dragging. It must be a direct child of RecyclerView.
     */
    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        initializeItemTouchHelper();
        mDefaultItemTouchHelper.startDrag(viewHolder);
    }

}
