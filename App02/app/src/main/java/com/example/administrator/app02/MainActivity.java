package com.example.administrator.app02;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    // TODO 타임라인형식

    // 리스트뷰
    private ListView mLvList;

    // 데이터 리스트
    private ArrayList<String> mAlData;

    // 리스트뷰에 사용되는 ArrayAdapter
    private ArrayAdapter<String> mAaString;

    final int[] selectedItem = {0};

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

        data_view = ( TextView ) findViewById(R.id.data_view);


        // 설정페이지로부터 저장되있는 값 받음 "AA"
        Intent intent = getIntent();
        String AA = intent.getStringExtra("settingData");
        data_view.setText(AA);

        mLvList = ( ListView ) findViewById(R.id.main_lv_list);

        // ArrayList 생성
        mAlData = new ArrayList<String>();

        // ArrayAdapter 생성
        mAaString = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mAlData);

        // 어뎁터를 리스트뷰에 세팅한다.
        mLvList.setAdapter(mAaString);

        // 리스트뷰에 아이템클릭리스너를 등록한다.
        mLvList.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ArrayList 초기화
        mAlData.clear();

//        defaultData();
    }
    // 초기설정
//    private void defaultData() {}

    public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
        // 리스트에서 데이터를 받아온다.
//      String data = (String) parent.getItemAtPosition(position);
        String data = mAlData.get(position);

        // 삭제 다이얼로그에 보여줄 메시지를 만든다.
        String message = "해당 데이터를 삭제하시겠습니까?<br />" +
                "data : " + data + "<br />";

        // 삭제 설정
        DialogInterface.OnClickListener deleteListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // 선택된 아이템을 리스트에서 삭제한다.
                mAlData.remove(position);

                // Adapter에 데이터가 바뀐걸 알리고 리스트뷰에 다시 그린다.
                mAaString.notifyDataSetChanged();
            }
        };

        // 삭제를 물어보는 다이얼로그를 생성한다.
        new AlertDialog.Builder(this)
                .setTitle("진짜지울건가욥")
                .setMessage(Html.fromHtml(message))
                .setPositiveButton("삭제", deleteListener)
                .show();
    }

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
                final String [] items = {"아침식전", "점심식전", "저녁식전", "취침전"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("식사상태를 선택해주세요.")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = which;
                    }
                })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MainActivity.this, items[selectedItem[0]], Toast.LENGTH_SHORT).show();

                                if (data_view.getText().toString() == "") {
                                    // 데이터를 입력하라는 메시지 토스트를 출력한다.
                                    Toast.makeText(getApplicationContext(), "설정부터하세요.", Toast.LENGTH_SHORT).show();
                                } else {
                                    // 입력할 데이터를 받아온다.
                                    Toast.makeText(getApplicationContext(), data_view.getText().toString() + ", " + items[selectedItem[0]], Toast.LENGTH_SHORT).show();

                    String data = data_view.getText().toString() + ", " + items[selectedItem[0]];

                                    // 리스트에 데이터를 입력한다.
                    mAlData.add(data);
                                    // Adapter에 데이터가 바뀐걸 알리고 리스트뷰에 다시 그린다.
                    mAaString.notifyDataSetChanged();

                                    // 데이터 추가 성공 메시지 토스트를 출력한다.
                    Toast.makeText(getApplicationContext(), "입력 완료", Toast.LENGTH_SHORT).show();

                                    // 데이터가 추가된 위치(리스트뷰의 마지막)으로 포커스를 이동시킨다.
                    mLvList.setSelection(mAlData.size() - 1);
                                    dialog.cancel();
                                }
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
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
                CustomDialog dialog_ble = new CustomDialog(this);
                dialog_ble.setDialogListener(new MyDialogListener() {
                    @Override
                    public void onPositiveClicked() {
                    }
                });
                dialog_ble.callFunction(this);
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
                        .setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
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
                        .setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
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