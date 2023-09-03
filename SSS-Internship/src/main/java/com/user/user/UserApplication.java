package com.user.user;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.user.user.repository.AuditRepo;
import com.user.user.repository.UserRepo;

//import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient
@EnableCaching
@EnableJpaAuditing
@EnableMongoRepositories(basePackageClasses = AuditRepo.class)
@EnableJpaRepositories (basePackageClasses = UserRepo.class)
public class UserApplication {

		//http://localhost:8092/swagger-ui/
	
//	@Bean
//	@LoadBalanced
//	public RestTemplate restTemplate() {
//		return new RestTemplate();	
//	}

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
