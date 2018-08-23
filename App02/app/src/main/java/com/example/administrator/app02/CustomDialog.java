package com.example.administrator.app02;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog extends Dialog implements View.OnClickListener{

    private MyDialogListener dialogListener;
    private Context context;
    private TextView textTitle;
    private TextView textContent;
    private String title;

    public CustomDialog(Context context, String title){
        super(context);
        this.context = context;
        this.title = title;
    }

    public void setDialogListener(MyDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);
        textTitle.setText(title);

//        Button okButton = (Button) findViewById(R.id.okButton);
//        Button okButton = (Button) findViewById(R.id.okButton);
//        Button okButton = (Button) findViewById(R.id.okButton);

        Button okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.okButton:
                cancel();
                break;
        }
    }

}
