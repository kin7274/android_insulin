package com.example.administrator.app02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;

// 인트로 : 2초 대기화면
public class Intro extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            // 2초 뒤에 다음화면(MainActivity)으로 넘어간다
            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
            String exist_setting = pref.getString("PREF_STRNAME", "");
            String[] str = exist_setting.split("&");
            if (str[0].isEmpty()) {
                // 설정값이 없으면 설정부터
                Intent intent = new Intent(Intro.this, I_want_to_know_you.class);
                startActivity(intent);
            } else {
                // 설정값이 존재한다면 메인으로 바로
                Intent intent2 = new Intent(Intro.this, MainActivity.class);
                startActivity(intent2);
            }
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.loop(true);
        animationView.playAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 2초 뒤에 Runnable 객체 수행
        handler.postDelayed(r, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 예약 취소
        handler.removeCallbacks(r);
    }
}