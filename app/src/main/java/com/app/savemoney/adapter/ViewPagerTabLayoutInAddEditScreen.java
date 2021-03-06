package com.app.savemoney.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.app.savemoney.fragment.ExpenseFragment;
import com.app.savemoney.fragment.ExpenseFragmentInAddEditScreen;
import com.app.savemoney.fragment.IncomeFragment;
import com.app.savemoney.fragment.IncomeFragmentInAddEditScreen;

public class ViewPagerTabLayoutInAddEditScreen extends FragmentStatePagerAdapter {
    public ViewPagerTabLayoutInAddEditScreen(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ExpenseFragmentInAddEditScreen();
            case 1:
                return new IncomeFragmentInAddEditScreen();
            default:
                return new ExpenseFragmentInAddEditScreen();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Expense";
                break;
            case 1:
                title = "Income";
                break;
        }
        return title;
    }

}
