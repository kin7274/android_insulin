package com.example.administrator.app02;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialog extends Dialog implements View.OnClickListener {
    private MyDialogListener dialogListener;

    private static final int LAYOUT = R.layout.dialog_custom;

    private Context context;

    Button btn1;
RadioGroup radioGroup;
RadioButton radio1, radio2, radio3, radio4;

    private String name;

    public CustomDialog(Context context){
        super(context);
        this.context = context;
    }

    public CustomDialog(Context context,String name){
        super(context);
        this.context = context;
        this.name = name;
    }

    public void setDialogListener(MyDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        btn1 = (Button) findViewById(R.id.btn1);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
//        radio1 = (RadioButton) findViewById(R.id.radio1);
//        radio2 = (RadioButton) findViewById(R.id.radio2);
//        radio3 = (RadioButton) findViewById(R.id.radio3);
//        radio4 = (RadioButton) findViewById(R.id.radio4);

        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn1:
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton radio = (RadioButton) findViewById(id);
                String data22 = radio.getText().toString();
//                Toast.makeText(getContext(), "결과는 " + data22, Toast.LENGTH_LONG).show();
                dialogListener.onPositiveClicked(data22);
                cancel();
                break;
        }
    }
}
