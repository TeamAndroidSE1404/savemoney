package com.app.savemoney;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.savemoney.adapter.MainRecyclerViewAdapter;
import com.app.savemoney.callbacks.CategoryCallBack;
import com.app.savemoney.common.CommonCodeValues;
import com.app.savemoney.common.DateUtils;
import com.app.savemoney.dao.CategoryDao;
import com.app.savemoney.model.Category;
import com.app.savemoney.model.Expense;
import com.app.savemoney.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    int i = 0;

    private RecyclerView recyclerViewExpense;

    private Map<Integer, List<Expense>> tempMap;

    private List<Category> categoryList;

    private Map<String, Category> listCategory = new HashMap<>();

    private Map<String, Category> test;

    private DatabaseReference databaseReference;

    private CategoryDao categoryDao;

    private String userUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp1 = this.getSharedPreferences("Login", MODE_PRIVATE);

        userUid = "-Mo8aySfeG6bFZReOovT";

//        Log.d("1112", unm);

        setInit();
        getCategory();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                setDataEvent();
            }
        }, 2000);


    }

    private void setDataEvent() {
        databaseReference = FirebaseDatabase.getInstance(CommonCodeValues.INSTANCE).getReference();

        categoryList = new ArrayList<>();

        Map<String, Category> test = new HashMap<>();

        tempMap = new HashMap<>();

        MainRecyclerViewAdapter mainRecyclerViewAdapter = new MainRecyclerViewAdapter(tempMap);
        recyclerViewExpense = findViewById(R.id.rvMainMenu);
        recyclerViewExpense.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewExpense.setAdapter(mainRecyclerViewAdapter);


        DatabaseReference expenseDb = FirebaseDatabase.getInstance(CommonCodeValues.INSTANCE).getReference(CommonCodeValues.DB_USERS).child(userUid).child(CommonCodeValues.DB_EXPENSES);

        expenseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Expense> expenseList1 = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    HashMap<String, Object> expenseMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    Expense expense = new Expense();
                    Log.d("123", String.valueOf(listCategory.size()));
                    if (!listCategory.isEmpty()) {

                        expense.toObject(expenseMap, listCategory);
                        if (expense.getCate() != null) {
                            expenseList1.add(expense);
                        }

                    }

                }
                getListExpense(expenseList1);
                mainRecyclerViewAdapter.changedData(tempMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void getCategory() {
        categoryDao = new CategoryDao(userUid);
        categoryDao.getAllCate(new CategoryCallBack() {
            @Override
            public void onCallbackCategory(Map<String, Category> value) {
                Log.d("121", "get category");
                Log.d("122", String.valueOf(value.size()));
                listCategory.putAll(value);

            }
        });


    }

    private void setInit() {

    }


    private void getListExpense(List<Expense> expenseList) {

        Map<String, List<Expense>> mapExpense = new HashMap<>();

        for (Expense ex : expenseList) {
            String key = DateUtils.dateToString(ex.getDate());
            if (mapExpense.get(key) == null) {
                ArrayList<Expense> listItem = new ArrayList<>();
                listItem.add(ex);
                mapExpense.put(key, listItem);
            } else {
                mapExpense.get(key).add(ex);
            }
        }
        this.tempMap.clear();
        int count = 0;
        List<String> keySet = new ArrayList<>(mapExpense.keySet());
        Collections.sort(keySet, Collections.reverseOrder());
        for (String key : keySet) {

            this.tempMap.put(count++, mapExpense.get(key));


        }
    }


    public void clickAbc(View view) {
        // Write a message to the database
        Intent intentBtnLogin = new Intent(MainActivity.this, AddExpenseScreenActivity.class);
        startActivity(intentBtnLogin);

//        Category a = new Category(key, String.valueOf(i++), "das", "0");
//
//        CategoryDao cateDao = new CategoryDao();
//        List<Category> tests = cateDao.getAllCate();
//
//        for(Category i:tests){
//            Log.d("XYZ", i.getCategoryName());
//        }


    }


}