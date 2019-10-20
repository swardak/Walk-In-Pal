package  com.example.mywalkinpal.ui.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mywalkinpal.R;
import com.example.mywalkinpal.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView extends AppCompatActivity {

    Button logout;
    //FirebaseAuth fbAuth;
    DatabaseReference dbUsers;
    EditText firstName;

    //@Override
    /*protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbUsers = FirebaseDatabase.getInstance().getReference("Users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();

        Query query = dbUsers.orderByChild("userFirstName").equalTo(userID);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    UserProfile user = dataSnapshot.getValue(UserProfile.class);
                    String name = user.getUserFirstName();
                    firstName = (EditText) findViewById(R.id.userWelcome);
                    firstName.setText(name);
                }
                else{
                    Toast.makeText(LoggedInUserView.this, "Can't find user!", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoggedInUserView.this, "Can't find user!", Toast.LENGTH_SHORT).show();

            }
        });







    }*/


}
