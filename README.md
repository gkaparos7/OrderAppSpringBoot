# OrderAppSpringBoot

OrderAppSpringBoot is the Restful API component of OrderAppAndroid, a mobile application designed to streamline inventory management and facilitate the ordering process for businesses.

## Introduction

OrderAppSpringBoot is built to address the challenges faced by shop owners in manually compiling and organizing product orders. It is meant to be combined with OrderAppAndroid, providing a comprehensive solution for managing wishlists, orders, and order history.

## Features

### User Authentication (Jwt implementation):

Securely register and log in to the app, ensuring data privacy and user-specific functionality.

### Wishlist Management: 

Create and manage wishlists to keep track of products needed for future orders.

### Order Creation: 

Seamlessly transition from wishlists to orders, streamlining the process of generating and sending orders to suppliers.

### Order History: 

Access a comprehensive record of previous orders, facilitating easy reference and reordering.

## Getting Started

### Prerequisites:

Java Development Kit (JDK): Ensure that you have Java installed on your system. You can download it from Oracle JDK or use OpenJDK.

Integrated Development Environment (IDE): Use an IDE such as IntelliJ IDEA, Eclipse, or Visual Studio Code to import and work with the project.

MySQL Database: Install and configure a MySQL database server. Create a database and user for the Spring Boot application.

### Steps:

1. Clone the Repository:

  Use git clone to clone the repository to your local machine.

  git clone https://github.com/your-username/your-project.git

2. Open Project in IDE:
   

Open the project in your preferred IDE.

3. Configure Database Connection:

Locate the application.properties or application.yml file in the project's resources directory.

Update the database connection properties, including URL, username, and password.

spring.datasource.url=jdbc:mysql://localhost:3306/your-database

spring.datasource.username=your-username

spring.datasource.password=your-password

Ensure that the database specified in the configuration already exists. If not, create it before running the application.

4. Run the Application:

Run the main application class, typically annotated with @SpringBootApplication.

The DB schema will be auto-created and populated with some test data and users.

5. Verify Application Startup:

Check the console output for any errors or warnings.

Once the application starts without issues, you can then run the OrderAppAndroid to use in a friendly UI the endpoints provided by this API.

This is the version of the app, more functionallity and features will be added soon, so in the spring boot app there are already there some more endpoints than the OrderAppAndroid can use in the beggining.
