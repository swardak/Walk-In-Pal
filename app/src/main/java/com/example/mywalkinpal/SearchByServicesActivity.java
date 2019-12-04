package com.example.mywalkinpal;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mywalkinpal.ui.login.Employee;
import com.example.mywalkinpal.ui.login.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchByServicesActivity extends AppCompatActivity{
    FirebaseAuth fbAuth;
    DatabaseReference dbUsers;
    Button goButton;
    EditText service;
    String serviceName;
    ListView serviceClinicList;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> clinicNames = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    ArrayList<UserProfile> users = new ArrayList<>();
    ArrayList<String> address = new ArrayList<>();
    ArrayList<String> numbers = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();

    Boolean clicked = false;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_services);

        dbUsers = FirebaseDatabase.getInstance().getReference("Users");
        goButton = (Button) findViewById(R.id.goSearchServices);
        service = (EditText) findViewById(R.id.entServicesName);
        serviceClinicList = (ListView) findViewById(R.id.searchServiceList);
        serviceName = service.getText().toString();

        //arrayList.add("Clinic 1");
        //arrayList.add("Clinic 2");

        goButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Query query = dbUsers.orderByChild("userType").equalTo("Employee");

                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            //arrayList.add(childSnapshot.toString());
                            //arrayList.add(childSnapshot.child("userType").toString());
                           //arrayList.add(childSnapshot.child("userType").getValue().toString());

                            String userType = childSnapshot.child("userType").getValue().toString();

                            if(userType.compareTo("Employee") == 0){
                                //arrayList.add(childSnapshot.child("Services").getValue().toString());
                                if(childSnapshot.child("Services").child(serviceName).exists()){
                                    arrayList.add("Clinic Name : " + childSnapshot.child("clinicName").getValue().toString()+"\nAddress : " + childSnapshot.child("address").getValue().toString() + "\nPhone Number : " + childSnapshot.child("phoneNumber").getValue().toString());
                                    address.add(childSnapshot.child("address").getValue().toString());
                                    names.add(childSnapshot.child("clinicName").getValue().toString());
                                    numbers.add(childSnapshot.child("phoneNumber").getValue().toString());

                                }


                            }


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };

                dbUsers.addListenerForSingleValueEvent(eventListener);

            }
        });





        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,arrayList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position,convertView,parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(22);
                return view;
            }
        };

        this.serviceClinicList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        serviceClinicList.setAdapter(arrayAdapter);

        serviceClinicList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                clicked = true;
                pos = position;
                Intent intent = new Intent(SearchByServicesActivity.this, ViewClinicInfoActivity.class);

                intent.putExtra("address", address.get(pos));
                intent.putExtra("phoneNumber", numbers.get(pos));
                intent.putExtra("name", names.get(pos));

                startActivity(new Intent(SearchByServicesActivity.this, AppointmentBookingActivity.class));

            }
        });

        
    }
}
