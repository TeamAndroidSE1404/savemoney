package com.app.savemoney.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.AddEditCategoryActivity;
import com.app.savemoney.R;
import com.app.savemoney.callbacks.ListCategoryFragmentCallBack;
import com.app.savemoney.common.CommonIcon;
import com.app.savemoney.model.Category;

import java.util.List;

public class ListExpenseIncomeInAddScreenAdapter extends RecyclerView.Adapter<ListExpenseIncomeInAddScreenAdapter.ViewHolder> {

    private ListExpenseIncomeInAddScreenAdapter.ViewHolder viewHolder;
    private List<Category> categoriesList;
    private Context context;
    private AlertDialog.Builder builder;
    private ListCategoryFragmentCallBack onSelectCategoryListener;

    public ListExpenseIncomeInAddScreenAdapter(List<Category> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;

    }

    public ListExpenseIncomeInAddScreenAdapter(List<Category> categoriesList, Context context, ListCategoryFragmentCallBack onSelectCategoryListener) {
        this.categoriesList = categoriesList;
        this.context = context;


        this.onSelectCategoryListener = onSelectCategoryListener;

    }


    public void changedData(List<Category> list) {
        this.categoriesList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public ListExpenseIncomeInAddScreenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView =
                inflater.inflate(R.layout.activity_category_item, parent, false);
        builder = new AlertDialog.Builder(context);

        viewHolder = new ListExpenseIncomeInAddScreenAdapter.ViewHolder(context, categoryView);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(ListExpenseIncomeInAddScreenAdapter.ViewHolder holder, int position) {
        Category category = categoriesList.get(position);
        holder.txtCategoryName.setText(category.getCategoryName());

        holder.ivCategoryImage.setImageDrawable(CommonIcon.getIcon(context, category.getIcon()));

        holder.txtCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectCategoryListener.onClickCategoryListener(category);
            }
        });

        holder.ivCategoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("555", category.getUid());
                onSelectCategoryListener.onClickCategoryListener(category);
            }
        });
    }


    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivCategoryImage;
        public TextView txtCategoryName;
        public Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            ivCategoryImage = (ImageView) itemView.findViewById(R.id.iv_category_name);
            txtCategoryName = (TextView) itemView.findViewById(R.id.txt_category_name);
            this.context = context;
        }

    }




}
