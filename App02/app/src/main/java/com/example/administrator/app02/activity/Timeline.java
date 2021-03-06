package com.example.administrator.app02.activity;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.app02.BluetoothLeService;
import com.example.administrator.app02.model.CardItem;
import com.example.administrator.app02.model.Global;
import com.example.administrator.app02.adapter.MyRecyclerAdapter;
import com.example.administrator.app02.R;
import com.example.administrator.app02.model.myDBDB;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.app02.BluetoothLeService.ACTION_DATA_AVAILABLE;
import static com.example.administrator.app02.BluetoothLeService.ACTION_DATA_AVAILABLE_CHANGE;
import static com.example.administrator.app02.BluetoothLeService.EXTRA_DATA;
import static com.example.administrator.app02.adapter.MyRecyclerAdapter.MyRecyclerViewClickListener;
import static com.example.administrator.app02.activity.DeviceControlActivity.EXTRAS_DEVICE_ADDRESS;

// 실질적으로 블루투스값 리시브 액티비티입니다.
// 여기서 DB에도 저장합시다!
public class Timeline extends AppCompatActivity implements MyRecyclerViewClickListener, View.OnClickListener {
    private final static String TAG = Timeline.class.getSimpleName();
    Context mContext;
    myDBDB my;
    SQLiteDatabase sql;
    String user_name2;
    Button donggihwa, insert_db, load_db, delete_db;
    TextView textview;
    List<CardItem> lists;
    private MyRecyclerAdapter mAdapter;
    RecyclerView recycler_view;
    String[] INSULIN1, INSULIN2;
    String insulin_data1, insulin_data2;
    String aaa, bbb;
    String eat_status = "";
    String deviceAddress = "";

    BluetoothLeService mBluetoothLeService = new BluetoothLeService();
    // 브로드캐스트
    private final BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_DATA_AVAILABLE_CHANGE.equals(action)) {
                final String message = intent.getStringExtra(EXTRA_DATA);
                Log.d(TAG, "겟 메세지" + message);
                Log.d(TAG, "message = 값 조쿠요");
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

                // searchImage() : 인슐린 종류에 따른 이미지 선택
                // REALREALREAL : 현재 시간
                // settingdata22 :
//                String dsdfnkvlndsklcndslk = text_data2.getText().toString();
                String dsdfnkvlndsklcndslk = Global.getData2();

                // 설정한 약값이 하나면 고민할 필요가 없다!//
                if (dsdfnkvlndsklcndslk.equals("없습니다")) {
                    // searchImage() : 인슐린 종류에 따른 이미지 선택
                    // REALREALREAL : 현재 시간
                    // settingdata22 : 설정값
                    lists.add(new CardItem(searchImage(INSULIN1[0]), REALREALREAL, dsdfnkvlndsklcndslk));
                    mAdapter.notifyDataSetChanged();
                    Log.d(TAG, "222222띠용...므엇이죠 이게...");
                    Log.d(TAG, "리얼리얼리어리리 : " + REALREALREAL);
                } else {
                    /////////////////////////////////////////////
                    // 설정한 약값이 두개야
                    /////////////////////////////////////////////
                    String str_hh = MSG[9] + MSG[10];
                    int hh = Integer.parseInt(str_hh);
                    Log.d(TAG, "현재 시간은 " + hh + "시입니다");
                    Log.d(TAG, "음 지금 시간이면 ..." + hh_kinds(hh) + "상태구나!!!");
                    // 현재 시간에 따라 상태 설정

                    if (hh_kinds(hh).equals(aaa)) {
                        // 그럼 나는 1번 약을 투여한거야
                        Log.d(TAG, "그럼 나는 1번 약을 투여한거야");
                        lists.add(new CardItem(searchImage(INSULIN1[0]), REALREALREAL, insulin_data1));
                    } else if (hh_kinds(hh).equals(bbb)) {
                        // 그럼 나는 2번 약을 투여한거야
                        Log.d(TAG, "그럼 나는 2번 약을 투여한거야");
                        lists.add(new CardItem(searchImage(INSULIN2[0]), REALREALREAL, insulin_data2));
                    } else {
                        // 난 1, 2번에 설정해논거에 없네?
                        // 그럼 머야
                        // ??로 표시하자
                        Log.d(TAG, "읭? 난 무슨 약을 투여한거지??");
                        lists.add(new CardItem(R.drawable.ic_priority_high_black_24dp, REALREALREAL, "다시 설정해줘.. 시간이 맞는게 없어.. 무야.."));
                    }
                    mAdapter.notifyDataSetChanged();
                    Log.d(TAG, "333333띠용...무요!!");
                    Log.d(TAG, "리얼리얼리어리리 : " + REALREALREAL);
                }
            }
        }
    };

    // 투약 종류마다 리스트 맨 앞 색 구별!!!!!!
    int searchImage(String aa) {
        int a = 0;
        switch (aa) {
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
            Log.d(TAG, "서비스가 연결되었습니다!");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    // 메인
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        mContext = this;
        setToolbar();
        set();
        setRecyclerview();
        // BLE
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
    }

    // 툴바
    public void setToolbar() {
        Toolbar mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("");
    }

    // 리사이클러뷰 + 어댑터
    public void setRecyclerview() {
        // 객체 생성
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 반대로 쌓기
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recycler_view.setLayoutManager(layoutManager);
        // 배열 null 예외처리
        try {
            lists = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAdapter = new MyRecyclerAdapter(lists);
        mAdapter.setOnClickListener(this);
        recycler_view.setAdapter(mAdapter);
    }

    public void set() {
        // DB관련
        my = new myDBDB(this);
        // 버튼 객체
        donggihwa = (Button) findViewById(R.id.donggihwa);
        insert_db = (Button) findViewById(R.id.insert_db);
        load_db = (Button) findViewById(R.id.load_db);
        delete_db = (Button) findViewById(R.id.delete_db);
        donggihwa.setOnClickListener(this);
        insert_db.setOnClickListener(this);
        load_db.setOnClickListener(this);
        delete_db.setOnClickListener(this);
    }

    // 시간에 따른 식사상태 구분;
    // MSG[9] + MSG[10] = 현재 시간
    // 형식 : 00 ~ 24시
    // 21-05(8h) : 취침전;
    // 05-11(6h) : 아침식전;
    // 11-16(5h) : 점심식전;
    // 16-21(5h) : 저녁식전;
    public String hh_kinds(int hh) {
        if ((hh >= 05) && (hh < 11)) {
            eat_status = "아침식전";
        } else if ((hh >= 11) && (hh < 16)) {
            eat_status = "점심식전";
        } else if ((hh >= 16) && (hh < 21)) {
            eat_status = "저녁식전";
        } else {
            eat_status = "취침전";
        }
        Log.d(TAG, "eat_status = " + eat_status);
        return eat_status;
    }

    // DB에 저장하는 메서드
    public void setDB(String time, String setting) {
        sql = my.getWritableDatabase();
        sql.execSQL("INSERT INTO tb_needle VALUES(null, '" + time + "/" + setting + "')");
//        Toast.makeText(getApplicationContext(), "저장하였습니다", Toast.LENGTH_SHORT).show();
//        Log.d(TAG, "TIME값은 " + time);
//        Log.d(TAG, "SETTING값은 " + setting);
        Log.d(TAG, "저장했어유");
        sql.close();
//        check();
    }

    // 조회 메서드
    public void check() {
        sql = my.getReadableDatabase();
        // 화면 clear
        user_name2 = "";
        Cursor cursor;
        cursor = sql.rawQuery("select*from tb_NEEDLE", null);
        while (cursor.moveToNext()) {
            user_name2 += cursor.getString(0) + ":"
                    + cursor.getString(1) + "/"
                    + cursor.getString(2) + "\n";
            String oioi = cursor.getString(2).substring(0, cursor.getString(2).indexOf("/"));
//            Log.d(TAG, "oioi = " + oioi);
            lists.add(new CardItem(searchImage(oioi), cursor.getString(1), cursor.getString(2)));
        }
        // 텍스트뷰에 말고
        // 카드뷰로 저장을 해
        mAdapter.notifyDataSetChanged();
        cursor.close();
        sql.close();
        Toast.makeText(getApplicationContext(), "조회하였습니다.", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.donggihwa:
                // sd카드 다 리드
                // "a" 값 전송
                mBluetoothLeService.writeCharacteristic("a");
                break;
            // DB에 추가한다
            case R.id.insert_db:
                int rows_cnt = (int) (long) rows_count();
                int cnt = lists.size();
                Log.d(TAG, "저장할 리스트 갯수 : " + lists.size());
                for (int i = rows_cnt; i < cnt; i++) {
                    Log.d(TAG, (i + 1) + "번 시간값 : " + lists.get(i).getState());
                    Log.d(TAG, (i + 1) + "번 설정값 : " + lists.get(i).getSetting());
//                    Log.d(TAG, "1번 시간값 : " + lists.get(0).getState());
//                    Log.d(TAG, "1번 설정값 : " + lists.get(0).getSetting());
//                    Log.d(TAG, "2번 시간값 : " + lists.get(1).getState());
//                    Log.d(TAG, "2번 설정값 : " + lists.get(1).getSetting());
                    setDB(lists.get(i).getState(), lists.get(i).getSetting());
                }
                break;
            // DB를 가져온다
            case R.id.load_db:
                check();
                break;
            // DB 완전 삭제
            case R.id.delete_db:
                sql = my.getWritableDatabase();
                user_name2 = "";
                my.onUpgrade(sql, 1, 2);
                sql.close();
                check();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClicked(int position) {
    }
}
