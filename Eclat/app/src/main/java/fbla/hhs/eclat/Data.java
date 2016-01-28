package fbla.hhs.eclat;

/**
 * Created by nirav on 05/10/15.
 */
public class Data {

    private String description;

    private String imagePath;

    private String USERID;

    public Data(String imagePath, String description, String USERID) {
        this.imagePath = imagePath;
        this.description = description;
        this.USERID = USERID;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {return imagePath;}

    public String getUSERID() {return USERID;}

}
