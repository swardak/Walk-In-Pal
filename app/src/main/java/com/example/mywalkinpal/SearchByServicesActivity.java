package com.example.mywalkinpal;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SearchByServicesActivity extends AppCompatActivity{
    Button goButton;
    EditText service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_services);

        goButton = (Button) findViewById(R.id.goSearchServices);
        service = (EditText) findViewById(R.id.entServicesName);

        goButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            }
        });
    }
}
