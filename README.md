# Spring Cloud Contract Tutorial

_A lunch-sized tutorial on consumer driven contracts with [Spring Cloud Contract](https://cloud.spring.io/spring-cloud-contract/)._

## The Scenario

This tutorial walks through the creation of a set of small event-driven customer onboarding services.  The `customer-service` is a simple CRUD service that let's us create and retrieve some customer information.  The `greeting-service` needs to be able to pull customer information from the service to send out a friendly greeting email to the customer (we'll simulate the email in this case for simplicity).

We'll start by writing a contract for the info we need to pull from customer service in order to send our email.  Once we have the greeting-service testing a polling-based implementation, we'll write a messaging contract to let the customer notify us whenever there's a new customer to email.

## Using This Tutorial

Each step of this tutorial lists a "starting branch" in this repo, and you can check this branch out to start at the beginning of that step at any point.  The final project can be viewed on the `completed` branch.

Both projects have beens started here, based on the output of the Spring Initializer.  The `customer-service` project already has a customer data model defined, some in-memory storage of customers, and the ability to add new customers using the provided `POST` endpoint.  You can think of this as the existing functionality of the customer service before the `greeting-service` was built.

### Step 1:  Add Spring Cloud Contract to the projects
Starting Branch: `step-1-dependencies`

### Step 2:  Write a REST API contract for `GET /customers`
Starting Branch: `step-2-rest-contract`

### Step 3:  Write a test for the customer greeting feature
Starting Branch: `step-3-polling-test`

### Step 4:  Implement the customer greeting feature
Starting Branch: `step-4-polling-feature`

### Step 5:  Implement the `GET /customers` endpoint
Starting Branch: `step-5-get-customers`

### Step 6:  Write a contract for the `newCustomer` message
Starting Branch: `step-6-message-contract`

### Step 7:  Write a test for handling `newCustomer` messages
Starting Branch: `step-7-message-test`

### Step 8:  Implement the event-based greeting feature
Starting Branch: `step-8-message-feature`

### Step 9:  Implement publishing of `newCustomer` messages
Starting Branch: `step-9-publish-message`

## Resources

[Supporting Slides](TODO)
[Spring Cloud Contract website](https://cloud.spring.io/spring-cloud-contract/)


