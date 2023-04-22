package com.example.textil_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private static final int SECRET_KEY = 318;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);
        if(secret_key != SECRET_KEY){
            finish();
        }
    }


    public void login(View view){
        EditText usernameEt = findViewById(R.id.editTextUsername);
        EditText passwordEt = findViewById(R.id.editTextPassword);

        String username = usernameEt.getText().toString();

    }

    public void back(View view) {
        finish();
    }
}