package com.app.savemoney.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.AddEditCategoryActivity;
import com.app.savemoney.R;
import com.app.savemoney.adapter.AddEditExpenseIncomeAdapter;
import com.app.savemoney.adapter.ListExpenseIncomeAdapter;
import com.app.savemoney.callbacks.CategoryCallBack;
import com.app.savemoney.callbacks.ListCategoryFragmentCallBack;
import com.app.savemoney.common.CommonCodeValues;
import com.app.savemoney.dao.CategoryDao;
import com.app.savemoney.model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseFragment extends Fragment implements ListCategoryFragmentCallBack {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
    private RecyclerView recyclerView;
    private List<Category> categoryList;
    private String userUid;
    private CategoryDao categoryDao;
    private EditText decription;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static String index;

    public ExpenseFragment() {
    }

    public static ExpenseFragment newInstance(String param1, String param2) {
        ExpenseFragment fragment = new ExpenseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp1 = this.getContext().getSharedPreferences("Login", MODE_PRIVATE);
        userUid = sp1.getString("userUid", null);
        categoryDao = new CategoryDao(userUid);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        categoryList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_expense, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_expense);
        ListExpenseIncomeAdapter listExpenseIncomeAdapter = new ListExpenseIncomeAdapter(categoryList, this.getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listExpenseIncomeAdapter);
        categoryDao.getCateRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Category> list = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Category cate = dataSnapshot.getValue(Category.class);
                    if("0".equals(cate.getDisable())&& CommonCodeValues.SPENDING.equals(cate.getClassify())){
                       list.add(cate);
                    }
                }
                listExpenseIncomeAdapter.changedData(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }


    @Override
    public void onClickCategoryListener(Category data) {
        Log.d("666", data.getUid());
    }
}