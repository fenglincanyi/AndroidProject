package com.geng.dragsortbyrecyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gengjiarong
 * on 2017/11/10.
 */

public class MoveItemHelper extends ItemTouchHelper {

    public MoveItemHelper() {
        this(new MoveCallBack());
    }

    /**
     * Creates an ItemTouchHelper that will work with the given Callback.
     * <p>
     * You can attach ItemTouchHelper to a RecyclerView via
     * {@link #attachToRecyclerView(RecyclerView)}. Upon attaching, it will add an item decoration,
     * an onItemTouchListener and a Child attach / detach listener to the RecyclerView.
     *
     * @param callback The Callback which controls the behavior of this touch helper.
     */
    public MoveItemHelper(Callback callback) {
        super(callback);
    }

//    @Override
//    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
//        super.attachToRecyclerView(recyclerView);
//    }





    /**
     * 实现动效相关的 Callback，即上面要传入的callback
     */
    static class MoveCallBack extends Callback {

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            /**
             * 设置 变换的状态，及 这个状态支持的方向有哪些
             */
            return makeFlag(ACTION_STATE_DRAG, LEFT | UP | RIGHT | DOWN);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            // 效果相当于 将  fromPosition的item 移到 toPosition 位置上，其后面的元素挨个往后挪，
            // 相应的，数据应该也是这种逻辑
            DataAdapter adapter = (DataAdapter) recyclerView.getAdapter();
            adapter.swapDataItem(fromPosition, toPosition);

            // 是否支持移动
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        }


        /**
         * 长按时，ItemTouchHelperGestureListener.onLongPress() -> callback.select()  ->  callback.onSelectedChanged()
         */
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
            }

            // If you override this method, you should call super. 不能省略
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            // 交互动画结束后，还原背景色
            viewHolder.itemView.setBackgroundColor(0);


            // test
            TextView content = ((LinearLayout)(recyclerView.getParent())).findViewById(R.id.content);
            List<ItemModel> list = ((DataAdapter) recyclerView.getAdapter()).getData();
            StringBuffer sb = new StringBuffer("[");
            for (ItemModel m : list) {
                sb.append(m.iconText).append(", ");
            }
            sb.append("]");
            content.setText(sb.toString());
        }

    }
}
