package com.example.mywalkinpal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
//import com.example.mywalkinpal.data.model.LoggedInUser;
//import com.example.mywalkinpal.ui.login.LoginActivity;
import com.example.mywalkinpal.ui.login.Employee;
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
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import android.content.Intent;
import java.security.MessageDigest;




public class SignUpActivity extends AppCompatActivity {

    private EditText userFN, userLN, userEmail, userPassword, userConfirmedPassword;
    private Button registerButton;
    private FirebaseAuth fbAuth;
    private RadioButton selectedRadioButton;
    private RadioGroup chooseUser;
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

    @NonNull
    private String getFirstName(){
       return userFN.getText().toString();
    }

    @NonNull
    private String getLastName(){
        return userLN.getText().toString();
    }

    @NonNull
    private String geteMail(){
        return userEmail.getText().toString();
    }

    @NonNull
    private String getPassword(){
        return userPassword.getText().toString();
    }

    @NonNull
    private String getConfirmedPassword(){
        return userConfirmedPassword.getText().toString();
    }

    private boolean validate(){

        RadioButton employee = findViewById(R.id.employeeButton);
        RadioButton patient = findViewById(R.id.patientButton);

        boolean result = false;

        if(!(validateNoEmptyFields(getFirstName(), getLastName(), geteMail(), getPassword(), getConfirmedPassword()))){
            Toast.makeText(SignUpActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        }

        else if(!(validatePasswordsMatch(getPassword(), getConfirmedPassword()))){
            Toast.makeText(SignUpActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();

        }

        else if(!(validatePasswordLength(getPassword()))){
            Toast.makeText(SignUpActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();

        }
        else if(!employee.isChecked() && !patient.isChecked()){
            Toast.makeText(SignUpActivity.this, "Please select an account type", Toast.LENGTH_SHORT).show();

        }

        else{
            result = true;
        }

        return result;
    }

    protected static boolean validateNoEmptyFields(String firstName, String lastName, String eMail, String password, String confirmedPassword){
        boolean result = true;

        if(firstName.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty() || lastName.isEmpty() || eMail.isEmpty()){
            result = false;
        }
        return result;
    }

    protected static boolean validatePasswordsMatch(String password, String confirmedPassword){
        boolean result = true;
        if (confirmedPassword.compareTo(password) != 0){
            result = false;
        }
        return result;
    }

    protected static boolean validatePasswordLength(String password){
        boolean result = true;
        if (password.length() < 8){
            result = false;
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
        String pass = userConfirmedPassword.getText().toString();
        String userTypestr;

        //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
       // FirebaseUser user = fbAuth.getCurrentUser();
        //DatabaseReference myRef = firebaseDatabase.getReference(fbAuth.getUid()).child("Users");

        chooseUser = findViewById(R.id.chooseUser);
        int radioId = chooseUser.getCheckedRadioButtonId();
        selectedRadioButton = findViewById(radioId);

        userTypestr = selectedRadioButton.getText().toString();
        if(userTypestr.compareTo("I'm an Employee")==0){
            userTypestr = "Employee";
        }
        else if(userTypestr.compareTo("I'm a Patient") == 0){
            userTypestr = "Patient";
        }

        String hashedPass = sha256(pass);

        UserProfile userProfile;
        if(userTypestr == "Employee"){
            userProfile = new Employee(firstName, lastName, eMail,userTypestr,hashedPass,null,null,null,null,null,null, null, null,null);

        }else{
            userProfile = new UserProfile(firstName, lastName, eMail,userTypestr,hashedPass);
        }
        mDatabase.child("Users").child(fbAuth.getUid()).setValue(userProfile);
        Toast.makeText(SignUpActivity.this, "Data sent to Database", Toast.LENGTH_SHORT).show();



    }

    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}


