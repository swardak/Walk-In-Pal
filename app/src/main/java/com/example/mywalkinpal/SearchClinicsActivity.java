package com.example.mywalkinpal;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SearchClinicsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_clinic);

        Button sbHours = (Button) findViewById(R.id.searchTimeButton);

        sbHours.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchClinicsActivity.this, SearchByWhActivity.class));
            }
        });

        Button sbAddress = (Button) findViewById(R.id.searchAddressButton);

        sbAddress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchClinicsActivity.this, SearchByAddressActivity.class));
            }
        });

        Button sbService = (Button) findViewById(R.id.searchServicescButton);

        sbService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchClinicsActivity.this, SearchByServicesActivity.class));
            }
        });

        Button sbName = (Button) findViewById(R.id.searchNameButton);

        sbName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchClinicsActivity.this, SearchByNameActivity.class));
            }
        });

    }
}
