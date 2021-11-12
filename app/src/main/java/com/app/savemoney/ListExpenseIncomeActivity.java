package com.app.savemoney;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.savemoney.adapter.ViewPagerTabLayout;
import com.google.android.material.tabs.TabLayout;

public class ListExpenseIncomeActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout btnAddCategory;
    private ImageView btnBackHomePage;
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_setting_list);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        btnAddCategory = findViewById(R.id.btn_add_category_setting);
        btnBackHomePage = findViewById(R.id.icon_back_list_expense_income);

        String categoryName = getIntent().getStringExtra("categoryName");
        Toast.makeText(ListExpenseIncomeActivity.this, categoryName, Toast.LENGTH_SHORT).show();

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListExpenseIncomeActivity.this, AddEditCategoryActivity.class);
                startActivity(intent);
            }
        });

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
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#212121"), Color.parseColor("#FFFFFF"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        Toast.makeText(ListExpenseIncomeActivity.this, String.valueOf(0), Toast.LENGTH_SHORT).show();

                        break;
                    case 1:
                        Toast.makeText(ListExpenseIncomeActivity.this, String.valueOf(1), Toast.LENGTH_SHORT).show();

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}