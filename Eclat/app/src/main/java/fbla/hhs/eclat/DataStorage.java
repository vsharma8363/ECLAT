package fbla.hhs.eclat;

import com.firebase.client.Firebase;

/**
 * Created by viksurf on 1/15/2016.
 */
public class DataStorage {

    private static Firebase ref;

    private static String fullName, email, UID, DataPath;

    public static void setEmail(String x){
        email = x;
    }
    public static void setDataPath(String x){
        DataPath = x;
    }
    public static void setUID(String x){
        UID = x;
    }
    public static void setFullName(String x){
        fullName = x
        ;
    }
    public static String getEmail(){
        return email;
    }
    public static Firebase getRef(){
        if(ref == null){
            ref = new Firebase("https://eclat.firebaseio.com/");
        }
        return ref;
    }
    public static String getDataPath(){
        return DataPath;
    }
    public static String getFullName(){
        return fullName;
    }
    public static String getUID(){
        return UID;
    }
    public static boolean hasUserData(){
        return fullName != null &&
                email != null && !fullName.trim().equals("")
                && !email.trim().equals("");
    }
}

