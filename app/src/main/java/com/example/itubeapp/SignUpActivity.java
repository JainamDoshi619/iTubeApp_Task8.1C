package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itubeapp.Util.LogInInfoStore;
import com.example.itubeapp.database.DatabaseOps;

public class SignUpActivity extends AppCompatActivity {
    EditText txtFullName,txtUsername,txtPassword,txtConfirmPassword;
    Button btnCreateNewAccount;
    LogInInfoStore logInInfoStore;
    DatabaseOps databaseOps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtFullName = findViewById(R.id.txtFName);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        btnCreateNewAccount = findViewById(R.id.btnCreateNewAccount);
        btnCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(txtFullName.getText().toString()) ||
                        TextUtils.isEmpty(txtPassword.getText().toString()) ||
                        TextUtils.isEmpty(txtConfirmPassword.getText().toString()) ||
                        TextUtils.isEmpty(txtUsername.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter all Fields",Toast.LENGTH_LONG).show();
                }
                else {
                    if(!txtPassword.getText().toString().equals(txtConfirmPassword.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_LONG).show();
                    }
                    else {

                        logInInfoStore = new LogInInfoStore(txtUsername.getText().toString(),txtFullName.getText().toString(), txtPassword.getText().toString());
                        databaseOps = new DatabaseOps(getApplicationContext());
                        databaseOps.insertUserInfo(logInInfoStore);
                        Toast.makeText(getApplicationContext(),"Account Created",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}