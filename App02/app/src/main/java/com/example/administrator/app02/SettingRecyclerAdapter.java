package com.example.administrator.app02;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SettingRecyclerAdapter extends RecyclerView.Adapter<SettingRecyclerAdapter.ViewHolder> {
    public static Context mContext;

    private final List<CardItem_Setting> mDataList;
    private SettingRecyclerViewClickListener mListener;

    public SettingRecyclerAdapter(List<CardItem_Setting> dataList) {
        mDataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardItem_Setting item = mDataList.get(position);

        TextView tex = holder.tex;
        holder.tex.setText(item.getKinds());
        if (mListener != null) {
            final int pos = holder.getAdapterPosition();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked(pos);
                    removeAt(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setOnClickListener(DialogInterface.OnClickListener onClickListener) {
    }

    public void removeAt(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDataList.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tex;

        public ViewHolder(View itemView) {
            super(itemView);
            tex = (TextView) itemView.findViewById(R.id.tex);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

    public void setOnClickListener(SettingRecyclerViewClickListener listener) {
        mListener = listener;
    }

    public interface SettingRecyclerViewClickListener {
        // 아이템 전체 부분의 클릭
        void onItemClicked(int position);
    }
}
