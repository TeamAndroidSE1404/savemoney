package com.app.savemoney;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.app.savemoney.adapter.AddEditCategoryAdapter;
import com.app.savemoney.adapter.AddEditExpenseIncomeAdapter;
import com.app.savemoney.adapter.ViewPagerTabLayout;
import com.app.savemoney.common.OnSwipeTouchListener;
import com.app.savemoney.model.Category;
import com.google.android.material.tabs.TabLayout;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddEditExpenseIncomeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private List<Category> categoryList;
    private AddEditExpenseIncomeAdapter addEditExpenseIncomeAdapter;
    private RecyclerView recyclerView;
    private LinearLayout layoutPopup, layoutOverlap;
    private TextView txtDate, txtTime;
    private ImageView imgBack, imgSetting;

    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        txtDate = findViewById(R.id.txt_date);
        txtTime = findViewById(R.id.txt_time);
        layoutPopup = findViewById(R.id.layout_popup_category_list);
        imgBack = findViewById(R.id.icon_back_add_expense);
        imgSetting = findViewById(R.id.iv_setting);
        layoutOverlap = findViewById(R.id.background_overlay);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  imgBackIntent = new Intent(AddEditExpenseIncomeActivity.this, MainActivity.class );
                startActivity(imgBackIntent);
            }
        });
        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  imgSettingIntent = new Intent(AddEditExpenseIncomeActivity.this, ListExpenseIncomeActivity.class );
                startActivity(imgSettingIntent);
            }
        });
        layoutPopup.setOnTouchListener(new OnSwipeTouchListener(AddEditExpenseIncomeActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(AddEditExpenseIncomeActivity.this, "Swipe Left gesture detected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(AddEditExpenseIncomeActivity.this, "Swipe Right gesture detected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
                Toast.makeText(AddEditExpenseIncomeActivity.this, "Swipe Up gesture detected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSwipeDown() {
                super.onSwipeDown();
                Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
                layoutPopup.startAnimation(animSlideDown);
                layoutPopup.setVisibility(View.INVISIBLE);
                layoutOverlap.setVisibility(View.INVISIBLE);
            }

        });
        layoutOverlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutPopup.setVisibility(View.INVISIBLE);
                layoutOverlap.setVisibility(View.INVISIBLE);
            }
        });
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        try {
            recyclerView = findViewById(R.id.rv_categories);

            categoryList = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {
                categoryList.add(new Category("Thue nha"));
            }
            addEditExpenseIncomeAdapter = new AddEditExpenseIncomeAdapter(categoryList, this);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

            recyclerView.setAdapter(addEditExpenseIncomeAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void handleShowPopupListCategory(View view) {
        layoutPopup = (LinearLayout) findViewById(R.id.layout_popup_category_list);
        layoutOverlap = (LinearLayout) findViewById(R.id.background_overlay);
        layoutPopup.setVisibility(View.VISIBLE);
        layoutOverlap.setVisibility(View.VISIBLE);
    }

    public void showTimePickerDialog() {
        if (this.lastSelectedHour == -1) {
            final Calendar c = Calendar.getInstance();
            this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
            this.lastSelectedMinute = c.get(Calendar.MINUTE);
        }

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String AM_PM;
                if (hourOfDay < 12) {
                    AM_PM = "AM";
                } else {
                    AM_PM = "PM";
                }

                txtTime.setText(hourOfDay + ":" + minute + " " + AM_PM);
                lastSelectedHour = hourOfDay;
                lastSelectedMinute = minute;
            }
        };

        TimePickerDialog timePickerDialog = null;
        try {
            timePickerDialog = new TimePickerDialog(this,
                    timeSetListener, lastSelectedHour, lastSelectedMinute, true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        timePickerDialog.show();
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = year + "-" + month + "-" + dayOfMonth;
        txtDate.setText(date);
    }
}