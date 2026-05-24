package framework.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Input {
    private List<User> users;
    private List<User> customers;

    private Properties props; // Store properties as a field

    public Input() {
        props = new Properties();
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

        customers = new ArrayList<>();
        customers.add(new User(
                props.getProperty("customer.test.username"),
                props.getProperty("customer.test.password"),
                props.getProperty("main.url"),
                null // No expected username for customer login
        ));
    }

    public User getUser(int index) {
        if (index >= 0 && index < users.size()) {
            return users.get(index);
        }
        else throw new IndexOutOfBoundsException("User index out of bounds: " + index);
    }

    public User getCustomer(int index) {
        if (index >= 0 && index < customers.size()) {
            return customers.get(index);
        }
        else throw new IndexOutOfBoundsException("Customer index out of bounds: " + index);
    }

    // New method to get URL from config.properties
    public String getUrl(String key) {
        String url = props.getProperty(key);
        if (url == null) {
            throw new IllegalArgumentException("URL key '" + key + "' not found in config.properties.");
        }
        return url;
    }
}