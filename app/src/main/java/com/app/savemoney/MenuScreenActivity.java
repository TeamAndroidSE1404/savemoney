package com.app.savemoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuScreenActivity extends AppCompatActivity {

    private TextView txtInfor, txtSetting, txtProfile, txtLogout, txtBackScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        txtBackScreen = findViewById(R.id.txt_backScreen);
        txtInfor = findViewById(R.id.txt_information);
        txtSetting = findViewById(R.id.txt_setting);
        txtProfile =findViewById(R.id.txt_profile);
        txtLogout= findViewById(R.id.txt_logout);

        txtBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuScreenActivity.this,"This function will be coming soon!!", Toast.LENGTH_LONG).show();
            }
        });

        txtSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSetting = new Intent(MenuScreenActivity.this, SettingScreenActivity.class);
                startActivity(intentSetting);
            }
        });

        txtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProfile = new Intent(MenuScreenActivity.this, ProfileScreenActivity.class);
                startActivity(intentProfile);
            }
        });


    }
}
