package com.example.administrator.app02;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialog extends Dialog {

    private MyDialogListener dialogListener;
    private Context context;
    private TextView textTitle;
    private String title;

    public CustomDialog(Context context, String title) {
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
        setContentView(R.layout.dialog_custom);

        textTitle = (TextView) findViewById(R.id.textTitle);
        textTitle.setText(title);

        // 블루투스를 킨다!
        Button btnONOFF = (Button) findViewById(R.id.btnONOFF);
        btnONOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "ONOFF", Toast.LENGTH_SHORT).show();
            }
        });

        // 블루투스 장치 검색 허용!
        Button btnDiscoverable_on_off = (Button) findViewById(R.id.btnDiscoverable_on_off);
        btnDiscoverable_on_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Discoverable_on_off", Toast.LENGTH_SHORT).show();
            }
        });

        // 장치 검색을 한다!
        Button btnFindUnpairedDevices = (Button) findViewById(R.id.btnFindUnpairedDevices);
        btnFindUnpairedDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "pairedDevices", Toast.LENGTH_SHORT).show();
            }
        });

        // 확인 = 종료 버튼
        Button okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }
}
