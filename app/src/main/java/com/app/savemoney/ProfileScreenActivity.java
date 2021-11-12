package com.app.savemoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileScreenActivity extends AppCompatActivity {

    private TextView txtBackScreen, profileName, fullName, emailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();

        String email = intent.getStringExtra("userEmail");
        String name = intent.getStringExtra("userName");

        txtBackScreen = findViewById(R.id.txt_backScreen);

        profileName = findViewById(R.id.profile_mainname);

        fullName = findViewById(R.id.textFullName);

        emailView = findViewById(R.id.textFullEmail);

        profileName.setText(name);
        fullName.setText(name);
        emailView.setText(email);

        txtBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
