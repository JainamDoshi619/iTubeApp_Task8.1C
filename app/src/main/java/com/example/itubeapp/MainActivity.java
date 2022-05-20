package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itubeapp.database.DatabaseOps;

public class MainActivity extends AppCompatActivity {
    EditText txtUsername,txtPassword;
    Button btnLogIn,btnSignUp;
    DatabaseOps databaseOps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsername = findViewById(R.id.editTextTextUsername);
        txtPassword = findViewById(R.id.editTextTextPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter All Fields",Toast.LENGTH_LONG).show();
                }
                else {
                    databaseOps = new DatabaseOps(getApplicationContext());
                    Boolean check = databaseOps.checkLogIn(username,password);
                    if(check){
                        Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(),iTube_Player_Input_Activity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Login unsuccessful",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}