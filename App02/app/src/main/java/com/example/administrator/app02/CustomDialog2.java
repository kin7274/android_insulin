package com.example.administrator.app02;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialog2 extends Dialog {

    private MyDialogListener dialogListener;
    private Context context;
    private TextView textTitle;
    private String title;

    String a1 = "";
    String a2 = "";

    private RadioGroup radiogroup1, radiogroup2;
    private RadioButton radioButton, radioButton2, radioButton3, radioButton4, radioButton5,
            radioButton6, radioButton7, radioButton8, radioButton9;

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
        RadioGroup rg2 = (RadioGroup) findViewById(R.id.radiogroup2);

        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        radioButton6 = (RadioButton) findViewById(R.id.radioButton6);
        radioButton7 = (RadioButton) findViewById(R.id.radioButton7);
        radioButton8 = (RadioButton) findViewById(R.id.radioButton8);
        radioButton9 = (RadioButton) findViewById(R.id.radioButton9);

        // 확인 = 종료 버튼
        Button okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mychoice();
                Toast.makeText(getContext(), "띠용" + " " + a1 + " " + a2, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 나의 선택은 무엇인가..
    public void mychoice() {
        // 좌
        if (radioButton.isChecked()) {
            a1 = (String) radioButton.getText();
        }
        if (radioButton2.isChecked()) {
            a1 = (String) radioButton2.getText();
        }
        if (radioButton3.isChecked()) {
            a1 = (String) radioButton3.getText();
        }
        if (radioButton4.isChecked()) {
            a1 = (String) radioButton4.getText();
        }
        if (radioButton5.isChecked()) {
            a1 = (String) radioButton5.getText();
        }
        if (radioButton6.isChecked()) {
            a1 = (String) radioButton6.getText();
        }
        // 우
        if (radioButton7.isChecked()) {
            a2 = (String) radioButton7.getText();
        }
        if (radioButton8.isChecked()) {
            a2 = (String) radioButton8.getText();
        }
        if (radioButton9.isChecked()) {
            a2 = (String) radioButton9.getText();
        }
    }
}