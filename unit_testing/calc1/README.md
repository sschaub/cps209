# README

This folder contains an example of a class and a unit test. 

To build this project and run the unit tests, execute:
```
gradle build
```

To generate a report, execute:
```
gradle clean build jacocoTestReport
```
This produces two reports:
* **build/reports/tests/test/index.html** displays unit test pass/fail info
* **build/reports/jacoco/test/html/index.html** displays code coverage info

Key files in this project include:

* **src/main/java/Calculator.java** - class to be tested
* **src/test/java/CalculatorTest.java** - unit test
