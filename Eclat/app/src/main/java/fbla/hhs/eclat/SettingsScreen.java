package fbla.hhs.eclat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import fbla.hhs.eclat.Camera.CameraActivity;

public class SettingsScreen extends AppCompatActivity {

    EditText Email;
    EditText FullName;
    EditText Password;
    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myFirebaseRef = DataStorage.getRef();
        setContentView(R.layout.activity_settings_screen);
        Email = (EditText) findViewById(R.id.EmailSettings);
        Password = (EditText) findViewById(R.id.PasswordSettings);
        FullName = (EditText) findViewById(R.id.FullNameSettings);
        Email.setText(DataStorage.getEmail());
        FullName.setText(DataStorage.getFullName());
    }

    public void startDrawer(View view) {
        Intent i = new Intent(this, NavDrawer.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void SaveSettingsChanges(View view) {
      if(Password.getText().toString() != null) {
          if (DataStorage.getEmail() != Email.getText().toString() || DataStorage.getFullName() != FullName.getText().toString()) {
              DataStorage.setEmail(Email.getText().toString());
              myFirebaseRef.changeEmail(DataStorage.getEmail(), Password.getText().toString(), Email.getText().toString(),
                      new Firebase.ResultHandler() {
                          @Override
                          public void onSuccess() {
                              DataStorage.setFullName(FullName.getText().toString());
                              Toast.makeText(getApplicationContext(), "All changes have been saved",
                                      Toast.LENGTH_LONG).show();
                              Intent i = new Intent(getApplicationContext(), MainActivity.class);
                              startActivity(i);
                          }

                          @Override
                          public void onError(FirebaseError firebaseError) {
                              Toast.makeText(getApplicationContext(), "There was an error, please try again later",
                                      Toast.LENGTH_LONG).show();
                          }
                      });
          } else {
              Toast.makeText(getApplicationContext(), "All changes have been saved",
                      Toast.LENGTH_LONG).show();
              Intent i = new Intent(getApplicationContext(), MainActivity.class);
              startActivity(i);
          }
      }
        else{
          Toast.makeText(getApplicationContext(), "Please enter your password",
                  Toast.LENGTH_LONG).show();
      }

    }

    public void ChangePassword(View view) {
        Intent i = new Intent(this, ResetPassword.class);
        startActivity(i);
    }

}
