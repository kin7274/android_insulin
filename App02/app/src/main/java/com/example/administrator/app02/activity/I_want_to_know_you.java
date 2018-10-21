package com.example.administrator.app02.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.app02.model.Global;
import com.example.administrator.app02.R;

import java.util.regex.Pattern;

public class I_want_to_know_you extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = I_want_to_know_you.class.getSimpleName();

    // 니들1
    TextView setting_kind_1, setting_name_1, setting_unit_1, setting_status_1;
    // 니들2
    TextView setting_kind_2, setting_name_2, setting_unit_2, setting_status_2;
    // 약1, 2
    TextView setting_medi_1, setting_medi_2;

    String[] settingdata1 = {"", "", "", ""};  // 니들 1번 설정
    String[] settingdata2 = {"", "", "", ""};  // 니들 2번 설정
    String settingdata3 = "";  // 약 1번 설정
    String settingdata4 = "";  // 약 2번 설정

    String set1, set2, set3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i_want_to_know_you);
        setToolbar();
        setStatusbar();
        set();
    }

    public void setToolbar() {
        Toolbar mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("");
    }

    public void setStatusbar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryPurle));
    }

    public void set() {
        // 니들1
        setting_kind_1 = (TextView) findViewById(R.id.setting_kind_1);
        setting_name_1 = (TextView) findViewById(R.id.setting_name_1);
        setting_unit_1 = (TextView) findViewById(R.id.setting_unit_1);
        setting_status_1 = (TextView) findViewById(R.id.setting_status_1);
        setting_kind_1.setOnClickListener(this);
        setting_name_1.setOnClickListener(this);
        setting_unit_1.setOnClickListener(this);
        setting_status_1.setOnClickListener(this);
        // 니들2
        setting_kind_2 = (TextView) findViewById(R.id.setting_kind_2);
        setting_name_2 = (TextView) findViewById(R.id.setting_name_2);
        setting_unit_2 = (TextView) findViewById(R.id.setting_unit_2);
        setting_status_2 = (TextView) findViewById(R.id.setting_status_2);
        setting_kind_2.setOnClickListener(this);
        setting_name_2.setOnClickListener(this);
        setting_unit_2.setOnClickListener(this);
        setting_status_2.setOnClickListener(this);
        // 약1
        setting_medi_1 = (TextView) findViewById(R.id.setting_medi_1);
        // 약2
        setting_medi_2 = (TextView) findViewById(R.id.setting_medi_2);
        // 설정끝
        Button next_btn = (Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
    }

    // 다이얼로그 인터페이스
    public void showDialog(String title, String context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title)
                .setMessage(context)
                .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                    }
                });
        dialog.create();
        dialog.show();
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
                AlertDialog.Builder builder11 = new AlertDialog.Builder(I_want_to_know_you.this)
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
                AlertDialog.Builder builder12 = new AlertDialog.Builder(I_want_to_know_you.this)
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
                final EditText et = new EditText(I_want_to_know_you.this);
                AlertDialog.Builder builder13 = new AlertDialog.Builder(I_want_to_know_you.this)
                        // 숫자만 입력가능하도록, 키패드를 띄울까?
                        .setTitle("1-3. 단위")
                        .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
                                settingdata1[2] = et.getText().toString();
                                // 입력한 값이 숫자인지 확인
//                                if (Pattern.matches("^[0-9]+$", settingdata1[2])) {
                                // 숫자인 경우
                                setting_unit_1.setText(et.getText().toString());
//                                } else {
                                // 숫자가 아니네?
//                                    Toast.makeText(getApplicationContext(), "숫자만 입력해주세요", Toast.LENGTH_SHORT).show();
//                                }
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
                AlertDialog.Builder builder14 = new AlertDialog.Builder(I_want_to_know_you.this)
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
                AlertDialog.Builder builder21 = new AlertDialog.Builder(I_want_to_know_you.this)
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
                AlertDialog.Builder builder22 = new AlertDialog.Builder(I_want_to_know_you.this)
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
                final EditText et2 = new EditText(I_want_to_know_you.this);
                AlertDialog.Builder builder23 = new AlertDialog.Builder(I_want_to_know_you.this)
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
                AlertDialog.Builder builder24 = new AlertDialog.Builder(I_want_to_know_you.this)
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
            // 설정 끝, 다음으로 이동
            case R.id.next_btn:
                // 저장데이터
                if ((settingdata1[0].equals("")) || (settingdata1[1].equals("")) || (settingdata1[2].equals("")) || (settingdata1[3].equals(""))) {
                    // 다시!! 빠꾸
//                    Toast.makeText(getApplicationContext(), "다시해", Toast.LENGTH_SHORT).show();
                } else {
                    // 1번 세팅 완료
                    if ((settingdata2[0].equals("")) || (settingdata2[1].equals("")) || (settingdata2[2].equals("")) || (settingdata2[3].equals(""))) {
                        // 2번 빠꾸
                        // 1번만 OK
//                        Toast.makeText(getApplicationContext(), "1번 OK", Toast.LENGTH_SHORT).show();

                    } else {
                        // 2번 세팅 완료
                        // 1,2번 모두 OK
//                        Toast.makeText(getApplicationContext(), "1.2번 OK", Toast.LENGTH_SHORT).show();
                        set2 = settingdata2[0] + "/" + settingdata2[1] + "/" + settingdata2[2] + "/" + settingdata2[3];
                        Global.setData2(set2);

                    }
                }
                set1 = settingdata1[0] + "/" + settingdata1[1] + "/" + settingdata1[2] + "/" + settingdata1[3];
                Global.setData1(set1);
                // 이런 식으로
                set3 = set1 + "&" + set2;
                // 저장하기
                shared();
                // 넘어가기
                Intent intent = new Intent(I_want_to_know_you.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void shared(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("PREF_STRNAME", set3);
        editor.apply();
    }
}
