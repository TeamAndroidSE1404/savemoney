package com.app.savemoney.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.R;
import com.app.savemoney.adapter.ListExpenseIncomeAdapter;
import com.app.savemoney.adapter.ListExpenseIncomeInAddScreenAdapter;
import com.app.savemoney.callbacks.ListCategoryFragmentCallBack;
import com.app.savemoney.common.CommonCodeValues;
import com.app.savemoney.common.CommonIcon;
import com.app.savemoney.dao.CategoryDao;
import com.app.savemoney.model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpenseFragmentInAddEditScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseFragmentInAddEditScreen extends Fragment implements ListCategoryFragmentCallBack {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
    private RecyclerView recyclerView;
    private List<Category> categoryList;
    private String userUid;
    private CategoryDao categoryDao;
    private TextView txtCategoryId;
    private ImageView imgCategory;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ExpenseFragmentInAddEditScreen() {
    }

    public static ExpenseFragmentInAddEditScreen newInstance(String param1, String param2) {
        ExpenseFragmentInAddEditScreen fragment = new ExpenseFragmentInAddEditScreen();
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
        txtCategoryId = getActivity().findViewById(R.id.txt_category_id);
        imgCategory = getActivity().findViewById(R.id.iv_category_icon);
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
        view = inflater.inflate(R.layout.fragment_expense_in_add_edit_screen, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_expense_in_add_edit);
        ListExpenseIncomeInAddScreenAdapter listExpenseIncomeInAddScreenAdapter = new ListExpenseIncomeInAddScreenAdapter(categoryList, this.getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listExpenseIncomeInAddScreenAdapter);
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
                listExpenseIncomeInAddScreenAdapter.changedData(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }


    @Override
    public void onClickCategoryListener(Category data) {
        txtCategoryId.setText(data.getUid());
        imgCategory.setImageDrawable(CommonIcon.getIcon(getContext(), data.getIcon()));
        Log.d("666", data.getUid());
    }
}