package com.example.mywalkinpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.telephony.PhoneNumberUtils.isGlobalPhoneNumber;

public class ManageWorkingHour extends AppCompatActivity {
    EditText MondayStart, MondayEnd,TuesdayStart, TuesdayEnd,WednesdayStart, WednesdayEnd,ThursdayStart, ThursdayEnd,FridayStart, FridayEnd,SaturdayStart, SaturdayEnd,SundayStart, SundayEnd;
    CheckBox Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
    Button saveButton;
    ArrayList<ArrayList<String>> workingHours;
    FirebaseAuth fbAuth;
    DatabaseReference dbUsers;
    FirebaseUser mUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_working_hour);
        fbAuth = FirebaseAuth.getInstance();
        mUser = fbAuth.getCurrentUser();
        dbUsers = FirebaseDatabase.getInstance().getReference("Users");
        saveButton = (Button) findViewById(R.id.modWh);

        MondayStart = (EditText) findViewById(R.id.entMondayStart);
        MondayEnd = (EditText) findViewById(R.id.entMondayEnd);
        TuesdayStart = (EditText) findViewById(R.id.entTuesdayStart);
        TuesdayEnd = (EditText) findViewById(R.id.entTuesdayEnd);
        WednesdayStart = (EditText) findViewById(R.id.entWednesdayStart);
        WednesdayEnd = (EditText) findViewById(R.id.entWednesdayEnd);
        ThursdayStart = (EditText) findViewById(R.id.entThursdayStart);
        ThursdayEnd = (EditText) findViewById(R.id.entThursdayEnd);
        FridayStart = (EditText) findViewById(R.id.entFridayStart);
        FridayEnd = (EditText) findViewById(R.id.entFridayEnd);
        SaturdayStart = (EditText) findViewById(R.id.entSaturdayStart);
        SaturdayEnd = (EditText) findViewById(R.id.entSaturdayEnd);
        SundayStart = (EditText) findViewById(R.id.entSundayStart);
        SundayEnd = (EditText) findViewById(R.id.entSundayEnd);

        Monday = (CheckBox) findViewById(R.id.checkBoxMonday);
        Tuesday = (CheckBox) findViewById(R.id.checkBoxTuesday);
        Wednesday = (CheckBox) findViewById(R.id.checkBoxWednesday);
        Thursday  = (CheckBox) findViewById(R.id.checkBoxThursday);
        Friday = (CheckBox) findViewById(R.id.checkBoxFriday);
        Saturday = (CheckBox) findViewById(R.id.checkBoxSaturday);
        Sunday = (CheckBox) findViewById(R.id.checkBoxSunday) ;

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    update();
                    startActivity(new Intent(ManageWorkingHour.this, ViewWorkingHour.class));
                    //finish();
                }
            }
        });

    }
    private void update(){

        if(mUser == null){
            finish();
        }

        else{
            workingHours = new ArrayList<>();
            if(Monday.isChecked()){
                ArrayList<String> Mon = new ArrayList();
                Mon.add(MondayStart.getText().toString());
                Mon.add(MondayEnd.getText().toString());
                workingHours.add(Mon);
            }else{
                ArrayList<String> Mon = new ArrayList();
                Mon.add("closed");
                Mon.add("closed");
                workingHours.add(Mon);
            }
            if(Tuesday.isChecked()){
                ArrayList<String> Tue = new ArrayList();
                Tue.add(TuesdayStart.getText().toString());
                Tue.add(TuesdayEnd.getText().toString());
                workingHours.add(Tue);
            }else{
                ArrayList<String> Tue = new ArrayList();
                Tue.add("closed");
                Tue.add("closed");
                workingHours.add(Tue);
            }
            if(Wednesday.isChecked()){
                ArrayList<String> Wed = new ArrayList();
                Wed.add(WednesdayStart.getText().toString());
                Wed.add(WednesdayEnd.getText().toString());
                workingHours.add(Wed);
            }else{
                ArrayList<String> Wed = new ArrayList();
                Wed.add("closed");
                Wed.add("closed");
                workingHours.add(Wed);
            }
            if(Thursday.isChecked()){
                ArrayList<String> Thur = new ArrayList();
                Thur.add(ThursdayStart.getText().toString());
                Thur.add(ThursdayEnd.getText().toString());
                workingHours.add(Thur);
            }else{
                ArrayList<String> Thur = new ArrayList();
                Thur.add("closed");
                Thur.add("closed");
                workingHours.add(Thur);
            }
            if(Friday.isChecked()){
                ArrayList<String> Fri = new ArrayList();
                Fri.add(FridayStart.getText().toString());
                Fri.add(FridayEnd.getText().toString());
                workingHours.add(Fri);
            }else{
                ArrayList<String> Fri = new ArrayList();
                Fri.add("closed");
                Fri.add("closed");
                workingHours.add(Fri);
            }
            if(Saturday.isChecked()){
                ArrayList<String> Sat = new ArrayList();
                Sat.add(SaturdayStart.getText().toString());
                Sat.add(SaturdayEnd.getText().toString());
                workingHours.add(Sat);
            }else{
                ArrayList<String> Sat = new ArrayList();
                Sat.add("closed");
                Sat.add("closed");
                workingHours.add(Sat);
            }
            if(Sunday.isChecked()){
                ArrayList<String> Tue = new ArrayList();
                Tue.add(SundayStart.getText().toString());
                Tue.add(SundayEnd.getText().toString());
                workingHours.add(Tue);
            }else{
                ArrayList<String> Sun = new ArrayList();
                Sun.add("closed");
                Sun.add("closed");
                workingHours.add(Sun);
            }
            dbUsers.child(mUser.getUid()).child("workingHours").setValue(workingHours);

        }
    }
    private Boolean validate(){
        Boolean ret = false;
        String MondayStartText = MondayStart.getText().toString();
        String MondayEndText = MondayEnd.getText().toString();
        String TuesdayStartText = TuesdayStart.getText().toString();
        String TuesdayEndText = TuesdayEnd.getText().toString();
        String WednesdayStartText = WednesdayStart.getText().toString();
        String WednesdayEndText = WednesdayEnd.getText().toString();
        String ThursdayStartText = ThursdayStart.getText().toString();
        String ThursdayEndText = ThursdayEnd.getText().toString();
        String FridayStartText = FridayStart.getText().toString();
        String FridayEndText = FridayEnd.getText().toString();
        String SaturdayStartText = SaturdayStart.getText().toString();
        String SaturdayEndText = SaturdayEnd.getText().toString();
        String SundayStartText = SundayStart.getText().toString();
        String SundayEndText = SundayEnd.getText().toString();

        if(Monday.isChecked() && (MondayStartText.isEmpty() || MondayEndText.isEmpty()) ){
            Toast.makeText(ManageWorkingHour.this, "Please fill out the working hours of Monday.", Toast.LENGTH_SHORT).show();
        }else if(Tuesday.isChecked() && (TuesdayStartText.isEmpty() || TuesdayEndText.isEmpty())) {
            Toast.makeText(ManageWorkingHour.this, "Please fill out the working hours of Tuesday.", Toast.LENGTH_SHORT).show();
        }else if(Wednesday.isChecked() && (WednesdayStartText.isEmpty() || WednesdayEndText.isEmpty())) {
            Toast.makeText(ManageWorkingHour.this, "Please fill out the working hours of Tuesday.", Toast.LENGTH_SHORT).show();
        }else if(Thursday.isChecked() && (ThursdayStartText.isEmpty() || ThursdayEndText.isEmpty())) {
            Toast.makeText(ManageWorkingHour.this, "Please fill out the working hours of Tuesday.", Toast.LENGTH_SHORT).show();
        }else if(Friday.isChecked() && (FridayStartText.isEmpty()|| FridayEndText.isEmpty())) {
        Toast.makeText(ManageWorkingHour.this, "Please fill out the working hours of Tuesday.", Toast.LENGTH_SHORT).show();
        }else if(Saturday.isChecked() && ( SaturdayStartText.isEmpty() || SaturdayEndText.isEmpty())) {
            Toast.makeText(ManageWorkingHour.this, "Please fill out the working hours of Tuesday.", Toast.LENGTH_SHORT).show();
        }else if(Sunday.isChecked() && (SundayStartText.isEmpty()|| SundayEndText.isEmpty())) {
            Toast.makeText(ManageWorkingHour.this, "Please fill out the working hours of Tuesday.", Toast.LENGTH_SHORT).show();
        }

        else{
            ret = true;
            Toast.makeText(ManageWorkingHour.this, "Valid", Toast.LENGTH_SHORT).show();
        }



        return ret;


    }
}
