package com.app.savemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.savemoney.common.DateUtils;
import com.app.savemoney.model.Expense;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference mDb = FirebaseDatabase.getInstance().getReference();



    }

    private Map<Integer, List<Expense>> getListExpense(){

        List<Expense> expenseList = new ArrayList<>();

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

        Map<Integer, List<Expense>> test = new HashMap<>();
        int count = 0;
        List<String> keySet = new ArrayList<>(mapExpense.keySet());
        Collections.sort(keySet, Collections.reverseOrder());
        for(String key:keySet){

            test.put(count, mapExpense.get(key));


        }
    return null;
    }
}