package com.example.mywalkinpal;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class ViewProfileActivity extends AppCompatActivity {
    TextView address, name, number, insurance, payment;
    DatabaseReference dbUsers;
    FirebaseUser mUser;
    FirebaseAuth fbAuth;
    String insuranceStr,paymentStr;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        fbAuth = FirebaseAuth.getInstance();
        mUser = fbAuth.getCurrentUser();
        dbUsers = FirebaseDatabase.getInstance().getReference();

        address = (TextView)findViewById(R.id.viewAddress);
        name = (TextView)findViewById(R.id.viewName);
        number = (TextView)findViewById(R.id.viewPhoneNumber);
        insurance = (TextView)findViewById(R.id.viewInsurance);
        payment = (TextView)findViewById(R.id.viewPayment);

        if(mUser == null){
            finish();
        }

        else{
            dbUsers.child("Users").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Employee user = dataSnapshot.getValue(Employee.class);
                    address.setText(user.getAddress());
                    name.setText(user.getClinicName());
                    number.setText(user.getPhoneNumber());
                    int insuranceSize = user.getInsuranceTypesAccepted().size();
                    int paymentSize = user.getPaymentTypesAccepted().size();
                    insuranceStr = user.getInsuranceTypesAccepted().get(0);
                    for(int i=1;i<insuranceSize;i++){
                        insuranceStr = insuranceStr + ", "+ user.getInsuranceTypesAccepted().get(i);
                    }
                    paymentStr = user.getPaymentTypesAccepted().get(0);
                    for(int i=1;i<paymentSize;i++){
                        paymentStr = paymentStr + ", "+ user.getPaymentTypesAccepted().get(i);
                    }
                    insurance.setText(insuranceStr);
                    payment.setText(paymentStr);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
}
