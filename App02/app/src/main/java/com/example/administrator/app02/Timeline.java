package com.example.administrator.app02;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.app02.BluetoothLeService.ACTION_DATA_AVAILABLE;
import static com.example.administrator.app02.BluetoothLeService.ACTION_DATA_AVAILABLE_CHANGE;
import static com.example.administrator.app02.BluetoothLeService.EXTRA_DATA;
import static com.example.administrator.app02.DeviceControlActivity.EXTRAS_DEVICE_ADDRESS;

import static com.example.administrator.app02.MyRecyclerAdapter.*;

// 실질적으로 블루투스값 리시브 액티비티입니다.
public class Timeline extends AppCompatActivity implements MyRecyclerViewClickListener, View.OnClickListener {
    private final static String TAG = Timeline.class.getSimpleName();
    Context mContext;
    List<CardItem> lists;
    private MyRecyclerAdapter mAdapter;
    RecyclerView recycler_view;

    String deviceAddress = "";
    String settingdata22 = "";
    String setting_insulin_kinds2 = "";
    String setting_insulin_names2 = "";
    String setting_insulin_unit2 = "";

    public Button btn1;

    BluetoothLeService mBluetoothLeService = new BluetoothLeService();

    private final BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_DATA_AVAILABLE_CHANGE.equals(action)) {
                final String message = intent.getStringExtra(EXTRA_DATA);
                Log.d(TAG, "겟 메세지" + message);
                Log.d(TAG, "message = 값 조쿠요");
                // TODO 자릿수로 끊어서 하자
                String[] MSG = message.split("");
                Log.d(TAG, "MSG[1] = " + MSG[1]);
                Log.d(TAG, "MSG[2] = " + MSG[2]);
                Log.d(TAG, "MSG[3] = " + MSG[3]);
                Log.d(TAG, "MSG[4] = " + MSG[4]);
                Log.d(TAG, "MSG[5] = " + MSG[5]);
                Log.d(TAG, "MSG[6] = " + MSG[6]);
                Log.d(TAG, "MSG[7] = " + MSG[7]);
                Log.d(TAG, "MSG[8] = " + MSG[8]);
                Log.d(TAG, "MSG[9] = " + MSG[9]);
                Log.d(TAG, "MSG[10] = " + MSG[10]);
                Log.d(TAG, "MSG[11] = " + MSG[11]);
                Log.d(TAG, "MSG[12] = " + MSG[12]);
                String REALREALREAL = MSG[1] + MSG[2] + MSG[3] + MSG[4] + "년 " + MSG[5] + MSG[6] + "월 " + MSG[7] + MSG[8] + "일 " + MSG[9] + MSG[10] + "시 " + MSG[11] + MSG[12] + "분";
                Log.d(TAG, "111111띠용...므엇이죠 이게...");
                lists.add(new CardItem(searchImage(), REALREALREAL, settingdata22));
                mAdapter.notifyDataSetChanged();
                Log.d(TAG, "222222띠용...므엇이죠 이게...");
                Log.d(TAG, "리얼리얼리어리리 : " + REALREALREAL);
            }
        }
    };

    // 투약 종류마다 리스트 맨 앞 색 구별!!!!!!
    // 범례도 꼭 넣자!!
    int searchImage() {
        int a = 0;
        switch (setting_insulin_kinds2) {
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

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            mBluetoothLeService.connect(deviceAddress);
            //
            Log.d(TAG, "서비스가 연결되었습니다!");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 툴바
        Toolbar mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("");

        mContext = this;

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 반대로 쌓기
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recycler_view.setLayoutManager(layoutManager);

        try {
            lists = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 어댑터 설정
        mAdapter = new MyRecyclerAdapter(lists);
        mAdapter.setOnClickListener(this);
        recycler_view.setAdapter(mAdapter);

        // 구분선
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), new LinearLayoutManager(this).getOrientation());
        recycler_view.addItemDecoration(dividerItemDecoration);

        settingdata22 = getIntent().getStringExtra("AYO");
        Log.d(TAG, "settingdata22 = " + settingdata22);
        String numbers = settingdata22;
        String[] arr = numbers.split(",");
        // setting_insulin_kinds = "초속효성"
        setting_insulin_kinds2 = arr[0];
        Log.d(TAG, "setting_insulin_kinds2 = " + setting_insulin_kinds2);

        // setting_insulin_names = "휴머로그"
        setting_insulin_names2 = arr[1];
        Log.d(TAG, "setting_insulin_names2 = " + setting_insulin_names2);

        // setting_insulin_unit = "5"
        setting_insulin_unit2 = arr[2];
        Log.d(TAG, "setting_insulin_unit2 = " + setting_insulin_unit2);

        deviceAddress = getIntent().getStringExtra(EXTRAS_DEVICE_ADDRESS);
        if (deviceAddress != null) {
            Log.d(TAG, "onCreate: " + deviceAddress);
        }

        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(ACTION_DATA_AVAILABLE);
        intentfilter.addAction(ACTION_DATA_AVAILABLE_CHANGE);
        registerReceiver(mMessageReceiver, intentfilter);

        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        btn1 = (Button) findViewById(R.id.btn1);
        // 블루투스 값 리시브!
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sd카드 다 리드
                // "a" 값 전송 / 0x61
                mBluetoothLeService.writeCharacteristic("a");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    // 설정 후 돌아오면!
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClicked(int position) {

    }
}


    // 뒤로 가기 버튼 : 팝업창을 띄움
//    @Override
//    public void onBackPressed() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("알림")
//                .setMessage("앱을 종료하실껀가유")
//                .setNegativeButton("취소", null)
//                .setPositiveButton("종료", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        android.os.Process.killProcess(android.os.Process.myPid());
//                    }
//                })
//                .show();
//    }
