package com.app.savemoney.dao;

import androidx.annotation.NonNull;

import com.app.savemoney.callbacks.CategoryCallBack;
import com.app.savemoney.common.CommonCodeValues;
import com.app.savemoney.model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDao {
    private DatabaseReference cateRef;


    public CategoryDao(String userUid) {
        cateRef = FirebaseDatabase.getInstance(CommonCodeValues.INSTANCE).getReference(CommonCodeValues.DB_USERS).child(userUid).child(CommonCodeValues.DB_CATEGORIES);
    }

    Map<String, Category> categories = new HashMap<>();

    public void getAllCate(CategoryCallBack categoryCallBack) {
        cateRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Category cate = dataSnapshot.getValue(Category.class);
                    categories.put(cate.getUid(), cate);

                }
                categoryCallBack.onCallbackCategory(categories);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getCateByKey(String key, CategoryCallBack categoryCallBack){

        cateRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Category cate = snapshot.getValue(Category.class);
                    categoryCallBack.onCallbackGetOneCategory(cate);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public DatabaseReference getCateRef() {
        return cateRef;
    }

    public void addCategory(Category cate) {

        String key = cateRef.push().getKey();
        cate.setUid(key);
        cateRef.child(key).setValue(cate.toMap());

    }

    public void updateCategory(Category cate) {
        Map<String, Object> cateMap = cate.toMap();

        cateRef.child(cate.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (String key : cateMap.keySet()) {
                        if (!Category.UID.equals(key)) {
                            cateRef.child(cate.getUid()).child(key).setValue(cateMap.get(key));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void deleteCate(String key){
        cateRef.child(key).child(Category.DISABLE).setValue("1");
    }
}
