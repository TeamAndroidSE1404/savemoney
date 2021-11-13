package com.app.savemoney;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.savemoney.adapter.AddEditCategoryAdapter;
import com.app.savemoney.callbacks.OnImageClickListener;
import com.app.savemoney.common.CommonCodeValues;
import com.app.savemoney.common.StringUtils;
import com.app.savemoney.dao.CategoryDao;
import com.app.savemoney.model.Category;

import java.util.List;

public class AddEditCategoryActivity extends AppCompatActivity implements OnImageClickListener {

    private AddEditCategoryAdapter addEditCategoryAdapter;
    private RecyclerView recyclerView;
    private List<Category> categoryList;
    private ImageView btnCheckMark, btnBackHomePage, imageIcon;
    private EditText edtCateName;
    private TextView nameIcon;
    private CategoryDao categoryDao;
    private String userUid;
    private String classifyCate;

    public static final String CLASSIFY_CATE = "classifyCate";

    private OnImageClickListener onImageClickListener;
    private int[] images = {R.drawable.icon_name_1, R.drawable.icon_name_2, R.drawable.icon_name_3,
            R.drawable.icon_name_4, R.drawable.icon_name_5, R.drawable.icon_name_6,
            R.drawable.icon_name_7, R.drawable.icon_name_8, R.drawable.icon_name_9, R.drawable.icon_name_10,
            R.drawable.icon_name_11, R.drawable.icon_name_12, R.drawable.icon_name_13,
            R.drawable.icon_name_14, R.drawable.icon_name_15};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp1 = this.getSharedPreferences("Login", MODE_PRIVATE);

        userUid = sp1.getString("userUid", null);

        Intent intent = getIntent();
        classifyCate = CommonCodeValues.SPENDING;
        if (intent != null) {
            String temp = intent.getStringExtra(CLASSIFY_CATE);
            classifyCate = StringUtils.isEmpty(temp) ? CommonCodeValues.SPENDING : temp;
        }

        setContentView(R.layout.activity_add_category);
        btnCheckMark = findViewById(R.id.ic_check_mark);
        btnBackHomePage = findViewById(R.id.icon_back_category_setting);
        edtCateName = findViewById(R.id.edt_cate_name);
        imageIcon = findViewById(R.id.iv_icon_in_add_screen);
        nameIcon = findViewById(R.id.txt_icon_name);

        nameIcon.setText("icon_name_1");

        categoryDao = new CategoryDao(userUid);

        String categoryId = getIntent().getStringExtra("categoryId");
        Toast.makeText(AddEditCategoryActivity.this, categoryId, Toast.LENGTH_SHORT).show();

        btnCheckMark.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String categoryName = edtCateName.getText().toString();

                String categoryIcon = nameIcon.getText().toString();

                if (StringUtils.isEmpty(categoryName) || StringUtils.isEmpty(categoryIcon)) {
                    Toast.makeText(getBaseContext(), "Please fill all information!!", Toast.LENGTH_SHORT).show();
                } else {
                    Category cate = new Category("", categoryName, categoryIcon, classifyCate, "0");
                    categoryDao.addCategory(cate);
                    Intent intentMain = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intentMain);
                    finish();
                }

            }
        });

        btnBackHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        try {
            recyclerView = findViewById(R.id.rv_list_icon);

            addEditCategoryAdapter = new AddEditCategoryAdapter(this, images, new OnImageClickListener() {
                @Override
                public void onImageClick(int imageId, String imageName) {
                    imageIcon.setImageResource(imageId);
                    nameIcon.setText(imageName);

                    Toast.makeText(AddEditCategoryActivity.this, imageName, Toast.LENGTH_SHORT).show();
                }
            });

            LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 5);
            recyclerView.setHasFixedSize(true);

            recyclerView.setAdapter(addEditCategoryAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onImageClick(int imageId, String imageName) {

    }
}
