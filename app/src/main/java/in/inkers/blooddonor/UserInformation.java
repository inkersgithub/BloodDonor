package in.inkers.blooddonor;

/**
 * Created by sreekkutty on 1/11/17.
 */

public class UserInformation {
    public String name;
    public String email;
    public String phone;
    public String blood;

    public UserInformation(){

    }

    public UserInformation(String name, String email, String phone, String blood) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.blood = blood;
    }
}
