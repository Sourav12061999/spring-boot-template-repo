# **App -** Gpay1

## **Submission Instructions [Please note]**

## **Maximum Marks - 10**

| Sr. No. | Description | Marks |
| --- | --- | --- |
| 1 | Able to submit the app | 1 |
| 2 | Application launched without error | 1 |
| 3 | creating bean & Correct dependency injected for PaymentPresentation | 1 |
| 4 | creating bean & Correct dependency injected for PaymentService | 1 |
| 5 | creating bean & Correct dependency injected for AccountDetailsDAO | 1 |
| 6 | creating bean & Correct dependency injected for ProductDetailsDAO | 1 |
| 7 | creating bean of Account and Product | 1 |
| 8 | Correct implementation for payment amount includes showPayment and totalPayment methods    | 3 |
|  | Total | 10 |

### **Objective:**

1. **Dependency Injection Concepts:** To successfully solve the problem, you need to have a clear understanding of Dependency Injection and its benefits in reducing tight coupling between classes.
2. Implement annotation-based configuration to define the dependencies and inject the required beans.

### Installation

- Download and unzip the boilerplate and follow the following steps to import it into your IDE.
- Open the IDE(STS) → File → Import → Maven → Existing Maven Projects (double click on it).
- Click on the Browse… button and select the project template → next → finish.

### Boilerplate folder structure

- Please ignore all files except the mentioned one to use.
- Do not change the given folder structure.

```java
Gpay1
		├───src
		│   ├───main
		│   │   ├───java/Gpay
		│   │   │   │   Runner.java [Use]
		│   │   │   │   AppConfig.java [Use]
		│   │   │   ├───controller
		│   │   │   │       PaymentPresentation.java [Use]
		│   │   │   ├───dao
		│   │   │   │       AccountDetailsDAO.java [Use]
		│   │   │   │       ProductDetailsDAO.java [Use]
		│   │   │   ├───entities
		│   │   │   │       Account.java [Use]
		│   │   │   │       Product.java [Use]
		│   │   │   └───service
		│   │   │           PaymentService.java [Use]
		│   │   └───resources
		│   │           applications.properties [Use]
		│   └───test
		│       └───java
		│				TestCases.java [ignore]
		└───pom.xml [Do not modify]
```

# Problem Statement: -

### **Problem Description:**

Google Pay, a renowned digital payment platform, aims to optimize its transaction system by reducing tight coupling among the classes in its Java codebase. They have decided to leverage the Spring Framework and Dependency Injection with an annotation-based configuration to achieve this. Your task is to handle the dependencies among the classes using this approach and create an appropriate configuration class to define the necessary beans and wiring.

**Classes and Responsibilities:**

1. **`AccountDetailsDAO`**: This class is responsible for fetching account details from the database.
2. **`ProductDetailsDAO`**: This class is responsible for fetching product details from the database.
3. **`PaymentService`**: This class calculates transaction fees and updates the account balance.
4. **`PaymentPresentation`**: This class presents transaction details to the user.
5. **`Account`**: This class holds the account details of the user.
6. **`Product`**: This class has information about the product.

**Dependencies:**

- **`PaymentService`** depends on **`AccountDetailsDAO`** and **`ProductDetailsDAO`** to fetch account details and product information respectively.
- **`PaymentPresentation`** depends on **`PaymentService`** to obtain transaction details.
- **`AccountDetailsDAO`** depends on the **`Account`** bean.
- **`ProductDetailsDAO`** depends on the **`Product`** bean.

**Requirements:**

1. Implement Dependency Injection with annotation-based configuration in the Spring Framework to handle the dependencies among the classes.
2. Create an appropriate configuration class to define the beans for the classes and specify the appropriate wiring using annotations.
3. Include the necessary injection annotations in the **`PaymentService`** and **`PaymentPresentation`** classes for injecting the required beans.
4. Use constructor-based dependency injection to inject the required beans into the **`ProductDetailsDAO`** and **`AccountDetailsDAO`** classes.
5. The method to implement:
    1. The **`PaymentPresentation`** class should have a **`double showPayment(int quantity) throws PaymentException`** method that relies on the **`PaymentService`** class's **`double totalPayment(int quantity) throws PaymentException`** method.
    2. The **`totalPayment`** method in the **`PaymentService`** class should calculate the payment amount based on the product price fetched from **`ProductDetailsDAO`** and the account details fetched from **`AccountDetailsDAO`**.
        
        Note: you should have taken care of all possible scenarios for payment and throw **`PaymentException`** for illegal payment.
        
    3. Make both methods **`public`**.
    4. After calculating the payment, update all necessary data.

**Provided Bean Classes:**

- **`Product`**: Contains attributes **`productPrice`** (double), **`productName`** (String), and **`availableQuantity`** (int).
    - Use the following details while creating the **`Product`** bean.
        - **`productPrice`**: **9.99**
        - **`productName`**: **Sample Product**
        - **`availableQuantity`***:* **26**
- **`Account`**: Contains attributes **`accountBalance`** (double) and **`userName`** (String).
    - Use the following details while creating the **`Account`** bean.
        - **`accountBalance`**: **1000.00**
        - **`userName`**: **Joe Doe**

Your solution should use annotation-based configuration and annotation injection to handle the dependencies among the classes effectively.

### Common instructions for pom.xml

- pom.xml file is given along with the boilerplate code.
- Do not make any changes to the existing content of the **`pom.xml`** file.

### Test your application’s methods on your local system.

- From the main method of the Runner class, you can test your application’s methods in your local system. You can retrieve an object from the spring container and call the methods if you want.

### Check your application for a few sample test cases:

Steps to test your application:

- Run the maven project (refer to the below steps).
    - Right-click on the project → Run As → Maven test.
    refer to the image for more clarity: [**link**](https://drive.google.com/file/d/1jIr8BUPfdoJ-JB8oP2SBJTzJt7boUWZ5/view?usp=sharing)

### General guidelines

- The system on cp.masaischool.com may take between 1-20 minutes for responding.
- So, we request you to read the problem carefully and debug it before itself.
- We also request you not just submit it last minute.
- Try to keep one submission at a time.
- Use the template provided to write your code (Mandatory).