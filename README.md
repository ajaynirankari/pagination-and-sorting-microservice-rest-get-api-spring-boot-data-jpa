# pagination-and-sorting-microservice-rest-get-api-spring-boot-data-jpa

File - src/main/resources/application.properties
------------------------------------------------
```
#spring.datasource.url=jdbc:h2:file:/data/demo

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true

# Accessing the H2 Console
# http://localhost:8080/h2-console
spring.h2.console.enabled=true

# DataSource Initialization
# new file name - data.sql, under src/main/resources
spring.jpa.defer-datasource-initialization=true

```

Steps to Run Application
------------------------
1. Open Git Bash
2. git clone https://github.com/ajaynirankari/pagination-and-sorting-microservice-rest-get-api-spring-boot-data-jpa.git
3. cd pagination-and-sorting-microservice-rest-get-api-spring-boot-data-jpa
4. mvn spring-boot:run

Steps to Test Application
-------------------------
1. Open Git Bash
2. curl -v http://localhost:8080/employees | jq
```
$ curl -v http://localhost:8080/employees | jq
*   Trying 127.0.0.1:8080...
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /employees HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.78.0
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Mon, 20 Nov 2023 06:33:22 GMT
<
{ [489 bytes data]
100   482    0   482    0     0  85460      0 --:--:-- --:--:-- --:--:--  117k
* Connection #0 to host localhost left intact
[
  {
    "id": 1,
    "name": "Smith",
    "age": 39,
    "salary": 67000
  },
  {
    "id": 2,
    "name": "John",
    "age": 76,
    "salary": 17000
  },
  {
    "id": 3,
    "name": "Marry",
    "age": 46,
    "salary": 68000
  }
```

Test REST API via Swagger
-------------------------
URL: http://localhost:8080/swagger-ui/index.html
![image](https://github.com/ajaynirankari/pagination-and-sorting-microservice-rest-get-api-spring-boot-data-jpa/assets/26870634/6b02c212-d568-4d85-8872-fddae1725dc2)

Configuration for the Swagger-UI
--------------------------------
Reference Link: https://springdoc.org/

Add below dependency in pom.xml
```
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.2.0</version>
		</dependency>
```

