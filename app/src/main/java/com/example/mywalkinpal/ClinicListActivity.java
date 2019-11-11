package com.example.mywalkinpal;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ClinicListActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_list);
        /*ListView listView = (ListView) findViewById(R.id.list);
        String[] values = new String[]{
                "My clinic", "empty", "empty", "empty", "empty", "empty", "empty","empty"
        };
        ClinicArrayAdapter adapter = new ClinicArrayAdapter(this, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                //DO SOMETHING with your item, maybe open a new activity!
            }
        });*/
    }
}
