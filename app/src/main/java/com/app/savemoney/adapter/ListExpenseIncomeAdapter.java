package com.app.savemoney.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.AddEditCategoryActivity;
import com.app.savemoney.ListExpenseIncomeActivity;
import com.app.savemoney.R;
import com.app.savemoney.callbacks.ListCategoryFragmentCallBack;
import com.app.savemoney.model.Category;

import java.util.List;

public class ListExpenseIncomeAdapter extends RecyclerView.Adapter<ListExpenseIncomeAdapter.ViewHolder> {

    private ListExpenseIncomeAdapter.ViewHolder viewHolder;
    private List<Category> categoriesList;
    private Context context;
    private AlertDialog.Builder builder;
    private ListCategoryFragmentCallBack onSelectCategoryListener;

    public ListExpenseIncomeAdapter(List<Category> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;

    }

    public ListExpenseIncomeAdapter(List<Category> categoriesList, Context context,ListCategoryFragmentCallBack onSelectCategoryListener) {
        this.categoriesList = categoriesList;
        this.context = context;
        this.onSelectCategoryListener = onSelectCategoryListener;

    }


    public void changedData(List<Category> list) {
        this.categoriesList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public ListExpenseIncomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView =
                inflater.inflate(R.layout.activity_category_setting_item, parent, false);
        builder = new AlertDialog.Builder(context);

        viewHolder = new ListExpenseIncomeAdapter.ViewHolder(context, categoryView);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(ListExpenseIncomeAdapter.ViewHolder holder, int position) {
        Category category = categoriesList.get(position);
        holder.txtCategoryName.setText(category.getCategoryName());
        holder.ivCategoryImage.setImageResource(R.drawable.ic_game);
        holder.ivRemove.setImageResource(R.drawable.ic_remove_category);
        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setTitle("Warning").setMessage("Do you want to delete item?")
                        .setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

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
        public ImageView ivEdit;
        public ImageView ivRemove;
        public Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            ivCategoryImage = (ImageView) itemView.findViewById(R.id.iv_category_setting);
            txtCategoryName = (TextView) itemView.findViewById(R.id.txt_category_setting_name);
//            ivEdit = (ImageView) itemView.findViewById(R.id.iv_category_setting_edit);
            ivRemove = (ImageView) itemView.findViewById(R.id.iv_category_setting_remove);

            this.context = context;
        }

    }




}
