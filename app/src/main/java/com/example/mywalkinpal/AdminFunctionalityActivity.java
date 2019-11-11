package com.example.mywalkinpal;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminFunctionalityActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_functionality);

        Button serviceBtn = (Button) findViewById(R.id.serviceButton);
        Button accountBtn = (Button) findViewById(R.id.accountButton);


        serviceBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminFunctionalityActivity.this, ServiceListActivity.class));

            }
        });

        accountBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminFunctionalityActivity.this, AccountListActivity.class));
            }
        });
    }
}
