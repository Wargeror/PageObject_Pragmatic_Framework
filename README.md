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
- [Visual Regression Testing](#visual-regression-testing)
- [Contribution Guide](#contribution-guide)

---

## Core Technologies

*   **Java 25**: Core programming language.
*   **Selenium WebDriver 4.40**: For browser automation.
*   **TestNG 7.12**: Testing framework for assertions and parallel execution.
*   **Maven**: Build and dependency management.
*   **AShot**: For advanced visual regression and full-page screenshots.

---

## Framework Architecture

### `base`
*   `BasePage.java`: Generic interaction methods (`click`, `type`, `scroll`).
*   `BaseTest.java`: Manages the `ThreadLocal` WebDriver lifecycle and automatic failure screenshots.

### `pages`
Page Objects representing web pages (e.g., `LoginPage`, `DashboardPage`). Methods are designed with **Fluid Syntax** to allow chaining:
`login().goToOrdersPage().clickFilterButton();`

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

---

## Getting Started

### Prerequisites
*   JDK 25 or higher.
*   Maven installed and in System PATH.
*   Google Chrome browser.

### Setup
1.  **Clone the project.**
2.  **Configuration**: Create `config.properties` in the root:
    ```properties
    test.username=your_username
    test.password=your_password
    site.url=https://auto.pragmatic.bg/manage/
    expected.dashboard.username=\   John Doe
    ```

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

## Visual Regression Testing
This framework uses **AShot**.
1.  **First Run**: If no baseline exists in `src/test/resources/visual/baseline/`, the test saves the current screen and fails.
2.  **Comparison**: Subsequent runs compare the screen to the baseline.
3.  **Failures**: Differences are saved as highlighted images in `target/visual-diffs/`.

---
*Framework developed by Momchil Slavov*
