package com.example.mywalkinpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mywalkinpal.ui.login.Employee;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ViewClinicInfoActivity extends AppCompatActivity {
    String clinicUID;
    TextView address, name, number, insurance, payment, mon, tues, wed, thur, fri, sat, sun;
    String insuranceStr,paymentStr;
    Button rate;
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clinic_info);

        Intent intent = getIntent();

        address = (TextView)findViewById(R.id.patientViewAddress);
        name = (TextView)findViewById(R.id.patientViewName);
        number = (TextView)findViewById(R.id.patientViewPhoneNumber);
        insurance = (TextView)findViewById(R.id.patientViewInsurance);
        payment = (TextView)findViewById(R.id.patientViewPayment);

        mon = (TextView)findViewById(R.id.patientViewMondayWh);
        tues = (TextView)findViewById(R.id.patientViewTuesdayWh);
        wed = (TextView)findViewById(R.id.patientViewWednesdayWh);
        thur = (TextView)findViewById(R.id.patientViewThursdayWh);
        fri = (TextView)findViewById(R.id.patientViewFridayWh);
        sat = (TextView)findViewById(R.id.patientViewSaturdayWh);
        sun = (TextView)findViewById(R.id.patientViewSundayWh);

        address.setText(intent.getStringExtra("address"));
        name.setText(intent.getStringExtra("name"));
        number.setText(intent.getStringExtra("phoneNumber"));

        rate = findViewById(R.id.rateButton);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewClinicInfoActivity.this, RateClinicActivity.class);
                intent.putExtra("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                startActivity(intent);
            }
        });


    }
}
