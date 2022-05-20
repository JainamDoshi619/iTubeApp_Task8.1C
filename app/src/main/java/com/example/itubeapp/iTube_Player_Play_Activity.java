package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.itubeapp.database.DatabaseOps;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class iTube_Player_Play_Activity extends AppCompatActivity {

    YouTubePlayer.OnInitializedListener monInitializedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itube_player_play);
        Button YouTubePlayButton = findViewById(R.id.youtubePlayButton);
        YouTubePlayerView youtubePlayerView = findViewById(R.id.YoutubeView);
        Intent intent = getIntent();
        String link = intent.getStringExtra("LINK");
        String[] seperatedLink = link.split("=");

        monInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(seperatedLink[1]);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        YouTubePlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youtubePlayerView.initialize("API KEY", monInitializedListener);
            }
        });


    }
}