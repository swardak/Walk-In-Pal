package com.example.mywalkinpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
//import com.example.mywalkinpal.data.model.LoggedInUser;
//import com.example.mywalkinpal.ui.login.LoginActivity;
import com.example.mywalkinpal.ui.login.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import android.content.Intent;



public class SignUpActivity extends AppCompatActivity {

    private EditText userFN, userLN, userEmail, userPassword, userConfirmedPassword;
    private Button registerButton;
    private FirebaseAuth fbAuth;
    private RadioButton chooseUser;
    private int userType; // 0 for patient, 1 for employee
    DatabaseReference mDatabase;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupUIViews();

        fbAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onRadioButtonClicked(v);
                if (validate()){
                    String user_Email = userEmail.getText().toString().trim();
                    String user_Pass = userPassword.getText().toString().trim();
                    String first_Name = userFN.getText().toString().trim();
                    String last_name = userLN.getText().toString().trim();

                    fbAuth.createUserWithEmailAndPassword(user_Email, user_Pass).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                FirebaseUser user = fbAuth.getCurrentUser();
                                Toast.makeText(SignUpActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                                sendUserData();
                                finish();
                            }else{
                                Toast.makeText(SignUpActivity.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }
            }
        });

    }

    private void setupUIViews(){
        userFN = (EditText)findViewById(R.id.entFN);
        userLN = (EditText)findViewById(R.id.entLN);
        userEmail = (EditText)findViewById(R.id.entEmailField);
        userPassword = (EditText)findViewById(R.id.entPassField);
        userConfirmedPassword = (EditText)findViewById(R.id.entconfirmPassfield);
        registerButton = (Button)findViewById(R.id.SignUpButton);


    }

    private boolean validate(){

        Boolean result = false;

        String firstName = userFN.getText().toString();
        String lastName = userLN.getText().toString();
        String eMail = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        String confirmedPassword = userConfirmedPassword.getText().toString();

        if(firstName.isEmpty() && password.isEmpty() && confirmedPassword.isEmpty() && lastName.isEmpty() && eMail.isEmpty()){
            Toast.makeText(SignUpActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }

        else if(confirmedPassword.compareTo(password) != 0){
            Toast.makeText(SignUpActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();

        }

        else{
            result = true;
        }

        return result;


    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch ((view.getId())){
            case R.id.employeeButton:
                if(checked)
                   userType = 1;
                break;
            case R.id.patientButton:
                if(checked)
                    userType = 0;
        }
    }
    private void sendUserData(){
        String firstName = userFN.getText().toString();
        String lastName = userLN.getText().toString();
        String eMail = userEmail.getText().toString();
        //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
       // FirebaseUser user = fbAuth.getCurrentUser();
        //DatabaseReference myRef = firebaseDatabase.getReference(fbAuth.getUid()).child("Users");

        UserProfile userProfile = new UserProfile(firstName, lastName, eMail, "Patient");
        mDatabase.child("Users").child(fbAuth.getUid()).setValue(userProfile);
        Toast.makeText(SignUpActivity.this, "Data sent to Database", Toast.LENGTH_SHORT).show();



    }
}


