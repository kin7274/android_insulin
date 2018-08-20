package com.example.administrator.app02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

// 유튜브 프로젝트 다시 만들자.. 프로젝트명이 틀리구나..

// 영상페이지
//    private static final String ARG_YOUTUBE_PLAYLIST_ID = "AIzaSyBE12xBlnHBOUCLQssNuBhZ37s1qd3Bbic";
//    private static final String KEY_SAVED_INSTANCE_PLAYLIST = "txifUq2nBHo";

public class EducationActivity extends YouTubeBaseActivity {

    YouTubePlayerView youtube_view;
    Button youtubebutton;
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        youtubebutton = (Button) findViewById(R.id.youtubebutton);
        youtube_view = (YouTubePlayerView) findViewById(R.id.youtube_view);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("AIzaSyBE12xBlnHBOUCLQssNuBhZ37s1qd3Bbic");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        };

        youtubebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youtube_view.initialize("vewH-f3fAes", listener);
            }
        });
    }
}
