package com.example.mywalkinpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class WorkingHourActivity extends AppCompatActivity{
    Button manageBtn, viewBtn;

    DatabaseReference dbUsers;
    FirebaseUser mUser;
    FirebaseAuth fbAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_hour);
        manageBtn = (Button) findViewById(R.id.manageWhButton);
        viewBtn = (Button) findViewById(R.id.viewWhButton);
        fbAuth = FirebaseAuth.getInstance();
        mUser = fbAuth.getCurrentUser();
        dbUsers = FirebaseDatabase.getInstance().getReference();

        manageBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkingHourActivity.this, ManageWorkingHour.class));
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dbUsers.child("Users").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Employee user = dataSnapshot.getValue(Employee.class);
                        String mon, tues, wed, thurs, fri, sat, sun;
                        ArrayList<ArrayList<String>> workingHours = user.getWorkingHours();
                        if (workingHours == null) {
                            startActivity(new Intent(WorkingHourActivity.this, WorkingHourActivity.class));

                        }
                        else{
                            startActivity(new Intent(WorkingHourActivity.this, ViewWorkingHour.class));

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
