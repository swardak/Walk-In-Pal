package com.example.mywalkinpal;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class RateClinicActivity extends AppCompatActivity{
    Button saveButton;
    EditText comment;
    RadioButton oneStar, twoStar, threeStar, fourStar, fiveStar;
    RadioGroup rateRadio;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_clinic);

        saveButton = (Button) findViewById(R.id.commentButton);
        comment = (EditText) findViewById(R.id.entComment);
        oneStar = findViewById(R.id.rateOneStar);
        twoStar = findViewById(R.id.rateTwoStar);
        threeStar = findViewById(R.id.rateThreeStar);
        fourStar = findViewById(R.id.rateFourStar);
        fiveStar = findViewById(R.id.rateFiveStar);

    }
}
