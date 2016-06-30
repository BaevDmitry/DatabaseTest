package com.jezik.databasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView et_name;
    TextView et_college;
    TextView et_place;
    TextView et_userId;
    TextView et_number;

    Button btn_next;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = DbHelper.getInstance(getApplicationContext());

        et_name = (TextView) findViewById(R.id.et_name);
        et_college = (TextView) findViewById(R.id.et_college);
        et_place = (TextView) findViewById(R.id.et_place);
        et_userId = (TextView) findViewById(R.id.et_userid);
        et_number = (TextView) findViewById(R.id.et_number);

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData userData = new UserData();

                if (!et_name.getText().toString().isEmpty()) {
                    userData.name = et_name.getText().toString();
                } else {
                    userData.name = "";
                }

                if (!et_college.getText().toString().isEmpty()) {
                    userData.college = et_college.getText().toString();
                } else {
                    userData.college = "";
                }

                if (!et_place.getText().toString().isEmpty()) {
                    userData.place = et_place.getText().toString();
                } else {
                    userData.place = "";
                }

                if (!et_userId.getText().toString().isEmpty()) {
                    userData.user_id = et_userId.getText().toString();
                } else {
                    userData.user_id = "";
                }

                if (!et_number.getText().toString().isEmpty()) {
                    userData.number = et_number.getText().toString();
                } else {
                    userData.number = "";
                }

                dbHelper.insertUserDetail(userData);

                Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
