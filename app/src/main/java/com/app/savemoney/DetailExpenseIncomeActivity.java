package com.app.savemoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.savemoney.callbacks.CategoryCallBack;
import com.app.savemoney.callbacks.ExpenseCallBack;
import com.app.savemoney.common.CommonIcon;
import com.app.savemoney.dao.CategoryDao;
import com.app.savemoney.dao.ExpenseDao;
import com.app.savemoney.model.Category;
import com.app.savemoney.model.Expense;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DetailExpenseIncomeActivity extends AppCompatActivity {

    private ImageView btnBackHomePage, iconCategory;
    private Button btnEditExpense;
    private TextView txtDate, txtTime, txtNote, txtPrice, txtCategory;
    private CategoryDao categoryDao;
    private ExpenseDao expenseDao;
    private String userUid;
    private Category category;
    private Expense expense;
    private String cateId, expenseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_expense);

        SharedPreferences sp1 = this.getSharedPreferences("Login", MODE_PRIVATE);
        userUid = sp1.getString("userUid", null);

        Intent intent = getIntent();

        if(intent!=null){
            cateId = intent.getStringExtra("CATEGORY");
            expenseId = intent.getStringExtra("EXPENSE");
        }

        setInit();

        setListenerInit();
        setData();
        getDataFromDb();


    }

    private void setInit() {
        btnBackHomePage = findViewById(R.id.icon_back_detail);
        btnEditExpense = findViewById(R.id.btn_edit_expense);
        iconCategory = findViewById(R.id.img_detail_category_icon);
        txtDate = findViewById(R.id.txt_detail_date);

        txtTime = findViewById(R.id.txt_detail_time);
        txtNote = findViewById(R.id.txt_detail_note);
        txtPrice = findViewById(R.id.txt_detail_price);
        txtCategory = findViewById(R.id.txt_detail_category_name);

        categoryDao = new CategoryDao(userUid);

        expenseDao = new ExpenseDao(userUid);

    }


    private void setListenerInit(){
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
                DateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat formatterTime = new SimpleDateFormat("HH:mm");
                String dateStr = formatterDate.format(expense.getDate());
                String timeStr = formatterTime.format(expense.getDate());

//                iconCategory.setImageDrawable(CommonIcon.getIcon(, category.getIcon()));

                Intent intent = new Intent(DetailExpenseIncomeActivity.this, AddEditExpenseIncomeActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("UPDATE", "1");
                mBundle.putString("dateUpdate", dateStr);
                mBundle.putString("timeUpdate", timeStr);
                mBundle.putString("noteUpdate", expense.getDescription());
                mBundle.putString("priceUpdate", String.valueOf(expense.getPrice()));
                mBundle.putString("iconUpdate", category.getIcon());
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }

    private void getDataFromDb(){
        categoryDao.getCateByKey(cateId, new CategoryCallBack() {
            @Override
            public void onCallbackCategory(Map<String, Category> value) {

            }

            @Override
            public void onCallbackGetOneCategory(Category value) {
                category = value;
            }
        });

        expenseDao.getExpenseByKey(expenseId, new ExpenseCallBack() {
            @Override
            public void onCallbackGetOneExpense(Expense value) {
                expense = value;
            }
        });
    }

    private void setData(){
//        String dateStr = txtDate.getText().toString() + " " + txtTime.getText().toString();

        DateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatterTime = new SimpleDateFormat("HH:mm");
        String dateStr = formatterDate.format(expense.getDate());
        String timeStr = formatterTime.format(expense.getDate());

        iconCategory.setImageDrawable(CommonIcon.getIcon(this, category.getIcon()));
        txtCategory.setText(category.getCategoryName());
        txtPrice.setText(String.valueOf(expense.getPrice()));
        txtDate.setText(dateStr);
        txtTime.setText(timeStr);
        txtNote.setText(expense.getDescription());

    }
}
