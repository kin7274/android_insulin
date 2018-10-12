package com.example.administrator.app02;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.administrator.app02.DeviceControlActivity.EXTRAS_DEVICE_ADDRESS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    public static Context mContext;
    // 사용자 설정 해당 칸
    TextView setting_kind_1, setting_name_1, setting_unit_1, setting_status_1;
    TextView setting_kind_2, setting_name_2, setting_unit_2, setting_status_2;

    String[] settingdata1 = {"", "", "", ""};  // 1번 설정
    String[] settingdata2 = {"", "", "", ""};  // 2번 설정
    TextView deviceName, deviceAddress;  // 장치 정보 텍스트뷰

    // 저장데이터
    String set1, set2;
    String total_setting_data;
    String insulin_1of2, insulin_2of2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        // 오디오 권한 설정
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 5);
            Toast.makeText(getApplicationContext(),"권한 허용 없이는 음성 인식 기능 사용 불가 ",Toast.LENGTH_SHORT).show();
        }
        setToolbar();
        setStatusbar();
        set();
        // 저장데이터 불러오기
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String str = pref.getString("PREF_STRNAME", "");
        Log.d(TAG, "str = " + str);
        if (str.equals("")) {
            // 초기 1회
            // 설정값이 없다
            Log.d(TAG, "설정부터하세욥..");
            // 사용자에게 알림띄우자
            Toast.makeText(getApplicationContext(), "@@설정부터하세욥@@", Toast.LENGTH_SHORT).show();
        }
        // 설정값이 존재할 경우
        else {
            if (str.contains("&")) {
                // 사용하는 약이 두개인 경우
                String[] arr = str.split("&");
                // 1of2
                insulin_1of2 = arr[0];
//                Log.d(TAG, "insulin_1of2 =" + insulin_1of2);
                String[] insulin_1of2_setdata = insulin_1of2.split(",");
                setting_kind_1.setText(insulin_1of2_setdata[0]);
                setting_name_1.setText(insulin_1of2_setdata[1]);
                setting_unit_1.setText(insulin_1of2_setdata[2]);
                setting_status_1.setText(insulin_1of2_setdata[3]);
                // 2of2
                insulin_2of2 = arr[1];
//                Log.d(TAG, "insulin_2of2 = " + insulin_2of2);
                String[] insulin_2of2_setdata = insulin_2of2.split(",");
                setting_kind_2.setText(insulin_2of2_setdata[0]);
                setting_name_2.setText(insulin_2of2_setdata[1]);
                setting_unit_2.setText(insulin_2of2_setdata[2]);
                setting_status_2.setText(insulin_2of2_setdata[3]);
                // 전역변수로 저장
                Global.setData1(insulin_1of2);
                Global.setData2(insulin_2of2);
            } else {
                // 사용하는 약이 한개인 경우
                String[] arr = str.split(",");
                setting_kind_1.setText(arr[0]);
                setting_name_1.setText(arr[1]);
                setting_unit_1.setText(arr[2]);
                setting_status_1.setText(arr[3]);
                // 전역변수로 저장
                Global.setData1(str);
                Global.setData2("없습니다");
            }
        }
    }

    // 툴바
    public void setToolbar() {
        Toolbar mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("");
    }

    // 상태바 색 변경
    public void setStatusbar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryPurle));
    }

    // 객체
    public void set() {
        // 약품1
        setting_kind_1 = (TextView) findViewById(R.id.setting_kind_1);
        setting_name_1 = (TextView) findViewById(R.id.setting_name_1);
        setting_unit_1 = (TextView) findViewById(R.id.setting_unit_1);
        setting_status_1 = (TextView) findViewById(R.id.setting_status_1);
        setting_kind_1.setOnClickListener(this);
        setting_kind_1.setOnClickListener(this);
        setting_name_1.setOnClickListener(this);
        setting_unit_1.setOnClickListener(this);
        setting_status_1.setOnClickListener(this);
        // 약품2
        setting_kind_2 = (TextView) findViewById(R.id.setting_kind_2);
        setting_name_2 = (TextView) findViewById(R.id.setting_name_2);
        setting_unit_2 = (TextView) findViewById(R.id.setting_unit_2);
        setting_status_2 = (TextView) findViewById(R.id.setting_status_2);
        setting_kind_2.setOnClickListener(this);
        setting_name_2.setOnClickListener(this);
        setting_unit_2.setOnClickListener(this);
        setting_status_2.setOnClickListener(this);
        // 약품 저장
        Button setting_set = (Button) findViewById(R.id.setting_set);
        setting_set.setOnClickListener(this);
        // 장치 스캔 버튼
        Button scan_device = (Button) findViewById(R.id.scan_device);
        scan_device.setOnClickListener(this);
        // 페어링 버튼
        Button pairing_btn = (Button) findViewById(R.id.pairing_btn);
        pairing_btn.setOnClickListener(this);
        // 선택한 장치 이름과 주소 보여주기
        deviceName = (TextView) findViewById(R.id.deviceName);
        deviceAddress = (TextView) findViewById(R.id.deviceAddress);
        // 설정 끝, 다음 페이지로 이동
        Button next_btn = (Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
    }

    // 툴바 메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // 툴바 탭 클릭 이벤트
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 오버플로우 메뉴
            // 영상 페이지로 이동
            case R.id.action_education:
                Intent intent_edu = new Intent(MainActivity.this, EducationActivity.class);
                startActivity(intent_edu);
                break;
            // 앱 평가하기
            // 플레이스토어 댓글 링크 걸자 나중에
            case R.id.action_evaluate:
                showDialog("앱 평가하기", "불편한 점 말씀해주셍..");
                break;
            // 앱 소개하기
            case R.id.app_introduce:
                // 소개앱으로 넘어감
                // 인텐트
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 다이얼로그 인터페이스
    public void showDialog(String title, String context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title)
                .setMessage(context)
                .setPositiveButton("NO", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                    }
                });
        dialog.create();
        dialog.show();
    }

    // 블루투스 설정 후 Device Name, Address 가져옴
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String device_name_address = data.getStringExtra("result");
                String[] device = device_name_address.split(",");
                // 디바이스 이름과 주소 표시
                deviceName.setText(device[0]);
                deviceAddress.setText(device[1]);
            }
        }
    }

    @Override
    public void onClick(View v) {
        //// 1번 설정에 관해
        // 1번 종류
        // spinner01 : 인슐린 종류(5)
        final String[] items = {getResources().getString(R.string.insulin_name), getResources().getString(R.string.insulin_name1), getResources().getString(R.string.insulin_name2), getResources().getString(R.string.insulin_name3), getResources().getString(R.string.insulin_name4)};
        // spinner02 : 하위 품목
        // 초속효성
        final String[] items1 = {getResources().getString(R.string.insulin_name0_0), getResources().getString(R.string.insulin_name0_1), getResources().getString(R.string.insulin_name0_2)};
        // 속효성
        final String[] items2 = {getResources().getString(R.string.insulin_name1_0)};
        // 중간형
        final String[] items3 = {getResources().getString(R.string.insulin_name2_0), getResources().getString(R.string.insulin_name2_1), getResources().getString(R.string.insulin_name2_2), getResources().getString(R.string.insulin_name2_3)};
        // 혼합형
        final String[] items4 = {getResources().getString(R.string.insulin_name3_0), getResources().getString(R.string.insulin_name3_1), getResources().getString(R.string.insulin_name3_2), getResources().getString(R.string.insulin_name3_3)};
        // 지속형
        final String[] items5 = {getResources().getString(R.string.insulin_name4_0), getResources().getString(R.string.insulin_name4_1)};
        // spinner03 : 식사상태
        final String[] items6 = {getResources().getString(R.string.state_0_0), getResources().getString(R.string.state_0_1), getResources().getString(R.string.state_0_2), getResources().getString(R.string.state_0_3)};
        // 임시사용
        final String[] items99;
        //////////////
        // 스위치문
        switch (v.getId()) {
            case R.id.setting_kind_1:
                ArrayAdapter<String> adapter11 = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, items);
                adapter11.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                AlertDialog.Builder builder11 = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("1-1. 종류")
                        .setNegativeButton("NO", null)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            // 리스트 목록 클릭 이벤트
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
//                                Toast.makeText(getApplicationContext(), "선택한 값 : " + items[position], Toast.LENGTH_SHORT).show();
                                settingdata1[0] = items[position];
                                // 메인 텍스트에 값 넣음
                                setting_kind_1.setText(items[position]);
                            }
                        });
                builder11.create();
                builder11.show();
                break;
            // 1번 이름
            case R.id.setting_name_1:
                String mykinds = settingdata1[0];
                if (mykinds.equals("초속효성")) {
                    items99 = items1;
                } else if (mykinds.equals("속효성")) {
                    items99 = items2;
                } else if (mykinds.equals("중간형")) {
                    items99 = items3;
                } else if (mykinds.equals("혼합형")) {
                    items99 = items4;
                } else {
                    items99 = items5;
                }
                ArrayAdapter<String> adapter12 = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, items99);
                adapter12.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                AlertDialog.Builder builder12 = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("1-2. 하위품명")
                        .setNegativeButton("NO", null)
                        .setItems(items99, new DialogInterface.OnClickListener() {
                            // 리스트 목록 클릭 이벤트
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
//                                Toast.makeText(getApplicationContext(), "선택한 값 : " + items99[position], Toast.LENGTH_SHORT).show();
                                settingdata1[1] = items99[position];
                                setting_name_1.setText(items99[position]);
                            }
                        });
                builder12.create();
                builder12.show();
                break;
            // 1번 단위
            case R.id.setting_unit_1:
                final EditText et = new EditText(MainActivity.this);
                AlertDialog.Builder builder13 = new AlertDialog.Builder(MainActivity.this)
                        // 숫자만 입력가능하도록, 키패드를 띄울까?
                        .setTitle("1-3. 단위")
                        .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
                                settingdata1[2] = et.getText().toString();
                                // 입력한 값이 숫자인지 확인
                                if (Pattern.matches("^[0-9]+$", settingdata1[2])) {
                                    // 숫자인 경우
                                    setting_unit_1.setText(et.getText().toString());
                                } else {
                                    // 숫자가 아니네?
                                    Toast.makeText(getApplicationContext(), "숫자만 입력해주세요", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setView(et);
                builder13.create();
                builder13.show();
                break;
            // 1번 식사상태
            case R.id.setting_status_1:
                ArrayAdapter<String> adapter14 = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, items6);
                adapter14.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                AlertDialog.Builder builder14 = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("1-4. 식사상태")
                        .setNegativeButton("NO", null)
                        .setItems(items6, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
//                                Toast.makeText(getApplicationContext(), "선택한 값 : " + items6[position], Toast.LENGTH_SHORT).show();
                                settingdata1[3] = items6[position];
                                setting_status_1.setText(items6[position]);
                            }
                        });
                builder14.create();
                builder14.show();
                break;
            //// 2번 설정에 관해
            // 2번 종류
            case R.id.setting_kind_2:
                ArrayAdapter<String> adapter21 = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, items);
                adapter21.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                AlertDialog.Builder builder21 = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("2-1. 종류")
                        .setNegativeButton("NO", null)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
//                                Toast.makeText(getApplicationContext(), "선택한 값 : " + items[position], Toast.LENGTH_SHORT).show();
                                settingdata2[0] = items[position];
                                setting_kind_2.setText(items[position]);
                            }
                        });
                builder21.create();
                builder21.show();
                break;
            // 2번 이름
            case R.id.setting_name_2:
                String mykinds2 = settingdata2[0];
                if (mykinds2.equals("초속효성")) {
                    items99 = items1;
                } else if (mykinds2.equals("속효성")) {
                    items99 = items2;
                } else if (mykinds2.equals("중간형")) {
                    items99 = items3;
                } else if (mykinds2.equals("혼합형")) {
                    items99 = items4;
                } else {
                    items99 = items5;
                }
                ArrayAdapter<String> adapter22 = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, items99);
                adapter22.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                AlertDialog.Builder builder22 = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("2-2. 하위품명")
                        .setNegativeButton("NO", null)
                        .setItems(items99, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
//                                Toast.makeText(getApplicationContext(), "선택한 값 : " + items99[position], Toast.LENGTH_SHORT).show();
                                settingdata2[1] = items99[position];
                                setting_name_2.setText(items99[position]);
                            }
                        });
                builder22.create();
                builder22.show();
                break;
            // 2번 단위
            case R.id.setting_unit_2:
                final EditText et2 = new EditText(MainActivity.this);
                AlertDialog.Builder builder23 = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("2-3. 단위")
                        .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
                                settingdata2[2] = et2.getText().toString();
                                // 입력값이 숫자인지 확인
                                if (Pattern.matches("^[0-9]+$", settingdata2[2])) {
                                    // 숫자인경우
                                    setting_unit_2.setText(et2.getText().toString());
                                } else {
                                    // 숫자가 아니네?
                                    Toast.makeText(getApplicationContext(), "숫자만 입력해주세요", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setView(et2);
                builder23.create();
                builder23.show();
                break;
            // 2번 식사상태
            case R.id.setting_status_2:
                ArrayAdapter<String> adapter24 = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, items6);
                adapter24.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                AlertDialog.Builder builder24 = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("2-4. 식사상태")
                        .setNegativeButton("NO", null)
                        .setItems(items6, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
//                                Toast.makeText(getApplicationContext(), "선택한 값 : " + items[position], Toast.LENGTH_SHORT).show();
                                settingdata2[3] = items6[position];
                                setting_status_2.setText(items6[position]);
                            }
                        });
                builder24.create();
                builder24.show();
                break;
            // 설정값 저장
            case R.id.setting_set:
                // TODO NULL값이 있으면 저장X, 단 2번 다 비어있으면 1번만 된걸로
                Toast.makeText(getApplicationContext(), "저장하겠습니다.", Toast.LENGTH_SHORT).show();
                // 1번
//                Log.d(TAG, "settingdata1[0] = " + settingdata1[0]);
//                Log.d(TAG, "settingdata1[1] = " + settingdata1[1]);
//                Log.d(TAG, "settingdata1[2] = " + settingdata1[2]);
//                Log.d(TAG, "settingdata1[3] = " + settingdata1[3]);
                // 2번
//                Log.d(TAG, "settingdata2[0] = " + settingdata2[0]);
//                Log.d(TAG, "settingdata2[1] = " + settingdata2[1]);
//                Log.d(TAG, "settingdata2[2] = " + settingdata2[2]);
//                Log.d(TAG, "settingdata2[3] = " + settingdata2[3]);

                // 저장데이터
                set1 = settingdata1[0] + "," + settingdata1[1] + "," + settingdata1[2] + "," + settingdata1[3];
                set2 = settingdata2[0] + "," + settingdata2[1] + "," + settingdata2[2] + "," + settingdata2[3];

                // 전역변수로 저장
                Global.setData1(set1);
                Global.setData2(set2);
                break;
            //// 블루투스
            // 블루투스 장치 검색
            case R.id.scan_device:
                Intent intent1 = new Intent(MainActivity.this, DeviceScanActivity.class);
                startActivityForResult(intent1, 1);
                break;
            // 페어링
            case R.id.pairing_btn:
                final Intent intent2 = new Intent(MainActivity.this, DeviceControlActivity.class);
                intent2.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, deviceName.getText());
                intent2.putExtra(EXTRAS_DEVICE_ADDRESS, deviceAddress.getText());
                startActivity(intent2);
                break;
            // 설정 끝, 다음으로 이동
            case R.id.next_btn:
                Intent intent4 = new Intent(MainActivity.this, Timeline.class);
                // 장치 주소값 전달
                intent4.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, deviceAddress.getText());
                startActivity(intent4);
                break;
        }
    }

    // 액티비티 종료 전에 저장!
    @Override
    protected void onStop() {
        super.onStop();
        String a = Global.getData1();
        String b = Global.getData2();
        if (b != null) {
            // 2번까지 함
            Log.d(TAG, "set1 = " + a + ", set2 = " + b);
            total_setting_data = a + "&" + b;
        } else {
            // 1번만 있음
            Log.d(TAG, "set1 = " + a + ", set2 = null이겠지?" + b);
            total_setting_data = a;
        }
        Log.d(TAG, "쉐어드프레퍼런스값  = " + total_setting_data);
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("PREF_STRNAME", total_setting_data);
        editor.apply();
        Log.d(TAG, "저장완료");
    }
}