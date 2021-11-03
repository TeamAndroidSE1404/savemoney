package com.app.savemoney;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterScreenActivity extends AppCompatActivity {

    EditText edtFullName, edtEmail, edtPassword, edtRePassword;
    Button btnRegister;

    private Boolean validateRegister(String fullName, String email, String password, String rePassword) {
        if(fullName.length() ==0){
            edtFullName.requestFocus();
            edtFullName.setError("Fullname can't empty");
            return false;
        }
        else if(!fullName.matches("[a-zA-Z]+")){
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
            edtPassword.setError("Email can't empty");
            return false;
        }
        else if (password.length() <= 5){
            edtPassword.requestFocus();
            edtPassword.setError("Password at least 6 characters");
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
        else {
            return true;
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtFullName = findViewById(R.id.edit_fullname);
        edtEmail = findViewById(R.id.edit_email);
        edtPassword = findViewById(R.id.edit_password);
        edtRePassword = findViewById(R.id.edit_rePassword);
        btnRegister = findViewById(R.id.btn_regis);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = edtFullName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String rePassword = edtRePassword.getText().toString();
                validateRegister(fullName, email, password, rePassword);
            }
        });

    }
}
