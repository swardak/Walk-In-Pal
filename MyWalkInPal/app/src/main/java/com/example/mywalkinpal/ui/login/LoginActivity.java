package  com.example.mywalkinpal.ui.login;

import android.app.Activity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.NonNull;
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
import com.example.mywalkinpal.data.model.LoggedInUser;
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

import com.example.mywalkinpal.R;
import com.example.mywalkinpal.SignUpActivity;
import com.example.mywalkinpal.ui.login.LoginViewModel;
import com.example.mywalkinpal.ui.login.LoginViewModelFactory;
import com.google.firebase.auth.AuthResult;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private FirebaseAuth fbAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fbAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = fbAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(getApplicationContext(), "You are logged in!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,LoggedInUserView.class));

                }
                else{
                    Toast.makeText(getApplicationContext(), "Please log in!", Toast.LENGTH_SHORT).show();

                }

            }
        };


        final EditText usernameEditText = findViewById(R.id.emailField);
        final EditText passwordEditText = findViewById(R.id.passField);
        final Button loginButton = findViewById(R.id.LoginButton);
        final Button signUpButton = findViewById(R.id.SignUpButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eMail = usernameEditText.getText().toString();
                String pass = passwordEditText.getText().toString();

                if(eMail.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter an e-mail.", Toast.LENGTH_SHORT).show();

                }
                if(pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter a password.", Toast.LENGTH_SHORT).show();

                }

                if(eMail.isEmpty() && pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter an e-mail and password.", Toast.LENGTH_SHORT).show();

                }
                else if( !eMail.isEmpty() && !pass.isEmpty()){
                    fbAuth.signInWithEmailAndPassword(eMail,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Login Failed. Please try again.", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Success!.", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(LoginActivity.this, LoggedInUserView.class));

                            }
                        }
                    });

                }

               // loginViewModel.login(usernameEditText.getText().toString(),
               //         passwordEditText.getText().toString());
                
               // loginViewModel.login(usernameEditText.getText().toString(),
               //         passwordEditText.getText().toString());

                else{
                    Toast.makeText(getApplicationContext(), "An error occurred.", Toast.LENGTH_SHORT).show();

                }




                //loginViewModel.login(usernameEditText.getText().toString(),
                        //passwordEditText.getText().toString());
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
    }

   /* private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }*/

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fbAuth.getCurrentUser();
    }

}
