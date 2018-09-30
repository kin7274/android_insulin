package com.example.administrator.app02;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity implements OnClickListener, OnItemSelectedListener, SettingRecyclerAdapter.SettingRecyclerViewClickListener {


    Spinner spinner01, spinner02, spinner03;  // 상위 스피너, 인슐린 종류(5)
    // 하위 스피너, 하위 품목
    // 초하위 스피너, 투약 시간
    TextView abc_num;  // 단위 표시
    Button abc_inc, abc_dec;  // 단위 증가, 감소
    Button set_btn, exit_btn;  // 저장버튼

    String a1 = "";
    String a2 = "";
    String a3 = "";
    String a4 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        abc_num = (TextView) findViewById(R.id.abc_num);
        abc_inc = (Button) findViewById(R.id.abc_inc);
        abc_dec = (Button) findViewById(R.id.abc_dec);
        set_btn = (Button) findViewById(R.id.set_btn);
        exit_btn = (Button) findViewById(R.id.exit_btn);

        abc_inc.setOnClickListener(this);
        abc_dec.setOnClickListener(this);
        set_btn.setOnClickListener(this);
        exit_btn.setOnClickListener(this);

        // 상위 스피너 : 인슐린 종류(5)
        final String[] items = {getResources().getString(R.string.insulin_name), getResources().getString(R.string.insulin_name1), getResources().getString(R.string.insulin_name2), getResources().getString(R.string.insulin_name3), getResources().getString(R.string.insulin_name4)};

        // 하위 스피너 : 하위 품목
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

        // 초하위 스피너 : 투약 시간(4)
        final String[] items6 = {"아침식전", "점심식전", "저녁식전", "취침전"};

        final Spinner spinner01 = (Spinner) findViewById(R.id.spinner01);
        final Spinner spinner02 = (Spinner) findViewById(R.id.spinner02);
        final Spinner spinner03 = (Spinner) findViewById(R.id.spinner03);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner01.setAdapter(adapter);
        spinner01.setSelection(0);
        a1 = (String) spinner01.getSelectedItem();
        spinner01.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner01.getItemAtPosition(position) == getResources().getString(R.string.insulin_name)) {
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items1);
                    adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter2);
                    spinner02.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            a2 = (String) spinner02.getItemAtPosition(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else if (spinner01.getItemAtPosition(position) == getResources().getString(R.string.insulin_name1)) {
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items2);
                    adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter3);
                    spinner02.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            a2 = (String) spinner02.getItemAtPosition(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else if (spinner01.getItemAtPosition(position) == getResources().getString(R.string.insulin_name2)) {
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items3);
                    adapter4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter4);
                    spinner02.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            a2 = (String) spinner02.getItemAtPosition(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else if (spinner01.getItemAtPosition(position) == getResources().getString(R.string.insulin_name3)) {
                    ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items4);
                    adapter5.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter5);
                    spinner02.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            a2 = (String) spinner02.getItemAtPosition(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else if (spinner01.getItemAtPosition(position) == getResources().getString(R.string.insulin_name4)) {
                    ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_spinner_item, items5);
                    adapter6.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner02.setAdapter(adapter6);
                    spinner02.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            a2 = (String) spinner02.getItemAtPosition(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                a1 = (String) spinner01.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items6);
        adapter7.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner03.setAdapter(adapter7);
        spinner03.setSelection(0);
        spinner03.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                a4 = (String) spinner03.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    @Override
    public void onClick(View view) {
        // 버튼 이벤트
        String abc_str = (String) abc_num.getText();
        int inabc = (int) Integer.parseInt(abc_str);
        switch (view.getId()) {
            case R.id.abc_inc:
                // 단위 - 증가 버튼
                inabc++;
                abc_num.setText(String.valueOf(inabc));
                break;
            case R.id.abc_dec:
                // 단위 - 감소 버튼
                if (inabc == 1) {
                    // 음수 구분용 - MAX는 필요할까? 10-30? 몇이 적당할까?
                    Toast.makeText(SettingActivity.this, getResources().getString(R.string.num_limit), Toast.LENGTH_SHORT).show();
                } else {
                    // 음수 아니면 원래대로 감소
                    inabc--;
                    abc_num.setText(String.valueOf(inabc));
                }
                abc_num.setText(String.valueOf(inabc));
                break;
            case R.id.set_btn:
                // 저장 버튼
                a3 = (String) abc_num.getText();
                // 최종 확인
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("최종 확인")
                        .setMessage("인슐린 : " + a1 + "\n하위품목 : " + a2 + "\n단위 : " + a3 + "\n투약 시간 : " + a4)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO 투약시간순으로 정렬하고 싶다.
                                String AA = a1 + ", " + a2 + ", " + a3 + ", " + a4;
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("AA", AA);
                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                // 데이터 전송
                break;
            case R.id.exit_btn:
                // 닫기 버튼
                finish();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onItemClicked(int position) {
    }
}



