# JAX-RS Project

Our JAX-RS project is a Java web application that utilizes JPA to efficiently manage relational data and create
repositories for accessing the Sakila schema database through Restful API. The web services are deployed on Apache
Tomcat, a popular web server and servlet container, providing an easy and intuitive way for users to interact with the
Sakila database and retrieve the desired information.

Our application is built with Java 17 and Apache Maven 3.8.6, and uses MySQL for the database. To install and deploy the
project, clone the repository to your local machine and follow the instructions in the README file.

The project is equipped with a Postman documenter to facilitate API documentation, which is accessible at [DOCUMENTATION USING POSTMAN](https://documenter.getpostman.com/view/25978326/2s93Xu1QoA).

## Technologies Used:

- Java 17
- Apache Tomcat 10 - Version 10.1.7
- Apache Maven 3.8.6
- JAX-RS(Jersey)
- JSON-B
- JAX-B
- Lombok
- Model Mapper
- Jakarta persistance (Hibernate)
- MySQL
- Postman
- JUnit5

## Installation

To install the project, follow these steps:

- Clone the repository to your local machine.

```bash
  git clone https://github.com/karimtismail/Sakila-RestfulApi.git
```

- Navigate to the project directory.

```bash    
  cd Sakila-RestfulApi
```

- Build the project using Maven.

```bash
  mvn clean package
````

## Deployment

To deploy the project, follow these steps:

1. Open the `pom.xml` file located in the root directory of the project.

    ```xml
    <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
        <configuration>
            <username>{your username in mysql database}</username>
            <password>{your password in mysql database}</password>
            <url>http://localhost:{your port}/manager/text</url>
            <path>/sakila-api</path>
        </configuration>
    </plugin>
    ```

2. Replace `{your username in mysql database}` and `{your password in mysql database}` with your MySQL database
   credentials.
3. Replace `{your port}` with the port number you want to use for the web server (e.g. `8080`).
4. Save the `pom.xml` file.
5. Start Tomcat server.
6. execute ```mvn clean tomcat7:deploy```

## Usage/Examples

- Open a web browser and go to http://localhost:8080/sakila-restful/webapi/{actor}.
- The application should be up and running. You can now use it to interact with the Sakila database using REST
  web services.

## Sakila Database Information

Sakila is a sample database that provides a schema for a DVD rental store. The database includes tables for
customers, inventory, rentals, payments, staff, and stores. Here's a brief description of some of the tables: brief
overview of each table:

- **actor** - stores actor information such as first name and last name
- **address** - stores address information for customers and staff
- **category** - stores movie category information
- **city** - stores city information for customers and staff
- **country** - stores country information for customers and staff
- **customer** - stores customer information such as name and contact details
- **film** - stores movie information such as title, description, and length
- **film_actor** - stores information about the actors who played in each film
- **film_category** - stores information about which category each film belongs to
- **inventory** - stores information about each DVD that the rental store has in stock
- **language** - stores information about the languages available for films
- **payment** - stores information about customer payments
- **rental** - stores information about movie rentals, including the date and return date
- **staff** - stores staff information, such as name and contact details
- **store** - stores information about the stores, including location and manager
- **film_text** - stores the full text of the movie descriptions for searching purposes
