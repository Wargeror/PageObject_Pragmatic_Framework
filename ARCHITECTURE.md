# Project Architecture Overview

This document provides a detailed overview of the project's architecture, directory structure, and the role of key files and components.

## Directory Tree and Explanations

```
.
├── .gemini/
├── .github/
│   └── workflows/
│       └── ci.yml
├── .gitignore
├── .mvn/
├── ARCHITECTURE.md
├── config.properties
├── pom.xml
├── README.md
└── src/
    └── test/
        ├── java/
        │   ├── framework/
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
        │   │   ├── pages/
        │   │   │   ├── AccountPage.java
        │   │   │   ├── CartPage.java
        │   │   │   ├── CheckoutPage.java
        │   │   │   ├── CustomerLoginPage.java
        │   │   │   ├── CustomersFormPage.java
        │   │   │   ├── CustomersPage.java
        │   │   │   ├── DashboardPage.java
        │   │   │   ├── LoginPage.java
        │   │   │   ├── MainPage.java
        │   │   │   ├── OnlineReportPage.java
        │   │   │   ├── OrderPage.java
        │   │   │   ├── OrdersPage.java
        │   │   │   ├── Product4SalePage.java
        │   │   │   ├── ProductsFormPage.java
        │   │   │   ├── ProductsPage.java
        │   │   │   ├── RegisteredPage.java
        │   │   │   ├── RegistrationPage.java
        │   │   │   ├── SearchPage.java
        │   │   │   └── SuccessfulCheckout.java
        │   │   └── utils/
        │   │       ├── LoginManager.java
        │   │       └── Utils.java
        │   ├── functionstest/
        │   │   ├── CustomersTest.java
        │   │   ├── ProductsTest.java
        │   │   └── SearchTest.java
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
            ├── log4j2.xml
            ├── screenshots/
            ├── testng.xml
            ├── parallel.xml
            ├── regression.xml
            ├── smoke.xml
            └── visual/
                └── baseline/
```

## Explanations of Directories and Files

### Project Root (`.`)
*   **`.gemini/`**: IDE-specific configuration files for the Gemini environment.
*   **`.github/workflows/ci.yml`**: GitHub Actions workflow file that automates the execution of parallel tests (CI/CD) on every push or pull request to the `main` branch.
*   **`.gitignore`**: Specifies intentionally untracked files and directories that Git should ignore (e.g., `target/`, `config.properties`).
*   **`.mvn/`**: Maven wrapper files, used to ensure consistent Maven execution across different development environments.
*   **`ARCHITECTURE.md`**: This document, detailing the project's structure and design principles.
*   **`config.properties`**: Externalized configuration properties, such as application URLs, user credentials, and file paths. This file is excluded from version control for security and environment-specific customization.
*   **`pom.xml`**: The Project Object Model file. It defines project dependencies (Selenium, TestNG, Log4j2, Allure, AShot), build plugins (Maven Surefire Plugin), and overall project structure for Maven.
*   **`README.md`**: Provides a high-level overview of the project, setup instructions, key features, and usage guide.

### `src/test/java/framework/`
Contains the core framework components, Page Objects, and utilities.

*   **`base/`**: Core framework components that provide foundational functionality.
    *   **`BasePage.java`**: The base class for all Page Objects. It encapsulates common WebDriver interactions (e.g., `clickWebElement`, `typeText`, `isDisplayed`, `scrollToElement`) and utility methods that are reusable across different pages. It also holds references to the `WebDriver`, `WebDriverWait`, and `WebApp` instances.
    *   **`BaseTest.java`**: The base class for all test classes. It manages the `ThreadLocal` WebDriver lifecycle (setup and teardown), initializes the `WebApp` instance, handles automatic failure screenshots, and integrates Log4j2 for logging test execution. It also provides common login methods for both admin and customer roles.
    *   **`WebApp.java`**: Implements the lazy initialization pattern for all Page Objects. It acts as a central hub, providing public methods to access any Page Object. Page Objects are instantiated only when they are first requested, improving performance and simplifying test setup.

*   **`components/`**: Classes representing reusable UI components that appear on multiple pages (e.g., navigation bars, headers).
    *   **`Components.java`**: Represents common UI elements like search fields, cart icons, etc., typically found in the public-facing site's header.
    *   **`HighBar.java`**: Represents the top-most bar on the public-facing site, containing elements like currency, phone numbers, and account login/registration links.
    *   **`LeftNavigationBar.java`**: Represents the left-hand navigation menu found in the admin panel, used for navigating between different sections (e.g., Catalog, Customers, Sales).
    *   **`TopBar.java`**: Represents the top bar in the admin panel, often containing page titles and breadcrumbs.
    *   **`TopBarMain.java`**: Represents the main navigation bar on the public-facing site, containing links to product categories.

*   **`data/`**: Classes for data models and input providers.
    *   **`Description.java`**: Provides static description text, potentially used for populating product descriptions or other text areas.
    *   **`Input.java`**: Manages reading configuration properties from `config.properties` and provides structured user data (e.g., `User` objects) for both admins and customers.
    *   **`Slavov.java`**: (Specific utility class, likely for a custom message or placeholder).
    *   **`User.java`**: A data model class representing a user, typically holding properties like username, password, site URL, and expected dashboard username.

*   **`pages/`**: Page Object classes, each representing a distinct web page or a significant section of a web page.
    *   **`AccountPage.java`**: Page Object for the customer's account dashboard after a successful user login.
    *   **`CartPage.java`**: Page Object for the shopping cart page.
    *   **`CheckoutPage.java`**: Page Object for the checkout process, handling shipping, payment, and order confirmation.
    *   **`CustomerLoginPage.java`**: Page Object specifically for the customer-facing login page.
    *   **`CustomersFormPage.java`**: Page Object for the customer creation/edit form within the admin panel.
    *   **`CustomersPage.java`**: Page Object for the customer listing and management page in the admin panel.
    *   **`DashboardPage.java`**: Page Object for the admin panel's main dashboard, displaying summaries and widgets.
    *   **`LoginPage.java`**: Page Object for the application's admin login page.
    *   **`MainPage.java`**: Page Object for the public-facing main landing page of the application.
    *   **`OnlineReportPage.java`**: Page Object for the online users report page in the admin panel.
    *   **`OrderPage.java`**: Page Object for a specific order's detailed view in the admin panel.
    *   **`OrdersPage.java`**: Page Object for the orders listing and filtering page in the admin panel.
    *   **`Product4SalePage.java`**: Page Object for a specific product detail page (e.g., a MacBook product page) meant for purchasing.
    *   **`ProductsFormPage.java`**: Page Object for the product creation/edit form within the admin panel.
    *   **`ProductsPage.java`**: Page Object for the product listing and management page in the admin panel.
    *   **`RegisteredPage.java`**: Page Object for the confirmation page displayed immediately after a successful customer registration.
    *   **`RegistrationPage.java`**: Page Object for the customer registration form on the public-facing site.
    *   **`SearchPage.java`**: Page Object for the search results page on the public-facing site.
    *   **`SuccessfulCheckout.java`**: Page Object for the page displayed after a successful checkout.

*   **`utils/`**: Utility classes for cross-cutting concerns.
    *   **`LoginManager.java`**: Handles advanced session management, drastically speeding up test execution by caching authentication cookies per thread (both in-memory and file-based persistence) to avoid full UI logins.
    *   **`Utils.java`**: Provides centralized, static methods for generating random valid data (names, emails, passwords), taking screenshots, and handling visual elements.

### `src/test/java/` (Tests)
Contains all test-related source code.

*   **`functionstest/`**: Contains end-to-end functional tests that cover complete user workflows and business logic.
    *   **`CustomersTest.java`**: Functional tests for customer creation, searching, and deletion. Utilizes **DataProviders** for extensive, data-driven negative testing of forms.
    *   **`ProductsTest.java`**: Functional tests for product purchasing, cart management, and product administration (add/delete).
    *   **`SearchTest.java`**: Functional tests for the product search functionality.
*   **`pagetest/`**: Contains page-level tests focusing on the functionality and elements of individual pages.
    *   **`DashboardTest.java`**: Tests for the various widgets and navigation links on the admin dashboard.
    *   **`LeftNavigationBarTest.java`**: Tests for the functionality and visibility of the left navigation bar in the admin panel.
    *   **`LoginTest.java`**: Tests for login scenarios, including successful login, unsuccessful attempts, and cookie management.
    *   **`MainPageTest.java`**: Tests for the display and basic interactions on the public-facing main page.
    *   **`OrdersPageTest.java`**: Tests for the orders listing page, including filtering options.
    *   **`ProductsPageTest.java`**: Tests for filtering and display on the product listing page in the admin panel.
*   **`performance/`**: Contains performance-related tests, typically measuring response times for critical actions.
    *   **`PerformanceTests.java`**: Tests to measure the load time of key application pages or actions (e.g., login).
*   **`visual/`**: Contains visual regression tests to ensure UI consistency.
    *   **`VisualRegressionTest.java`**: Tests that compare current UI screenshots against a set of baseline images to detect unintended visual changes.

### `src/test/resources/`
Non-Java resources for tests.

*   **`log4j2.xml`**: Configuration file for Log4j 2, defining logging levels, formats, and appenders (e.g., console output, rolling file logs). Placed here to ensure it is picked up during the test phase.
*   **`screenshots/`**: Directory where screenshots are automatically saved upon test failure.
*   **`testng.xml`**: The main TestNG suite XML file.
*   **`parallel.xml`**: TestNG suite configuration designed specifically for multi-threaded, parallel test execution.
*   **`regression.xml`**: TestNG suite configuration for a full regression run.
*   **`smoke.xml`**: TestNG suite configuration for a quick smoke test run.
*   **`visual/`**: Resources specifically for visual regression testing.
    *   **`baseline/`**: Stores the approved baseline images against which current UI screenshots are compared during visual regression tests.
