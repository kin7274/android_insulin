package com.example.administrator.app02;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.administrator.app02.R.layout;

public class MainActivity extends AppCompatActivity{

    Dialog dialog;  // 팝업창
    final int[] selectedItem = {0};  // 팝업창의 선택목록 표시


    Button bt_support;
    Button bt_turnon;
    Button bt_pared;

    // 메인
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        // 툴바
        Toolbar mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("");

        bt_support = (Button) findViewById(R.id.bt_support);
        bt_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"bt_support", Toast.LENGTH_SHORT).show();
            }
        });
        bt_turnon = (Button) findViewById(R.id.bt_turnon);
        bt_turnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"bt_turnon", Toast.LENGTH_SHORT).show();
            }
        });
        bt_pared = (Button) findViewById(R.id.bt_pared);
        bt_pared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"bt_pared", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_auto) {  // 수동버튼 클릭 시
            // 식사상태 선택 팝업창 열림
            final String[] items = new String[]{"아침식전", "아침식후", "점심식전", "점심식후","저녁식전","저녁식후"};
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("식사상태를선택하세요.").setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                // 선택된 하나 받아서
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedItem[0] = which;}
            }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    // 표시
                    Toast.makeText(getApplicationContext(),items[selectedItem[0]] + "을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.create();
            dialog.show();
        }
        if (id == R.id.action_ble) {  // 블루투스 연결
            Toast.makeText(getApplicationContext(), "BLE연결", Toast.LENGTH_LONG).show();
            // 블루투스 연결
            // 다이얼로그 넣자
        }
        if (id == R.id.action_setting) {  // 설정 페이지로 이동
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_education) {  // 교육(추가자료) 페이지로 이동
            Intent intent2 = new Intent(MainActivity.this, EducationActivity.class);
            startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}