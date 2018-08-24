package com.example.administrator.app02;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.IconCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialog extends AppCompatActivity {
    // TODO 블루투스 기능 구현(메인에 한걸 일로..)

    private MyDialogListener dialogListener;
    private Context context;

    public CustomDialog(Context context) {
        this.context = context;
    }

    public void setDialogListener(MyDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    public void callFunction(final MainActivity main_label) {
        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_custom);

        dialog.show();

        final Button btnONOFF = ( Button ) dialog.findViewById(R.id.btnONOFF);
        final Button btnDiscoverable_on_off = ( Button ) dialog.findViewById(R.id.btnDiscoverable_on_off);
        final Button btnFindUnpairedDevices = ( Button ) dialog.findViewById(R.id.btnFindUnpairedDevices);

        // 블루투스를 킨다!
        btnONOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "btnONOFF", Toast.LENGTH_SHORT).show();
            }
        });
        // 블루투스 장치 검색 허용!
        btnDiscoverable_on_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "btnDiscoverable_on_off.", Toast.LENGTH_SHORT).show();
            }
        });
        // 장치 검색을 한다!
        btnFindUnpairedDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "btnFindUnpairedDevices.", Toast.LENGTH_SHORT).show();
            }
        });

        // 확인 = 종료 버튼
        final Button okButton = ( Button ) dialog.findViewById(R.id.okButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}