package com.geng.dragsortbyrecyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by gengjiarong
 * on 2017/11/10.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private boolean isInit;

    public SpaceItemDecoration() {
        super();
    }

    /**
     * Any content drawn by this method will be drawn before the item views are drawn,
     * and will thus appear underneath the views.(在子view之前绘制，绘制效果在子view的下面)
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    /**
     * Any content drawn by this method will be drawn after the item views are drawn
     * and will thus appear over the views(在子view之后绘制，绘制效果在子view的上面)
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     * <p>
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * before returning.
     * <p>
     * If you need to access Adapter for additional data, you can call
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * View.
     * <p>
     * <p>
     * 此方法，会在{@link RecyclerView#getItemDecorInsetsForChild(View)}中调用，设置子view的4个坐标点，控制子view的显示位置
     * <p>
     * for (int i = 0; i < decorCount; i++) {
     * mTempRect.set(0, 0, 0, 0);// 默认设置 0:
     * mItemDecorations.get(i).getItemOffsets(mTempRect, child, this, mState);
     * insets.left += mTempRect.left;
     * insets.top += mTempRect.top;
     * insets.right += mTempRect.right;
     * insets.bottom += mTempRect.bottom;
     * }
     *
     * 这个方法是挨个设置 view 的位置属性
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (!isInit) {
            initWidth(view, parent);
            isInit = true;
        }
        outRect.set(space, space/2, space, 0);
    }

    private void initWidth(View view, RecyclerView recyclerView) {
        int width = ScreenUtil.getScreenW(recyclerView.getContext());
        int viewWidth = view.getMeasuredWidth();
        int spanCount = ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
        space = (width - spanCount * viewWidth) / (spanCount * 8);
    }
}
