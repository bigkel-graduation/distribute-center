package com.itchenyang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CenterCoreApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CenterCoreApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(CenterCoreApplication.class, args);
            LOG.info("distribute center core application started!");
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
