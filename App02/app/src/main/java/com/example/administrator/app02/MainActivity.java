package com.example.administrator.app02;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.app02.MyRecyclerAdapter.*;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewClickListener {
    // TODO 타임라인형식

    private MyRecyclerAdapter mAdapter;
    RecyclerView recyclerView;
    List<CardItem> dataList;

    // 다이얼로그 선택된 값
    final int[] selectedItem = {0};

    // 설정 페이지에서 저장된 값 표시
    TextView data_view;

    // 메인
    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 툴바
        Toolbar mytoolbar = ( Toolbar ) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("");

        // 상태바 색 변경
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor(getResources().getString(R.color.colorPrimaryPurle)));

        // 설정페이지로부터 저장되있는 값 받음 AA = 설정페이지에서 설정한 값
        Intent intent = getIntent();
        String AA = intent.getStringExtra("settingData");
        data_view = ( TextView ) findViewById(R.id.data_view);
        data_view.setText(AA);

        RecyclerView recyclerView = ( RecyclerView ) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);

        // 레이아웃 매니저로 LinearLayoutManager를 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // 표시할 임시 데이터
        List<CardItem> dataList = new ArrayList<>();
//        dataList.add(new CardItem("하죶나","빡치네"));

        // 어댑터 설정
        mAdapter = new MyRecyclerAdapter(dataList);
        mAdapter.setOnClickListener(( MyRecyclerViewClickListener ) this);
        recyclerView.setAdapter(mAdapter);

        // 구분선
        // 이쁘면 메뉴얼쪽에도 추가하자
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

//        mLvList = ( ListView ) findViewById(R.id.main_lv_list);
//        mLvList.setOnItemClickListener(this);
//        mAlData = new ArrayList<String>();
//        mAaString = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mAlData);
//        mLvList.setAdapter(mAaString);
    }

//    public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
//        String data = mAlData.get(position);
//        String data = dataList.get(position);
//        // 삭제 설정
//        OnClickListener deleteListener = new OnClickListener() {
//            @Override
//            public void onClick(DialogInterface arg0, int arg1) {
//                // 선택된 아이템을 리스트에서 삭제
//                dataList.remove(position);
//                // 리스트 갱신
////                dataList.notifyDataSetChanged();
//            }
//        };

        // 삭제
//        new AlertDialog.Builder(this)
//                .setTitle("진짜지울건가욥")
//                .setMessage("해당 데이터를 삭제하시겠습니까?" + "\ndata : " + data)
//                .setPositiveButton("삭제", deleteListener)
//                .show();
//    }

    // 클릭 이벤트
    @Override
    public void onItemClicked(int position) {
        Toast.makeText(getApplicationContext(), "띠용", Toast.LENGTH_LONG).show();
    }

    private View.OnClickListener leftListener = new View.OnClickListener() {
        public void onClick(View v) {
            //
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
                builder.setTitle("식사상태를 선택해주세요.")
                        .setSingleChoiceItems(items, 0, new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedItem[0] = which;
                            }
                        })
                        .setPositiveButton("확인", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MainActivity.this, items[selectedItem[0]], Toast.LENGTH_SHORT).show();
                                if (data_view.getText().toString() == "") {
                                    // 설정을 안한 경우
                                    Toast.makeText(getApplicationContext(), "설정부터하세요.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), items[selectedItem[0]]+ ", " + data_view.getText().toString(), Toast.LENGTH_SHORT).show();
//                                    // 현재시간 미구현
//                                    String data = "현재시간, " + data_view.getText().toString() + ", " + items[selectedItem[0]];
//                                    // 리스트에 데이터를 입력
//                                    mAdapter.notifyItemInserted(dataList.size() - 1);
                                    mAdapter.notifyItemInserted(which, items[selectedItem[0]]);
//                                    dataList.add(new CardItem(items[selectedItem[0]], data_view.getText().toString()));
//                                    mAdapter.notifyDataSetChanged();
//                                    // 입력 완료
                                    Toast.makeText(getApplicationContext(), "입력 완료", Toast.LENGTH_SHORT).show();
                                    // 데이터가 추가된 위치(리스트뷰의 마지막)으로 포커스를 이동시킨다.
//                                    recyclerView.setSelection(dataList.size() - 1);
                                    dialog.cancel();
                                }
                            }
                        })
                        .setNegativeButton("취소", new OnClickListener() {
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
                Intent intent_ble = new Intent(MainActivity.this, BluetoothSetting.class);
                Toast.makeText(getApplicationContext(), "띠용", Toast.LENGTH_LONG).show();
                startActivity(intent_ble);
                break;
            // 오버플로우 메뉴
            case R.id.action_setting:
                // 설정 페이지로 이동
                Intent intent_setting = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent_setting);
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