
# **Payment Gateway with Stripe and RazorPay Integration**

This project demonstrates how to integrate **Stripe** and **RazorPay** payment gateways into a Spring Boot application. The **PaymentGateway** service listens for payment requests after a product purchase is made in the **ProductCatalog** service. Once the product purchase is successful, the **PaymentGateway** processes the payment using either **Stripe** or **RazorPay**.

### **Project Structure**

- **PaymentGateway**: A Spring Boot service responsible for processing payments via **Stripe** and **RazorPay**.
- **ProductCatalog**: A Spring Boot service that handles product details and initiates purchase requests -- TODO.

---

### **Technologies Used**

- **Spring Boot**: Framework for building microservices.
- **Stripe**: Payment gateway for processing online payments.
- **RazorPay**: Payment gateway for handling online payments.

---

### **Architecture Overview**

1. **ProductCatalog Service** triggers a Kafka message once a product is purchased -- TODO.
2. **PaymentGateway Service** listens for these purchase events and processes the payment through **Stripe** or **RazorPay**.
3. **Stripe** and **RazorPay** are used to handle payment processing.

---

### **Stripe and RazorPay Setup in PaymentGateway Service**

#### 1. **Add Dependencies**

In `pom.xml`, add the necessary dependencies for **Stripe**, **RazorPay**
```xml

<dependency>
    <groupId>com.stripe</groupId>
    <artifactId>stripe-java</artifactId>
    <version>20.0.0</version>
</dependency>
<dependency>
    <groupId>com.razorpay</groupId>
    <artifactId>razorpay-java</artifactId>
    <version>2.1.1</version>
</dependency>
```

#### . **Configure API Keys in `application.properties`**

In `application.properties`, configure the API keys for **Stripe** and **RazorPay**.

```properties
# Stripe Configuration
stripe.api.key=sk_test_4eC39HqLyjWDarjtT1zdp7dc

# RazorPay Configuration
razorpay.api.key=rzp_test_YOUR_KEY
razorpay.api.secret=YOUR_SECRET_KEY

```


### **Service Flow**

1. **ProductCatalog Service** triggers a Kafka message with purchase details when a product is purchased -- TODO.
2. **PaymentGateway Service** listens to the `product-purchase` topic and processes the payment using **Stripe** or **RazorPay**.

---

### **Running the Application**

1. **Start PaymentGatewayService**:
   - Run the **PaymentGateway** service.

2. **Start ProductCatalogService**:
   - Run the **ProductCatalog** service.

3. **Test the Flow**:
   - Simulate a product purchase in **ProductCatalogService** (for example, through a REST API or a direct method call).
   - **PaymentGatewayService** will listen to the Kafka topic, consume the message, and process the payment using **Stripe** or **RazorPay** - TODO.

---

### **Summary**

- **PaymentGatewayService** processes payments using **Stripe** or **RazorPay** based on the incoming Kafka (TODO) messages triggered by product purchases.
- **ProductCatalogService** produces Kafka(TODO) messages when a product is purchased, which triggers payment processing in **PaymentGatewayService**.
- Kafka(TODO) provides an asynchronous communication mechanism between these services, ensuring decoupled processing and scalability.
