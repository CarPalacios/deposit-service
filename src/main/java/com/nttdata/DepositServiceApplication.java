package com.nttdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**Se crea la clase principal DepositServiceApplication.*/
@EnableEurekaClient
@SpringBootApplication
public class DepositServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DepositServiceApplication.class, args);
  }

}
