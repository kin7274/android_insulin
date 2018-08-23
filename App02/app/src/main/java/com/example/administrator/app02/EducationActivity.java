package com.example.administrator.app02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class EducationActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubeView;
    Button button;
    YouTubePlayer.OnInitializedListener listener;

    // 내 api 키 : AIzaSyC12r-vWRn_wZHF9EAtUVP_93BioYKRYSM
    // 재생 리스트 1번 : 일단 유튭공부영상으로.. : vewH-f3fAes
    // TODO 유튜브 목록을 찾아보자, 저작권 조심!

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        button = (Button) findViewById(R.id.youtubeButton);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("vewH-f3fAes");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubeView.initialize("AIzaSyC12r-vWRn_wZHF9EAtUVP_93BioYKRYSM", listener);
            }
        });
    }
}
