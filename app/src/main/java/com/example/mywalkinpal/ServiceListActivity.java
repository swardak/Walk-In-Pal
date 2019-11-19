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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceListActivity extends AppCompatActivity {
    private Button addService;
    private Button deleteService;
    private Button modifyService;
    DatabaseReference dbServices;
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
        setContentView(R.layout.activity_service_list);

        addService = (Button) findViewById(R.id.addService);
        deleteService = (Button) findViewById(R.id.deleteService);
        modifyService = (Button) findViewById(R.id.modService);

        dbServices = FirebaseDatabase.getInstance().getReference("Services");
        listView = (ListView) findViewById(R.id.serviceListView);
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

        deleteService.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!clicked){
                    Toast.makeText(ServiceListActivity.this, "Please select an item to delete.",Toast.LENGTH_SHORT).show();

                }
                else{
                    final String selectedService = (String) serviceNames.get(pos);
                    dbServices.child(selectedService).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dbServices.child(serviceNames.get(pos)).removeValue();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Toast.makeText(ServiceListActivity.this, "Service Deleted!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ServiceListActivity.this,ServiceListActivity.class));
                }
                }

        });

        addService.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceListActivity.this,AddServiceActivity.class));
            }
        });

        modifyService.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!clicked){
                    Toast.makeText(ServiceListActivity.this, "Please select an item to modify.",Toast.LENGTH_SHORT).show();

                }

                else{
                    String serviceName = module.getService();
                    String roleName = module.getRole();
                    Intent intent = new Intent(ServiceListActivity.this, ModifyServiceActivity.class);
                    intent.putExtra("service_name", serviceName);
                    intent.putExtra("service_role", roleName);
                    startActivity(intent);
                }
            }
        });




    }
}
