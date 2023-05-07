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

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private static final int SECRET_KEY = 318;
    private static final String LOG_TAG = "hihi";
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
        userEmailEditText = findViewById(R.id.editTextEmailAddressRegist);
        passwordEditText = findViewById(R.id.editTextPasswordRegist);
        passwordConfirmEditText = findViewById(R.id.editTextPassword2Regist);

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);
        if(secret_key != SECRET_KEY){
            finish();
        }


    }
    public void register(View view) {
        String userName = userNameEditText.getText().toString();
        String email = userEmailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordConfirm = passwordConfirmEditText.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        if (!password.equals(passwordConfirm)) {
            Log.e(LOG_TAG, "Nem egyenlő a jelszó és a megerősítése.");
            return;
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i(LOG_TAG, "User created successfully");
                    startBrowsing();
                }else{
                    //startBrowsing();
                    Log.i(LOG_TAG, "User creation failed:" + Objects.requireNonNull(task.getException()).getMessage(), task.getException());
                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
                }
            });
        }
    }

    private void startBrowsing() {
        Intent intent = new Intent(this, BrowsingActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
}