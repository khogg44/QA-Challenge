# QA-Challenge

## Summary
A simple UI-automation framework written in Java and utilizing WebDriver with TestNG.

One test-case is included in CreateUserPublishWrapTest.java

## Description
This test-case automates the following steps:

1. Register for a personalized account

2. Create a wrap from a template

3. Publish the wrap

Originally I chose to use HtmlUnitDriver for simplicity. However, its handling of JS prevented correct rendering of the Wrap site, so FirefoxDriver was the next-best choice due to its low setup overhead compared to ChromeDriver

## Instructions
Maven and Java are required.  After cloning the project, execute the following command to run the test-suite:

> mvn test

## Sample Output
Running TestSuite

2016-09-11 08:09:25,051 INFO  - Test-case: Create Wrap user and publish a wrap using a template

2016-09-11 08:09:25,052 INFO  - 1. Go to the Wrap site

2016-09-11 08:09:53,939 INFO  - 2. Click free trial button

2016-09-11 08:10:07,343 INFO  - 3. Click sign up button in personal column

2016-09-11 08:10:08,295 INFO  - 4. Provide email and continue

2016-09-11 08:10:10,248 INFO  - 5. Click username suggestion button and make sure suggestion is filled in

2016-09-11 08:10:10,966 INFO  - 6. Enter the password and click the button to continue

2016-09-11 08:10:11,733 INFO  - 7. Fill in the rest of the account info, finalize the account and arrive on authoring page logged-in

2016-09-11 08:10:14,715 INFO  - 8. Create a new wrap from a template in 'Other' category and publish it

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 231.999 sec - in TestSuite

## Packages:
>In src/main/java/:

khogg.demos.qachallenge.pages.wrap
- HomePage.java
- LoginPage.java
- RegisterDialog.java
- SignupPage.java
- WrapsPage.java

khogg.demos.qachallenge.utils
- BrowserInteractions.java

>In src/test/java/:

khogg.demos.qachallenge
- AbstractUITest.java
- CreateUserPublishWrapTest.java