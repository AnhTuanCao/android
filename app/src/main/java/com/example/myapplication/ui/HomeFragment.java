package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.adapter.ItemAdapter;
import com.example.myapplication.db.UserSQL;
import com.example.myapplication.model.Account;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.model.User;
import com.example.myapplication.R;
public class HomeFragment extends Fragment implements ItemAdapter.OnItemListener {
    RecyclerView rcv;
    List<User> listUser = new ArrayList<>();
    ItemAdapter adapter;
    FloatingActionButton btnAdd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcv = view.findViewById(R.id.rcv_list_item);
        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddActivity.class);
                startActivityForResult(i, 1);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        UserSQL sqLiteHelper = new UserSQL(getContext());
        listUser = sqLiteHelper.getAll(MainActivity.account.getId());
        adapter = new ItemAdapter(getContext(), listUser, false);
        adapter.setClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(manager);
        rcv.setAdapter(adapter);

    }

    @Override
    public void onClick(View view, int position) {
        User user = adapter.getItem(position);
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("user", user);
        getContext().startActivity(intent);
    }
}
