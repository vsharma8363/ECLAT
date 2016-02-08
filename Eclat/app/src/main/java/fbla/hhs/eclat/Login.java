package fbla.hhs.eclat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class Login extends AppCompatActivity {

    EditText Email;
    EditText Password;
    Intent Login;
    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        myFirebaseRef = DataStorage.getRef();
        setContentView(R.layout.activity_login);
        Password = (EditText) findViewById(R.id.PasswordLogin);
        Email = (EditText) findViewById(R.id.EmailLogin);
        Login = new Intent(this, MainActivity.class);


    }

    public void SubmitLogin(View view) {



        myFirebaseRef.authWithPassword(Email.getText().toString(),
                Password.getText().toString(), new Firebase.AuthResultHandler() {

                    @Override
                    public void onAuthenticated(AuthData authData) {

                        if(!DataStorage.hasUserData()) {
                            DataStorage.setUID(authData.getUid());
                            myFirebaseRef.child("users").child(DataStorage.getUID())
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            Map<String, Object> newUser = (Map<String, Object>) dataSnapshot.getValue();
                                            Log.d("E-Test", newUser.toString());
                                            DataStorage.setEmail(newUser.get("email").toString());
                                            DataStorage.setFullName(newUser.get("full_name").toString());
                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {

                                        }
                                    });
                        }

                      //  DataStorage.setEmail(Email.getText().toString());
                      startActivity(Login);
                       // startActivity(Login);

                    }
                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            //Create alert with firebaseError.getMessage()
                            Toast.makeText(Login.this, firebaseError.getMessage(),
                                    Toast.LENGTH_SHORT).show();





                        }

                    });
    }

    public void NoAccount(View view){
        Intent i = new Intent(this, Signup.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

public void ForgotAccount(View view){
    Intent i = new Intent(this, ResetPassword.class);
    startActivity(i);
}

}
