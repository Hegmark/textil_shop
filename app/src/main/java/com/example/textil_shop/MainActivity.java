package com.example.textil_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int SECRET_KEY = 318;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("SECRET_KEY", 318);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        intent.putExtra("SECRET_KEY", 318);
        startActivity(intent);
    }
}