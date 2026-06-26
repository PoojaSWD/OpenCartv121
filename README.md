# Place README.md in the root of your project (same level as pom.xml), then:
git add README.md
git commit -m "Add project README"
git push origin main

# 🛒 TutorialsNinja — Selenium Automation Framework

> End-to-end test automation for [tutorialsninja.com/demo](https://tutorialsninja.com/demo/) built with **Selenium 4 + Java + TestNG + Page Object Model (POM)**

---

## 📌 What This Project Covers

A fully structured automation framework that tests the complete purchase flow on the TutorialsNinja demo e-commerce site:

1. **Search** for a product (iPhone)
2. **View** the product detail page
3. **Add to Cart** and verify the success message
4. **Navigate to Cart** and validate cart contents
5. **Proceed to Checkout** and validate the stock warning alert:

```
Products marked with *** are not available in the desired quantity or not in stock!
```

---

## 🧰 Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 11+ | Programming language |
| Selenium WebDriver | 4.18.1 | Browser automation |
| TestNG | 7.9.0 | Test execution & assertions |
| WebDriverManager | 5.7.0 | Auto browser driver management |
| ExtentReports | 5.1.1 | HTML test reporting |
| Maven | 3.8+ | Build & dependency management |

---

## 📁 Project Structure

```
selenium-pom-automation/
│
├── pom.xml
│
└── src/
    ├── main/java/com/tutorialsninja/
    │   │
    │   ├── pages/                          # Page Object Model classes
    │   │   ├── BasePage.java               # Parent page — shared driver + helpers
    │   │   ├── HomePage.java               # Search bar & navigation
    │   │   ├── SearchResultsPage.java      # Search results listing
    │   │   ├── ProductDetailPage.java      # Product detail + Add to Cart
    │   │   ├── CartPage.java               # Shopping cart + stock alert
    │   │   └── CheckoutPage.java           # Checkout + stock warning validation
    │   │
    │   └── utils/                          # Utility / helper classes
    │       ├── DriverManager.java          # ThreadLocal WebDriver lifecycle
    │       ├── WaitUtils.java              # Explicit wait wrappers
    │       └── ConfigReader.java           # Reads config.properties
    │
    └── test/
        ├── java/com/tutorialsninja/tests/
        │   ├── BaseTest.java               # @BeforeMethod / @AfterMethod hooks
        │   └── SearchToCheckoutTest.java   # All 6 test cases (TC01–TC06)
        │
        └── resources/
            ├── config.properties           # Browser, URL, product config
            └── testng.xml                  # TestNG suite definition
```

---

## ✅ Test Cases

| Test ID | Test Name | Description |
|---|---|---|
| TC01 | `tc01_searchProductAndVerifyResults` | Search "iphone" → assert heading and result count |
| TC02 | `tc02_viewSearchedProduct` | Click first result → assert product title, URL, and price |
| TC03 | `tc03_addProductToCartAndVerifySuccess` | Add to cart → assert green success alert message |
| TC04 | `tc04_verifyProductInCart` | View cart → assert product name and cart total |
| TC05a | `tc05a_verifyStockWarningAlertWithHighQuantity` | Set qty=1000 → checkout → assert `***` stock alert **IS visible** |
| TC05b | `tc05b_verifyNoStockWarningForNormalQuantity` | Set qty=1 → checkout → assert stock alert **is NOT visible** |
| TC06 | `tc06_fullEndToEndFlow` | Full chained E2E flow covering all 5 phases |

---

## 🔑 Key XPath Locators

All XPaths are verified live against the actual site DOM.

```java
// Search input
//input[@name='search' and @class='form-control input-lg']

// Search button
//div[@id='search']//button[@type='button']

// First product in search results
(//div[@class='caption']//h4/a)[1]

// Add to Cart button on product page
//button[@id='button-cart']

// Success alert after adding to cart
//div[contains(@class,'alert-success')]

// ⭐ Stock warning alert on checkout (key validation)
//div[contains(@class,'alert-danger') and contains(.,'***')]

// Proceed to Checkout button
//a[contains(text(),'Checkout') and contains(@class,'btn-primary')]
```

---

## ⚙️ Prerequisites

Make sure the following are installed on your machine:

- [Java JDK 11+](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [Google Chrome](https://www.google.com/chrome/) (latest)
- Internet connection (WebDriverManager downloads ChromeDriver automatically)

> **No manual ChromeDriver download needed.** WebDriverManager handles it.

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/selenium-pom-automation.git
cd selenium-pom-automation
```

### 2. Configure test parameters

Edit `src/test/resources/config.properties`:

```properties
base.url=https://tutorialsninja.com/demo/
browser=chrome
search.product=iphone
```

> Change `browser=firefox` to run on Firefox instead of Chrome.

### 3. Run all tests

```bash
mvn test
```

### 4. Run a specific test class

```bash
mvn test -Dtest=SearchToCheckoutTest
```

### 5. Run a specific test method

```bash
mvn test -Dtest=SearchToCheckoutTest#tc05a_verifyStockWarningAlertWithHighQuantity
```

### 6. Run in headless mode (CI/CD)

Uncomment the headless line in `DriverManager.java`:

```java
chromeOptions.addArguments("--headless=new");
chromeOptions.addArguments("--no-sandbox");
chromeOptions.addArguments("--disable-dev-shm-usage");
```

---

## 📊 Test Reports

After a test run, TestNG generates an HTML report at:

```
target/surefire-reports/index.html
```

Screenshots on test failure are saved automatically to:

```
test-output/screenshots/<TestName>_FAILED_<timestamp>.png
```

---

## 🏗️ Framework Design

### Page Object Model (POM)

Each page of the website is represented by a dedicated class. Test logic is separated from UI interaction logic.

```
Test Class  →  Page Class  →  WebElement  →  Browser
```

### ThreadLocal WebDriver

`DriverManager` uses `ThreadLocal<WebDriver>` ensuring each test thread gets its own independent browser instance — safe for parallel execution.

### Explicit Waits

All interactions go through `WaitUtils`, which uses `WebDriverWait` with `ExpectedConditions` — no `Thread.sleep()` anywhere in the framework.

### Soft Assertions

Most test cases use TestNG `SoftAssert` so all assertions in a test are evaluated even if one fails, giving complete failure information in one run.

---

## 🔍 Stock Alert Validation Logic

The framework tests two scenarios for the stock warning:

```
Scenario A (TC05a) — High quantity (1000 units)
  Search → Product → Set qty=1000 → Add to Cart
  → Cart → Checkout
  → ASSERT: "Products marked with *** are not available..." IS visible

Scenario B (TC05b) — Normal quantity (1 unit)
  Search → Product → Add to Cart (qty=1)
  → Cart → Checkout
  → ASSERT: stock alert is NOT visible (happy path)
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/my-new-test`
3. Commit your changes: `git commit -m "Add test for XYZ page"`
4. Push to the branch: `git push origin feature/my-new-test`
5. Open a Pull Request

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 👤 Author

Built as a Selenium + Java + TestNG + POM automation framework demo for [tutorialsninja.com/demo](https://tutorialsninja.com/demo/).

---

> ⭐ If you found this project useful, give it a star on GitHub!
