# Project Architecture Overview

This document provides a detailed overview of the project's architecture, directory structure, and the role of key files and components.

## Directory Tree and Explanations

```
.
├── .gemini/
├── .github/
├── .gitignore
├── .mvn/
├── ARCHITECTURE.md
├── config.properties
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── base/
    │   │   │   ├── BasePage.java
    │   │   │   ├── BaseTest.java
    │   │   │   └── WebApp.java
    │   │   ├── components/
    │   │   │   ├── Components.java
    │   │   │   ├── HighBar.java
    │   │   │   ├── LeftNavigationBar.java
    │   │   │   ├── TopBar.java
    │   │   │   └── TopBarMain.java
    │   │   ├── data/
    │   │   │   ├── Description.java
    │   │   │   ├── Input.java
    │   │   │   ├── Slavov.java
    │   │   │   └── User.java
    │   │   └── pages/
    │   │       ├── CartPage.java
    │   │       ├── CheckoutPage.java
    │   │       ├── CustomersFormPage.java
    │   │       ├── CustomersPage.java
    │   │       ├── DashboardPage.java
    │   │       ├── LoginPage.java
    │   │       ├── MainPage.java
    │   │       ├── OnlineReportPage.java
    │   │       ├── OrderPage.java
    │   │       ├── OrdersPage.java
    │   │       ├── Product4$Page.java
    │   │       ├── ProductsFormPage.java
    │   │       ├── ProductsPage.java
    │   │       ├── SearchPage.java
    │   │       └── SuccessfulCheckout.java
    │   └── resources/
    │       └── log4j2.xml
    └── test/
        ├── java/
        │   ├── functionstest/
        │   │   ├── CustomersTest.java
        │   ├── ProductsTest.java
        │   └── SearchTest.java
        │   ├── pagetest/
        │   │   ├── DashboardTest.java
        │   │   ├── LeftNavigationBarTest.java
        │   │   ├── LoginTest.java
        │   │   ├── MainPageTest.java
        │   │   ├── OrdersPageTest.java
        │   │   └── ProductsPageTest.java
        │   ├── performance/
        │   │   └── PerformanceTests.java
        │   └── visual/
        │       └── VisualRegressionTest.java
        └── resources/
            ├── screenshots/
            ├── testng.xml
            └── visual/
                └── baseline/
```

## Explanations of Directories and Files

### Project Root (`.`)
*   **`.gemini/`**: IDE-specific configuration files for the Gemini environment.
*   **`.github/`**: Contains GitHub-specific configurations, primarily for GitHub Actions (CI/CD workflows).
*   **`.gitignore`**: Specifies intentionally untracked files and directories that Git should ignore (e.g., `target/`, `config.properties`).
*   **`.mvn/`**: Maven wrapper files, used to ensure consistent Maven execution across different development environments.
*   **`ARCHITECTURE.md`**: This document, detailing the project's structure and design principles.
*   **`config.properties`**: Externalized configuration properties, such as application URLs, user credentials, and file paths. This file is excluded from version control for security and environment-specific customization.
*   **`pom.xml`**: The Project Object Model file. It defines project dependencies (Selenium, TestNG, Log4j2, Allure, AShot), build plugins (Maven Surefire Plugin), and overall project structure for Maven.
*   **`README.md`**: Provides a high-level overview of the project, setup instructions, key features, and usage guide.

### `src/`
The root directory for all source code.

#### `src/main/`
Contains the main application source code.

*   **`src/main/java/`**: Java source files for the core framework components and Page Objects.
    *   **`src/main/java/base/`**: Core framework components that provide foundational functionality.
        *   **`BasePage.java`**: The base class for all Page Objects. It encapsulates common WebDriver interactions (e.g., `clickWebElement`, `typeText`, `isDisplayed`, `scrollToElement`) and utility methods that are reusable across different pages. It also holds references to the `WebDriver`, `WebDriverWait`, and `WebApp` instances.
        *   **`BaseTest.java`**: The base class for all test classes. It manages the `ThreadLocal` WebDriver lifecycle (setup and teardown), initializes the `WebApp` instance, handles automatic failure screenshots, and integrates Log4j2 for logging test execution. It also provides common login methods.
        *   **`WebApp.java`**: Implements the lazy initialization pattern for all Page Objects. It acts as a central hub, providing public methods to access any Page Object. Page Objects are instantiated only when they are first requested, improving performance and simplifying test setup.
    *   **`src/main/java/components/`**: Classes representing reusable UI components that appear on multiple pages (e.g., navigation bars, headers).
        *   **`Components.java`**: Represents common UI elements like search fields, cart icons, etc., typically found in the public-facing site's header.
        *   **`HighBar.java`**: Represents the top-most bar on the public-facing site, containing elements like currency or phone numbers.
        *   **`LeftNavigationBar.java`**: Represents the left-hand navigation menu found in the admin panel, used for navigating between different sections (e.g., Catalog, Customers, Sales).
        *   **`TopBar.java`**: Represents the top bar in the admin panel, often containing page titles and breadcrumbs.
        *   **`TopBarMain.java`**: Represents the main navigation bar on the public-facing site, containing links to product categories.
    *   **`src/main/java/data/`**: Classes for data models and input providers.
        *   **`Description.java`**: Provides static description text, potentially used for populating product descriptions or other text areas.
        *   **`Input.java`**: Manages reading configuration properties from `config.properties` and provides structured user data (e.g., `User` objects).
        *   **`Slavov.java`**: (Specific utility class, likely for a custom message or placeholder).
        *   **`User.java`**: A data model class representing a user, typically holding properties like username, password, site URL, and expected dashboard username.
    *   **`src/main/java/pages/`**: Page Object classes, each representing a distinct web page or a significant section of a web page.
        *   **`CartPage.java`**: Page Object for the shopping cart page.
        *   **`CheckoutPage.java`**: Page Object for the checkout process, handling shipping, payment, and order confirmation.
        *   **`CustomersFormPage.java`**: Page Object for the customer creation/edit form within the admin panel.
        *   **`CustomersPage.java`**: Page Object for the customer listing and management page in the admin panel.
        *   **`DashboardPage.java`**: Page Object for the admin panel's main dashboard, displaying summaries and widgets.
        *   **`LoginPage.java`**: Page Object for the application's login page.
        *   **`MainPage.java`**: Page Object for the public-facing main landing page of the application.
        *   **`OnlineReportPage.java`**: Page Object for the online users report page in the admin panel.
        *   **`OrderPage.java`**: Page Object for a specific order's detailed view in the admin panel.
        *   **`OrdersPage.java`**: Page Object for the orders listing and filtering page in the admin panel.
        *   **`Product4$Page.java`**: Page Object for a specific product detail page (e.g., a MacBook product page).
        *   **`ProductsFormPage.java`**: Page Object for the product creation/edit form within the admin panel.
        *   **`ProductsPage.java`**: Page Object for the product listing and management page in the admin panel.
        *   **`SearchPage.java`**: Page Object for the search results page on the public-facing site.
        *   **`SuccessfulCheckout.java`**: Page Object for the page displayed after a successful checkout.
*   **`src/main/resources/`**: Non-Java resources for the main application.
    *   **`log4j2.xml`**: Configuration file for Log4j 2, defining logging levels, formats, and appenders (e.g., console output, rolling file logs).

#### `src/test/`
Contains all test-related source code and resources.

*   **`src/test/java/`**: Java source files for the test classes.
    *   **`src/test/java/functionstest/`**: Contains end-to-end functional tests that cover complete user workflows and business logic.
        *   **`CustomersTest.java`**: Functional tests for customer creation, searching, and deletion.
        *   **`ProductsTest.java`**: Functional tests for product purchasing, cart management, and product administration (add/delete).
        *   **`SearchTest.java`**: Functional tests for the product search functionality.
    *   **`src/test/java/pagetest/`**: Contains page-level tests focusing on the functionality and elements of individual pages.
        *   **`DashboardTest.java`**: Tests for the various widgets and navigation links on the admin dashboard.
        *   **`LeftNavigationBarTest.java`**: Tests for the functionality and visibility of the left navigation bar in the admin panel.
        *   **`LoginTest.java`**: Tests for login scenarios, including successful login, unsuccessful attempts, and cookie management.
        *   **`MainPageTest.java`**: Tests for the display and basic interactions on the public-facing main page.
        *   **`OrdersPageTest.java`**: Tests for the orders listing page, including filtering options.
        *   **`ProductsPageTest.java`**: Tests for filtering and display on the product listing page in the admin panel.
    *   **`src/test/java/performance/`**: Contains performance-related tests, typically measuring response times for critical actions.
        *   **`PerformanceTests.java`**: Tests to measure the load time of key application pages or actions (e.g., login).
    *   **`src/test/java/visual/`**: Contains visual regression tests to ensure UI consistency.
        *   **`VisualRegressionTest.java`**: Tests that compare current UI screenshots against a set of baseline images to detect unintended visual changes.
*   **`src/test/resources/`**: Non-Java resources for tests.
    *   **`screenshots/`**: Directory where screenshots are automatically saved upon test failure.
    *   **`testng.xml`**: TestNG suite XML file, which defines which test classes or methods to run, their grouping (e.g., smoke, regression), and parallel execution settings.
    *   **`visual/`**: Resources specifically for visual regression testing.
        *   **`baseline/`**: Stores the approved baseline images against which current UI screenshots are compared during visual regression tests.
```