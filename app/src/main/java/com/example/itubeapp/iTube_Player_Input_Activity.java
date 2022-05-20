package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itubeapp.database.DatabaseOps;
import com.google.android.youtube.player.YouTubePlayer;

public class iTube_Player_Input_Activity extends AppCompatActivity {

    Button btnPlay,btnViewPlaylist,btnAddPlaylist;
    EditText txtlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itube_player_input);
        txtlink = findViewById(R.id.txtLink);
        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = txtlink.getText().toString();
                Intent intent = new Intent(getApplicationContext(),iTube_Player_Play_Activity.class);
                intent.putExtra("LINK",link);
                startActivity(intent);
            }
        });
        btnAddPlaylist = findViewById(R.id.btnAddPlaylist);
        btnViewPlaylist = findViewById(R.id.btnViewPlaylist);

        DatabaseOps databaseOps = new DatabaseOps(getApplicationContext());

        btnAddPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtlink.getText().toString() == ""){
                    Toast.makeText(getApplicationContext(),"Please enter the link",Toast.LENGTH_LONG).show();
                }
                else {
                    String temp = txtlink.getText().toString();
                    databaseOps.insertVideo(temp);
                    Toast.makeText(getApplicationContext(),"Video Added",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnViewPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),iTube_Player_View_Activity.class);
                startActivity(i);
            }
        });
    }
}