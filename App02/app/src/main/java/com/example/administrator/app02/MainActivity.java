package com.example.administrator.app02;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.app02.CardItem;
import com.example.administrator.app02.CustomDialog;
import com.example.administrator.app02.DeviceScanActivity;
import com.example.administrator.app02.EducationActivity;
import com.example.administrator.app02.MyDialogListener;
import com.example.administrator.app02.MyRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.app02.MyRecyclerAdapter.*;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewClickListener, View.OnClickListener {

    TextView textview1;
    TextView textview2;

    private final static String TAG = MainActivity.class.getSimpleName();

    private CustomDialog dialog;

    int i = 0;

    public static Context mContext;

    // 쉐어드
    EditText et;

    String result3 = "";

    // 임시로 만듦
    Button clickclick;

    String receive_data_real;

    SharedPreferences sharedPreferences;
    private MyRecyclerAdapter mAdapter;
    RecyclerView recyclerView;
    List<CardItem> dataList;

    // 다이얼로그 선택된 값
    final int[] selectedItem = {0};

    // 설정 페이지에서 저장된 값 표시
    TextView data_view;
    TextView data_receive;

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

        // 어댑터 설정
        mAdapter = new MyRecyclerAdapter(dataList);
        mAdapter.setOnClickListener(this);
        recyclerView.setAdapter(mAdapter);

        // 구분선
        // 이쁘면 메뉴얼쪽에도 추가하자
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        // 저장데이터 불러오기
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String str = pref.getString("PREF_STRNAME", "");
//        data_view.setText(str);

        String numbers = str;
        String[] arr = numbers.split(",");
//        Toast.makeText(getApplicationContext(), arr[0], Toast.LENGTH_LONG).show();
        result3 = arr[0];

//        clickclick = (Button) findViewById(R.id.clickclick);
//        clickclick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (data_view.getText().toString() == "") {
//                    Toast.makeText(getApplicationContext(), "설정하고와!", Toast.LENGTH_LONG).show();
//                } else {
////                    Toast.makeText(getApplicationContext(), "ㄱㄱ", Toast.LENGTH_LONG).show();
//                    // 저장을 해보자
//                    // 리사이클러뷰 추가!
//
////                    dataList.add(new CardItem("제발4", "제발요4"));
////                    mAdapter.notifyDataSetChanged();
//                    final CustomDialog dialog = new CustomDialog(MainActivity.this);
//                    dialog.setDialogListener(new MyDialogListener() {
//                        @Override
//                        public void onPositiveClicked(String email) {
//                            Toast.makeText(getApplicationContext(), "받은 값은 : " + email, Toast.LENGTH_LONG).show();
//
//                            dataList.add(new CardItem(searchImage(), email, data_view.getText().toString()));
////                            dataList.add(new CardItem(searchImage(), email, data_view.getText().toString()));
//                            mAdapter.notifyDataSetChanged();
//                        }
//
//                        @Override
//                        public void onNegativeClicked() {
//                        }
//                    });
//                    dialog.show();
//                }
//            }
//        });
    }

    public void set(){
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        final TextView textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
    }
    // 투약 종류마다 리스트 맨 앞 색 구별!!!!!!
    // 범례도 꼭 넣자!!
    public int searchImage() {
        int a = 0;
        switch (result3) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                // 인슐린 종합 데이터
                String result = data.getStringExtra("AA");
                data_view.setText(result);
                String numbers = result;
                String[] arr = numbers.split(",");
                Toast.makeText(getApplicationContext(), arr[0], Toast.LENGTH_LONG).show();
                result3 = arr[0];
            }
        }
    }

    // 액티비티 종료 전에 저장!
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String strrr = data_view.getText().toString();
        editor.putString("PREF_STRNAME", strrr);
        editor.apply();
    }

    //  클릭 이벤트
    @Override
    public void onItemClicked(int position) {
        Toast.makeText(getApplicationContext(), position + "번째 삭제", Toast.LENGTH_LONG).show();
    }

    private View.OnClickListener leftListener = new View.OnClickListener() {
        public void onClick(View v) {
        }
    };

    // 메뉴.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 툴바 탭 클릭 이벤트
        switch (item.getItemId()) {
            case R.id.action_ble:
                // 블루투스 연결
                Intent intent_ble = new Intent(MainActivity.this, DeviceScanActivity.class);
                Toast.makeText(getApplicationContext(), "띠용", Toast.LENGTH_LONG).show();
                startActivity(intent_ble);
                break;
            // 오버플로우 메뉴
            case R.id.action_setting:
                // 설정 페이지로 이동
//                Intent intent_setting = new Intent(MainActivity.this, SettingActivity.class);
//                startActivityForResult(intent_setting, 1);
                break;
            case R.id.action_education:
                // 영상 페이지로 이동
                Intent intent_edu = new Intent(MainActivity.this, EducationActivity.class);
                startActivity(intent_edu);
                break;
            case R.id.action_guide:
                // 어플에 대한 사용법 간단하게
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
            case R.id.action_myinfo:
                // 개발자에 대한 사용법 간단하게
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
            case R.id.btn2:
                final Intent intent2 = new Intent(MainActivity.this, DeviceControlActivity.class);
                intent2.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, textview1.getText());
                intent2.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, textview2.getText());
                startActivity(intent2);
                break;
            case R.id.btn3:
                Intent intent3 = new Intent(MainActivity.this, AddNeedleActivity.class);
                intent3.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, textview1.getText());
                intent3.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, textview2.getText());
                startActivity(intent3);
                break;
        }
    }
}
