package com.example.administrator.app02;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class I_want_to_know_you extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i_want_to_know_you);
        setToolbar();
        setStatusbar();
        set();
    }

    public void setToolbar() {
        Toolbar mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("");
    }

    public void setStatusbar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryPurle));
    }

    public void set() {
        // 니들1
        TextView setting_kind_1 = (TextView) findViewById(R.id.setting_kind_1);
        TextView setting_name_1 = (TextView) findViewById(R.id.setting_name_1);
        TextView setting_unit_1 = (TextView) findViewById(R.id.setting_unit_1);
        TextView setting_status_1 = (TextView) findViewById(R.id.setting_status_1);
        setting_kind_1.setOnClickListener(this);
        setting_name_1.setOnClickListener(this);
        setting_unit_1.setOnClickListener(this);
        setting_status_1.setOnClickListener(this);
        // 니들2
        TextView setting_kind_2 = (TextView) findViewById(R.id.setting_kind_2);
        TextView setting_name_2 = (TextView) findViewById(R.id.setting_name_2);
        TextView setting_unit_2 = (TextView) findViewById(R.id.setting_unit_2);
        TextView setting_status_2 = (TextView) findViewById(R.id.setting_status_2);
        setting_kind_2.setOnClickListener(this);
        setting_name_2.setOnClickListener(this);
        setting_unit_2.setOnClickListener(this);
        setting_status_2.setOnClickListener(this);
        // 약1
        TextView setting_medi_1 = (TextView) findViewById(R.id.setting_medi_1);
        // 약2
        TextView setting_medi_2 = (TextView) findViewById(R.id.setting_medi_2);
        // 설정끝
        Button next_btn = (Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_btn:
                Intent intent = new Intent(I_want_to_know_you.this, DetailActivity.class);
                startActivity(intent);
                break;
        }
    }
}
