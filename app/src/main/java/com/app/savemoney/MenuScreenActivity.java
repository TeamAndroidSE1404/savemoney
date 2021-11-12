package com.app.savemoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuScreenActivity extends AppCompatActivity {

    private TextView txtInfor, txtSetting, txtProfile, txtLogout, txtBackScreen, menu_txt_welcome;
    private String fullName, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        SharedPreferences sp1 = this.getSharedPreferences("Login", MODE_PRIVATE);

        fullName = sp1.getString("userName", null);
        email = sp1.getString("userEmail", null);


        txtBackScreen = findViewById(R.id.txt_backScreen);
        txtInfor = findViewById(R.id.txt_information);
        txtSetting = findViewById(R.id.txt_setting);
        txtProfile =findViewById(R.id.txt_profile);
        txtLogout= findViewById(R.id.txt_logout);
        menu_txt_welcome = findViewById(R.id.menu_txt_welcome);

        menu_txt_welcome.setText("Welcome, "+fullName);

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
                intentProfile.putExtra("userName", fullName);
                intentProfile.putExtra("userEmail", email);
                startActivity(intentProfile);
            }
        });

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor Ed=sp.edit();
                Ed.putString("userUid",null);
                Ed.putString("userEmail", null);
                Ed.putString("userName", null);
                Ed.commit();

                Intent intent = new Intent(MenuScreenActivity.this, MainScreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finishAffinity();

            }
        });


    }
}
