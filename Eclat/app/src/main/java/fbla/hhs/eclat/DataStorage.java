package fbla.hhs.eclat;

/**
 * Created by viksurf on 1/15/2016.
 */
public class DataStorage {

    private static String fullName, email, UID;

    public static void setEmail(String x){
        email = x;
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

