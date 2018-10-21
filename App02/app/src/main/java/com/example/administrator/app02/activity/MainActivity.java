package com.example.administrator.app02.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.app02.R;

import static com.example.administrator.app02.activity.DeviceControlActivity.EXTRAS_DEVICE_ADDRESS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    public static Context mContext;
    // 사용자 설정 해당 칸
    TextView setting_kind_1, setting_name_1, setting_unit_1, setting_status_1;
    TextView setting_kind_2, setting_name_2, setting_unit_2, setting_status_2;

    String[] settingdata1 = {"", "", "", ""};  // 1번 설정
    String[] settingdata2 = {"", "", "", ""};  // 2번 설정
    TextView deviceName, deviceAddress;  // 장치 정보 텍스트뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        set();
        setToolbar();
        setStatusbar();
        shared_read();
    }

    // 캐시에 저장된 값(사용자설정) 불러오기
    public void shared_read() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String set3 = pref.getString("PREF_STRNAME", "");
        // 나눠서 저장하자 텍스트뷰에
        String[] big = set3.split("&");
        // big[0] = 니들1
        String[] small = big[0].split("/");
        setting_kind_1.setText(small[0]);
        setting_name_1.setText(small[1]);
        setting_unit_1.setText(small[2]);
        setting_status_1.setText(small[3]);
        // big[1] = 니들2
        String[] small2 = big[1].split("/");
        try {
            setting_kind_2.setText(small2[0]);
            setting_name_2.setText(small2[1]);
            setting_unit_2.setText(small2[2]);
            setting_status_2.setText(small2[3]);
        } catch (Exception e) {
            e.printStackTrace();
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
        // 약품2
        setting_kind_2 = (TextView) findViewById(R.id.setting_kind_2);
        setting_name_2 = (TextView) findViewById(R.id.setting_name_2);
        setting_unit_2 = (TextView) findViewById(R.id.setting_unit_2);
        setting_status_2 = (TextView) findViewById(R.id.setting_status_2);
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
            case R.id.action_evaluate:
                // 플레이스토어 댓글 링크 걸자 나중에
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
        switch (v.getId()) {
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
}