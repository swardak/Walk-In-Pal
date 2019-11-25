package com.example.mywalkinpal;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SearchByAddressActivity extends AppCompatActivity{
    Button goButton;
    EditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_address);

        goButton = (Button) findViewById(R.id.goSearchAddress);
        address = (EditText) findViewById(R.id.entAddress);

        goButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            }
        });
    }
}
