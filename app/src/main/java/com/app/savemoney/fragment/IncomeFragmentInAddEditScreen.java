package com.app.savemoney.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.R;
import com.app.savemoney.adapter.ListExpenseIncomeAdapter;
import com.app.savemoney.adapter.ListExpenseIncomeInAddScreenAdapter;
import com.app.savemoney.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IncomeFragmentInAddEditScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeFragmentInAddEditScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    private RecyclerView recyclerView;
    private List<Category> categoryList;
    public IncomeFragmentInAddEditScreen() {
        // Required empty public constructor
    }

    public static IncomeFragmentInAddEditScreen newInstance(String param1, String param2) {
        IncomeFragmentInAddEditScreen fragment = new IncomeFragmentInAddEditScreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        categoryList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            categoryList.add(new Category("Thue nha"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_income_in_add_edit_screen, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_income_in_add_edit);
        ListExpenseIncomeInAddScreenAdapter listExpenseIncomeInAddScreenAdapter = new ListExpenseIncomeInAddScreenAdapter(categoryList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listExpenseIncomeInAddScreenAdapter);
        return view;
    }
}