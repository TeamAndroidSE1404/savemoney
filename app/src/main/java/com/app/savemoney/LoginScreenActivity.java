package com.app.savemoney;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginScreenActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin;

    private Boolean validateLogin(String email, String password) {
        if(email.length() ==0){
            edtEmail.requestFocus();
            edtEmail.setError("Email can't empty");
            return false;
        }
        else if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            edtEmail.requestFocus();
            edtEmail.setError("Please input valid email");
            return false;
        }
        else if(password.length() == 0){
            edtPassword.requestFocus();
            edtPassword.setError("Password can't empty");
            return false;
        }
        else if (password.length() <= 5){
            edtPassword.requestFocus();
            edtPassword.setError("Password at least 6 characters");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edit_email);
        edtPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                validateLogin(email, password);
            }

        });
    }
}
