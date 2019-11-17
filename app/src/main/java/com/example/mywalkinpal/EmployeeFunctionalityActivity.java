package com.example.mywalkinpal;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mywalkinpal.ui.login.Employee;
import com.example.mywalkinpal.ui.login.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeFunctionalityActivity extends AppCompatActivity {
    FirebaseAuth fbAuth;
    DatabaseReference dbUsers;
    FirebaseUser mUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_functionality);

        fbAuth = FirebaseAuth.getInstance();
        mUser = fbAuth.getCurrentUser();
        dbUsers = FirebaseDatabase.getInstance().getReference();

        Button manageServiceBtn = (Button) findViewById(R.id.manegeServiceButton);
        Button profileBtn = (Button) findViewById(R.id.profileButton);
        Button workingHourBtn = (Button) findViewById(R.id.workingHourButton);



        profileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mUser == null){
                    finish();
                }

                else{
                    dbUsers.child("Users").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Employee user = (Employee) dataSnapshot.getValue(Employee.class);

                            if(user.getAddress() == null){
                                startActivity(new Intent(EmployeeFunctionalityActivity.this, ManageProfileActivity.class));

                            }
                            else{
                                startActivity(new Intent(EmployeeFunctionalityActivity.this, ViewProfileActivity.class));

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                startActivity(new Intent(EmployeeFunctionalityActivity.this, ManageProfileActivity.class));

            }
        });

    }
}
