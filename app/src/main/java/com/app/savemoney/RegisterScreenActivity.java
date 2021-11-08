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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btn_register);
        txtLogin = findViewById(R.id.txt_login);

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
    }
}
