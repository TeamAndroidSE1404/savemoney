package com.app.savemoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.R;
import com.app.savemoney.model.Category;

import java.util.List;

public class ListExpenseIncomeAdapter extends  RecyclerView.Adapter<ListExpenseIncomeAdapter.ViewHolder> {

    ListExpenseIncomeAdapter.ViewHolder viewHolder;
    List<Category> categoriesList;
    Context context;

    public ListExpenseIncomeAdapter(List<Category> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;
    }

    @Override
    public ListExpenseIncomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView =
                inflater.inflate(R.layout.activity_category_setting_item, parent, false);

        viewHolder = new ListExpenseIncomeAdapter.ViewHolder(context ,categoryView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ListExpenseIncomeAdapter.ViewHolder holder, int position) {
        Category category = categoriesList.get(position);
        // Set item views based on your views and data model
        holder.txtCategoryName.setText(category.getCategoryName());
        holder.ivCategoryImage.setImageResource(R.drawable.ic_game);
        holder.ivEdit.setImageResource(R.drawable.ic_pencil_rv);
        holder.ivRemove.setImageResource(R.drawable.ic_remove_category);

    }


    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public static class  ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivCategoryImage;
        public TextView txtCategoryName;
        public ImageView ivEdit;
        public ImageView ivRemove;
        public Context context;
        public ViewHolder(Context context ,View itemView) {
            super(itemView);
            ivCategoryImage = (ImageView) itemView.findViewById(R.id.iv_category_setting);
            txtCategoryName = (TextView) itemView.findViewById(R.id.txt_category_setting_name);
            ivEdit = (ImageView) itemView.findViewById(R.id.iv_category_setting_edit);
            ivRemove = (ImageView) itemView.findViewById(R.id.iv_category_setting_remove);
            this.context = context;
        }
    }


}
