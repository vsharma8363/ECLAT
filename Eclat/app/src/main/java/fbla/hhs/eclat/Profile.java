package fbla.hhs.eclat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
    }
    public void startDrawer(View view){
        Intent i = new Intent(this, NavDrawer.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
    public void TakePicture(View view){
        //Intent i = new Intent(this, Camera.class);
        //   startActivity(i);
    }
    public void SaveChangesProfile(View view){

    }
}

