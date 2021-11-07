package com.app.savemoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailExpenseIncomeActivity extends AppCompatActivity {

    private ImageView btnBackHomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_expense);
        btnBackHomePage = findViewById(R.id.icon_back_detail);
        btnBackHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailExpenseIncomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
