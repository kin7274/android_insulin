package com.example.administrator.dia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button pdf;
    Button word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdf = (Button) findViewById(R.id.pdf);
//        pdf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent pdf_intent = new Intent(this, PdfActivity.class);
//                startActivity(pdf_intent);
//            }
//        });
        word = (Button) findViewById(R.id.word);
        word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pdf_intent = new Intent(v.getContext(), EduWordActivity.class);
                startActivity(pdf_intent);
            }
        });
    }
}
