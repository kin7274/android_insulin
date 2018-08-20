package com.example.administrator.dia;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class ViewHolder extends RecyclerView.ViewHolder {
    TextView aurthorText;

    Dialog dialog;  // 팝업창
    final int[] selectedItem = {0};  // 팝업창의 선택목록 표시

    public interface RecyclerViewClickListener {
        void onItemClicked(int position);
    }

    public ViewHolder(final View itemView) {
        super(itemView);
        aurthorText = itemView.findViewById(R.id.aurthorText);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(v.getContext(), "띠용1111111111", Toast.LENGTH_SHORT).show();
//
////                Intent intent77 = new Intent(v.getContext(), Plzfinish.class);
////                v.getContext().startActivity(intent77);
//
//                final String[] items = new String[]{"아침식전", "아침식후", "점심식전", "점심식후","저녁식전","저녁식후"};
//                AlertDialog.Builder dialog = new AlertDialog.Builder(itemView.getContext());
//                dialog.setTitle("식사상태를선택하세요.");
//                dialog.create();
//                dialog.show();
//            }
//        });
    }
}

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private int resourceId;
    private List<RecyclerModel> dataList;


    public RecyclerAdapter(Context context, int resourceId, List<RecyclerModel>dataList){
        this.context=context;
        this.resourceId=resourceId;
        this.dataList=dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(resourceId,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        RecyclerModel recyclerModel = dataList.get(position);
        holder.aurthorText.setText(recyclerModel.getAurthor());
//        Toast.makeText(context, "띠용", Toast.LENGTH_SHORT).show();
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 클릭이벤트
////                Toast.makeText(v.getContext(), ""+position, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(v.getContext(), Plzfinish.class);
//                v.getContext().startActivity(intent);
//            }
//        });
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }
}