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

        Category cate = new Category(cateKey, "Chứng khoáng", "icon_name_7", CommonCodeValues.INCOME, "0");

        userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).child(cate.getUid()).setValue(cate.toMap());

        String cateKey1 = userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).push().getKey();

        Category cate1 = new Category(cateKey1, "Bán xe", "icon_name_10", CommonCodeValues.INCOME, "0");

        userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).child(cate1.getUid()).setValue(cate1.toMap());

        String cateKey2 = userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).push().getKey();

        Category cate2 = new Category(cateKey2, "Sữa chữa", "icon_name_8", CommonCodeValues.INCOME, "0");

        userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).child(cate2.getUid()).setValue(cate2.toMap());


        String cateKey3 = userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).push().getKey();

        Category cate3 = new Category(cateKey3, "Thể thao", "icon_name_6", CommonCodeValues.SPENDING, "0");

        userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).child(cate3.getUid()).setValue(cate3.toMap());

        String cateKey4 = userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).push().getKey();

        Category cate4 = new Category(cateKey4, "Giải trí", "icon_name_1", CommonCodeValues.SPENDING, "0");

        userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).child(cate4.getUid()).setValue(cate4.toMap());

        String cateKey5 = userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).push().getKey();

        Category cate5 = new Category(cateKey5, "Đồ ăn", "icon_name_4", CommonCodeValues.SPENDING, "0");

        userRef.child(key).child(CommonCodeValues.DB_CATEGORIES).child(cate5.getUid()).setValue(cate5.toMap());



    }

    public void checkLogin(String email, String password, UserCallBack userCallBack) {

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User loginUser = null;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    HashMap<String, Object> userMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    String emailDb = (String)userMap.get(User.EMAIL);

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
