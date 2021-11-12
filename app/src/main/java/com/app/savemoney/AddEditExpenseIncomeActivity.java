package com.app.savemoney;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.app.savemoney.adapter.ViewPagerTabLayoutInAddEditScreen;
import com.app.savemoney.common.OnSwipeTouchListener;
import com.app.savemoney.fragment.ExpenseFragment;
import com.app.savemoney.model.Category;
import com.google.android.material.tabs.TabLayout;

import android.content.Intent;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddEditExpenseIncomeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Category> categoryList;
    private AddEditExpenseIncomeAdapter addEditExpenseIncomeAdapter;
    private RecyclerView recyclerView;
    private LinearLayout layoutPopup, layoutOverlap;
    private TextView txtDate, txtTime;
    private ImageView imgBack;
    private EditText txtMoney;
    private Button btnAddExpense;
    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;
    private String current = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        tabLayout = findViewById(R.id.tab_layout_in_add_edit_screen);
        viewPager = findViewById(R.id.view_pager_in_add_edit_screen);
        txtDate = findViewById(R.id.txt_date);
        txtTime = findViewById(R.id.txt_time);
        layoutPopup = findViewById(R.id.layout_popup_category_list);
        imgBack = findViewById(R.id.icon_back_add_expense);
        layoutOverlap = findViewById(R.id.background_overlay);
        btnAddExpense = findViewById(R.id.btn_add_expense);
        txtMoney = findViewById(R.id.txt_money);

        setDateTimeInit();

        txtMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    if (txtMoney.getText().toString().equals("0")) {
                        txtMoney.setText("");
                    }
                } else {
                    if (txtMoney.getText().toString().equals("")) {
                        txtMoney.setText("0");
                    }
                }
            }
        });
        txtMoney.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtMoney.getText().toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        txtMoney.removeTextChangedListener(this);

                        String cleanString = s.toString().replaceAll("[$,.]", "");

                        double parsed = Double.parseDouble(cleanString);
                        NumberFormat formatter = new DecimalFormat("##,###,###,###");
                        String formatted = formatter.format(parsed);

                        current = formatted;
                        txtMoney.setText(formatted);
                        txtMoney.setSelection(formatted.length());

                        txtMoney.addTextChangedListener(this);
                    }
                } else {
                    txtMoney.setText("0");
                }

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        layoutOverlap.setOnTouchListener(new OnSwipeTouchListener(AddEditExpenseIncomeActivity.this) {
            @Override
            public void onSwipeDown() {
                super.onSwipeDown();
                Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                layoutPopup.startAnimation(animSlideDown);
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
        ViewPagerTabLayoutInAddEditScreen viewPagerTabLayoutInAddEditScreen = new ViewPagerTabLayoutInAddEditScreen(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerTabLayoutInAddEditScreen);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#2497F3"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#404040"), Color.parseColor("#2497F3"));
        try {
//            recyclerView = findViewById(R.id.rv_categories);

//            categoryList = new ArrayList<>();
//            for (int i = 1; i <= 20; i++) {
//                categoryList.add(new Category("Thue nha"));
//            }
//            addEditExpenseIncomeAdapter = new AddEditExpenseIncomeAdapter(categoryList, this);

//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

//            recyclerView.setAdapter(addEditExpenseIncomeAdapter);
//            recyclerView.setLayoutManager(linearLayoutManager);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("##,###,###,###");
        return formatter.format(Double.parseDouble(amount));
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

                txtTime.setText(hourOfDay + ":" + String.format("%02d", minute) + " " + AM_PM);
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

    private void setDateTimeInit() {
        final Calendar c = Calendar.getInstance();
        this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
        this.lastSelectedMinute = c.get(Calendar.MINUTE);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String date = year + "-" + month + "-" + day;
        String AM_PM;
        if (this.lastSelectedHour < 12) {
            AM_PM = "AM";
        } else {
            AM_PM = "PM";
        }


        this.txtDate.setText(date);
        txtTime.setText(this.lastSelectedHour + ":" + String.format("%02d", this.lastSelectedMinute) + " " + AM_PM);


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = year + "-" + month + "-" + dayOfMonth;
        txtDate.setText(date);
    }
}