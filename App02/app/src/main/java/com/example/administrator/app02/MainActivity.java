package com.example.administrator.app02;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static com.example.administrator.app02.R.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        Toolbar mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_auto) {  // 수동추가 버튼
            Toast.makeText(getApplicationContext(), "수동추가", Toast.LENGTH_LONG).show();
            // 여기에 팝업창 하나 넣고싶어
        }
        if (id == R.id.action_ble) {  // 블루투스 연결
            Toast.makeText(getApplicationContext(), "BLE연결", Toast.LENGTH_LONG).show();
            // 블루투스 연결
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
