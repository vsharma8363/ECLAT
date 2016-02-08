package fbla.hhs.eclat;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.*;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class Splash extends Activity {

    Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ref.setAndroidContext(this);
        ref = DataStorage.getRef();
        final Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                }
            }
        };


        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {

                    DataStorage.setUID(authData.getUid());
                    final Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    ref.child("users").child(DataStorage.getUID())
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
                    Timer t = new Timer();
                    TimerTask tt = new TimerTask() {
                        @Override
                        public void run() {
                            startActivity(i);
                        }
                    };
                    t.schedule(tt, 2000);
                } else {
                    final Intent i = new Intent(getApplicationContext(), Login.class);
                    Timer t = new Timer();
                    TimerTask tt = new TimerTask() {
                        @Override
                        public void run() {
                            startActivity(i);
                        }
                    };
                    t.schedule(tt, 2000);
                }
            }
        });



         timerThread.start();
     }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}