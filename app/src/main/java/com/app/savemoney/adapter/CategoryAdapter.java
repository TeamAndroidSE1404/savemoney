package com.app.savemoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.R;
import com.app.savemoney.dto.Category;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ViewHolder viewHolder;

    List<Category> categoriesList;
    Context context;

    public CategoryAdapter(List<Category> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView =
                inflater.inflate(R.layout.activity_category_item, parent, false);

        viewHolder = new ViewHolder(context ,categoryView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        Category category = categoriesList.get(position);
        // Set item views based on your views and data model
        holder.txtCategoryName.setText(category.getName());
        holder.ivCategoryImage.setImageResource(R.drawable.icon_game);
    }


    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivCategoryImage;
        public TextView txtCategoryName;
        public ImageView imgShowPopupCateList;
        public Context context;
        public ViewHolder(Context context ,View itemView) {
            super(itemView);
            ivCategoryImage = (ImageView) itemView.findViewById(R.id.iv_category_name);
            txtCategoryName = (TextView) itemView.findViewById(R.id.txt_category_name);
            imgShowPopupCateList = (ImageView) itemView.findViewById(R.id.iv_category);
            this.context = context;
        }
    }

}
