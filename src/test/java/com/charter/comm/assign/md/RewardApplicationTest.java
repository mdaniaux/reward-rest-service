package com.charter.comm.assign.md;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//@SpringBootApplication()
public class RewardApplicationTest {

    private static final Logger log = LoggerFactory.getLogger(RewardApplicationTest.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RewardApplicationTest.class, args);
    }
    
}
