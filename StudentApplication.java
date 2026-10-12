package com.kiet.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@SpringBootApplication(scanBasePackages = {"com.kiet.student", "controller"})
public class StudentApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(StudentApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("KIET Student Management System");
        log.info("Loading Student Data");
        log.info("Student Management REST API is Ready");
    }
}
