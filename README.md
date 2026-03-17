# Selenium Test Automation Framework

This is a Test Automation Framework built with Selenium, Java, and TestNG for automated testing of a web application. It follows the Page Object Model (POM) design pattern and is designed to be robust, maintainable, and scalable.

## Table of Contents
- [Core Technologies](#core-technologies)
- [Framework Architecture](#framework-architecture)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Setup](#setup)
  - [Running Tests](#running-tests)
- [Key Features](#key-features)
- [Writing New Tests](#writing-new-tests)

---

## Core Technologies

*   **Java**: The core programming language.
*   **Selenium WebDriver**: For browser automation.
*   **TestNG**: As the testing framework for assertions, test execution, and reporting.
*   **Maven**: For project dependency management and build automation.

---

## Framework Architecture

The framework is structured into several packages, each with a distinct responsibility:

### `base`
*   `BasePage.java`: The foundation for all page objects. It contains generic methods for interacting with WebElements (e.g., `clickWebElement`, `typeText`, `scrollToElement`) and handles common `WebDriverWait` conditions. This class is crucial for code reuse and keeping page objects clean.
*   `BaseTest.java`: The foundation for all test classes. It manages the `WebDriver` lifecycle (`setUp` and `tearDown` methods), initializes `WebDriverWait`, and provides a default `login()` method for convenience. All test classes should extend `BaseTest`.

### `pages`
This package contains all the Page Object classes. Each class represents a specific page or a major section of the application (e.g., `LoginPage`, `DashboardPage`, `ProductsPage`). These classes encapsulate the locators and business-specific methods for interacting with that page.

### `components`
This package holds classes that represent reusable UI components found across multiple pages, such as:
*   `LeftNavigationBar.java`: The main navigation menu on the admin side.
*   `TopBar.java`: The top bar in the admin panel.

Page objects are *composed* of these components, promoting reusability (e.g., a `DashboardPage` *has a* `LeftNavigationBar`).

### `data`
This package is responsible for managing test data.
*   `Input.java`: Reads test data (like usernames and passwords) from an external file.
*   `User.java`: A model class representing a user's credentials and expected data.
*   `config.properties`: **(Crucially, this file is in `.gitignore`)** This file stores sensitive data like usernames, passwords, and URLs. It is kept out of version control for security.

### `utils`
Contains utility classes for common tasks.
*   `CustomRandomStringGenerator.java`: A helper class to generate random data for tests, such as emails, names, and passwords.

### `pageTest`
This is where the actual TestNG test classes reside. Each test class typically corresponds to a page or a feature and contains `@Test` methods that define the test steps and assertions.

---

## Getting Started

### Prerequisites
*   Java Development Kit (JDK) 11 or higher.
*   Apache Maven.
*   An IDE like IntelliJ IDEA or Eclipse.
*   Google Chrome browser (as the default driver is `ChromeDriver`).

### Setup
1.  **Clone the repository.**
2.  **Create the configuration file:** In the root of the project, create a file named `config.properties`. This file is ignored by Git and must be created manually. Add the following content to it:
    ```properties
    test.username=your_username
    test.password=your_password
    site.url=https://auto.pragmatic.bg/manage/
    expected.dashboard.username=\   Your Expected Username
    ```
    *Note: The `\` before the spaces in `expected.dashboard.username` is important to preserve the leading whitespace.*
3.  **Build the project:** Open the project in your IDE and let Maven download all the required dependencies from the `pom.xml` file.

### Running Tests
You can run the tests in several ways:
*   **From your IDE:** Right-click on a test class or a specific `@Test` method and select "Run".
*   **Using TestNG XML:** Create a `testng.xml` file to define test suites and run them.
*   **Via Maven:** Run the command `mvn clean test` from the project root.

---

## Key Features

*   **Page Object Model (POM):** Clear separation between test logic and page interaction logic.
*   **Component-Based Design:** Reusable UI components reduce code duplication.
*   **Data-Driven:** Test data is externalized into a `config.properties` file for easy management and security.
*   **Robust Waits:** Explicit waits are centralized in `BasePage` to handle dynamic elements and avoid flaky tests.
*   **Scalable Structure:** The package structure makes it easy to add new pages, components, and tests.

---

## Writing New Tests

### Creating a New Page Object
1.  Create a new class in the `pages` package (e.g., `NewPage.java`).
2.  Make it extend `BasePage`.
3.  Add `@FindBy` locators for the elements on the page.
4.  If the page uses shared components, add them as public fields (e.g., `public LeftNavigationBar navBar;`).
5.  Create a constructor that calls `super(driver, wait)` and initializes any components.
6.  Write public methods that represent user actions on the page (e.g., `public void clickSubmitButton()`).

### Creating a New Test Class
1.  Create a new class in the `pageTest` package (e.g., `NewPageTest.java`).
2.  Make it extend `BaseTest`.
3.  Write `@Test` methods.
4.  Inside your test, use the page objects to interact with the application and TestNG's `Assert` class to verify outcomes.

*Framework By Momchil Slavov*
---
