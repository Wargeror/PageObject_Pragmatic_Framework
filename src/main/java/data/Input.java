package data;
import java.util.ArrayList;
import java.util.List;

public class Input {
    private List<User> users;

    public Input() {
        users = new ArrayList<>();
        users.add(new User(
                "admin",
                "parola123!",
                "https://auto.pragmatic.bg/manage/",
                "   John Doe"));
    }

    public Input(String username, String password, String siteName, String expectedDashboardUsername){
        users.add(new User(username, password, siteName, expectedDashboardUsername));
    }

    public User getUser(int index) {
        if (index >= 0 && index < users.size()) {
            return users.get(index);
        }
        else throw new IndexOutOfBoundsException("User index out of bounds: " + index);
    }
}
