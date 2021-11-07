package com.app.savemoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.savemoney.adapter.ViewPagerTabLayout;
import com.google.android.material.tabs.TabLayout;

public class ListExpenseIncomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout btnAddCategory;
    private ImageView btnEditCategory, btnBackHomePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_setting_list);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        btnAddCategory = findViewById(R.id.btn_add_category_setting);
        btnEditCategory = findViewById(R.id.btn_edit_show_screen_category);
        btnBackHomePage = findViewById(R.id.icon_back_list_expense_income);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListExpenseIncomeActivity.this, AddEditCategoryActivity.class);
                startActivity(intent);
            }
        });
//        btnEditCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Create new fragment and transaction
//                ReplaceFragment replaceFragment = new ReplaceFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.view_pager, replaceFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
        btnBackHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListExpenseIncomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //View page tab layout
        ViewPagerTabLayout demoFragment = new ViewPagerTabLayout(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(demoFragment);
        tabLayout.setupWithViewPager(viewPager);

    }

}