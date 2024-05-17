# SSS Internship
Knowledge and Information gained and used into making a project that will aid whenever working with Springboot.
This was created while doing an intern at Solarus Smart Solutions (SSS).



# Timeline
1. Learning SOLID Principles and Agile workflow
2. Learning what is Spring MVC and Spring Boot
3. Learning about Spring Boot and its annotations
4. Making a simple User CRUD while taking the SOLID Principles into consideration
   - Clean code
   - Dividing Files into Entities, DTOs, Repositories, Services, Controller, Security, Configurations and Exceptions
6. Storing data on the in-memory database H2
7. Using Postman for Api requests testing
8. Using Lombok to minimize/remove the boilerplate code and save time and have cleaner code
9. Creating a Mapper to map Entities to DTOs and vice versa
10. Changing database to MySQL
11. Implementing JWT for authentication and authorization
12. Changing to Swagger instead of Postman
13. Adding Errors and Exceptions codes and messages for easier debugging
14. Creating unit tests for my microservice
15. Creating another microservice (orders) and joining it to the CRUD microservice
16. Connecting two microservices using Eureka for communication
17. Caching items using Redis
18. Getting trace ID
19. Creating an Audit Trail (Logback) for changes that happen in MySQL and store the log in MongoDB
20. Creating custom annotation and composite validation
21. Dockerizing the project
22. Making a Docker Swarm for the project


#### Notes
1. Project running on server port 8092
2. In application.propeties, changes to the url/uri and ports of MongoDB, redis and MySQL will be needed
3. Passwords are all removed.
   - Places:
     - application.properties
     - docker-compose
4. If running as a Docker container is wanted
   - run ```docker build -t image_name .```
   - run ```docker compose up``` in git bash or cmd while in project file. Docker Engine must be running.
6. If not, run application normally while having Redis and MySQL80 runnning on device.

## Contributions
Contributions are welcome! Please fork the repository and submit a pull request for any improvements or fixes.

## License
This project is licensed under the MIT License.


By showcasing this application built with industry-standard practices, this repository reflects my commitment to quality software development and continuous learning. I hope this project demonstrates my technical skills and dedication to excellence.
Feel free to explore the code and reach out if you have any questions or feedback. Thank you for visiting!
