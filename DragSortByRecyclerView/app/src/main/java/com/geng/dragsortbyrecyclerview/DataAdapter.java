package com.geng.dragsortbyrecyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gengjiarong
 * on 2017/11/10.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<ItemModel> mList = new ArrayList<>();
    private boolean isFlagShow = true;
    private ClickListener listener;
    private boolean flag;

//    public void appendData(List<ItemModel> data) {
//        int startIndex = mList.size();
//        insertData(startIndex, data);
//    }
//
//    public void insertData(int insertPosition, List<ItemModel> data) {
//        mList.addAll(data);
//        notifyItemRangeInserted(insertPosition, data.size());
//    }

    public void changeFlagState() {
        isFlagShow = !isFlagShow;
        flag = true;
        notifyDataSetChanged();
    }

    public List<ItemModel> getData() {
        return mList;
    }


    public void updateData(List<ItemModel> data) {
        if (!mList.isEmpty()) {
            mList.clear();
        }
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public void deleteData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }


    public void swapDataItem(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public void setOnItemLongClickListener(ClickListener longClickListener) {
        this.listener = longClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.iconText.setText(mList.get(position).iconText);
        holder.icon.setBackgroundColor(Color.parseColor(mList.get(position).icon));
        holder.iconFlag.setVisibility(isFlagShow ? View.VISIBLE : View.INVISIBLE);
        if (flag) {
            AnimationHelper.showHideAnimation(holder.iconFlag, isFlagShow);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView iconFlag;
        TextView iconText;

        public ViewHolder(View itemView, final ClickListener listener) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            iconFlag = itemView.findViewById(R.id.icon_flag);
            iconText = itemView.findViewById(R.id.icon_text);

            iconFlag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClickDeleteFlag(v, getLayoutPosition());
                    }
                }
            });

            if (listener != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        listener.onLongClick(v, getLayoutPosition());
                        return true;
                    }
                });
            }
        }
    }

    /**
     * 好处是，可以选择性 实现相应的方法
     */
    static class ClickListener {
        void onLongClick(View view, int position) {
        }

        void onClickDeleteFlag(View view, int position) {
        }

    }
}
