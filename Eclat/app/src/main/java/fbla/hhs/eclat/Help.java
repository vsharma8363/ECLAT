package fbla.hhs.eclat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Help extends AppCompatActivity {

    Button send;
    EditText subject, message;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        message = (EditText) findViewById(R.id.Message);

        subject = (EditText) findViewById(R.id.Subject);

        send = (Button) findViewById(R.id.button1);

        send.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Do stuff here
                SendEmail();
                startIntent();
                Log.d("MyApp", "Hi Vikram");
            }
        });
    }
    public void startDrawer(View view){
        Intent i = new Intent(this, NavDrawer.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void SendEmail(){

        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        String EclatTeam = "viksurf+eclatteam@gmail.com";

        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, EclatTeam);

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject.getText().toString());

        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message.getText().toString());

        startActivity(emailIntent);

        Toast.makeText(this, "An email has been sent to the Eclat Team about your issue",
                Toast.LENGTH_LONG).show();


    }

    public void startIntent(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
