package com.app.savemoney;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.savemoney.callbacks.UserCallBack;
import com.app.savemoney.dao.UserDao;
import com.app.savemoney.model.User;

import java.util.Locale;

public class RegisterScreenActivity extends AppCompatActivity {

    private Button btnRegister;
    private TextView txtLogin;
    private EditText edtFullName, edtEmail, edtPassword, edtRePassword;

    private UserDao userDao;


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
        userDao = new UserDao();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullName = edtFullName.getText().toString();
                String email = edtEmail.getText().toString().toLowerCase(Locale.ROOT);
                String password = edtPassword.getText().toString();
                String rePassword = edtRePassword.getText().toString();
                if (validateRegister(fullName, email, password, rePassword)) {

                    userDao.checkExitsEmail(email, new UserCallBack() {
                        @Override
                        public void onCheckExits(boolean check) {

                            if(!check){
                                Log.d("111", email);

                                userDao.addUser(new User(email, password, fullName));
                                Intent intentBtnRegister = new Intent(RegisterScreenActivity.this, LoginScreenActivity.class);
                                startActivity(intentBtnRegister);
                                finish();
                            }else{
                                Log.d("111", email);
                                edtEmail.requestFocus();
                                edtEmail.setError("Email Exits");
                            }

                        }

                        @Override
                        public void onCheckLogin(User user) {

                        }
                    });


                }
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTxtLogin = new Intent(RegisterScreenActivity.this, LoginScreenActivity.class);
                startActivity(intentTxtLogin);
                finish();
            }
        });


    }

    private Boolean validateRegister(String fullName, String email, String password, String rePassword) {
        if (fullName.length() == 0) {
            edtFullName.requestFocus();
            edtFullName.setError("Fullname can't empty");
            return false;
        } else if (!fullName.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
            edtFullName.requestFocus();
            edtFullName.setError("Fullname must be alphabet characters");
            return false;
        } else if (email.length() == 0) {
            edtEmail.requestFocus();
            edtEmail.setError("Email can't empty");
            return false;
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            edtEmail.requestFocus();
            edtEmail.setError("Please input valid email");
            return false;
        } else if (password.length() == 0) {
            edtPassword.requestFocus();
            edtPassword.setError("Password can't empty");
            return false;
        } else if (password.length() <= 5) {
            edtPassword.requestFocus();
            edtPassword.setError("Password at least 6 characters");
            return false;
        } else if (rePassword.length() == 0) {
            edtRePassword.requestFocus();
            edtRePassword.setError("Re-Password can't empty");
            return false;
        } else if (rePassword.length() <= 5) {
            edtRePassword.requestFocus();
            edtRePassword.setError("Re-Password at least 6 characters");
            return false;
        } else if (password.length() != rePassword.length()) {
            edtRePassword.requestFocus();
            edtRePassword.setError("Re-Password not matches password");
            return false;
        } else if (!rePassword.equals(password)) {
            edtRePassword.requestFocus();
            edtRePassword.setError("Re-Password not matches password");
            return false;
        } else {
            return true;
        }
    }


}
