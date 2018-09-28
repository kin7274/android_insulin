package com.example.administrator.app02;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.administrator.app02.BluetoothLeService.ACTION_DATA_AVAILABLE;
import static com.example.administrator.app02.BluetoothLeService.ACTION_DATA_AVAILABLE_CHANGE;
import static com.example.administrator.app02.BluetoothLeService.EXTRA_DATA;
import static com.example.administrator.app02.DeviceControlActivity.EXTRAS_DEVICE_ADDRESS;

public class AddNeedleActivity extends AppCompatActivity {

    private final static String TAG = AddNeedleActivity.class.getSimpleName();

    myDB my;
    SQLiteDatabase sql;

    public TextView user_name;
    public Button button1, button2, button3, button4;

    BluetoothLeService mBluetoothLeService = new BluetoothLeService();

    public ArrayList<String> mUserNameArrayList = new ArrayList<String>();
    String user_name2;
    //
    int i = 0;

    String deviceAddress;
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            final String action = intent.getAction();

            if (BluetoothLeService.ACTION_DATA_AVAILABLE_CHANGE.equals(action)) {
                // 이게 지금 내 DB의 행의 갯수야

                // DB의 행의 갯수만큼 데이터를 버리고
                // for문

                // DB의 행의 갯수 +1 만큼 값을 받아
                // while문

//                i++
                final String message = intent.getStringExtra(EXTRA_DATA);
//                Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_SHORT).sh;ow();
                Log.d(TAG, "겟 메세지" + message);

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
                setDB(REALREALREAL);
                Log.d(TAG, "리얼리얼리어리리 : " + REALREALREAL);
            }
//            i++;
        }
    };

    //
    public void setDB(String item) {
        sql = my.getWritableDatabase();
        sql.execSQL("INSERT INTO tb_needle VALUES(null, '" + item + "')");
        Log.d(TAG, "ITEM값은 : " + item);
        sql.close();
        check();
    }

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(deviceAddress);
            //
            Log.d(TAG, "서비스가 연결되었습니다!");
//            Toast.makeText(getApplicationContext(), "서비스가 연결되었습니다!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_needle);

        deviceAddress = getIntent().getStringExtra(EXTRAS_DEVICE_ADDRESS);
        if (deviceAddress != null) {
            Log.d(TAG, "onCreate: " + deviceAddress);
        }

        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(ACTION_DATA_AVAILABLE);
        intentfilter.addAction(ACTION_DATA_AVAILABLE_CHANGE);
        registerReceiver(mMessageReceiver, intentfilter);

//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("namsik"));

        my = new myDB(this);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        user_name = (TextView) findViewById(R.id.textView5);

        // 앱 내 화면 초기화
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "앱 내 화면 초기화", Toast.LENGTH_LONG).show();
                sql = my.getWritableDatabase();
                // 화면 clear
                user_name2 = "";
                my.onUpgrade(sql, 1, 2);
                sql.close();
                check();
            }
        });

        // sd카드의 데이터를 가져온다!
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a : sd카드 다 리드

                // "a" 값 전송 / 0x61
                mBluetoothLeService.writeCharacteristic("a");
            }
        });

        // sd카드의 데이터 삭제
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c : text 삭제
                mBluetoothLeService.writeCharacteristic("c");
                Toast.makeText(getApplicationContext(), "데이터 삭제", Toast.LENGTH_SHORT).show();
            }
        });

        // DB 행 갯수 확인
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //
                //
                //
            }
        });
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onPause();
    }

    // 설정 후 돌아오면!
    @Override
    protected void onResume() {
        super.onResume();
//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("namsik"));

    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    // 조회 메서드
    public void check() {
        sql = my.getReadableDatabase();
        // 화면 clear
        user_name2 = "";
        Cursor cursor;
        cursor = sql.rawQuery("select*from tb_NEEDLE", null);
        while (cursor.moveToNext()) {
            user_name2 += cursor.getString(0) + " : "
                    + cursor.getString(1) + "\n";
        }
        user_name.setText(user_name2);
        cursor.close();
        sql.close();
//        Toast.makeText(getApplicationContext(), "조회하였습니다.", Toast.LENGTH_SHORT).show();
    }

    // 행 갯수 확인 메서드
    public long rows_count() {
        sql = my.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(sql, "tb_NEEDLE");
        long rows_count = (long) count;
        sql.close();
        Toast.makeText(getApplicationContext(), "행의 갯수 : " + rows_count, Toast.LENGTH_SHORT).show();
        return rows_count;
    }

}