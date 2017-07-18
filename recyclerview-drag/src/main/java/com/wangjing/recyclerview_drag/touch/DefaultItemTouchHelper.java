package com.wangjing.recyclerview_drag.touch;

import android.support.v7.widget.helper.WJCompatItemTouchHelper;

/**
 * 作者：Created by WangJing on 2017/7/6.
 * 邮箱：wangjinggm@gmail.com
 * 描述：TODO
 * 最近修改：2017/7/6 10:11 by WangJing
 */
public class DefaultItemTouchHelper extends WJCompatItemTouchHelper {

    private DefaultItemTouchHelperCallback mDefaultItemTouchHelperCallback;

    /**
     * Create default item touch helper.
     */
    public DefaultItemTouchHelper() {
        this(new DefaultItemTouchHelperCallback());
    }

    /**
     * @param callback the behavior of ItemTouchHelper.
     */
    private DefaultItemTouchHelper(DefaultItemTouchHelperCallback callback) {
        super(callback);
        mDefaultItemTouchHelperCallback = (DefaultItemTouchHelperCallback) getCallback();
    }

    /**
     * Set OnItemMoveListener.
     *
     * @param onItemMoveListener {@link OnItemMoveListener}.
     */
    public void setOnItemMoveListener(OnItemMoveListener onItemMoveListener) {
        mDefaultItemTouchHelperCallback.setOnItemMoveListener(onItemMoveListener);
    }

    /**
     * Get OnItemMoveListener.
     *
     * @return {@link OnItemMoveListener}.
     */
    public OnItemMoveListener getOnItemMoveListener() {
        return mDefaultItemTouchHelperCallback.getOnItemMoveListener();
    }

    /**
     * Set OnItemMovementListener.
     *
     * @param onItemMovementListener {@link OnItemMovementListener}.
     */
    public void setOnItemMovementListener(OnItemMovementListener onItemMovementListener) {
        mDefaultItemTouchHelperCallback.setOnItemMovementListener(onItemMovementListener);
    }

    /**
     * Get OnItemMovementListener.
     *
     * @return {@link OnItemMovementListener}.
     */
    public OnItemMovementListener getOnItemMovementListener() {
        return mDefaultItemTouchHelperCallback.getOnItemMovementListener();
    }

    /**
     * Set can long press drag.
     *
     * @param canDrag drag true, otherwise is can't.
     */
    public void setLongPressDragEnabled(boolean canDrag) {
        mDefaultItemTouchHelperCallback.setLongPressDragEnabled(canDrag);
    }

    /**
     * Get can long press drag.
     *
     * @return drag true, otherwise is can't.
     */
    public boolean isLongPressDragEnabled() {
        return mDefaultItemTouchHelperCallback.isLongPressDragEnabled();
    }

    /**
     * Get can long press swipe.
     *
     * @return swipe true, otherwise is can't.
     */
    public boolean isItemViewSwipeEnabled() {
        return this.mDefaultItemTouchHelperCallback.isItemViewSwipeEnabled();
    }

    /**
     * Set OnItemStateChangedListener.
     *
     * @param onItemStateChangedListener {@link OnItemStateChangedListener}.
     */
    public void setOnItemStateChangedListener(OnItemStateChangedListener onItemStateChangedListener) {
        this.mDefaultItemTouchHelperCallback.setOnItemStateChangedListener(onItemStateChangedListener);
    }

    /**
     * Get OnItemStateChangedListener.
     *
     * @return {@link OnItemStateChangedListener}.
     */
    public OnItemStateChangedListener getOnItemStateChangedListener() {
        return this.mDefaultItemTouchHelperCallback.getOnItemStateChangedListener();
    }

}
