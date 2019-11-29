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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewClinicServiceListActivity extends AppCompatActivity {
    private Button addService;
    private Button deleteService;

    DatabaseReference dbClinicServices;
    DatabaseReference dbClinicRates;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> serviceNames = new ArrayList<>();
    ArrayList<String> roleNames = new ArrayList<>();
    ArrayList<String> rates = new ArrayList<>();
    private static Module clinicModule = new Module();
    Boolean clicked = false;
    ArrayAdapter<String> arrayAdapter;
    int pos;

    private FirebaseAuth fbAuth = FirebaseAuth.getInstance();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clinic_service_list);

        addService = (Button) findViewById(R.id.addClinicService);
        deleteService = (Button) findViewById(R.id.deleteClinicService);

        dbClinicServices = FirebaseDatabase.getInstance().getReference("Users").child(fbAuth.getUid()).child("Services");
        dbClinicRates = FirebaseDatabase.getInstance().getReference("Users").child(fbAuth.getUid()).child("Rates");

        listView = (ListView) findViewById(R.id.clinicServiceListView);
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
        dbClinicServices.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String service = dataSnapshot.getKey().toString();

                String role = dataSnapshot.child("Role").getValue().toString();
                String rate = dataSnapshot.child("Rate").getValue().toString();
                //String
                String stringService = "Service: " + service +"\nService Provider (Role): " + role + "\nRate: " + rate;

                //Service serv = dataSnapshot.getValue(Service.class);
                //serviceNames.add(serv.getName());
                serviceNames.add(service);
                roleNames.add(role);
                rates.add(rate);

                arrayList.add(stringService);
                listView.setAdapter(arrayAdapter);


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
                clinicModule.setRole(roleNames.get(pos));
                clinicModule.setService(serviceNames.get(pos));
                clinicModule.setRate(rates.get(pos));

            }
        });

        deleteService.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!clicked){
                    Toast.makeText(ViewClinicServiceListActivity.this, "Please select an item to delete.",Toast.LENGTH_SHORT).show();

                }
                else{
                    final String selectedService = (String) serviceNames.get(pos);
                    dbClinicServices.child(selectedService).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dbClinicServices.child(serviceNames.get(pos)).removeValue();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Toast.makeText(ViewClinicServiceListActivity.this, "Service Deleted!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ViewClinicServiceListActivity.this,ViewClinicServiceListActivity.class));
                }
            }

        });

        addService.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(ViewClinicServiceListActivity.this,ManageClinicServiceListActivity.class));
            }
        });


        Button backBtn = (Button) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewClinicServiceListActivity.this, EmployeeFunctionalityActivity.class));
            }
        });

    }
}
