package com.example.mywalkinpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModifyServiceActivity extends AppCompatActivity {
    EditText updateServ, updaterole,serviceName,roleName;
    DatabaseReference dbref;
    Module module;
    Button updateButton;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_service);

        updateServ = (EditText) findViewById(R.id.updateService);
        updaterole = (EditText) findViewById(R.id.updateRole);
        updateButton = (Button) findViewById(R.id.modService);
       // module = ( (Module)getApplicationContext());
        dbref = FirebaseDatabase.getInstance().getReference("Services");

        Intent intent = getIntent();

        updateServ.setText(intent.getStringExtra("service_name"));
        updaterole.setText(intent.getStringExtra("service_role"));

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupUIView();

                if(validate()){
                    update();
                    startActivity(new Intent(ModifyServiceActivity.this,ServiceListActivity.class));
                    finish();
                }
            }
        });


    }

    private void setupUIView(){
        serviceName = (EditText) findViewById(R.id.updateService);
        roleName = (EditText) findViewById(R.id.updateRole);
    }
    private boolean validate(){
        Boolean res = false;

        String serviceNameText = updateServ.getText().toString();
        String roleNameText = updaterole.getText().toString();

        if(serviceNameText.isEmpty() && roleNameText.isEmpty()){
            Toast.makeText(ModifyServiceActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else if(serviceNameText.isEmpty()){
            Toast.makeText(ModifyServiceActivity.this, "Please enter a service name", Toast.LENGTH_SHORT).show();

        }
        else if(roleNameText.isEmpty()){
            Toast.makeText(ModifyServiceActivity.this, "Please enter a service provider type", Toast.LENGTH_SHORT).show();

        }
        else{
            res = true;
        }


        return res;

    }
    private void update(){

        String serviceNameText = serviceName.getText().toString();
        String roleNameText = roleName.getText().toString();

        dbref.child(serviceNameText).removeValue();

        Service service = new Service(serviceNameText,roleNameText);
        dbref.child(serviceNameText).setValue(roleNameText);

    }

}
