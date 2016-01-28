package fbla.hhs.eclat;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;

public class StartUp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        Button Signup = (Button)findViewById(R.id.SignupHome);
        Button Login = (Button)findViewById(R.id.LoginHome);

    }

    public void SignupHome(View view){
        Intent i = new Intent(this, Signup.class);
        startActivity(i);
    }
    public void LoginHome(View view){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
