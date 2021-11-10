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

public class AddEditCategoryAdapter extends RecyclerView.Adapter<AddEditCategoryAdapter.ViewHolder> {

    private AddEditCategoryAdapter.ViewHolder viewHolder;
    private Context context;

    public AddEditCategoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public AddEditCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView =
                inflater.inflate(R.layout.activity_icon_list_rv, parent, false);

        viewHolder = new AddEditCategoryAdapter.ViewHolder(context, categoryView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(AddEditCategoryAdapter.ViewHolder holder, int position) {
        holder.ivCategoryImage1.setImageResource(R.drawable.ic_game);
        holder.ivCategoryImage2.setImageResource(R.drawable.ic_game);
        holder.ivCategoryImage3.setImageResource(R.drawable.ic_game);
        holder.ivCategoryImage4.setImageResource(R.drawable.ic_game);
        holder.ivCategoryImage5.setImageResource(R.drawable.ic_game);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivCategoryImage1;
        public ImageView ivCategoryImage2;
        public ImageView ivCategoryImage3;
        public ImageView ivCategoryImage4;
        public ImageView ivCategoryImage5;

        public Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            ivCategoryImage1 = (ImageView) itemView.findViewById(R.id.iv_category_setting_name_1);
            ivCategoryImage2 = (ImageView) itemView.findViewById(R.id.iv_category_setting_name_2);
            ivCategoryImage3 = (ImageView) itemView.findViewById(R.id.iv_category_setting_name_3);
            ivCategoryImage4 = (ImageView) itemView.findViewById(R.id.iv_category_setting_name_4);
            ivCategoryImage5 = (ImageView) itemView.findViewById(R.id.iv_category_setting_name_5);
            this.context = context;
        }
    }

}
