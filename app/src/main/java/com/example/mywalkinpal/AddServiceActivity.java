package com.example.mywalkinpal;

import android.app.Application;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddServiceActivity extends AppCompatActivity {

    private EditText serviceName,roleName;
    private Button addServiceButton;
    private DatabaseReference db;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        setupUIView();

        db = FirebaseDatabase.getInstance().getReference();

        addServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    sendUserData();
                    finish();

                }
                else{
                    Toast.makeText(AddServiceActivity.this, "Could not add service", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void setupUIView(){
        serviceName = (EditText) findViewById(R.id.entService);
        roleName = (EditText) findViewById(R.id.entRole);
        addServiceButton = (Button) findViewById(R.id.addService);
    }
    private boolean validate(){


        Boolean res = false;

        DatabaseReference dbServices = db.child("Services");
        String lookingFor = serviceName.getText().toString();

        dbServices.child(lookingFor).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(AddServiceActivity.this, "Service already exists", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        String serviceNameText = serviceName.getText().toString();
        String roleNameText = roleName.getText().toString();


        if(!validateEmptyField(serviceNameText, roleNameText)){
            Toast.makeText(AddServiceActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else if(serviceNameText.isEmpty()){
            Toast.makeText(AddServiceActivity.this, "Please enter a service name", Toast.LENGTH_SHORT).show();

        }
        else if(roleNameText.isEmpty()){
            Toast.makeText(AddServiceActivity.this, "Please enter a service provider type", Toast.LENGTH_SHORT).show();

        }
        // check if the service name already exists in the data base

        else{
            res = true;
        }


        return res;

    }

    protected static boolean validateEmptyField(String service, String role){
        boolean result = true;

        if(service.isEmpty() || role.isEmpty()){
            result = false;
        }
        return result;
    }

    private void sendUserData(){
        String serviceNameText = serviceName.getText().toString();
        String roleNameText = roleName.getText().toString();


        Service service = new Service(serviceNameText,roleNameText);
        db.child("Services").child(serviceNameText).setValue(roleNameText);

    }
}
