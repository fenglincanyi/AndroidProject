package com.chinahr.android.common.im.message.relay;


import android.view.View;

public interface OnItemClickListener {
    /**
     * item点击
     *
     * @param view
     * @param position
     */
    void onItemClick(View view, int position);

    /**
     * 滑动删除
     *
     * @param position
     */
    void onDeleteClick(int position);
}
