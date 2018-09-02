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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.app02.MyRecyclerAdapter.*;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewClickListener {
    // TODO 타임라인형식

    public static Context mContext;

    // 쉐어드
    EditText et;

    String receive_data_real;

    SharedPreferences sharedPreferences;
    private MyRecyclerAdapter mAdapter;
    RecyclerView recyclerView;
    List<CardItem> dataList;

    // 다이얼로그 선택된 값
    final int[] selectedItem = {0};

    int Init = 1;

    // 설정 페이지에서 저장된 값 표시
    TextView data_view;

    TextView data_receive;

    // 메인
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        data_view = (TextView) findViewById(R.id.data_view);
        data_receive = (TextView) findViewById(R.id.data_receive);

        // 툴바
        Toolbar mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("");

        // 상태바 색 변경
//        View view = getWindow().getDecorView();
//        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        getWindow().setStatusBarColor(Color.parseColor(getResources().getString(R.color.colorPrimaryPurle)));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);

        // 레이아웃 매니저로 LinearLayoutManager를 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // 표시할 임시 데이터
        List<CardItem> dataList = new ArrayList<>();
//        dataList.add(new CardItem("제발","제발요"));
        dataList.add(new CardItem("제발", "제발요"));
        dataList.add(new CardItem("제발2", "제발2요"));
        dataList.add(new CardItem("제발3", "제발3요"));

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
        data_view.setText(str);
    }

    public void cocococo(){
        receive_data_real = Global.getData();
        Toast.makeText(getApplicationContext(), receive_data_real, Toast.LENGTH_LONG).show();
        data_receive.setText(receive_data_real);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("AA");
                data_view.setText(result);
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
        editor.commit();
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
            case R.id.action_auto:
                // 수동버튼 클릭 시
                // 식사상태 선택 팝업창 열림
                final String[] items = {"아침식전", "점심식전", "저녁식전", "취침전"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("식사상태를 선택해주세요.");
                builder.setSingleChoiceItems(items, 0, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = which;
                    }
                });
                builder.setPositiveButton("확인", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MainActivity.this, items[selectedItem[0]], Toast.LENGTH_SHORT).show();
                        if (data_view.getText().toString() == "") {
                            // 설정을 안한 경우
                            Toast.makeText(getApplicationContext(), "설정부터하세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.cancel();
                            // 현재시간 미구현
                            String data = "현재시간, " + items[selectedItem[0]] + ", " + data_view.getText().toString();
                            Toast.makeText(getApplicationContext(), "입력값 : " + items[selectedItem[0]] + ", " + data_view.getText().toString(), Toast.LENGTH_SHORT).show();
                            // 데이터가 추가된 위치(리스트뷰의 마지막)으로 포커스를 이동시킨다.
//                                    recyclerView.setSelection(dataList.size() - 1);
//                            finish();
                        }
                    }
                });
                builder.setNegativeButton("취소", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "취소", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.action_ble:
                // 블루투스 연결
                Intent intent_ble = new Intent(MainActivity.this, DeviceScanActivity.class);
                Toast.makeText(getApplicationContext(), "띠용", Toast.LENGTH_LONG).show();
                startActivity(intent_ble);
                break;
            // 오버플로우 메뉴
            case R.id.action_setting:
                // 설정 페이지로 이동
                Intent intent_setting = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(intent_setting, 1);
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
}