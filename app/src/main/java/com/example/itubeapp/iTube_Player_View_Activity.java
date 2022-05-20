package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.itubeapp.database.DatabaseOps;

public class iTube_Player_View_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itube_player_view);
        ListView listViewPlaylist = findViewById(R.id.listViewPlaylist);
        DatabaseOps databaseOps = new DatabaseOps(getApplicationContext());
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, databaseOps.fetchAll());
        listViewPlaylist.setAdapter(adapter);
    }
}