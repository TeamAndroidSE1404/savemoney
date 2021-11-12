package com.app.savemoney.dao;

import androidx.annotation.NonNull;

import com.app.savemoney.common.CommonCodeValues;
import com.app.savemoney.model.Expense;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ExpenseDao {

    private DatabaseReference expenseRef;

    public ExpenseDao(String userUid) {
        expenseRef = FirebaseDatabase.getInstance(CommonCodeValues.INSTANCE).getReference(CommonCodeValues.DB_USERS).child(userUid).child(CommonCodeValues.DB_EXPENSES);
    }

    public void addExpense(Expense expense) {
        String key = expenseRef.push().getKey();
        expense.setUid(key);
        expenseRef.child(key).setValue(expense.toMap());
    }

    public void updateExpense(Expense expense) {

        Map<String, Object> expenseMap = expense.toMap();

        expenseRef.child(expense.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (String key : expenseMap.keySet()) {
                        if (!Expense.UID.equals(key)) {

                            expenseRef.child(expense.getUid()).child(key).setValue(expenseMap.get(key));
                        }

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }

    public void deleteExpense(String expenseId) {
        expenseRef.child(expenseId).removeValue();

    }

}
