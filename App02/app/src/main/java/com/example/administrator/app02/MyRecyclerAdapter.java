package com.example.administrator.app02;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.app02.CardItem;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    public static Context mContext;

    private final List<CardItem> mDataList;
    private MyRecyclerViewClickListener mListener;

    // Adapter 초기화 및 생성자로 받은 데이터기반으로 Adapter 내 데이터 세팅
    public MyRecyclerAdapter(List<CardItem> dataList) {
        mDataList = dataList;
    }

    // ViewHolder가 초기화 될 때 혹은 ViewHolder를 초기화 할 때 실행되는 메서드
    // 뷰 홀더를 생성하는 부분. 레이아웃을 만드는 부분
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    // RecyclerView의 Row 하나하나를 구현하기위해 Bind(묶이다) 될 때
    // 뷰 홀더에 데이터를 설정하는 부분
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardItem item = mDataList.get(position);

//        ImageView image = holder.image;
        TextView state = holder.state;
        TextView setting = holder.setting;

//        holder.image.setImageResource(item.getImage());
        holder.state.setText(item.getState());
        holder.setting.setText(item.getSetting());

        // 클릭 이벤트
        if (mListener != null) {
            // 현재 위치
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

    // 아이템의 수
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

    // 각각의 아이템의 레퍼런스를 저장할 뷰 홀더 클래스
    // 반드시 RecyclerView.ViewHolder를 상속해야 함
    public class ViewHolder extends RecyclerView.ViewHolder {

//        ImageView image;
        TextView state, setting;

        public ViewHolder(View itemView) {
            super(itemView);
//            image = (ImageView) itemView.findViewById(R.id.image);
            state = (TextView) itemView.findViewById(R.id.text_eat_state);
            setting = (TextView) itemView.findViewById(R.id.text_init_setting);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "click", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void setOnClickListener(MyRecyclerViewClickListener listener) {
        mListener = listener;
    }

    public interface MyRecyclerViewClickListener {
        // 아이템 전체 부분의 클릭
        void onItemClicked(int position);
    }
}