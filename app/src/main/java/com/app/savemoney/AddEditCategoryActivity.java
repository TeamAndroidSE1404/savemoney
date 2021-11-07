package com.app.savemoney;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.adapter.AddEditCategoryAdapter;
import com.app.savemoney.adapter.AddEditExpenseIncomeAdapter;
import com.app.savemoney.model.Category;

import java.util.ArrayList;
import java.util.List;

public class AddEditCategoryActivity extends AppCompatActivity {

    private AddEditCategoryAdapter addEditCategoryAdapter;
    private RecyclerView recyclerView;
    private List<Category> categoryList;
    private ImageView btnCheckMark, btnBackHomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        btnCheckMark = findViewById(R.id.ic_check_mark);
        btnBackHomePage = findViewById(R.id.icon_back_category_setting);

        btnCheckMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddEditCategoryActivity.this, ListExpenseIncomeActivity.class);
                startActivity(intent);
            }
        });

        btnBackHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddEditCategoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        try {
            recyclerView = findViewById(R.id.rv_categories);

            categoryList = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {
                categoryList.add(new Category(""));
            }
            addEditCategoryAdapter = new AddEditCategoryAdapter(categoryList, this);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

            recyclerView.setAdapter(addEditCategoryAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
