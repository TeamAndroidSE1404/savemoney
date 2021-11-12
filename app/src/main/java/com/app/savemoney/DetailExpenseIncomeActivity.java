package com.app.savemoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailExpenseIncomeActivity extends AppCompatActivity {

    private ImageView btnBackHomePage;
    private Button btnEditExpense;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_expense);
        btnBackHomePage = findViewById(R.id.icon_back_detail);
        btnEditExpense = findViewById(R.id.btn_edit_expense);
        btnBackHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailExpenseIncomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnEditExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailExpenseIncomeActivity.this, AddEditExpenseIncomeActivity.class);
                startActivity(intent);
            }
        });
    }

}
