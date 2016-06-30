package com.jezik.databasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class UserDetailActivity extends AppCompatActivity implements Listener{

    RecyclerView recyclerView;
    DbHelper dbHelper;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        dbHelper = DbHelper.getInstance(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.rv_contactlist);
        adapter = new ListAdapter(this, dbHelper.getAllUser());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void nameToChange(String name) {
        dbHelper.deleteRow(name);

        adapter = new ListAdapter(this, dbHelper.getAllUser());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
