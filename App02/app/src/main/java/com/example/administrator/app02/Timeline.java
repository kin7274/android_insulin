package com.example.administrator.app02;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.app02.BluetoothLeService.ACTION_DATA_AVAILABLE;
import static com.example.administrator.app02.BluetoothLeService.ACTION_DATA_AVAILABLE_CHANGE;
import static com.example.administrator.app02.BluetoothLeService.EXTRA_DATA;
import static com.example.administrator.app02.DeviceControlActivity.EXTRAS_DEVICE_ADDRESS;
import static com.example.administrator.app02.MyRecyclerAdapter.MyRecyclerViewClickListener;

// 실질적으로 블루투스값 리시브 액티비티입니다.
// 여기서 DB에도 저장합시다!
public class Timeline extends AppCompatActivity implements MyRecyclerViewClickListener, View.OnClickListener {
    private final static String TAG = Timeline.class.getSimpleName();
    Context mContext;

    myDBDB my;
    SQLiteDatabase sql;
    public ArrayList<String> mUserNameArrayList = new ArrayList<String>();
    String user_name2;

    Button insert_db, load_db, delete_db;

    TextView textview;

    List<CardItem> lists;
    private MyRecyclerAdapter mAdapter;
    RecyclerView recycler_view;

    String[] INSULIN1;
    String[] INSULIN2;

    // Global변수
    String insulin_data1, insulin_data2;

    String aaa, bbb;
    String dododo;
    String eat_status = "";
    String deviceAddress = "";
    String settingdata22 = "";

    String setting_insulin_kinds2 = "";
    String setting_insulin_names2 = "";
    String setting_insulin_unit2 = "";

    public Button btn1;

    TextView text_data1, text_data2;
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

                // searchImage() : 인슐린 종류에 따른 이미지 선택
                // REALREALREAL : 현재 시간
                // settingdata22 :
                String dsdfnkvlndsklcndslk = text_data2.getText().toString();

                /////////////////////////////////////////////
                // 설정한 약값이 하나면 고민할 필요가 없다!//
                /////////////////////////////////////////////
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
    // 범례도 꼭 넣자!!
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // 툴바
        Toolbar mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("");

        mContext = this;

        set();

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 반대로 쌓기
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recycler_view.setLayoutManager(layoutManager);

        // 배열에 자꾸 null값이 떠.. 예외처리로 해결!
        // try catch 단축키 : CTRL + ALT + T 자꾸까먹어..
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

    public void set() {
        // 값 리시브
        text_data1 = (TextView) findViewById(R.id.text_data1);
        text_data2 = (TextView) findViewById(R.id.text_data2);

        // DB관련
        my = new myDBDB(this);

        textview = (TextView) findViewById(R.id.textview);
        insert_db = (Button) findViewById(R.id.insert_db);
        load_db = (Button) findViewById(R.id.load_db);
        delete_db = (Button) findViewById(R.id.delete_db);
        insert_db.setOnClickListener(this);
        load_db.setOnClickListener(this);
        delete_db.setOnClickListener(this);

        // 설정한 1번 약
        insulin_data1 = Global.getData1();
        text_data1.setText(insulin_data1);
        INSULIN1 = insulin_data1.split(",");
        Log.d(TAG, "1번. 종류 = " + INSULIN1[0]);
        Log.d(TAG, "1번. 품명 = " + INSULIN1[1]);
        Log.d(TAG, "1번. 단위 = " + INSULIN1[2]);
        // 식사상태에 공백제거...
        aaa = INSULIN1[3].replace(" ", "");
        Log.d(TAG, "1번. 식사상태 = " + aaa);

        // 설정한 2번 약
        // 없으면 없다고 떠!!
        insulin_data2 = Global.getData2();
        text_data2.setText(insulin_data2);
        if (insulin_data2.equals("없습니다")) {
            Log.d(TAG, "또잉..");
        } else {
            INSULIN2 = insulin_data2.split(",");
            Log.d(TAG, "2번. 종류 = " + INSULIN2[0]);
            Log.d(TAG, "2번. 품명 = " + INSULIN2[1]);
            Log.d(TAG, "2번. 단위 = " + INSULIN2[2]);
            bbb = INSULIN2[3].replace(" ", "");
            Log.d(TAG, "2번. 식사상태 = " + bbb);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    // 시간에 따른 식사상태 구분;
    //
    // MSG[9] + MSG[10] = 현재 시간
    // 형식 : 00 ~ 24시
    // 21-05(8h) : 취침전;
    // 05-11(6h) : 아침식전;
    // 11-16(5h) : 점심식전;
    // 16-21(5h) : 저녁식전;

    // 지금 시간이 18시니까
    // 저녁식전이야
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
        sql.execSQL("INSERT INTO tb_needle VALUES(null, '" + time + "', '" + setting + "')");
//        Toast.makeText(getApplicationContext(), "저장하였습니다", Toast.LENGTH_SHORT).show();
//        Log.d(TAG, "TIME값은 " + time);
//        Log.d(TAG, "SETTING값은 " + setting);
        Log.d(TAG, "저장했어유");
        sql.close();
        check();
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
                    + cursor.getString(1) + "/"
                    + cursor.getString(2) + "\n";
            String oioi = cursor.getString(2).substring(0, cursor.getString(2).indexOf(","));
//            Log.d(TAG, "oioi = " + oioi);
            lists.add(new CardItem(searchImage(oioi),cursor.getString(1), cursor.getString(2)));
        }
        // 텍스트뷰에 말고
        // 카드뷰로 저장을 해
        mAdapter.notifyDataSetChanged();

        textview.setText(user_name2);
        cursor.close();
        sql.close();
        Toast.makeText(getApplicationContext(), "조회하였습니다.", Toast.LENGTH_SHORT).show();
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
        switch (v.getId()) {
            // DB에 추가한다
            case R.id.insert_db:
                int cnt = lists.size();
                Log.d(TAG, "저장할 리스트 갯수 : " + lists.size());
                for(int i=0; i<cnt; i++){
                    Log.d(TAG, (i+1) + "번 시간값 : " + lists.get(i).getState());
                    Log.d(TAG, (i+1) + "번 설정값 : " + lists.get(i).getSetting());
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
    public void onItemClicked(int position) {

    }
}
