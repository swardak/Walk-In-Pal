package com.example.mywalkinpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mywalkinpal.ui.login.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    Button logout;
    FirebaseAuth fbAuth;
    DatabaseReference dbUsers;
    TextView firstName;
    TextView userType;
    FirebaseUser mUser;



    //@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fbAuth = FirebaseAuth.getInstance();
        mUser = fbAuth.getCurrentUser();
        dbUsers = FirebaseDatabase.getInstance().getReference();
        firstName = (TextView) findViewById(R.id.userWelcome);
        userType =(TextView) findViewById(R.id.userType);

        if(mUser == null){
            finish();
        }

        else{
            dbUsers.child("Users").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserProfile user = dataSnapshot.getValue(UserProfile.class);

                    if(user.getUserFirstName().compareTo("Admin") == 0){
                        user.setUserType("Admin");
                    }

                    firstName.setText(user.getUserFirstName());
                    userType.setText(user.getUserType());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        //View rootView = inflater.inflate()
        Button btn = (Button) findViewById(R.id.nextButton);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(userType.getText().equals("Admin")) {
                    startActivity(new Intent(HomeActivity.this, AdminFunctionalityActivity.class));
                }else if(userType.getText().equals("Employee")){
                    startActivity(new Intent(HomeActivity.this, EmployeeFunctionalityActivity.class));
                }else{
                    startActivity(new Intent(HomeActivity.this, ClinicListActivity.class));
                }
            }
        });


    }
}
