package data;

public class User {
    private String username;
    private String password;
    private String siteURL;
    private String expectedDashboardUsername;

    //Constructor/Setter for the users I may use in my tests
    public User(String username, String password, String siteName, String expectedDashboardUsername) {
        this.username = username;
        this.password = password;
        this.siteURL = siteName;
        this.expectedDashboardUsername = expectedDashboardUsername;
    }

    //Getter for username
    public String getUsername() {
        return username;
    }

    //Getter for password
    public String getPassword() {
        return password;
    }

    //Getter for the site, that the user is registered in
    public String getSiteURL() {
        return siteURL;
    }

    //Getter for the expected username in the site
    public String getExpectedDashboardUsername() {
        return expectedDashboardUsername;
    }
}
