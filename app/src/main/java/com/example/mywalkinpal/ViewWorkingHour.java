package com.example.mywalkinpal;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mywalkinpal.ui.login.Employee;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewWorkingHour extends AppCompatActivity{
    TextView Monday, Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday;

    DatabaseReference dbUsers;
    FirebaseUser mUser;
    FirebaseAuth fbAuth;
    String insuranceStr,paymentStr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_working_hour);

        fbAuth = FirebaseAuth.getInstance();
        mUser = fbAuth.getCurrentUser();
        dbUsers = FirebaseDatabase.getInstance().getReference();

        Monday = (TextView)findViewById(R.id.textViewMondayWh);
        Tuesday = (TextView)findViewById(R.id.textViewTuesdayWh);
        Wednesday = (TextView)findViewById(R.id.textViewWednesdayWh);
        Thursday = (TextView)findViewById(R.id.textViewThursdayWh);
        Friday = (TextView)findViewById(R.id.textViewFridayWh);
        Saturday = (TextView)findViewById(R.id.textViewSaturdayWh);
        Sunday = (TextView)findViewById(R.id.textViewSundayWh);

        if(mUser == null){
            finish();
        }

        else{
            dbUsers.child("Users").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Employee user = dataSnapshot.getValue(Employee.class);
                    String mon, tues, wed, thurs, fri, sat, sun;
                    ArrayList<ArrayList<String>> workingHours = user.getWorkingHours();
                    if(workingHours.get(0).get(0).equals("closed")){
                        mon = "closed";
                    }else{
                        mon = workingHours.get(0).get(0)+ " - "  + workingHours.get(0).get(1);
                    }
                    if(workingHours.get(1).get(0).equals("closed")){
                        tues = "closed";
                    }else{
                        tues = workingHours.get(1).get(0)+ " - "  + workingHours.get(1).get(1);
                    }
                    if(workingHours.get(2).get(0).equals("closed")){
                        wed = "closed";
                    }else{
                        wed = workingHours.get(2).get(0)+ " - "  + workingHours.get(2).get(1);
                    }
                    if(workingHours.get(3).get(0).equals("closed")){
                        thurs = "closed";
                    }else{
                        thurs = workingHours.get(3).get(0)+ " - "  + workingHours.get(3).get(1);
                    }
                    if(workingHours.get(4).get(0).equals("closed")){
                        fri = "closed";
                    }else{
                        fri = workingHours.get(4).get(0) + " - " + workingHours.get(4).get(1);
                    }
                    if(workingHours.get(5).get(0).equals("closed")){
                        sat = "closed";
                    }else{
                        sat = workingHours.get(5).get(0) + " - "  + workingHours.get(5).get(1);
                    }
                    if(workingHours.get(6).get(0).equals("closed")){
                        sun = "closed";
                    }else{
                        sun = workingHours.get(6).get(0) + " - " + workingHours.get(6).get(1);
                    }

                    Monday.setText(mon);
                    Tuesday.setText(tues);
                    Wednesday.setText(wed);
                    Thursday.setText(thurs);
                    Friday.setText(fri);
                    Saturday.setText(sat);
                    Sunday.setText(sun);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
}
