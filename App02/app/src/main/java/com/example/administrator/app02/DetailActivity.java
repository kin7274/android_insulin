package com.example.administrator.app02;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // 저장데이터 불러오기
        shared_read();
    }

    public void shared_read() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String set3 = pref.getString("PREF_STRNAME", "");
        TextView txt1 = (TextView) findViewById(R.id.txt1);
        txt1.setText(set3);
    }
}
