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
*   `BasePage.java`: The foundation for all page objects. It contains generic methods for interacting with WebElements (e.g., `clickWebElement`, `typeText`, `scrollToElement`) and handles common `WebDriverWait` conditions.
*   `BaseTest.java`: The foundation for all test classes. It manages the `WebDriver` lifecycle (`setUp` and `tearDown`), initializes `WebDriverWait`, and provides a default `login()` method.

### `pages`
Contains Page Object classes representing specific pages (e.g., `LoginPage`, `DashboardPage`, `CustomersPage`). These classes encapsulate locators and business-specific methods. Most methods use **Fluid Syntax** (returning `this` or a new page object) to allow method chaining in tests.

### `components`
Holds classes representing reusable UI components found across multiple pages, such as:
*   `LeftNavigationBar.java`: The main side navigation menu.
*   `TopBar.java`: The top navigation/action bar.

### `data`
Manages test data and configuration.
*   `Input.java`: Loads configuration from `config.properties`.
*   `User.java`: Model class for user credentials.

### `utils`
Contains utility classes for common tasks.
*   `CustomRandomStringGenerator.java`: Generates random names, emails, and passwords for dynamic test data.

### `pagetest` & `functionstest`
*   `pagetest`: Contains tests focused on individual page functionality and UI elements.
*   `functionstest`: Contains end-to-end functional tests and complex business flows (e.g., `CustomersTest.addCustomerTest`).

---

## Getting Started

### Prerequisites
*   Java Development Kit (JDK) 11 or higher.
*   Apache Maven.
*   Google Chrome browser.

### Setup
1.  **Clone the repository.**
2.  **Create the configuration file:** In the root directory, create `config.properties`:
    ```properties
    test.username=your_username
    test.password=your_password
    site.url=https://auto.pragmatic.bg/manage/
    expected.dashboard.username=Your Expected Username
    ```
3.  **Build the project:** Run `mvn clean install` or import into your IDE.

### Running Tests
*   **Via Maven:** `mvn clean test`
*   **From IDE:** Run individual test classes or methods.

---

## Key Features

*   **Page Object Model (POM):** Clean separation of concerns.
*   **Fluid Syntax / Method Chaining**: Enhances test readability and reduces boilerplate code.
*   **Component-Based Design:** High reusability of UI elements.
*   **Data-Driven:** Configuration is externalized for security and flexibility.
*   **Robust Dynamic Waits:** Centralized wait logic in `BasePage` to prevent flakiness.

---

## Writing New Tests

### Creating a New Page Object
1.  Extend `BasePage`.
2.  Add `@FindBy` locators.
3.  Initialize components in the constructor.
4.  **Use Fluid Syntax**: Ensure methods that perform actions return `this` (if staying on the same page) or the next `PageObject`.

### Creating a New Test Class
1.  Extend `BaseTest`.
2.  Use the `login()` method from `BaseTest` to start the flow.
3.  Chain actions using the fluid API and use TestNG `Assert` for validations.

*Framework By Momchil Slavov*
---
