package com.example.administrator.app02.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.app02.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class EducationActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtLink1_1,
            txtLink2_1,
            txtLink3_1, txtLink3_2, txtLink3_3, txtLink3_4, txtLink3_5, txtLink3_6, txtLink3_7, txtLink3_8, txtLink3_9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        txtLink1_1 = (TextView) this.findViewById(R.id.txtLink1_1);
        txtLink2_1 = (TextView) this.findViewById(R.id.txtLink2_1);
        txtLink3_1 = (TextView) this.findViewById(R.id.txtLink3_1);
        txtLink3_2 = (TextView) this.findViewById(R.id.txtLink3_2);
        txtLink3_3 = (TextView) this.findViewById(R.id.txtLink3_3);
        txtLink3_4 = (TextView) this.findViewById(R.id.txtLink3_4);
        txtLink3_5 = (TextView) this.findViewById(R.id.txtLink3_5);
        txtLink3_6 = (TextView) this.findViewById(R.id.txtLink3_6);
        txtLink3_7 = (TextView) this.findViewById(R.id.txtLink3_7);
        txtLink3_8 = (TextView) this.findViewById(R.id.txtLink3_8);
        txtLink3_9 = (TextView) this.findViewById(R.id.txtLink3_9);

        txtLink1_1.setOnClickListener((View.OnClickListener) this);
        txtLink2_1.setOnClickListener((View.OnClickListener) this);
        txtLink3_1.setOnClickListener((View.OnClickListener) this);
        txtLink3_2.setOnClickListener((View.OnClickListener) this);
        txtLink3_3.setOnClickListener((View.OnClickListener) this);
        txtLink3_4.setOnClickListener((View.OnClickListener) this);
        txtLink3_5.setOnClickListener((View.OnClickListener) this);
        txtLink3_6.setOnClickListener((View.OnClickListener) this);
        txtLink3_7.setOnClickListener((View.OnClickListener) this);
        txtLink3_8.setOnClickListener((View.OnClickListener) this);
        txtLink3_9.setOnClickListener((View.OnClickListener) this);
    }

    //                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://youtu.be/_zqts6yXTaI"));
//                startActivity(intent);

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtLink1_1:
//                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://youtu.be/_zqts6yXTaI"));
                startActivity(intent);
                break;
            case R.id.txtLink2_1:
//                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("https://www.youtube.com/watch?time_continue=25&v=WUrLzPidsks"));
                startActivity(intent1);
                break;
            case R.id.txtLink3_1:
//                Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse("https://www.youtube.com/watch?v=raciLwP--L0"));
                startActivity(intent2);
                break;
            case R.id.txtLink3_2:
//                Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(Intent.ACTION_VIEW);
                intent3.setData(Uri.parse("https://www.youtube.com/watch?v=s5tbhH8bwEI"));
                startActivity(intent3);
                break;
            case R.id.txtLink3_3:
//                Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_LONG).show();
                Intent intent4 = new Intent(Intent.ACTION_VIEW);
                intent4.setData(Uri.parse("https://www.youtube.com/watch?v=7NDAbacKVqQ"));
                startActivity(intent4);
                break;
            case R.id.txtLink3_4:
//                Toast.makeText(getApplicationContext(), "6", Toast.LENGTH_LONG).show();
                Intent intent5 = new Intent(Intent.ACTION_VIEW);
                intent5.setData(Uri.parse("https://www.youtube.com/watch?v=xIY46uFujZQ"));
                startActivity(intent5);
                break;
            case R.id.txtLink3_5:
//                Toast.makeText(getApplicationContext(), "7", Toast.LENGTH_LONG).show();
                Intent intent6 = new Intent(Intent.ACTION_VIEW);
                intent6.setData(Uri.parse("https://www.youtube.com/watch?v=cnmMqfAliGU"));
                startActivity(intent6);
                break;
            case R.id.txtLink3_6:
//                Toast.makeText(getApplicationContext(), "8", Toast.LENGTH_LONG).show();
                Intent intent7 = new Intent(Intent.ACTION_VIEW);
                intent7.setData(Uri.parse("https://www.youtube.com/watch?v=glte-8TI4L4"));
                startActivity(intent7);
                break;
            case R.id.txtLink3_7:
//                Toast.makeText(getApplicationContext(), "9", Toast.LENGTH_LONG).show();
                Intent intent8 = new Intent(Intent.ACTION_VIEW);
                intent8.setData(Uri.parse("https://www.youtube.com/watch?v=2fKh70kh9tM&t=11s"));
                startActivity(intent8);
                break;
            case R.id.txtLink3_8:
//                Toast.makeText(getApplicationContext(), "10", Toast.LENGTH_LONG).show();
                Intent intent9 = new Intent(Intent.ACTION_VIEW);
                intent9.setData(Uri.parse("https://www.youtube.com/watch?v=19HG-_vNEAU&t=4s"));
                startActivity(intent9);
                break;
            case R.id.txtLink3_9:
//                Toast.makeText(getApplicationContext(), "11", Toast.LENGTH_LONG).show();
                Intent intent10 = new Intent(Intent.ACTION_VIEW);
                intent10.setData(Uri.parse("https://www.youtube.com/watch?v=2A1qBDpbQEs"));
                startActivity(intent10);
                break;
        }
    }
}