package com.example.administrator.dia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EduWordActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView = null;
    private RecyclerAdapter recyclerAdapter = null;
    private List<RecyclerModel> dataList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_word);
        init();
        addDummy();
        setRecyclerView();
        }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        dataList = new ArrayList<RecyclerModel>();
    }

    private void addDummy() {
        dataList.add(new RecyclerModel("당뇨병"));
        dataList.add(new RecyclerModel("제 1형 당뇨병"));
        dataList.add(new RecyclerModel("제 2형 당뇨병"));
        dataList.add(new RecyclerModel("임신성 당뇨병"));
        dataList.add(new RecyclerModel("저혈당"));
        dataList.add(new RecyclerModel("당화혈색소"));
        dataList.add(new RecyclerModel("당뇨 지식"));
        dataList.add(new RecyclerModel("자가 간호"));
        dataList.add(new RecyclerModel("당뇨 자기 관리"));
        dataList.add(new RecyclerModel("혈당강하제"));
        dataList.add(new RecyclerModel("내당능장애"));
        dataList.add(new RecyclerModel("동맥경화"));
        dataList.add(new RecyclerModel("포도당"));
        dataList.add(new RecyclerModel("인슐린"));
        dataList.add(new RecyclerModel("당부하검사"));
        dataList.add(new RecyclerModel("인슐린 저항성"));
        dataList.add(new RecyclerModel("고혈압"));
        dataList.add(new RecyclerModel("다갈"));
        dataList.add(new RecyclerModel("다뇨"));
        dataList.add(new RecyclerModel("다식"));
        dataList.add(new RecyclerModel("..."));
    }

    private void setRecyclerView() {
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), R.layout.item_default_recycler, dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "제발,....", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClicked(int position) {

    }
}

