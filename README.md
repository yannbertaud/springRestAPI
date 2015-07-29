# springRestAPI
an example of creating a Spring rest api in java

To run the rest api you need to run the following from the root of the project:

```mvn spring-boot:run```

from browser go to: ```http://localhost:8080/users```
this will return json list of users

to get count of users:

```http://localhost:8080/users/count```

to get a specific user:

```http://localhost:8080/users/1```



