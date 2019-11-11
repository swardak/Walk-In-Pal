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

import com.example.mywalkinpal.ui.login.LoginActivity;
import com.example.mywalkinpal.ui.login.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class AccountListActivity extends AppCompatActivity {
    FirebaseAuth fbAuth;
    DatabaseReference dbUsers;
    TextView firstName;
    TextView userType;
    FirebaseUser mUser;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<UserProfile> users = new ArrayList<>();
    ArrayList<String> uIDs = new ArrayList<>();

    ArrayAdapter<String> arrayAdapter;
    int pos;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        dbUsers = FirebaseDatabase.getInstance().getReference("Users");
        listView = (ListView) findViewById(R.id.acctListView);
        final Button deleteButton = findViewById(R.id.deleteUser);
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
        dbUsers.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(UserProfile.class).toString();
                String uID = dataSnapshot.getKey();
                UserProfile user = dataSnapshot.getValue(UserProfile.class);
                users.add(user);
                uIDs.add(uID);
                arrayList.add(value);
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

       deleteButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final UserProfile selectedUser = (UserProfile) users.get(pos);
               dbUsers.child(uIDs.get(pos)).addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       dbUsers.child(uIDs.get(pos)).removeValue();
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

               Toast.makeText(AccountListActivity.this, "Account Deleted!",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(AccountListActivity.this,AccountListActivity.class));
           }
       });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                pos = position;

            }
        });


    }

}
