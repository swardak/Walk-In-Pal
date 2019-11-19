package com.example.mywalkinpal;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewProfileActivity extends AppCompatActivity {
    TextView viewAddress, viewName, viewNumber, viewInsurance, viewPayment;
    DatabaseReference dbref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        viewAddress = findViewById(R.id.viewAddress);
        viewName = findViewById(R.id.viewName);
        viewNumber = findViewById(R.id.viewPhoneNumber);
        viewInsurance = findViewById(R.id.viewInsurance);
        viewPayment = findViewById(R.id.viewPayment);

        dbref = FirebaseDatabase.getInstance().getReference("Users");
        Intent intent = getIntent();

        viewAddress.setText(intent.getStringExtra("address"));
        viewName.setText(intent.getStringExtra("clinicName"));
        viewNumber.setText(intent.getStringExtra("phoneNumber"));
        viewInsurance.setText(intent.getStringExtra("insuranceTypesAccepted"));
        viewPayment.setText(intent.getStringExtra("paymentTypesAccepted"));

    }
}
