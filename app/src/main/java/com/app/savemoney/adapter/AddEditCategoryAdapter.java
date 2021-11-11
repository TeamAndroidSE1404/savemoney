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

public class AddEditCategoryAdapter extends RecyclerView.Adapter<AddEditCategoryAdapter.ImageViewHolder> {

    private String[] iconName = {"icon_name_1", "icon_name_2", "icon_name_3", "icon_name_4",
            "icon_name_5", "icon_name_6", "icon_name_7", "icon_name_8", "icon_name_9",
            "icon_name_10", "icon_name_11", "icon_name_12", "icon_name_13", "icon_name_14", "icon_name_15",
            "icon_name_16", "icon_name_17", "icon_name_18", "icon_name_19", "icon_name_20"};
    private AddEditCategoryAdapter.ImageViewHolder imageViewHolder;
    private int[] images;
    public Context context;

    public AddEditCategoryAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public AddEditCategoryAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView =
                inflater.inflate(R.layout.activity_icon_list_rv, parent, false);

        imageViewHolder = new AddEditCategoryAdapter.ImageViewHolder(context, categoryView);
        return imageViewHolder;

    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        int imageId = images[position];
        String imageName = iconName[position];
        holder.ivCategoryImage.setImageResource(imageId);
        holder.ivCategoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ivIcon.setImageResource(imageId);
                holder.txtIconName.setText(imageName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivCategoryImage;
        public ImageView ivIcon;
        public TextView txtIconName;
        public Context context;

        public ImageViewHolder(Context context, View itemView) {
            super(itemView);
            ivCategoryImage = (ImageView) itemView.findViewById(R.id.iv_category_setting_name);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon_in_add_screen) ;
            txtIconName = (TextView) itemView.findViewById(R.id.txt_icon_name);
            this.context = context;
        }
    }

}
