# Book On Rails



### To configure the project 

Go to your MYSQL Server/Workbench and Open as Root user and run the following commands 

```sql
create database bookonrails; -- Creates the new database
create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
grant all on bookonrails.* to 'springuser'@'%'; -- Gives all privileges to the new user
```

Create a new file as application.properties in src/main/resources and add the following code

```properties
spring.application.name=ooad

server.port = 4000

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/BookOnRails
spring.datasource.username=springuser
spring.datasource.password=ThePassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

```

### To run the project

```bash
cd ooad
./mvnw spring-boot:run
```

### To run the project in the browser

```bash
http://localhost:4000
```
