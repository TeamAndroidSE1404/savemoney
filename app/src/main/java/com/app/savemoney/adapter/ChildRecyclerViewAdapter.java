package com.app.savemoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.R;
import com.app.savemoney.common.CommonCodeValues;
import com.app.savemoney.common.CommonIcon;
import com.app.savemoney.model.Expense;

import java.util.List;

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.ViewHolder> {

    private List<Expense> items;

    private Context context;

    public ChildRecyclerViewAdapter(Context context, List<Expense> items) {
        this.items = items;
        this.context = context;
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

        holder.icCategory.setImageDrawable(CommonIcon.getIcon(context, item.getCate().getIcon()));


        holder.txtNameItem.setText(item.getCate().getCategoryName());


        String price = String.valueOf(item.getPrice());
        if(CommonCodeValues.INCOME.equals(item.getCate().getClassify())){

            holder.txtPrice.setText("+"+price);
        }else if(CommonCodeValues.SPENDING.equals(item.getCate().getClassify())){
            holder.txtPrice.setText("-"+price);
        }



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
