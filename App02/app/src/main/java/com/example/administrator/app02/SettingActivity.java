package com.example.administrator.app02;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class SettingActivity extends AppCompatActivity implements OnClickListener, OnItemSelectedListener {
    // TODO 양식 통일
    // TODO 데이터 꺼내기

    Spinner spinner01, spinner02;  // 상위 스피너, 인슐린 종류(5).  하위 스피너, 하위 품목
    TextView abc_num;  // 단위 표시
    Button abc_inc, abc_dec;  // 단위 증가, 감소
    Button set_btn;  // 저장버튼

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // 상태바 색 변경
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor(getResources().getString(R.color.colorPrimaryPurle)));

        abc_num = (TextView) findViewById(R.id.abc_num);
        abc_inc = (Button) findViewById(R.id.abc_inc);
        abc_dec = (Button) findViewById(R.id.abc_dec);
        set_btn = (Button) findViewById(R.id.set_btn);

        abc_inc.setOnClickListener(this);
        abc_dec.setOnClickListener(this);
        set_btn.setOnClickListener(this);

        // 상위 스피너, 인슐린 종류(5)
        final String[] items = {getResources().getString(R.string.insulin_name), getResources().getString(R.string.insulin_name1), getResources().getString(R.string.insulin_name2), getResources().getString(R.string.insulin_name3), getResources().getString(R.string.insulin_name4)};

        // 하위 스피너, 하위 품목
        // 초속효성
        final String[] items1 = {getResources().getString(R.string.insulin_name0_0), getResources().getString(R.string.insulin_name0_1), getResources().getString(R.string.insulin_name0_2)};
        // 속효성
        final String[] items2 = {getResources().getString(R.string.insulin_name1_0)};
        // 중간형
        final String[] items3 = {getResources().getString(R.string.insulin_name2_0), getResources().getString(R.string.insulin_name2_1), getResources().getString(R.string.insulin_name2_2), getResources().getString(R.string.insulin_name2_3)};
        // 혼합형
        final String[] items4 = {getResources().getString(R.string.insulin_name3_0), getResources().getString(R.string.insulin_name3_1), getResources().getString(R.string.insulin_name3_2), getResources().getString(R.string.insulin_name3_3)};
        // 지속형
        final String[] items5 = {getResources().getString(R.string.insulin_name4_0), getResources().getString(R.string.insulin_name4_1)};

        final Spinner spinner01 = (Spinner) findViewById(R.id.spinner01);
        spinner01.setOnItemSelectedListener((OnItemSelectedListener) this);
        final Spinner spinner02 = (Spinner) findViewById(R.id.spinner02);
        spinner02.setOnItemSelectedListener((OnItemSelectedListener) this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        spinner01.setAdapter(adapter);
        spinner01.setSelection(0);

        spinner01.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner01.getItemAtPosition(position) == getResources().getString(R.string.insulin_name)) {
                    // 스피너 칸, 폰트, 크기 바꾸자!
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items1);
                    adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter2);
                    spinner02.setSelection(0);
                } else if (spinner01.getItemAtPosition(position) == getResources().getString(R.string.insulin_name1)) {
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items2);
                    adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter3);
                    spinner02.setSelection(0);
                } else if (spinner01.getItemAtPosition(position) == getResources().getString(R.string.insulin_name2)) {
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items3);
                    adapter4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter4);
                    spinner02.setSelection(0);
                } else if (spinner01.getItemAtPosition(position) == getResources().getString(R.string.insulin_name3)) {
                    ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items4);
                    adapter5.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter5);
                    spinner02.setSelection(0);
                } else if (spinner01.getItemAtPosition(position) == getResources().getString(R.string.insulin_name4)) {
                    ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items5);
                    adapter6.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter6);
                    spinner02.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        // 버튼 이벤트
        String abc_str = (String) abc_num.getText();
        int inabc = (int) Integer.parseInt(abc_str);
        switch (view.getId()) {
            case R.id.set_btn:
                // 저장 버튼
                Toast.makeText(SettingActivity.this, getResources().getString(R.string.set_plz), Toast.LENGTH_SHORT).show();
                break;
            case R.id.abc_inc:
                // 단위 - 증가 버튼
                inabc++;
                abc_num.setText(String.valueOf(inabc));
                break;
            case R.id.abc_dec:
                // 단위 - 감소 버튼
                if (inabc == 1) {
                    // 음수 구분용 - MAX는 필요할까? 10-30? 몇이 적당할까?
                    Toast.makeText(SettingActivity.this, getResources().getString(R.string.num_limit), Toast.LENGTH_SHORT).show();
                } else {
                    // 음수 아니면 원래대로 감소
                    inabc--;
                    abc_num.setText(String.valueOf(inabc));
                }
                abc_num.setText(String.valueOf(inabc));
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
