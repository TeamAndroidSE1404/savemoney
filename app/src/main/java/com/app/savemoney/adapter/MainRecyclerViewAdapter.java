package com.app.savemoney.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.R;
import com.app.savemoney.common.CommonCodeValues;
import com.app.savemoney.common.ConvertUtils;
import com.app.savemoney.common.DateUtils;
import com.app.savemoney.model.Expense;

import java.util.List;
import java.util.Map;


public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    Map<Integer, List<Expense>> expenseList;

    private Context context;

    public MainRecyclerViewAdapter(Context context, Map<Integer, List<Expense>> expenseList) {
        this.expenseList = expenseList;
        this.context = context;
    }

    public void changedData(Map<Integer, List<Expense>> expenseList) {
        this.expenseList = expenseList;
        this.notifyDataSetChanged();


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//      LayoutInflater layoutInflater = LayoutInflater.from(this.context);

        View view = layoutInflater.inflate(R.layout.section_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<Expense> list = expenseList.get(position);

        ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(context, list);

        holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
        double income = 0;
        double spend = 0;

        for (Expense e : list) {
                if (CommonCodeValues.INCOME.equals(e.getCate().getClassify())) {
                    income += e.getPrice();
                } else if (CommonCodeValues.SPENDING.equals(e.getCate().getClassify())) {
                    spend += e.getPrice();
                }
        }

        if(income > 0 ){
            holder.txtIncome.setVisibility(View.VISIBLE);
            String in = "Thu nh???p: ";
            holder.txtIncome.setText(in+"+" + ConvertUtils.addComaPrice(income));
        }

        if(spend > 0 ){
            holder.txtSpending.setVisibility(View.VISIBLE);
            String in = "Chi ti??u: ";
            holder.txtSpending.setText(in+"-" + ConvertUtils.addComaPrice(spend));
        }

        holder.txtDate.setText(DateUtils.dateToString(list.get(0).getDate(), CommonCodeValues.DATE_DD_MM_YYYY));


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDate, txtSpending, txtIncome;
        RecyclerView childRecyclerView;


        public ViewHolder(View itemView) {
            super(itemView);

            txtDate = itemView.findViewById(R.id.txtDate);
            txtSpending = itemView.findViewById(R.id.txtSpending);
            txtIncome = itemView.findViewById(R.id.txtIncome);
            childRecyclerView = itemView.findViewById(R.id.childRecyclerView);


        }
    }

}
