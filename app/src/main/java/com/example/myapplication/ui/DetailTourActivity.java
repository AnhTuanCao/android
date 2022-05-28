package com.example.myapplication.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Tour;
import com.example.myapplication.model.User;

public class DetailTourActivity extends AppCompatActivity{
    Tour tour;
    ImageButton btnBack;
    TextView txtName, txtStartDate, txtTrans, txtDuration, txtTotal;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourdetail);
        init();
        tour = (Tour) getIntent().getSerializableExtra("tour");
        txtName.setText("Địa điểm: " + tour.getName());
        txtStartDate.setText("Ngày bắt đầu: "+ tour.getStartDate());
        if(tour.getTrans().compareToIgnoreCase("plane") == 0) txtTrans.setText("Phương tiện: Máy bay");
        else txtTrans.setText("Phương tiện: Tàu hỏa");
        txtDuration.setText("Thời gian: " + tour.getDuration());
        txtTotal.setText("Chi phí: " + tour.getTotal());
//        im.setImageURI(Uri.parse(tour.getImage()));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void init(){
        btnBack = findViewById(R.id.btn_back);
        txtName = findViewById(R.id.name);
        txtStartDate = findViewById(R.id.startDate);
        txtTrans = findViewById(R.id.trans);
        txtDuration = findViewById(R.id.duration);
        txtTotal = findViewById(R.id.total);
        im = findViewById(R.id.im_employ);
    }


}
