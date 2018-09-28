package com.example.administrator.app02;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.app02.BluetoothLeService.ACTION_DATA_AVAILABLE;
import static com.example.administrator.app02.BluetoothLeService.ACTION_DATA_AVAILABLE_CHANGE;
import static com.example.administrator.app02.BluetoothLeService.EXTRA_DATA;
import static com.example.administrator.app02.DeviceControlActivity.EXTRAS_DEVICE_ADDRESS;
import static com.example.administrator.app02.MyRecyclerAdapter.MyRecyclerViewClickListener;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewClickListener, View.OnClickListener {

    TextView textview1, textview2, setting_data;

    private final static String TAG = MainActivity.class.getSimpleName();

    private CustomDialog dialog;

    BluetoothLeService mBluetoothLeService = new BluetoothLeService();

    String deviceAddress;

    public static Context mContext;

    // 쉐어드
    EditText et;

    String setting_insulin_kinds = "";
    String setting_insulin_names = "";
    String setting_insulin_unit = "";

    String receive_data_real;

    SharedPreferences sharedPreferences;

    private MyRecyclerAdapter mAdapter;
    RecyclerView recyclerView;
    List<CardItem> dataList;

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

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);

        // 레이아웃 매니저로 LinearLayoutManager를 설정
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // 표시할 임시 데이터
        final List<CardItem> dataList = new ArrayList<>();

        if(setting_data.getText().toString().equals("") ){
            setting_data.setText("설정부터하고와");
        }

        // 어댑터 설정
        mAdapter = new MyRecyclerAdapter(dataList);
        mAdapter.setOnClickListener(this);
        recyclerView.setAdapter(mAdapter);

        // 구분선
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        // 저장데이터 불러오기
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String str = pref.getString("PREF_STRNAME", "");

        String numbers = str;
        String[] arr = numbers.split(",");
//        // insulin_kinds = "초속효성"
//        setting_insulin_kinds = arr[0];
//        // insulin_kinds = "휴머로그"
//        setting_insulin_names = arr[1];
//        // insulin_unit = "5"
//        setting_insulin_unit = arr[2];

        Log.d(TAG, "종류 = " + setting_insulin_kinds + ", 이름 = " + setting_insulin_names + ", 단위 = " + setting_insulin_unit);
    }

    public void set(){
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button action_setting = (Button) findViewById(R.id.action_setting);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        action_setting.setOnClickListener(this);

        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        setting_data = (TextView) findViewById(R.id.setting_data);
    }

    // 투약 종류마다 리스트 맨 앞 색 구별!!!!!!
    // 범례도 꼭 넣자!!
    public int searchImage() {
        int a = 0;
        switch (setting_insulin_kinds) {
            case "초속효성":
                return R.drawable.a1;
            case "속효성":
                return R.drawable.a2;
            case "중간형":
                return R.drawable.a3;
            case "혼합형":
                return R.drawable.a4;
            case "지속형":
                return R.drawable.a5;
        }
        return a;
    }

    // 설정페이지 인텐트로부터 리시브
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                // 인슐린 종합 데이터
                String result = data.getStringExtra("AA");
                Log.d(TAG, "result = " + result);
                // 메인 텍스트에 추가
                setting_data.setText(result);
                // 데이터 구분
                String numbers = result;
                String[] arr = numbers.split(",");
                // setting_insulin_kinds = "초속효성"
                setting_insulin_kinds = arr[0];
                // setting_insulin_names = "휴머로그"
                setting_insulin_names = arr[1];
                // setting_insulin_unit = "5"
                setting_insulin_unit = arr[2];
                Log.d(TAG, "내가 설정한 값은@@@@ 종류 = " + setting_insulin_kinds + ", 이름 = " + setting_insulin_names + ", 단위 = " + setting_insulin_unit);
            }
        }
    }

    //  클릭 이벤트
    @Override
    public void onItemClicked(int position) {
        Toast.makeText(getApplicationContext(), position + "번째 삭제", Toast.LENGTH_LONG).show();
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
                intent4.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, textview1.getText());
                intent4.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, textview2.getText());
                startActivity(intent4);
                break;
                // 설정 페이지로 이동
            case R.id.action_setting:
                Intent intent_setting = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(intent_setting, 1);
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
    }
}
