package com.example.myapplication.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.User;

public class DetailActivity extends AppCompatActivity{
    User user;
    ImageButton btnBack;
    TextView txtName, txtBirth, txtGender, txtPosition, txtSalary;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        user = (User) getIntent().getSerializableExtra("user");
        txtName.setText("Họ tên: " + user.getName());
        txtBirth.setText("Ngày sinh: "+ user.getBirthday());
        if(user.getGender().compareToIgnoreCase("male") == 0) txtGender.setText("Giới tính: Nam");
        else txtGender.setText("Giới tính: Nữ");
        im.setImageURI(Uri.parse(user.getImage()));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void init(){
        btnBack = findViewById(R.id.btn_back);
        txtName = findViewById(R.id.name_employ);
        txtBirth = findViewById(R.id.birth_employ);
        txtGender = findViewById(R.id.gender_employ);
        txtPosition = findViewById(R.id.position_employ);
        txtSalary = findViewById(R.id.salary_employ);
        im = findViewById(R.id.im_employ);
    }


}
