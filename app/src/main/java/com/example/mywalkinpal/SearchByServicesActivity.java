package com.example.mywalkinpal;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

import java.util.ArrayList;

public class SearchByServicesActivity extends AppCompatActivity{
    FirebaseAuth fbAuth;
    DatabaseReference dbUsers;
    Button goButton;
    EditText service;
    String serviceName;
    ListView serviceClinicList;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> clinicNames = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ArrayList<UserProfile> users = new ArrayList<>();
    ArrayList<String> uIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_services);

        dbUsers = FirebaseDatabase.getInstance().getReference("Users");
        goButton = (Button) findViewById(R.id.goSearchServices);
        service = (EditText) findViewById(R.id.entServicesName);
        serviceClinicList = (ListView) findViewById(R.id.searchServiceList);
        serviceName = service.toString();

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
        this.serviceClinicList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        serviceClinicList.setAdapter(arrayAdapter);



    /*    goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Query query = FirebaseDatabase.getInstance()
                        .getReference("Users")
                        .orderByChild("Services")
                        .equalTo(serviceName);
                query.addListenerForSingleValueEvent(valueEventListener);
            }
        });

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                if(dataSnapshot.exists
                        ()){
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        String employee = snapshot.getValue(Employee.class).toString();
                        arrayList.add(employee);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
       /* goButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dbUsers.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        String value = dataSnapshot.getValue(UserProfile.class).toString();
                        String uID = dataSnapshot.getKey();
                        UserProfile user = dataSnapshot.getValue(UserProfile.class);
                        if (user.getUserType().equals("Employee")){
                            Employee employee = dataSnapshot.getValue(Employee.class);
                            ArrayList<Service> services = employee.getServices();
                            if(services==null){

                            }else {
                                for(int i = 0;i< services.size();i++){
                                    if(services.get(i).getName().equals(serviceName)){
                                        users.add(employee);
                                        clinicNames.add(employee.getClinicName());
                                        uIDs.add(uID);
                                        arrayList.add("Hello");
                                        arrayAdapter.notifyDataSetChanged();

                                    }
                                }
                            }
                        }
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
            }
        });*/
    }
}
