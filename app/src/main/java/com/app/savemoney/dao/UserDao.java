package com.app.savemoney.dao;

import com.app.savemoney.common.CommonCodeValues;
import com.app.savemoney.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDao {

    private DatabaseReference userRef;

    public UserDao() {
        this.userRef = FirebaseDatabase.getInstance(CommonCodeValues.INSTANCE).getReference(CommonCodeValues.DB_USERS);
    }

    public void addUser(User user){

        


    }
}
