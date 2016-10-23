package com.chinahr.android.common.im.message.relay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinahr.android.m.R;

import java.util.List;

/**
 * Created by geng
 * on 2016/10/13.
 */
public class RelayMessageAdapter extends RecyclerView.Adapter<RelayMessageAdapter.RelayViewHolder> {

    public List<RelayMessageBean> mList;
    public OnItemClickListener itemClickListener;

    public RelayMessageAdapter(List<RelayMessageBean> relayMessageBeanList) {
        mList = relayMessageBeanList;
    }

    @Override
    public RelayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent, false);
        return new RelayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RelayViewHolder holder, int position) {
        holder.content.setText(mList.get(position).relayMessage);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void resetItem() {
        notifyDataSetChanged();
    }

//    /**
//     * 为每个条目设置点击事件
//     */
//    public interface OnItemClickListener{
//        void onItemClick(View view, int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener clickListener) {
//        itemClickListener = clickListener;
//    }

    public class RelayViewHolder extends RecyclerView.ViewHolder{

        public TextView content;
        public ImageView edit;
        public TextView delete;
        public LinearLayout layout;

        public RelayViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.relay_message_content_tv);
            delete = (TextView) itemView.findViewById(R.id.message_item_delete);
            edit = (ImageView) itemView.findViewById(R.id.relay_message_et_iv);
            layout = (LinearLayout) itemView.findViewById(R.id.message_item_layout);

//            edit.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            if (itemClickListener != null) {
//                itemClickListener.onItemClick(edit, getAdapterPosition());
//            }
//        }
    }

}
