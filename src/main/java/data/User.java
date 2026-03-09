package data;

public class User {
    private String username;
    private String password;
    private String siteURL;
    private String expectedDashboardUsername;

    public User(String username, String password, String siteName, String expectedDashboardUsername) {
        this.username = username;
        this.password = password;
        this.siteURL = siteName;
        this.expectedDashboardUsername = expectedDashboardUsername;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public String getExpectedDashboardUsername() {
        return expectedDashboardUsername;
    }
}
