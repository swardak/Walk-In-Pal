package com.example.mywalkinpal;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchByWhResultsActivity extends AppCompatActivity {

    //put the selected employee/clinic button thing here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_wh);




        Button apptBook = (Button) findViewById(R.id.appointmentBooking);

        apptBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchByWhResultsActivity.this, AppointmentBookingActivity.class));
            }
        });




        Button vWaitTimes = (Button) findViewById(R.id.waitTimes);
        vWaitTimes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showWaitTimesDialog(SearchByWhResultsActivity.this);
            }
        });

    }


    private void showWaitTimesDialog(Context c) {
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Current wait times for this clinic are:")
                //.setMessage((clinic.getBooking().waitTime()) + "minutes")
                .create();
        dialog.show();
    }
}
