package com.example.administrator.app02;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialog2 extends Dialog {
    // TODO 커스텀다이얼로그 뷰 꾸미기
    // TODO 데이터 꺼내기

    private MyDialogListener dialogListener;
    private Context context;
    private TextView textTitle;
    private String title;

    String AA = null;

    private RadioGroup radiogroup1;
    private RadioButton radioButton, radioButton2, radioButton3, radioButton4;

    public CustomDialog2(Context context, String title) {
        super(context);
        this.context = context;
        this.title = title;
    }

    public void setDialogListener(MyDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom2);

        textTitle = (TextView) findViewById(R.id.textTitle);
        textTitle.setText(title);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup);

        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);

        // 확인 = 종료 버튼
        Button okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mychoice();
                Toast.makeText(getContext(),"나의 선택은 : " + AA, Toast.LENGTH_SHORT).show();
                cancel();
            }
        });
    }

    // 나의 선택은 무엇인가..
    public void mychoice() {
        if (radioButton.isChecked()) {
            AA = (String) radioButton.getText();
        }
        if (radioButton2.isChecked()) {
            AA = (String) radioButton2.getText();
        }
        if (radioButton3.isChecked()) {
            AA = (String) radioButton3.getText();
        }
        if (radioButton4.isChecked()) {
            AA = (String) radioButton4.getText();
        }
    }
}