package com.app.savemoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterScreenActivity extends AppCompatActivity {

    private Button btnRegister;
    private TextView txtLogin;
    EditText edtFullName, edtEmail, edtPassword, edtRePassword;

    private Boolean validateRegister(String fullName, String email, String password, String rePassword) {
        if(fullName.length() ==0){
            edtFullName.requestFocus();
            edtFullName.setError("Fullname can't empty");
            return false;
        }
        else if(!fullName.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")){
            edtFullName.requestFocus();
            edtFullName.setError("Fullname must be alphabet characters");
            return false;
        }

        else if(email.length() ==0){
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
        else if(rePassword.length() == 0){
            edtRePassword.requestFocus();
            edtRePassword.setError("Re-Password can't empty");
            return false;
        }
        else if(rePassword.length() <= 5){
            edtRePassword.requestFocus();
            edtRePassword.setError("Re-Password at least 6 characters");
            return false;
        }
        else if(password.length() != rePassword.length()){
            edtRePassword.requestFocus();
            edtRePassword.setError("Re-Password not matches password");
            return false;
        }
        else if(!rePassword.equals(password)){
            edtRePassword.requestFocus();
            edtRePassword.setError("Re-Password not matches password");
            return false;
        }
        else {
            return true;
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btn_register);
        txtLogin = findViewById(R.id.txt_login);

        edtFullName = findViewById(R.id.edit_fullname);
        edtEmail = findViewById(R.id.edit_email);
        edtPassword = findViewById(R.id.edit_password);
        edtRePassword = findViewById(R.id.edit_rePassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBtnRegister = new Intent(RegisterScreenActivity.this, LoginScreenActivity.class);
                startActivity(intentBtnRegister);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTxtLogin = new Intent(RegisterScreenActivity.this, LoginScreenActivity.class);
                startActivity(intentTxtLogin);
            }
        });
                String fullName = edtFullName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String rePassword = edtRePassword.getText().toString();
                validateRegister(fullName, email, password, rePassword);
            }
        });

    }
}
