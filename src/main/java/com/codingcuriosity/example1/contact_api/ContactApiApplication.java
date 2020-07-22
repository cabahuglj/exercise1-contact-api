package com.codingcuriosity.example1.contact_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.codingcuriosity.example1")
public class ContactApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ContactApiApplication.class, args);
  }

}
