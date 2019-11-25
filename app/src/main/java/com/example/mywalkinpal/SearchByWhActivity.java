package com.example.mywalkinpal;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SearchByWhActivity extends AppCompatActivity {
    CheckBox Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday, openEarly, openLate;
    EditText searchTime;
    Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_wh);

        Monday = (CheckBox) findViewById(R.id.selectMonday);
        Tuesday = (CheckBox) findViewById(R.id.selectTuesday);
        Wednesday = (CheckBox) findViewById(R.id.selectWednesday);
        Thursday  = (CheckBox) findViewById(R.id.selectThursday);
        Friday = (CheckBox) findViewById(R.id.selectFriday);
        Saturday = (CheckBox) findViewById(R.id.selectSaturday);
        Sunday = (CheckBox) findViewById(R.id.selectSunday) ;
        openEarly = (CheckBox) findViewById(R.id.selectOpenEarly) ;
        openLate = (CheckBox) findViewById(R.id.selectOpenLate) ;
        searchTime = (EditText) findViewById(R.id.entTime);

        goButton = (Button) findViewById(R.id.goSearchWh);
        goButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            }
        });
    }
}
