package com.alakey.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnLogin;
    private Button btnCreateAccount;
    private Button btnLaterLogin;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnCreateAccount = findViewById(R.id.buttonCreateAccount);
        btnLaterLogin = findViewById(R.id.buttonLaterLogin);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view ->{
            loginUser();
        });

        btnCreateAccount.setOnClickListener(view ->{
            startActivity(new Intent(Login.this, Register.class));
        });

        btnLaterLogin.setOnClickListener(view ->{
            startActivity(new Intent(Login.this, MainActivity.class));
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Логин не заполнен");
            editTextEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Пароль не заполнен");
            editTextPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Вход выполнен успешно", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                    } else {
                        Toast.makeText(Login.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}