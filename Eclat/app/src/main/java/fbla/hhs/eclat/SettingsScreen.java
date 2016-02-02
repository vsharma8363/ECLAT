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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        Email = (EditText) findViewById(R.id.EmailSettings);
        FullName = (EditText) findViewById(R.id.FullNameSettings);
    }

    public void startDrawer(View view) {
        Intent i = new Intent(this, NavDrawer.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void SaveSettingsChanges(View view) {
        DataStorage.setEmail(Email.getText().toString());
        DataStorage.setFullName(FullName.getText().toString());
        Toast.makeText(this, "All changes have been saved",
                Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void ChangePassword(View view) {
        Intent i = new Intent(this, ResetPassword.class);
        startActivity(i);
    }

}
