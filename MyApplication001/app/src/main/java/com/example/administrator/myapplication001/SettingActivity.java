package com.example.administrator.myapplication001;

import android.os.Bundle;
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

public class SettingActivity extends AppCompatActivity implements OnClickListener, OnItemSelectedListener {

    Spinner spinner01;  // 1번 종류 스피너
    Spinner spinner02;  // 2번 하위 품목 스피너

    TextView abc_num;  // 단위 표시
    Button abc_inc;  // 단위 증가
    Button abc_dec;  // 단위 감소

    Button set_btn;  // 저장버튼
    Button exit_btn;  // 나가기, 취소


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        abc_num = (TextView) findViewById(R.id.abc_num);
        abc_inc = (Button) findViewById(R.id.abc_inc);
        abc_dec = (Button) findViewById(R.id.abc_dec);
        set_btn = (Button) findViewById(R.id.set_btn);
        exit_btn = (Button) findViewById(R.id.exit_btn);

        abc_inc.setOnClickListener(this);
        abc_dec.setOnClickListener(this);
        set_btn.setOnClickListener(this);
        exit_btn.setOnClickListener(this);

        // 스피너1 : 인슐린 종류
        final String[] items = {"초속효성", "속효성", "중간형", "혼합형", "지속형"};
        // 스피너2 (5가지)
        final String[] items1 = {"휴머로그", "노보래피드", "애피드라"};  // 초속효성
        final String[] items2 = {"휴먼인슐린"};  // 속효성
        final String[] items3 = {"노보린", "노보렛", "휴물린", "인슈라타드"};  // 중간형
        final String[] items4 = {"노보믹스", "휴머로그 믹스", "휴물린", "믹스타드"};  // 혼합형
        final String[] items5 = {"란투스", "레버미어"};  // 지속형

        final Spinner spinner01 = (Spinner) findViewById(R.id.spinner01);
        spinner01.setOnItemSelectedListener(( OnItemSelectedListener) this);
        final Spinner spinner02 = (Spinner) findViewById(R.id.spinner02);
        spinner02.setOnItemSelectedListener((OnItemSelectedListener) this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        spinner01.setAdapter(adapter);
        spinner01.setSelection(0);

        spinner01.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner01.getItemAtPosition(position) == "초속효성") {
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items1);
                    adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter2);
                    spinner02.setSelection(0);
                } else if (spinner01.getItemAtPosition(position) == "속효성") {
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items2);
                    adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter3);
                    spinner02.setSelection(0);
                } else if (spinner01.getItemAtPosition(position) == "중간형") {
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items3);
                    adapter4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter4);
                    spinner02.setSelection(0);
                } else if (spinner01.getItemAtPosition(position) == "혼합형") {
                    ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items4);
                    adapter5.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter5);
                    spinner02.setSelection(0);
                } else if (spinner01.getItemAtPosition(position) == "지속형") {
                    ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items5);
                    adapter6.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter6);
                    spinner02.setSelection(0);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    @Override
    public void onClick(View view) {  // 버튼 이벤트
        String abc_str = (String) abc_num.getText();
        int inabc = (int) Integer.parseInt(abc_str);
        switch (view.getId()) {
            case R.id.set_btn:  // 저장 버튼
                Toast.makeText(SettingActivity.this, "저장할게유", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit_btn:  // 나가기 버튼
                Toast.makeText(SettingActivity.this, "취소할게유", Toast.LENGTH_SHORT).show();
                break;
            case R.id.abc_inc:  // 단위 - 증가 버튼
                inabc++;
                abc_num.setText(String.valueOf(inabc));
                break;
            case R.id.abc_dec:  // 단위 - 감소 버튼
                if (inabc == 1) {  // 음수 구분용 - MAX는 필요할까? 10-30? 몇이 적당할까?
                    Toast.makeText(SettingActivity.this, "1 이하는 입력X", Toast.LENGTH_SHORT).show();
                } else {  // 음수 아니면 원래대로 감소
                    inabc--;
                    abc_num.setText(String.valueOf(inabc));
                }
                abc_num.setText(String.valueOf(inabc));
                break;
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
