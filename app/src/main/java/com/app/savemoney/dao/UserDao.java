package com.app.savemoney.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.app.savemoney.callbacks.UserCallBack;
import com.app.savemoney.common.CommonCodeValues;
import com.app.savemoney.common.DateUtils;
import com.app.savemoney.model.Category;
import com.app.savemoney.model.Expense;
import com.app.savemoney.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UserDao {

    private DatabaseReference userRef;

    public UserDao() {
        this.userRef = FirebaseDatabase.getInstance(CommonCodeValues.INSTANCE).getReference(CommonCodeValues.DB_USERS);
    }

    public void checkExitsEmail(String email, UserCallBack userCallBack) {

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean check = false;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    HashMap<String, Object> userMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    if (email.equals(userMap.get(User.EMAIL))) {
                        check = true;
                        break;
                    }

                }
                userCallBack.onCheckExits(check);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void addUser(User user) {
        String key = userRef.push().getKey();
        user.setUid(key);
        userRef.child(key).setValue(user.toMap());


       String cateKey = userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).push().getKey();


        Category cate = new Category(cateKey, "Cate1", "icon1", CommonCodeValues.INCOME, "0");

        userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).child(cate.getUid()).setValue(cate.toMap());

        String expenseKey = userRef.child(key).child(CommonCodeValues.DB_EXPENSES).push().getKey();

        Expense expense = new Expense(expenseKey, "Decription", DateUtils.StringToDate("20210920_1010", CommonCodeValues.DATE_DDMMYYYY_HHMM), cate, 20);

        userRef.child(key).child(CommonCodeValues.DB_EXPENSES).child(expense.getUid()).setValue(expense.toMap());

    }

    public void checkLogin(String email, String password, UserCallBack userCallBack) {

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User loginUser = null;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    HashMap<String, Object> userMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    String emailDb = userMap.get(User.EMAIL).toString();

                    if (email.equalsIgnoreCase(emailDb)) {
                        if (password.equals(userMap.get(User.PASSWORD).toString())) {
                            loginUser = new User();
                            loginUser.setUid(userMap.get(User.UID).toString());
                            loginUser.setEmail(emailDb);
                            loginUser.setFullName(userMap.get(User.FULL_NAME).toString());
                        }
                        break;
                    }
                }

                userCallBack.onCheckLogin(loginUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
