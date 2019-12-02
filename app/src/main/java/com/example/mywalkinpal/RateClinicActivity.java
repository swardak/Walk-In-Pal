package com.example.mywalkinpal;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mywalkinpal.ui.login.Employee;
import com.example.mywalkinpal.ui.login.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RateClinicActivity extends AppCompatActivity{
    Button saveButton;
    EditText comment;
    RadioButton selectedRadioButton;
    RadioGroup rateRadio;
    FirebaseAuth fbAuth;
    DatabaseReference dbUsers;
    FirebaseUser mUser;
    Rate rate;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_clinic);
        fbAuth = FirebaseAuth.getInstance();
        mUser = fbAuth.getCurrentUser();
        dbUsers = FirebaseDatabase.getInstance().getReference("Users");
        saveButton = (Button) findViewById(R.id.commentButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    sendUserData();
                }
            }
        });
    }
    private void sendUserData(){
        String newComment = comment.getText().toString();
        int newRate = 0;

        int radioId = rateRadio.getCheckedRadioButtonId();
        selectedRadioButton = findViewById(radioId);

        String rateStr = selectedRadioButton.getText().toString();
        if(rateStr.compareTo("rateOneStar")==0){
            newRate = 1;
        }
        else if(rateStr.compareTo("rateTwoStar")==0){
            newRate = 2;
        }
        else if(rateStr.compareTo("rateThreeStar")==0){
            newRate = 3;
        }
        else if(rateStr.compareTo("rateFourStar")==0){
            newRate = 4;
        }
        else if(rateStr.compareTo("rateFiveStar")==0){
            newRate = 5;
        }
        rate = new Rate(newRate, newComment);
        Intent intent = getIntent();
        String clinicUID = intent.getStringExtra("uid");
        dbUsers.child(clinicUID).child("rates").setValue(rate);
        Toast.makeText(RateClinicActivity.this, "Data sent to Database", Toast.LENGTH_SHORT).show();
    }

    private boolean validate(){

        RadioButton oneStar, twoStar, threeStar, fourStar, fiveStar;
        comment = (EditText) findViewById(R.id.entComment);
        oneStar = findViewById(R.id.rateOneStar);
        twoStar = findViewById(R.id.rateTwoStar);
        threeStar = findViewById(R.id.rateThreeStar);
        fourStar = findViewById(R.id.rateFourStar);
        fiveStar = findViewById(R.id.rateFiveStar);
        boolean result = false;

        if((!oneStar.isChecked()) && (!twoStar.isChecked()) && (!threeStar.isChecked())&& (!fourStar.isChecked())&&(!fiveStar.isChecked())){
            Toast.makeText(RateClinicActivity.this, "Please select a rating", Toast.LENGTH_SHORT).show();
        }

        else if(comment.toString().isEmpty()){
            Toast.makeText(RateClinicActivity.this, "Please enter a comment", Toast.LENGTH_SHORT).show();

        }
        else{
            result = true;
        }

        return result;
    }
}
