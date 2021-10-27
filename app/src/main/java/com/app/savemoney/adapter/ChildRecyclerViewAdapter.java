package com.app.savemoney.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.R;
import com.app.savemoney.model.Expense;

import java.util.List;

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.ViewHolder> {

    private List<Expense> items;

    public ChildRecyclerViewAdapter(List<Expense> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ChildRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildRecyclerViewAdapter.ViewHolder holder, int position) {

        Expense item = items.get(position);

        holder.txtNameItem.setText(item.getCate().getCategoryName());

        holder.txtPrice.setText(String.valueOf(item.getPrice()));





    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icCategory;
        TextView txtNameItem, txtPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            icCategory = itemView.findViewById(R.id.icCategory);
            txtNameItem = itemView.findViewById(R.id.txtNameItem);
            txtPrice = itemView.findViewById(R.id.txtPrice);


        }
    }

}
