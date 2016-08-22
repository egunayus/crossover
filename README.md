# crossover tech trial project
An air ticket reservation system by Erdem Günay.
 
Software design and solution is explained in detail in Software Design Document.pdf

Maven is used as the build manager for the project.

## Project sctructure
The project is configured to be a multi module maven project. At the root level techtrial/ is the parent pom module. Below there are three modules. 

+ techtrial/
	+ techtrial-domain/
	+ techtrial-api/
	+ tectrial-admin/

Important configuration files and folders are presented in the following picture
![Folder structure](/docs/img/folder_structure.png)
 

## Installation
The project has two executable package 

- techtrial-api-0.0.1-SNAPSHOT.jar
- techtrial-admin-0.0.1-SNAPSHOT.jar

### Prerequisites
Following are required to build and run the executables

+ java 8
+ maven 3.3+
+ mysql 5.6+ 

### Application configuration
Application is configured from several properties files. These files should be configured before proceeding

- techtrial/techtrial-domain/src/main/resources/<b>application.properties</b>
- techtrial/techtrial-domain/src/main/resources/<b>application-mysql-local.properties</b>
- techtrial/techtrial-domain/src/main/resources/<b>application-mail.properties</b>

##### techtrial-domain/ application.properties

```properties
application.properties

# Active Spring Profiles
spring.profiles.active=development, mysql-local, mail
```

developemnt and test profiles are used to load test data every time the application is started. 

##### techtrial-domain/ application-mysql-local.properties

<b>change</b> the database connection parameters, leave the other parameters unchanged. 

```properties
application-mysql-local.properties


# Mysql - local database connection properties

spring.datasource.url=jdbc:mysql://localhost:3306/ticket?profileSQL=false&useSSL=db-user
spring.datasource.username=db-user
spring.datasource.password=****
```

##### techtrial-domain/ application-mail.properties

<b>change</b> the smtp connection parameters if you want to use your favourite smtp server. if you want to use gmail, just change the username & password, leave the others unchanged

```properties
application-mail.properties

# spring mail configuration
spring.mail.host = smtp.gmail.com
spring.mail.username = egunayatr@gmail.com
spring.mail.password = ****
spring.mail.port = 465

spring.mail.properties.mail.smtp.ssl.enable = true
spring.mail.properties.mail.smtp.auth = true
spring.mail.properties.mail.smtp.socketFactory.port = 465
spring.mail.properties.mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory
spring.mail.properties.mail.smtp.socketFactory.fallback = false
```

### Google account 

You have to configure your Google application for authenticating the users via Google

Follow the steps mentioned in section 'Obtain OAuth 2.0 credentials' from following url
 
https://developers.google.com/identity/protocols/OpenIDConnect

Get your client_id and client_secret 

##### techtrial-api/ application.properties

change the Google client_id and client_secret obtained from previous step. 
if you want to test on your localhost following urls are already defined and accepted with my Google App, you can just use it without changing any thing.

- http://www.example.org

- http://localhost:8080

```properties
application.properties


# Google OAuth connection parameters
auth.google.client.id=387640011435-t6csd426r6j03sncvn5pbagg3gdr08ba.apps.googleusercontent.com
auth.google.client.secret=XnpZXbqC20VDTrT9XKG7BgeS
```

 

### Packaging executables


In order to generate the packages following comment should be executed in techtrial/ folder where the parent pom.xml file exists. 

mvn clean package


## Executing on local environment
Once the project is extracted and built, you can open the project modules with your favourite IDE (Eclipse, IntelliJ, etc.) and then just run following class as a Java Application 

com.crossover.techtrial.ApiApplicationMain


## Executing on AWS (or any other cloud)
The following files should be uploaded to the cloud server in the same folder, application configuration should be handled as explained in previous sections

- The executable JAR file (techtrial-api-0.0.1-SNAPSHOT.jar)
- application.properties
- application-mysql-local.properties
- application-mail.properties

The application can be run by this command

 java -jar techtrial-api-0.0.1-SNAPSHOT.jar
 
 If you have any questions, please do not hesitate getting in contact with me. 
 
 e.gunay@gmail.com