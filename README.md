# UI_JupiterToys
BDD/Cucumber/IntelliJ/Maven/Java

Feature file : Tests.feature [3 test cases] 
- Contact page error validations
- Submit feedback
- Shop and verify the cart > price, subtotal(price*quantity), total(sum(subtotals))
  
Step Definition: PageStepDefinition.java  (all step difinitions)

Page file : Page.java (all the methods)

Object file : PageObjects.java (all the objects and locators)

pom.xml file holds all the project dependencies

Testdata file: TestDataProvider.java (test data for the test cases)

Screenshots folder: /bin/Debug/Screenshots (holds all the screenshots)

Note:
the jdk file should be installed in the pipeline agent inorder to work

Plugins to be installed in IntelliJ:
1. Cucumber for Java
2. Gherkin
3. Maven Helper
4. Selenium plugin
5. Substeps IntelliJ Plugin
