# Selenium Test Automation Framework

A robust, multi-threaded Selenium automation framework built with Java and TestNG, following the Page Object Model (POM) and Fluid Syntax design patterns.

## Table of Contents
- [Core Technologies](#core-technologies)
- [Framework Architecture](#framework-architecture)
- [Key Features](#key-features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Setup](#setup)
  - [Running Tests from Console](#running-tests-from-console)
- [Allure Reports](#allure-reports)
- [Visual Regression Testing](#visual-regression-testing)
- [Contribution Guide](#contribution-guide)

---

## Core Technologies

*   **Java 25**: Core programming language.
*   **Selenium WebDriver 4.40**: For browser automation.
*   **TestNG 7.12**: Testing framework for assertions and parallel execution.
*   **Maven**: Build and dependency management.
*   **AShot**: For advanced visual regression and full-page screenshots.
*   **Log4j 2**: For flexible and powerful logging.
*   **Allure Framework**: For generating rich, interactive test reports.

---

## Framework Architecture

### `base`
*   `BasePage.java`: Generic interaction methods (`click`, `type`, `scroll`).
*   `BaseTest.java`: Manages the `ThreadLocal` WebDriver lifecycle, automatic failure screenshots, and the `WebApp` instance.
*   `WebApp.java`: A central hub for accessing all page objects using a lazy initialization pattern. This improves performance and simplifies test setup.

### `pages`
Page Objects representing web pages (e.g., `LoginPage`, `DashboardPage`). Methods are designed with **Fluid Syntax** to allow chaining:
`webApp.loginPage().clickLoginButton();`

### `pagetest` & `functionstest`
*   `pagetest`: Unit-like tests for individual page elements.
*   `functionstest`: End-to-end functional flows (e.g., creating and deleting customers).

### `visual`
Contains `VisualRegressionTest.java` for image-based testing of the UI components.

### `utils`
*   `Utils.java`: Random data generation, screenshot capture, and browser animation freezing.

---

## Key Features

*   **Parallel Execution**: Run tests in multiple threads using `ThreadLocal` for 4x faster execution.
*   **Auto-Screenshots**: Screenshots are automatically saved to `resources/screenshots/` upon assertion failure.
*   **Visual Regression**: Compare current UI state against baseline images with automatic diff generation.
*   **Animation Control**: Custom JS injection to freeze CSS/JS animations for stable testing.
*   **Dynamic Data**: Built-in random string and email generators for unique test data.
*   **Centralized Configuration**: All URLs and sensitive data are managed in a `config.properties` file, which is excluded from version control via `.gitignore`.
*   **Descriptive Assertions**: All TestNG assertions include detailed failure messages to aid in debugging from logs.
*   **Lazy Page Initialization**: The `WebApp` class ensures that page objects are only created when they are first needed, reducing overhead.
*   **Advanced Logging**: Uses Log4j 2 to provide detailed, multi-level logging to both the console and a rolling log file (`logs/tests.log`).
*   **Interactive Test Reports**: Integration with Allure Framework for comprehensive and visually appealing test reports.

---

## Getting Started

### Prerequisites
*   JDK 25 or higher.
*   Maven installed and in System PATH.
*   Google Chrome browser.
*   Allure Command Line Interface (CLI) installed (for generating reports locally).

### Setup
1.  **Clone the project.**
2.  **Configuration**: Create `config.properties` in the root and populate it with the necessary key-value pairs. You can use `config.properties.example` as an example.

### Running Tests from Console
If you are using **PowerShell** (default in VS Code/IntelliJ), use double quotes for arguments:

*   **Standard Run (Sequential)**:
    `mvn clean test`
*   **Smoke Suite (Critical Path)**:
    `mvn clean test "-DsuiteXmlFile=smoke.xml"`
*   **Parallel Run (4 Threads)**:
    `mvn clean test "-DsuiteXmlFile=parallel.xml"`
*   **Full Regression**:
    `mvn clean test "-DsuiteXmlFile=regression.xml"`

---

## Allure Reports
To generate and view interactive Allure reports:

1.  **Run Tests**: Execute your tests using Maven. This will generate Allure results in the `target/allure-results` directory.
    ```bash
    mvn clean test
    ```
2.  **Generate and Serve Report**: Use the Allure CLI to generate the report and open it in your default browser.
    ```bash
    allure serve allure-results
    ```
    (Ensure you have the Allure CLI installed and configured in your system's PATH.)

---

## Visual Regression Testing
This framework uses **AShot**.
1.  **First Run**: If no baseline exists in `src/test/resources/visual/baseline/`, the test saves the current screen and fails.
2.  **Comparison**: Subsequent runs compare the screen to the baseline.
3.  **Failures**: Differences are saved as highlighted images in `target/visual-diffs/`.

---
*Framework developed by Momchil Slavov*
