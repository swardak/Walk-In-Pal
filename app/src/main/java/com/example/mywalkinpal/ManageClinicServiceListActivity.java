package com.example.mywalkinpal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class ManageClinicServiceListActivity extends AppCompatActivity {
    private Button addService;
    private FirebaseAuth fbAuth = FirebaseAuth.getInstance();
    DatabaseReference dbServices;
    DatabaseReference dbClinics = FirebaseDatabase.getInstance().getReference("Clinics").child(fbAuth.getUid());
    //dbClinics
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> serviceNames = new ArrayList<>();
    ArrayList<String> roleNames = new ArrayList<>();
    private static Module module = new Module();
    Boolean clicked = false;
    ArrayAdapter<String> arrayAdapter;
    int pos;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_clinic_service_list);

        addService = (Button) findViewById(R.id.addClinicServiceFromAdmin);

        dbServices = FirebaseDatabase.getInstance().getReference("Services");
        listView = (ListView) findViewById(R.id.addServiceToClinicListView);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,arrayList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position,convertView,parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(22);
                return view;
            }
        };

        this.listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(arrayAdapter);
        dbServices.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String role = dataSnapshot.getValue().toString();
                String service = dataSnapshot.getKey().toString();
                String serviceAndRole = "Service: " + service +"\nService Provider (Role): " + role;

                //Service serv = dataSnapshot.getValue(Service.class);
                //serviceNames.add(serv.getName());
                serviceNames.add(service);
                roleNames.add(role);
                arrayList.add(serviceAndRole);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                clicked = true;
                pos = position;
                module.setRole(roleNames.get(pos));
                module.setService(serviceNames.get(pos));

            }
        });

        addService.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!clicked){
                    Toast.makeText(ManageClinicServiceListActivity.this, "Please select a service to add.",Toast.LENGTH_SHORT).show();

                }

                else{
                    //NOW ADD THE STUFF TO THE DB
                    //FOLLOW THE STUFF FROM ADD SERVICE ACTIVITY
                    //SEND THE USER DATA AND FINISH
                    sendUserData();


                    //String serviceName = module.getService();
                    //String roleName = module.getRole();
                    Intent intent = new Intent(ManageClinicServiceListActivity.this, ViewClinicServiceListActivity.class);
                    //intent.putExtra("service_name", serviceName);
                    //intent.putExtra("service_role", roleName);

                    startActivity(intent);
                }
            }
        });




    }

    private void sendUserData(){
        final String serviceNameText = module.getService();
        final String roleNameText = module.getRole();

        dbClinics.child(serviceNameText).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(ManageClinicServiceListActivity.this, "Service is already added to clinic.", Toast.LENGTH_SHORT).show();
                }
                else{
                    dbClinics.child(serviceNameText).setValue(roleNameText);
                    Toast.makeText(ManageClinicServiceListActivity.this, "Service added to clinic.",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

