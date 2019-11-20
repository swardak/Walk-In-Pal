package com.example.mywalkinpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WorkingHourActivity extends AppCompatActivity{
    Button manageBtn, viewBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_hour);
        manageBtn = (Button) findViewById(R.id.manageWhButton);
        viewBtn = (Button) findViewById(R.id.viewWhButton);

        manageBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkingHourActivity.this, ManageWorkingHour.class));
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkingHourActivity.this, ViewWorkingHour.class));
            }
        });
    }
}
