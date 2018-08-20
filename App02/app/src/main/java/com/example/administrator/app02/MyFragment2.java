package com.example.administrator.app02;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by myeongsic on 2017. 4. 10..
 */



//    private static final String ARG_YOUTUBE_PLAYLIST_ID = "AIzaSyBE12xBlnHBOUCLQssNuBhZ37s1qd3Bbic";
//    private static final String KEY_SAVED_INSTANCE_PLAYLIST = "txifUq2nBHo";
//    View v = inflater.inflate(R.layout.fragment2, container, false);

public class MyFragment2 extends Fragment {

     @Nullable
     @Override
     public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
          return inflater.inflate(R.layout.fragment2,container,false);
     }
}