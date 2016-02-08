
 package fbla.hhs.eclat;

 import android.app.Activity;
 import android.app.AlertDialog;
 import android.app.Dialog;
 import android.app.DialogFragment;
 import android.content.DialogInterface;
 import android.content.Intent;
 import android.graphics.Typeface;
 import android.net.Uri;
 import android.os.Bundle;
 import android.provider.ContactsContract;
 import android.support.v4.content.IntentCompat;
 import android.view.View;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.firebase.client.Firebase;

 import fbla.hhs.eclat.Camera.CameraActivity;

 public class NavDrawer extends Activity {
  String name;
  String email;
  private DialogInterface.OnClickListener dialogListener;
  private static int currentScreen = 0; //0 = home, 1 = main, 2 = settings, 3 = help

  protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.navigation_bar);
   TextView namedisplay = (TextView) findViewById(R.id.NameDisplay);
   /**TextView HomeText = (TextView) findViewById(R.id.HomeText);
    TextView SearchText = (TextView) findViewById(R.id.SearchText);
    TextView profile = (TextView) findViewById(R.id.profile);
    TextView HelpText = (TextView) findViewById(R.id.HelpText);**/
   TextView emaildisplay = (TextView) findViewById(R.id.EmailDisplay);
   name = DataStorage.getFullName();
   email = DataStorage.getEmail();
   namedisplay.setText(name);
   emaildisplay.setText(email);

  }

  public void goHome(View view) {
   currentScreen = 0;
   Intent i = new Intent(this, MainActivity.class);
   startActivity(i);
   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
  }

  public void goSettings(View view) {
   currentScreen = 1;
   Intent i = new Intent(this, SettingsScreen.class);
   startActivity(i);
   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
  }

  public void goProfile(View view) {
   currentScreen = 2;
   Intent i = new Intent(this, CameraActivity.class);
   startActivity(i);
   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
  }

  public void goHelp(View view) {
   currentScreen = 3;
   Intent i = new Intent(this, Help.class);
   startActivity(i);
   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
  }

  public void goLogout(View view) {
    logout();

  }

  public void returnHome(View view){
   if(currentScreen == 0){
    Intent i = new Intent(this, MainActivity.class);
    startActivity(i);
    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
   }
   if(currentScreen == 1){
    Intent i = new Intent(this, SettingsScreen.class);
    startActivity(i);
    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
   }
   if(currentScreen == 2){
    Intent i = new Intent(this, CameraActivity.class);
    startActivity(i);
    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
   }
   if(currentScreen == 3){
    Intent i = new Intent(this, Help.class);
    startActivity(i);
    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
   }
  }


  public void logout(){
   Toast.makeText(getApplicationContext(), "You have been logged out",
           Toast.LENGTH_SHORT).show();
   DataStorage.setEmail(null);
   DataStorage.setFullName(null);
   DataStorage.setUID(null);
   DataStorage.setDataPath(null);
   Firebase ref = DataStorage.getRef();
   ref.unauth();
   Intent i = new Intent(getApplicationContext(), Login.class);
   startActivity(i);
  }

  /**
   * public void goBack(View view) {
   * if (currentScreen == 0) {
   * Intent i = new Intent(this, Mainactivity.class);
   * startActivity(i);
   * overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
   * } else if (currentScreen == 1) {
   * Intent i = new Intent(this, MainActivity.class);
   * startActivity(i);
   * overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
   * } else if (currentScreen == 2) {
   * Intent i = new Intent(this, SettingsScreen.class);
   * startActivity(i);
   * overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
   * } else if (currentScreen == 3) {
   * Intent i = new Intent(this, HelpScreen.class);
   * startActivity(i);
   * overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
   * }
   * }
   **/


 }