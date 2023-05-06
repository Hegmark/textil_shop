package com.example.textil_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private static final int SECRET_KEY = 318;
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private FirebaseAuth mAuth;

    EditText userNameEditText;
    EditText userEmailEditText;
    EditText passwordEditText;
    EditText passwordConfirmEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameEditText = findViewById(R.id.editTextUsername);
        userEmailEditText = findViewById(R.id.editTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextPassword);
        passwordConfirmEditText = findViewById(R.id.editTextPassword2);

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);
        if(secret_key != SECRET_KEY){
            finish();
        }

        mAuth = FirebaseAuth.getInstance();
    }
    public void register(View view) {
        String userName = userNameEditText.getText().toString();
        String email = userEmailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordConfirm = passwordConfirmEditText.getText().toString();

        if (!password.equals(passwordConfirm)) {
            Log.e(LOG_TAG, "Nem egyenlő a jelszó és a megerősítése.");
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(LOG_TAG, "User created successfully");
                    startBrowsing();
                } else {
                    Log.d(LOG_TAG, "User creation failed:", task.getException());
                    Toast.makeText(RegisterActivity.this, "User creation failed:", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void startBrowsing() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
}