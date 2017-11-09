package in.inkers.blooddonor;

/**
 * Created by sreekkutty on 9/11/17.
 */

public class ListItem {
    private String name,place;

    public ListItem(String name, String place) {
        this.name = name;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }
}
