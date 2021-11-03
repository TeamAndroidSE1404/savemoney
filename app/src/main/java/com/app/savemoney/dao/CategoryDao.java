package com.app.savemoney.dao;

import androidx.annotation.NonNull;

import com.app.savemoney.model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryDao {


//    private DatabaseReference categoryRef;
//
//    public CategoryDao() {
//        FirebaseDatabase database = FirebaseDatabase.getInstance("https://savemoney-fcf71-default-rtdb.asia-southeast1.firebasedatabase.app");
//        categoryRef = database.getReference("Categories");
//
//    }
//
//    public List<Category> getAllCate() {
//        List<Category> result = new ArrayList<>();
//
//        categoryRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    Category cate = snapshot.getValue(Category.class);
//                    result.add(cate);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        return result;
//    }
}
