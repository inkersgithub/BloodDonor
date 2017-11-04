package in.inkers.blooddonor;

import com.google.android.gms.location.places.Place;

/**
 * Created by sreekkutty on 1/11/17.
 */

public class UserInformation {
    public String name;
    public String email;
    public String phone;
    public String blood;
    public Place place;

    public UserInformation(){

    }

    public UserInformation(String name, String email, String phone, String blood, Place place) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.blood = blood;
        this.place = place;
    }
}
