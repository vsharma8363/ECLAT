package fbla.hhs.eclat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class ResetPassword extends AppCompatActivity {

    EditText Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Email = (EditText) findViewById(R.id.EmailResetPassword);
    }

    public void passwordReset(View view){
        Firebase ref = new Firebase("https://eclat.firebaseio.com");
        ref.resetPassword(Email.getText().toString(), new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                makeToast("A password reset email has been sent to " + Email.getText().toString());
                startMainActivity();
                  }

            @Override
            public void onError(FirebaseError firebaseError) {
                makeToast("The account: " + Email.getText().toString() + ", does not exist");
            }
        });

    }

    public void makeToast(String s){
        Toast.makeText(this, s,
                Toast.LENGTH_LONG).show();
    }
    public void startMainActivity(){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
