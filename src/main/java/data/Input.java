package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Input {
    private List<User> users;

    public Input() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("config.properties")) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties file.", e);
        }

        users = new ArrayList<>();
        users.add(new User(
                props.getProperty("test.username"),
                props.getProperty("test.password"),
                props.getProperty("site.url"),
                props.getProperty("expected.dashboard.username")
        ));
    }

    public User getUser(int index) {
        if (index >= 0 && index < users.size()) {
            return users.get(index);
        }
        else throw new IndexOutOfBoundsException("User index out of bounds: " + index);
    }
}
