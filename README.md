# Registration System

This is a simple registration system with CRUD (Create, Read, Update, Delete) operations built using Java Servlets and MySQL.

## Project Description

The project allows users to register with their personal information (Name, Email, Date of Birth, Phone Number). It also supports updating and deleting user records.

---

## Technologies Used

- Java (Servlets)
- MySQL Database
- JDBC
- Git
- Maven
- DAO(Data Object Model) Pattern

---

## Folder Structure
/registration-system
    /WEB-INF
        /classes
            - DBConnection.java
            - Registration.java
            - RegistrationDAO.java
            - RegistrationDAOImpl.java
            - CreateServlet.java
            - ReadServlet.java
            - UpdateServlet.java
            - DeleteServlet.java
        /lib
        web.xml
    /index.jsp
    .gitignore
    README.md



## Setup Instructions
## Deploy to Tomcat
If you're using Apache Tomcat, place the project files inside the webapps directory of your Tomcat installation.
Start your Tomcat server.
Open a web browser and go to http://localhost:8080/RegistrationSystem/ to access the application.

### 1. Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/your-username/registration-system.git

