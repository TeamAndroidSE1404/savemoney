package com.app.savemoney;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginScreenActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView txtRegister;
    EditText edtEmail, edtPassword;
    private UserDao userDao;

    private Boolean validateLogin(String email, String password) {
        if (email.length() == 0) {
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
        } else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        txtRegister = findViewById(R.id.txt_register);

        edtEmail = findViewById(R.id.edit_email);
        edtPassword = findViewById(R.id.edit_password);

        userDao = new UserDao();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if (validateLogin(email, password)) {

                    userDao.checkLogin(email, password, new UserCallBack() {
                        @Override
                        public void onCheckExits(boolean check) {

                        }

                        @Override
                        public void onCheckLogin(User user) {

                            if(user!=null){

                                Log.d("112", user.getEmail());

                                SharedPreferences sp=getSharedPreferences("Login", MODE_PRIVATE);
                                SharedPreferences.Editor Ed=sp.edit();
                                Ed.putString("userUid", user.getUid());
                                Ed.putString("userEmail", user.getEmail());
                                Ed.putString("userName", user.getFullName());

                                Ed.commit();


                                Intent intentBtnLogin = new Intent(LoginScreenActivity.this, MainActivity.class);
                                startActivity(intentBtnLogin);
                                finish();
                            }else{
                                edtEmail.requestFocus();
                                edtEmail.setError("Email or Password was wrong!");
                            }

                        }
                    });


                }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTxtRegister = new Intent(LoginScreenActivity.this, RegisterScreenActivity.class);
                startActivity(intentTxtRegister);
                finish();
            }


        });
    }
}
