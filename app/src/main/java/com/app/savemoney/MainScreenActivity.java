package com.app.savemoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainScreenActivity extends AppCompatActivity {

    private Button btnStart;
    private String userUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        SharedPreferences sp1 = this.getSharedPreferences("Login", MODE_PRIVATE);

        userUid = sp1.getString("userUid", null);

        btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userUid != null && !userUid.isEmpty()) {
                    Intent intentMain = new Intent(MainScreenActivity.this, MainActivity.class);
                    startActivity(intentMain);
                    finish();
                } else {
                    Intent intentStart = new Intent(MainScreenActivity.this, LoginScreenActivity.class);
                    startActivity(intentStart);
                    finish();
                }


            }
        });
    }
}
