package com.example.administrator.app02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.app02.DeviceControlActivity.EXTRAS_DEVICE_ADDRESS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SettingRecyclerAdapter.SettingRecyclerViewClickListener {

    String rrrrr;
    List<CardItem_Setting> settinglists;
    private SettingRecyclerAdapter mAdapter;
    RecyclerView recycler_view;

    TextView textview1, textview2, setting_data;

    private final static String TAG = MainActivity.class.getSimpleName();

    String deviceAddress;

    public static Context mContext;

    String setting_insulin_kinds, setting_insulin_names, setting_insulin_unit, setting_insulin_time;
    String setting_insulin1, setting_insulin2, setting_insulin_total;

    // 다이얼로그 선택된 값
    final int[] selectedItem = {0};

    // 메인
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        // 툴바
        Toolbar mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("");

        set();

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 반대로 쌓기
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recycler_view.setLayoutManager(layoutManager);

        try {
            settinglists = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 어댑터 설정
        mAdapter = new SettingRecyclerAdapter(settinglists);
        mAdapter.setOnClickListener(this);
        recycler_view.setAdapter(mAdapter);

        // 구분선
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), new LinearLayoutManager(this).getOrientation());
        recycler_view.addItemDecoration(dividerItemDecoration);

        // 저장데이터 불러오기
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String str = pref.getString("PREF_STRNAME", "");
        Log.d(TAG, "str = " + str);
        if (str.equals("")) {
            // 초기 1회
            Log.d(TAG, "설정부터하세욥..");
            setting_data.setText("설정부터하고와");
        }
        // 설정값이 존재할 경우
        else {
            if (str.contains("&&")) {
                // 사용하는 약이 두개인 경우
                String numbers = str;
                String[] arr = numbers.split("&&");
                String strradv1 = arr[0];
                Log.d(TAG, "strradv1 =" + strradv1);
                settinglists.add(new CardItem_Setting(strradv1));
                String strradv2 = arr[1];
                Log.d(TAG, "strradv2 = " + strradv2);
                settinglists.add(new CardItem_Setting(strradv2));
                mAdapter.notifyDataSetChanged();
            } else {
                settinglists.add(new CardItem_Setting(str));
                mAdapter.notifyDataSetChanged();
            }
        }
    }
//            if (str.contains("&&")) {
//                String numbers = str;
//                String[] arr = numbers.split("&&");
//                String strradv1 = arr[0];
//                Log.d(TAG, "strradv1 =" + strradv1);
//
//                settinglists.add(new CardItem_Setting(strradv1));
//                String strradv2 = arr[1];
//                Log.d(TAG, "strradv2 = " + strradv2);
//
//                settinglists.add(new CardItem_Setting(strradv2));
//                mAdapter.notifyDataSetChanged();

//                setting_data.setText(str);
//                String numbers = str;
//                String[] arr = numbers.split(",");
//                // insulin_kinds = "초속효성"
//                setting_insulin_kinds = arr[0];
//                // insulin_kinds = "휴머로그"
//                setting_insulin_names = arr[1];
//                // insulin_unit = "5"
//                setting_insulin_unit = arr[2];
//                // setting_insulin_time = "아침식전"
//                setting_insulin_time = arr[3];
//                Log.d(TAG, "종류 = " + setting_insulin_kinds + ", 이름 = " + setting_insulin_names + ", 단위 = " + setting_insulin_unit + ", 투약시간 = " + setting_insulin_time);
//            } else {
    // 약이 1개야
    // null값 삭제
//                String strr = str.replace(null, "");
//                setting_data.setText(str);
//                String numbers = str;

//                String numbers = str;
//                String[] arr = numbers.split("//");
//                String sttr = arr[0];
//                Log.d(TAG, "sttr = " + sttr);
//                settinglists.add(new CardItem_Setting(sttr));
//                mAdapter.notifyDataSetChanged();

    // 약이 2개일 경우

//                String[] arr = numbers.split(",");
//                // insulin_kinds = "초속효성"
//                setting_insulin_kinds = arr[0];
//                // insulin_kinds = "휴머로그"
//                setting_insulin_names = arr[1];
//                // insulin_unit = "5"
//                setting_insulin_unit = arr[2];
//                // setting_insulin_time = "아침식전"
//                setting_insulin_time = arr[3];
//                Log.d(TAG, "종류 = " + setting_insulin_kinds + ", 이름 = " + setting_insulin_names + ", 단위 = " + setting_insulin_unit + ", 투약시간 = " + setting_insulin_time);

    public void set() {
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button action_setting = (Button) findViewById(R.id.action_setting);
        Button action_setting2 = (Button) findViewById(R.id.action_setting2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        action_setting.setOnClickListener(this);
        action_setting2.setOnClickListener(this);

        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        setting_data = (TextView) findViewById(R.id.setting_data);
    }

    // 설정페이지 인텐트로부터 리시브
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                setting_insulin1 = data.getStringExtra("AA");
                settinglists.add(new CardItem_Setting(setting_insulin1));
                mAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 2) {
            setting_insulin2 = data.getStringExtra("BB");
            settinglists.add(new CardItem_Setting(setting_insulin2));
            mAdapter.notifyDataSetChanged();
        }
        // 인슐린 종합 데이터
//                if (settinglists.size() > 0) {
//                    // 2번
//                    setting_insulin2 = data.getStringExtra("AA");
//                    settinglists.add(new CardItem_Setting(setting_insulin2));
//                    mAdapter.notifyDataSetChanged();
//                } else {
//                    // 1번
//                    setting_insulin1 = data.getStringExtra("AA");
//                    settinglists.add(new CardItem_Setting(setting_insulin1));
//                    mAdapter.notifyDataSetChanged();
//                }
//                Log.d(TAG, "setting_insulin1 = " + setting_insulin1);
//                Log.d(TAG, "setting_insulin2 = " + setting_insulin2);
//                if(setting_insulin2 != null){
//                    setting_insulin_total = setting_insulin1 + "&&" + setting_insulin2;
//                } else {
//                    setting_insulin_total = setting_insulin1 + "//";
//                }
//
//                Log.d(TAG, "setting_insulin_total = " + setting_insulin_total);

//                // 메인 텍스트에 추가
//                setting_data.setText(result);
//                // 데이터 구분
//                String numbers = result;
//                String[] arr = numbers.split(",");
//                // setting_insulin_kinds = "초속효성"
//                setting_insulin_kinds = arr[0];
//                // setting_insulin_names = "휴머로그"
//                setting_insulin_names = arr[1];
//                // setting_insulin_unit = "5"
//                setting_insulin_unit = arr[2];
//                // setting_insulin_time = "아침식전"
//                setting_insulin_time = arr[3];
//
//                Log.d(TAG, "내가 설정한 값은@@@@ 종류 = " + setting_insulin_kinds + ", 이름 = " + setting_insulin_names + ", 단위 = " + setting_insulin_unit + ", 투약 시간 = " + setting_insulin_time);
//            }
    }

    // 메뉴.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 툴바 탭 클릭 이벤트
        switch (item.getItemId()) {
            // 오버플로우 메뉴
            // 영상 페이지로 이동
            case R.id.action_education:
                Intent intent_edu = new Intent(MainActivity.this, EducationActivity.class);
                startActivity(intent_edu);
                break;
            // 어플에 대한 사용법 간단하게
            case R.id.action_guide:
                AlertDialog.Builder dialog_app = new AlertDialog.Builder(this);
                dialog_app.setTitle(getResources().getString(R.string.dialog_app_title))
                        .setMessage(getResources().getString(R.string.dialog_app_content))
                        .setPositiveButton(getResources().getString(R.string.ok), new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                            }
                        });
                dialog_app.create();
                dialog_app.show();
                break;

            // 개발자에 대한 사용법 간단하게
            case R.id.action_myinfo:
                AlertDialog.Builder dialog_info = new AlertDialog.Builder(this);
                dialog_info.setTitle(getResources().getString(R.string.dialog_my_title))
                        .setMessage(getResources().getString(R.string.dialog_my_content))
                        .setPositiveButton(getResources().getString(R.string.ok), new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                            }
                        });
                dialog_info.create();
                dialog_info.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 블루투스
            case R.id.btn1:
                Intent intent1 = new Intent(MainActivity.this, DeviceScanActivity.class);
                startActivity(intent1);
                break;
            // 페어링
            case R.id.btn2:
                final Intent intent2 = new Intent(MainActivity.this, DeviceControlActivity.class);
                intent2.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, textview1.getText());
                intent2.putExtra(EXTRAS_DEVICE_ADDRESS, textview2.getText());
                startActivity(intent2);
                break;
            // DB
            case R.id.btn3:
                Intent intent3 = new Intent(MainActivity.this, AddNeedleActivity.class);
                intent3.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, textview1.getText());
                intent3.putExtra(EXTRAS_DEVICE_ADDRESS, textview2.getText());
                startActivity(intent3);
                break;
            // 설정 끝, 다음으로 이동
            case R.id.btn4:
                Intent intent4 = new Intent(MainActivity.this, Timeline.class);
                intent4.putExtra("AYO", setting_data.getText());
                intent4.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, textview2.getText());
                startActivity(intent4);
                break;
            // 설정 페이지로 이동
            case R.id.action_setting:
                Intent intent_setting = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(intent_setting, 1);
                break;
            case R.id.action_setting2:
                Intent intent_setting2 = new Intent(MainActivity.this, SettingActivity2.class);
                startActivityForResult(intent_setting2, 2);
                break;
        }
    }

    // 설정 후 돌아오면!
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "11111 설정 후 내가 돌아왔어");
        textview1.setText(BluetoothLog.getName());
        textview2.setText(BluetoothLog.getAddress());
    }

    // 액티비티 종료 전에 저장!
    @Override
    protected void onStop() {
        super.onStop();
        if (setting_insulin1 != null) {
            // 설정안햇네 저장할거없다이
            Log.d(TAG, "setting_insulin1 X NULL");
            // 약1은 설정햇네
            // 그럼 약2는?
            if (setting_insulin2 != null) {
                // 2번도 햇네?
                rrrrr = setting_insulin1 + "&&" + setting_insulin2;
            } else {
                Log.d(TAG, "setting_insulin2 = NULL");
                // 2번은 안햇네?
                // 그럼 1번만 저장할게
                rrrrr = setting_insulin1;

            }
        } else {
            Log.d(TAG, "setting_insulin1 = NULL");
        }

        Log.d(TAG, "rrrrr = " + rrrrr);

        // getSharedPreferences(String name, int mode)
        // : 특정 이름을 가진 SharedPreferences를 생성. 애플리케이션 전체에서 사용
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("PREF_STRNAME", rrrrr);
//        String strrr = setting_data.getText().toString();
//        editor.putString("PREF_STRNAME", strrr);
//        editor.putString("PREF_STRNAME", setting_insulin_total);
        editor.apply();
    }

    @Override
    public void onItemClicked(int position) {

    }
}
