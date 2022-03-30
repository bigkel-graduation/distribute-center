package com.itchenyang;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CenterRequestApplication {

    private static final Logger LOG = LoggerFactory.getLogger(CenterRequestApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(CenterRequestApplication.class, args);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        LOG.info("distribute center request application started!");
    }
}
